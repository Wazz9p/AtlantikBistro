package com.wazz9p.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wazz9p.core.extension.asLiveData
import kotlin.properties.Delegates.observable

abstract class BaseViewModel<ViewState : BaseViewState,
        Action : BaseAction>(initialState: ViewState) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutableLiveData.asLiveData()

    protected var state by observable(initialState) { _, _, new ->
        stateMutableLiveData.value = new
    }

    fun sendAction(viewAction: Action) {
        state = onReduceState(viewAction)
    }

    fun loadData() {
        onLoadData()
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduceState(viewAction: Action): ViewState
}