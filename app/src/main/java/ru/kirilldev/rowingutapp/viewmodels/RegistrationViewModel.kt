package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.google.firebase.auth.FirebaseUser
import ru.kirilldev.rowingutapp.auth.AuthenticationFirebase
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.base.Notify
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IRegistrationViewModel
import java.lang.IllegalArgumentException

class RegistrationViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<RegistrationData>(
        RegistrationData(),
        savedStateHandle
    ), IRegistrationViewModel{

    private val authenticationFirebase = AuthenticationFirebase()



    override fun handleSignIn(email: String, passwordHash: String) {
        var notification: Notify? = null

        authenticationFirebase.signIn(email, passwordHash){

            notification = when(it){
                email -> Notify.Success(it)
                else -> Notify.Error(it!!)
            }

        }

        notify(notification!!)
    }

    override fun handleSignUp(email: String, passwordHash: String) {
        var notification: Notify? = null

        authenticationFirebase.signUp(email, passwordHash){

            notification = when(it){
                email -> Notify.Success(it)
                else -> Notify.Error(it!!)
            }

        }

        notify(notification!!)
    }


    override fun handleIsSignedIn(callback: (FirebaseUser?) -> Unit) =
        callback(authenticationFirebase.isSignedIn())

}

data class RegistrationData(
    val currentPage: Int = 0,
    val email: String? = null
)

class ActivityRegistrationViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, bundleOf()){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
            return RegistrationViewModel(handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}