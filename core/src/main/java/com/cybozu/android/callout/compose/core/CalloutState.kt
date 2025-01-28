package com.cybozu.android.callout.compose.core

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.IntSize

public interface CalloutState {
    public fun show()
    public fun hide()
}

internal class CalloutStateImpl(
    isVisible: Boolean = false,
) : CalloutState {
    var isVisible: Boolean by mutableStateOf(isVisible)
        private set

    val isAnchored by derivedStateOf {
        parentSize != null && targetPositionInWindow != null && targetRectInParent != null
    }

    var parentSize: IntSize? by mutableStateOf(null)
    var targetPositionInWindow: Offset? by mutableStateOf(null)
    var targetRectInParent: Rect? by mutableStateOf(null)

    override fun show() {
        isVisible = true
    }

    override fun hide() {
        isVisible = false
    }
}
