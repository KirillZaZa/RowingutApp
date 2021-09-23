package ru.kirilldev.rowingutapp.viewmodels.base

import androidx.annotation.UiThread
import androidx.lifecycle.*

abstract class BaseViewModel<T>(
    initstate: T? = null,
    private val savedStateHandle: SavedStateHandle,
    private val initListState: List<T> = emptyList()
) :
    ViewModel() {


    companion object {
        private const val SAVED_STATE_KEY: String = "saved_state_key"
        private const val SAVED_STATE_LIST_KEY: String = "saved_state_list_key"
    }

    val state = MediatorLiveData<T>().apply {
        value = initstate
    }

    val listState = MutableLiveData<List<T>>().apply {
        value = initListState
    }

    val notification = MutableLiveData<Event<Notify>>()

    protected val currentState
        get() = state.value!!

    protected val currentListState
        get() = listState.value!!

    fun observeListState(
        owner: LifecycleOwner,
        onChanged: (newState: List<T>) -> Unit
    ) {
        listState.observe(owner, Observer { onChanged(it!!) })
    }

    fun observeState(
        owner: LifecycleOwner,
        onChanged: (newState: T) -> Unit
    ) {
        state.observe(owner, Observer { onChanged(it!!) })
    }

    fun observeNotifications(owner: LifecycleOwner, onEvent: (event: Notify) -> Unit) {
        notification.observe(owner, EventObserver { onEvent(it!!) })
    }

    fun notify(content: Notify) {
        notification.postValue(Event(content))
    }

    fun saveState(){
        savedStateHandle.set(SAVED_STATE_KEY, currentState)
        savedStateHandle.set(SAVED_STATE_LIST_KEY, currentListState)
    }

    fun restoreState(){
        val restoredState = savedStateHandle.get<T>(SAVED_STATE_KEY)
        val restoredListState = savedStateHandle.get<List<T>>(SAVED_STATE_LIST_KEY)

        restoredState ?: return
        restoredListState ?: return

        state.value = restoredState
        listState.value = restoredListState
    }

    @UiThread
    protected inline fun updateState(update: (currentState: T) -> T?) {
        state.value = update(currentState)
    }

    @UiThread
    protected fun resetState() {
        state.value = null
    }

    @UiThread
    protected inline fun updateListState(
        update: (currentListState: List<T>) -> List<T>
    ) {
        listState.value = update(currentListState)
    }

    protected fun <S> subscribeOnDataSource(
        source: LiveData<S>,
        onChanged: (newState: S, currentState: T) -> T?
    ) {
        state.addSource(source) {
            state.value = onChanged(it, currentState) ?: return@addSource
        }
    }

    protected fun subscribeOnListDataSource(
        source: List<T>?
    ) {
        listState.value = source ?: return
    }

}

sealed class Notify{
    abstract val msg: String

    data class Error(
        override val msg: String
    ): Notify()

    data class Success(
        override val msg: String
    ): Notify()




}

class Event<out E>(private val content: E) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): E? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}


class EventObserver<E>(private val onEventUnhandledContent: (E) -> Unit) : Observer<Event<E>> {
    override fun onChanged(e: Event<E>?) {
        e?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}



