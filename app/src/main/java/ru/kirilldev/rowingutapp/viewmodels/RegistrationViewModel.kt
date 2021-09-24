package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.google.firebase.auth.FirebaseUser
import ru.kirilldev.rowingutapp.data.firebase.auth.Authentication
import ru.kirilldev.rowingutapp.data.local.EntrySettings
import ru.kirilldev.rowingutapp.data.repository.RowingutRepository
import ru.kirilldev.rowingutapp.extensions.checkPasswordLength
import ru.kirilldev.rowingutapp.extensions.isPasswordValid
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.base.Notify
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IRegistrationViewModel
import java.lang.IllegalArgumentException

class RegistrationViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<RegistrationData>(
        RegistrationData(),
        savedStateHandle
    ), IRegistrationViewModel {

    private val repository = RowingutRepository()


    override fun handleSignIn(email: String, password: String) {
        var notification: Notify? = null
        when {
            password.isPasswordValid() -> {

                notification = Notify.Error(ValidationNotifies.PasswordValidationError().message)

            }
            password.checkPasswordLength() -> {

                notification = Notify.Error(ValidationNotifies.PasswordLengthError().message)

            }
            else -> {
                repository.signUp(email, password) {

                    notification = when (it) {
                        email -> Notify.Success(it)
                        else -> Notify.Error(it)
                    }

                }
            }
        }
        notify(notification!!)
    }

    override fun handleSignUp(email: String, password: String) {
        var notification: Notify? = null
        when {
            password.isPasswordValid() -> {

                notification = Notify.Error(ValidationNotifies.PasswordValidationError().message)

            }
            password.checkPasswordLength() -> {

                notification = Notify.Error(ValidationNotifies.PasswordLengthError().message)

            }
            else -> {
                repository.signUp(email, password) {

                    notification = when (it) {
                        email -> {

                            Notify.Success(it)

                        }
                        else -> Notify.Error(it)
                    }

                }
            }
        }
        notify(notification!!)
    }


    override fun handlePage(position: Int) {
        updateState { it.copy(currentPage = position) }
    }

    override fun handleIsPasswordsEquals(password: String, confirmedPassword: String) {
        notify(Notify.Error(ValidationNotifies.EmailValidationError().message))
    }

    override fun handleUpdateEntrySettings(entrySettings: EntrySettings) {
        repository.updateEntrySettings(
            EntrySettings(
                isSignedIn = true
            )
        )
    }


}

sealed class ValidationNotifies{
    abstract val message: String

    data class PasswordLengthError(
        override val message: String = "Пароль должен содержать минимум 6 символов"
    ): ValidationNotifies()

    data class PasswordValidationError(
        override val message: String = "Пароль должен содержать спецсимволы\n " +
                                        "и минимум одну заглавную букву"
    ): ValidationNotifies()

    data class EmailValidationError(
        override val message: String = "Почта введена неверно"
    ): ValidationNotifies()
}

data class RegistrationData(
    val currentPage: Int = 0,
    val email: String? = null,
    val password: String? = null
)

class ActivityRegistrationViewModelFactory(
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, bundleOf()) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}