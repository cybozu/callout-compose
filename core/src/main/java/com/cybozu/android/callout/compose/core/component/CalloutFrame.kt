package com.cybozu.android.callout.compose.core.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import com.cybozu.android.callout.compose.core.CalloutState
import com.cybozu.android.callout.compose.core.CalloutStateImpl
import com.cybozu.android.callout.compose.core.LocalContentColorProvider
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.CalloutProperties
import com.cybozu.android.callout.compose.core.graphic.CalloutLayoutConstraints
import com.cybozu.android.callout.compose.core.graphic.CalloutLayoutConstraintsCalculator
import com.cybozu.android.callout.compose.core.graphic.PopupLayoutCalculator
import com.cybozu.android.callout.compose.core.graphic.PopupLayoutContext
import com.cybozu.android.callout.compose.core.modifier.calloutShape

@Composable
internal fun CalloutFrame(
    contentColorProvider: LocalContentColorProvider,
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    alignmentContext: CalloutAlignmentContext,
    onDismissRequest: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    val stateImpl = when (calloutState) {
        is CalloutStateImpl -> calloutState
    }
    val alpha by animateFloatAsState(
        targetValue = if (stateImpl.isVisible && stateImpl.isAnchored) 1f else 0f,
        label = "CalloutFrameAlpha",
        animationSpec = tween(durationMillis = calloutProperties.animationDurationMillis)
    )

    if (stateImpl.isVisible && stateImpl.isAnchored) {
        val density = LocalDensity.current
        val popupLayoutContext = rememberPopupLayoutContext(
            parentSize = stateImpl.parentSize ?: Size.Zero,
            anchorRectInParent = stateImpl.anchorRectInParent ?: Rect.Zero,
            alignment = alignmentContext
        )
        val calloutLayoutConstraints = rememberCalloutLayoutConstraints(
            density = density,
            alignment = alignmentContext,
            parentSize = stateImpl.parentSize ?: Size.Zero,
            anchorRectInParent = stateImpl.anchorRectInParent ?: Rect.Zero
        )
        Popup(
            alignment = popupLayoutContext.alignment,
            offset = popupLayoutContext.offsetFromBaseline.toIntOffset(),
            onDismissRequest = {
                stateImpl.hide()
                onDismissRequest?.let { it() }
            }
        ) {
            contentColorProvider.Provide(
                contentColor = calloutProperties.color.contentColor
            ) {
                CalloutFrameImpl(
                    modifier = Modifier.alpha(alpha),
                    density = density,
                    anchorSize = stateImpl.anchorRectInParent?.size ?: Size.Zero,
                    calloutProperties = calloutProperties,
                    calloutLayoutConstraints = calloutLayoutConstraints,
                    alignmentContext = alignmentContext,
                    content = content
                )
            }
        }
    }
}

private fun Offset.toIntOffset(): IntOffset = IntOffset(x.toInt(), y.toInt())

@Composable
private fun CalloutFrameImpl(
    modifier: Modifier = Modifier,
    density: Density,
    anchorSize: Size,
    calloutProperties: CalloutProperties,
    calloutLayoutConstraints: CalloutLayoutConstraints,
    alignmentContext: CalloutAlignmentContext,
    content: @Composable () -> Unit,
) {
    Box(
        modifier
            .calloutShape(
                anchorSize = anchorSize,
                alignment = alignmentContext,
                borderWidth = calloutProperties.border.width,
                borderColor = calloutProperties.border.color,
                backgroundColor = calloutProperties.color.backgroundColor,
                shadowColor = calloutProperties.color.shadowColor,
                elevation = calloutProperties.elevation
            )
            .widthIn(
                min = with(density) { calloutLayoutConstraints.minWidth.toDp() },
                max = with(density) { calloutLayoutConstraints.maxWidth.toDp() }
            )
            .heightIn(
                min = with(density) { calloutLayoutConstraints.minHeight.toDp() },
                max = with(density) { calloutLayoutConstraints.maxHeight.toDp() }
            )
    ) {
        content()
    }
}

@Composable
private fun rememberPopupLayoutContext(
    parentSize: Size,
    anchorRectInParent: Rect,
    alignment: CalloutAlignmentContext,
): PopupLayoutContext = remember(parentSize, anchorRectInParent, alignment) {
    PopupLayoutCalculator.calculate(
        parentSize = parentSize,
        anchorRectInParent = anchorRectInParent,
        alignment = alignment
    )
}

@Composable
private fun rememberCalloutLayoutConstraints(
    density: Density,
    alignment: CalloutAlignmentContext,
    parentSize: Size,
    anchorRectInParent: Rect,
): CalloutLayoutConstraints = remember(density, parentSize, anchorRectInParent, alignment) {
    CalloutLayoutConstraintsCalculator.calculate(
        density = density,
        alignment = alignment,
        parentSize = parentSize,
        anchorRectInParent = anchorRectInParent
    )
}
