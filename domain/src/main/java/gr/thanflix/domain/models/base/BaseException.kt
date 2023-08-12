package gr.thanflix.domain.models.base

data class BaseException(
    val errorCode: Int = -1,
    val throwable: Throwable? = null,
    val errorBody: Any? = null
)
