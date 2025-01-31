package com.cybozu.android.callout.compose.core.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.toSize
import com.cybozu.android.callout.compose.core.CalloutState
import com.cybozu.android.callout.compose.core.CalloutStateImpl
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.graphic.CalloutShape

internal fun Modifier.calloutShape(
    anchorSize: Size,
    alignment: CalloutAlignmentContext,
    borderWidth: Dp,
    borderColor: Color,
    backgroundColor: Color,
    shadowColor: Color,
    elevation: Dp,
): Modifier = this
    .border(
        width = borderWidth,
        color = borderColor,
        shape = CalloutShape(
            anchorSize = anchorSize,
            alignment = alignment
        )
    )
    .shadow(
        elevation = elevation,
        shape = CalloutShape(
            anchorSize = anchorSize,
            alignment = alignment
        ),
        spotColor = shadowColor,
        ambientColor = shadowColor,
        clip = false
    )
    .background(
        color = backgroundColor,
        shape = CalloutShape(
            anchorSize = anchorSize,
            alignment = alignment
        )
    )

public fun Modifier.anchoredCallout(
    state: CalloutState,
): Modifier = this.onGloballyPositioned { coordinates ->
    val stateImpl = when (state) {
        is CalloutStateImpl -> state
    }

    stateImpl.parentSize = coordinates.parentCoordinates?.size?.toSize()
    stateImpl.anchorPositionInWindow = coordinates.positionInWindow()
    stateImpl.anchorRectInParent = coordinates.boundsInParent()
}
