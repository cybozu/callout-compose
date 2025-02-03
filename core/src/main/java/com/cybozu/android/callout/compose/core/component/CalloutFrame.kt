package com.cybozu.android.callout.compose.core.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.cybozu.android.callout.compose.core.CalloutState
import com.cybozu.android.callout.compose.core.CalloutStateImpl
import com.cybozu.android.callout.compose.core.DefaultLocalContentColorProvider
import com.cybozu.android.callout.compose.core.LocalContentColorProvider
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.CalloutLayoutConstraints
import com.cybozu.android.callout.compose.core.data.CalloutProperties
import com.cybozu.android.callout.compose.core.data.PopupLayoutContext
import com.cybozu.android.callout.compose.core.data.ccu
import com.cybozu.android.callout.compose.core.graphic.CalloutLayoutConstraintsCalculator
import com.cybozu.android.callout.compose.core.graphic.PopupLayoutCalculator
import com.cybozu.android.callout.compose.core.modifier.anchoredCallout
import com.cybozu.android.callout.compose.core.modifier.calloutShape
import com.cybozu.android.callout.compose.core.preview.AlignmentPreviewParameterProvider
import com.cybozu.android.callout.compose.core.rememberCalloutState

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
        CalloutFrameImpl(
            contentColorProvider = contentColorProvider,
            calloutProperties = calloutProperties,
            alpha = alpha,
            density = density,
            anchorRectInParent = stateImpl.anchorRectInParent ?: Rect.Zero,
            alignmentContext = alignmentContext,
            popupLayoutContext = popupLayoutContext,
            calloutLayoutConstraints = calloutLayoutConstraints,
            onDismissRequest = {
                calloutState.hide()
                onDismissRequest?.let { it() }
            },
            content = content
        )
    }
}

@Composable
private fun CalloutFrameImpl(
    contentColorProvider: LocalContentColorProvider,
    calloutProperties: CalloutProperties,
    alpha: Float,
    density: Density,
    anchorRectInParent: Rect,
    alignmentContext: CalloutAlignmentContext,
    popupLayoutContext: PopupLayoutContext,
    calloutLayoutConstraints: CalloutLayoutConstraints,
    onDismissRequest: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Popup(
        alignment = popupLayoutContext.alignment,
        offset = popupLayoutContext.offsetFromBaseline.toIntOffset(),
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(
            focusable = calloutProperties.isFocusable
        )
    ) {
        contentColorProvider.Provide(
            contentColor = calloutProperties.color.contentColor
        ) {
            Content(
                modifier = Modifier.alpha(alpha),
                density = density,
                anchorSize = anchorRectInParent.size,
                calloutProperties = calloutProperties,
                calloutLayoutConstraints = calloutLayoutConstraints,
                alignmentContext = alignmentContext,
                content = content
            )
        }
    }
}

private fun Offset.toIntOffset(): IntOffset = IntOffset(x.toInt(), y.toInt())

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    density: Density,
    anchorSize: Size,
    calloutProperties: CalloutProperties,
    calloutLayoutConstraints: CalloutLayoutConstraints,
    alignmentContext: CalloutAlignmentContext,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .widthIn(
                min = with(density) { calloutLayoutConstraints.minWidth.toDp() },
                max = with(density) { calloutLayoutConstraints.maxWidth.toDp() }
            )
            .heightIn(
                min = with(density) { calloutLayoutConstraints.minHeight.toDp() },
                max = with(density) { calloutLayoutConstraints.maxHeight.toDp() }
            )
            .calloutShape(
                anchorSize = anchorSize,
                alignment = alignmentContext,
                borderWidth = calloutProperties.border.width,
                borderColor = calloutProperties.border.color,
                backgroundColor = calloutProperties.color.backgroundColor,
                shadowColor = calloutProperties.color.shadowColor,
                elevation = calloutProperties.elevation
            )
            .padding(
                top = if (alignmentContext.vertical is CalloutAlignment.Vertical.BottomOver) {
                    2.ccu
                } else {
                    1.ccu
                },
                start = if (alignmentContext.horizontal is CalloutAlignment.Horizontal.EndOver) {
                    2.ccu
                } else {
                    1.ccu
                },
                bottom = if (alignmentContext.vertical is CalloutAlignment.Vertical.TopOver) {
                    2.ccu
                } else {
                    1.ccu
                },
                end = if (alignmentContext.horizontal is CalloutAlignment.Horizontal.StartOver) {
                    2.ccu
                } else {
                    1.ccu
                }
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

@Preview
@Composable
private fun CalloutPreview(
    @PreviewParameter(AlignmentPreviewParameterProvider::class) alignmentContext: CalloutAlignmentContext,
) {
    val density = LocalDensity.current

    fun Dp.toPixel(): Float = with(density) {
        toPx()
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val state = rememberCalloutState(
            isVisible = true
        )
        val anchorRectInParent = Rect(
            left = 100.dp.toPixel(),
            top = 100.dp.toPixel(),
            right = 150.dp.toPixel(),
            bottom = 150.dp.toPixel()
        )
        val popupLayoutContext = rememberPopupLayoutContext(
            parentSize = Size(maxWidth.toPixel(), maxHeight.toPixel()),
            anchorRectInParent = anchorRectInParent,
            alignment = alignmentContext
        )
        val calloutLayoutConstraints = rememberCalloutLayoutConstraints(
            density = density,
            alignment = alignmentContext,
            parentSize = Size(maxWidth.toPixel(), maxHeight.toPixel()),
            anchorRectInParent = anchorRectInParent
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 100.dp,
                    top = 100.dp
                )
                .background(Color.Black)
                .size(50.dp)
                .anchoredCallout(
                    state = state
                )
        )
        CalloutFrameImpl(
            contentColorProvider = DefaultLocalContentColorProvider(),
            calloutProperties = CalloutProperties(
                borderColor = Color.Black,
                contentColor = Color.Black,
                backgroundColor = Color.White,
                elevation = 6.dp
            ),
            alpha = 1f,
            density = density,
            anchorRectInParent = anchorRectInParent,
            alignmentContext = alignmentContext,
            popupLayoutContext = popupLayoutContext,
            calloutLayoutConstraints = calloutLayoutConstraints,
            onDismissRequest = null
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Blue)
            )
        }
    }
}
