package ru.kirilldev.rowingutapp.viewmodels.base

import androidx.annotation.UiThread
import androidx.lifecycle.*

abstract class BaseViewModel<T, E>(initstate: T, private val savedStateHandle: SavedStateHandle) :
    ViewModel() {




    companion object {
        private const val SAVED_STATE_KEY: String = "savedstatekey"
    }

    val state = MediatorLiveData<T>().apply {
        value = initstate
    }

    val events = MutableLiveData<Event<E>>()

    protected val currentState
        get() = state.value!!

    fun observeState(
        owner: LifecycleOwner,
        onChanged: (newState: T) -> Unit
    ) {
        state.observe(owner, Observer { onChanged(it!!) })
    }

    fun observeEvents(owner: LifecycleOwner, onEvent: (event: E) -> Unit){
        events.observe(owner, EventObserver{onEvent(it!!)})
    }

    fun handleEvent(content: E){
        events.postValue(Event(content))
    }

    @UiThread
    protected inline fun updateState(update: (currentState: T) -> T){
        state.value = update(currentState)
    }

    protected fun <S> subscribeOnDataSource(
        source: LiveData<S>,
        onChanged: (newState: S, currentState: T) -> T?
    ){
        state.addSource(source){
            state.value = onChanged(it, currentState) ?: return@addSource
        }
    }



}




class Event<out E>(private val content: E) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): E?{
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }
}


class EventObserver<E>(private val onEventUnhandledContent: (E) -> Unit): Observer<Event<E>>{
    override fun onChanged(e: Event<E>?) {
        e?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}

