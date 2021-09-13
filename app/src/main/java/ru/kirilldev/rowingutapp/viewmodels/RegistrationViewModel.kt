package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IRegistrationViewModel
import java.lang.IllegalArgumentException

class RegistrationViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<RegistrationData>(
        RegistrationData(),
        savedStateHandle
    ), IRegistrationViewModel{


    override fun handleSignIn(callback: (Boolean) -> Unit) {
        /**Проверяем данные пользователя
         *
         * если true - то заходим
         * иначе - показываем ошибку
         *
         */

    }

    override fun handleSignUp() {
        // Регистрируем пользователя в firebase
    }
}

data class RegistrationData(
    val currentPage: Int = 0,
    val email: String? = null,

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