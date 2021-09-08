package ru.kirilldev.rowingutapp.viewmodels.delegates

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.presentation.splashscreen.SplashActivity
import ru.kirilldev.rowingutapp.viewmodels.TrainingViewModel
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewModelDelegate<T>(
    private val viewModel: BaseViewModel<T>
): ReadOnlyProperty<SplashActivity, BaseViewModel<T>>, LifecycleObserver {

    private var value: BaseViewModel<T>? = null



    override fun getValue(thisRef: SplashActivity, property: KProperty<*>): BaseViewModel<T> {
        if(value == null){
            value = ViewModelProvider(thisRef).get(viewModel::class.java)

        }

        return value!!
    }


    private fun Class<T>.isAssignable() {

    }


    class ViewModelFactory(owner: SavedStateRegistryOwner) :
        AbstractSavedStateViewModelFactory(owner, bundleOf()) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            if(modelClass.isAssignableFrom(TrainingViewModel::class.java)){
                return TrainingViewModel(handle) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}



