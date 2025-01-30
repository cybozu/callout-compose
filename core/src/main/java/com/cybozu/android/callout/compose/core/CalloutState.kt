package com.cybozu.android.callout.compose.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

public sealed interface CalloutState {
    public fun show()
    public fun hide()
}

internal class CalloutStateImpl(
    isVisible: Boolean = false,
) : CalloutState {
    var isVisible: Boolean by mutableStateOf(isVisible)
        private set

    val isAnchored by derivedStateOf {
        parentSize != null && anchorPositionInWindow != null && anchorRectInParent != null
    }

    var parentSize: Size? by mutableStateOf(null)
    var anchorPositionInWindow: Offset? by mutableStateOf(null)
    var anchorRectInParent: Rect? by mutableStateOf(null)

    override fun show() {
        isVisible = true
    }

    override fun hide() {
        isVisible = false
    }
}

@Composable
public fun rememberCalloutState(
    isVisible: Boolean = false,
): CalloutState = remember {
    CalloutStateImpl(
        isVisible = isVisible
    )
}
