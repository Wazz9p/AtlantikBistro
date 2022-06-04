package com.wazz9p.core.extension

import android.os.SystemClock
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.core.view.forEach
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

fun View.setOnDebouncedClickListener(action: () -> Unit) {

    val actionDebouncer = ActionDebouncer(action)

    setOnClickListener {
        actionDebouncer.notifyAction()
    }
}

private class ActionDebouncer(private val action: () -> Unit) {

    companion object {
        const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
    }

    private var lastActionTime = 0L

    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            action.invoke()
        }
    }
}

fun View.removeOnDebouncedClickListener() {
    setOnClickListener(null)
    isClickable = false
}

var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }


fun BottomNavigationView.disableTooltip() {
    val content: View = getChildAt(0)
    if (content is ViewGroup) {
        content.forEach {
            it.setOnLongClickListener {
                return@setOnLongClickListener true
            }
            it.isHapticFeedbackEnabled = false
        }
    }
}

fun TabLayout.disableTooltip() {
    val content: View = getChildAt(0)
    if (content is ViewGroup) {
        content.forEach {
            it.setOnLongClickListener {
                return@setOnLongClickListener true
            }
        }
    }
}
