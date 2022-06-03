package com.wazz9p.atlantikbistro.screens.main

import androidx.navigation.NavController
import com.wazz9p.core.base.BaseAction
import com.wazz9p.core.base.BaseViewModel
import com.wazz9p.core.base.BaseViewState

class MainFragmentViewModel :
    BaseViewModel<MainFragmentViewModel.ViewState, MainFragmentViewModel.Action>(ViewState()) {

    companion object {
        val FULLSCREEN_FRAGMENTS_ID: Set<Int> = setOf(

        )
    }

    fun navigationControllerChanged(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val action = if (FULLSCREEN_FRAGMENTS_ID.contains(destination.id)) {
                Action.FullScreen
            } else {
                Action.NavigationScreen
            }
            sendAction(action)
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.NavigationScreen -> state.copy(
            isNavigationScreen = true
        )
        is Action.FullScreen -> state.copy(
            isNavigationScreen = false
        )
    }

    data class ViewState(
        val isNavigationScreen: Boolean = true
    ) : BaseViewState

    sealed interface Action : BaseAction {

        object FullScreen : Action
        object NavigationScreen : Action
    }
}