package gr.thanflix.onboarding.ui.onboarding.user.mediators

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.models.base.BaseException
import javax.inject.Inject
import gr.thanflix.domain.models.base.Result

//@HiltViewModel
class OnboardingMediator @Inject constructor(
    @ApplicationContext context: Context
): AndroidViewModel(context.applicationContext as Application) {

    private var userName: String? = null
    private var userSurname: String? = null
    private var phoneNumber: String? = null

    private var streetName: String? = null
    private var streetNumber: String? = null
    private var postalCode: String? = null

    fun setUserDetails(userName: String, userSurname: String,  phoneNumber: String): Result<Boolean, BaseException> {
        this.userName = userName
        this.userSurname = userSurname
        this.phoneNumber = phoneNumber

        return Result.Success(true)
    }

    fun setAddressDetails(streetName: String, streetNumber: String,  postalCode: String): Result<Boolean, BaseException> {
        this.streetName = streetName
        this.streetNumber = streetNumber
        this.postalCode = postalCode

        return Result.Success(true)
    }
}