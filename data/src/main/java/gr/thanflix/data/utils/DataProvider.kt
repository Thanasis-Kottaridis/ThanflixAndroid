package gr.thanflix.data.utils

import com.google.gson.Gson
import gr.thanflix.domain.models.base.BaseException
import okhttp3.ResponseBody
import retrofit2.Response
import gr.thanflix.domain.models.base.Result
import timber.log.Timber

//TODO Update exceptions to Generic Response Errors.

/**
 * Map fetch data operation [ApiResponse] or [LocalCache] to [Result]
 * This Builder Class is used in order to wrap fetch data operation
 * @param apiCall: This suspended callback performs the Api call
 * @param apiMapper: This callback performs the mapping from DTO to Domain model.
 * @param cacheLocally: This optional callback is the proper place to perform local
 * caching operation (ex Inset into local DB.)
 * @param getLocalData: This suspended callback performs request to Local DB.
 * This method has to be Throwable and throw an exception if data does not exists locally or
 * local storage is empty
 * @param forceUpdate: indicates that client has to update local data from api.
 */
class DataProvider<ApiModel, DomainModel> private constructor(
    private val apiCall: (suspend () -> Response<ApiModel>)? = null,
    private val apiMapper: ((ApiModel?) -> DomainModel)? = null,
    private val cacheLocally: ((DomainModel) -> DomainModel)? = null,
    private val getLocalData: (suspend () -> DomainModel)? = null,
    private val forceUpdate: Boolean = true,
) {

    /**
     * This helper func performs a request to local storage and returns re response as [Result]
     * @param localRequest: A suspended callback function that [Throws] an exception
     * if operation with db failed or empty data returned
     */
    private suspend fun <DomainModel> mapLocalRequestToResult(
        localRequest: suspend () -> DomainModel,
    ): Result<DomainModel, BaseException> {
        return try {
            Result.Success(body = localRequest.invoke())
        } catch (throwable: Throwable) {
            Timber.d(".... Cached Promo feed: $throwable")
            Result.Failure(errorBody = BaseException(
                errorCode = -1,
                throwable = throwable,
                errorBody = null
            ))
        }
    }

    /**
     * Map the api call [Response] to [Result] object
     * Usage: mapApiCallToResult({ apicall }, { mapper.toDomain })
     * e.g. mapApiCallToResult({ dashboardApi.getDashboardDashboardAggregation() },
     * { DashboardCardsMapper.dtoToDomain(it?.data) })
     */
    private suspend fun mapApiCallToResult(): Result<DomainModel?, BaseException> =
        try {
            val response = apiCall!!.invoke()
            if (response.isSuccessful) {
                var domainModel = apiMapper?.invoke(response.body())
                if (domainModel != null && cacheLocally != null) {
                    domainModel = cacheLocally.invoke(domainModel)
                }
                Result.Success(domainModel)
            } else {
                Result.Failure(errorBody = BaseException(
                    errorCode = -response.code(),
                    throwable = null,
                    errorBody = response.errorBody()
                ))
            }
        } catch (throwable: Throwable) {
            Result.Failure(errorBody = BaseException(
                errorCode = -1,
                throwable = throwable,
                errorBody = null
            ))
        }

    /**
     * This Func executes the request.
     * performs any mapping and caching operations
     * and returns a [Result]
     */
    suspend fun execute(): Result<DomainModel?, BaseException> {

        return when {
            // if no Api Callback provided return local Result if exists
            apiCall == null && getLocalData != null -> mapLocalRequestToResult(getLocalData)
            // if no local storage callback provided return Api result.
            getLocalData == null -> mapApiCallToResult()
            forceUpdate -> {
                // fetch data from API
                // if force update request fail try to get data from local db if exists.
                when (val apiResult = mapApiCallToResult()) {
                    is Result.Failure -> mapLocalRequestToResult(getLocalData)
                    is Result.Success -> apiResult
                }
            }
            else -> { // fetch data from Local storage. if no local data fetch from Api as fallback.
                return when (val localDataResult = mapLocalRequestToResult(getLocalData)) {
                    is Result.Failure -> mapApiCallToResult()
                    is Result.Success -> localDataResult
                }
            }
        }
    }

    /**
     * Request Builder class
     * @param apiCall: Is Mandatory,
     * @param apiMapper: Is Mandatory,
     * others are optionals
     */
    data class Builder<ApiModel, DomainModel>(
        private var apiCall: (suspend () -> Response<ApiModel>)? = null,
        private var apiMapper: ((ApiModel?) -> DomainModel)? = null,
        private var cacheLocally: ((DomainModel) -> DomainModel)? = null,
        private var getLocalData: (suspend () -> DomainModel)? = null,
        private var forceUpdate: Boolean = true,
    ) {

        fun apiCall(apiCall: suspend () -> Response<ApiModel>) = apply {
            this.apiCall = apiCall
        }

        fun apiMapper(apiMapper: (ApiModel?) -> DomainModel) = apply {
            this.apiMapper = apiMapper
        }

        fun cacheLocally(cacheLocally: (DomainModel) -> DomainModel) = apply {
            this.cacheLocally = cacheLocally
        }

        fun getLocalData(getLocalData: (suspend () -> DomainModel)?) = apply {
            this.getLocalData = getLocalData
        }

        fun forceUpdate(forceUpdate: Boolean) = apply {
            this.forceUpdate = forceUpdate
        }

        fun build(): DataProvider<ApiModel, DomainModel> {
            return DataProvider(
                apiCall = apiCall,
                apiMapper = apiMapper,
                cacheLocally = cacheLocally,
                getLocalData = getLocalData,
                forceUpdate = forceUpdate
            )
        }
    }
}