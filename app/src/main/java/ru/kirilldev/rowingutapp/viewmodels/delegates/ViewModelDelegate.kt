package ru.kirilldev.rowingutapp.viewmodels.delegates

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
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

}