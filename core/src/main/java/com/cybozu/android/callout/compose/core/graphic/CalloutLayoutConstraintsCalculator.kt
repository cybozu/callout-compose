package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.Alignment
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.data.dpu
import kotlin.math.abs

internal object CalloutLayoutConstraintsCalculator {
    fun calculate(
        density: Density,
        alignment: AlignmentContext,
        parentSize: Size,
        anchorRectInParent: Rect,
    ): CalloutLayoutConstraints {
        val minWidth = when (alignment.vertical) {
            is Alignment.Vertical.Inner -> 0.dp
            is Alignment.Vertical.Over -> 5.dpu
        }.let {
            with(density) { it.toPx() }
        }
        val minHeight = when (alignment.horizontal) {
            is Alignment.Horizontal.Inner -> 0.dp
            is Alignment.Horizontal.Over -> 5.dpu
        }.let {
            with(density) { it.toPx() }
        }

        val maxWidth = when (alignment.horizontal) {
            Alignment.Horizontal.Start -> parentSize.width - anchorRectInParent.left
            Alignment.Horizontal.Center -> parentSize.width - abs(parentSize.width / 2f - anchorRectInParent.center.x)
            Alignment.Horizontal.End -> anchorRectInParent.right
            Alignment.Horizontal.StartOver -> anchorRectInParent.left
            Alignment.Horizontal.EndOver -> parentSize.width - anchorRectInParent.right
        }
        val maxHeight = when (alignment.vertical) {
            Alignment.Vertical.Top -> parentSize.height - anchorRectInParent.top
            Alignment.Vertical.Center -> parentSize.height - abs(parentSize.height / 2f - anchorRectInParent.center.y)
            Alignment.Vertical.Bottom -> anchorRectInParent.bottom
            Alignment.Vertical.TopOver -> anchorRectInParent.top
            Alignment.Vertical.BottomOver -> parentSize.height - anchorRectInParent.bottom
        }
        return CalloutLayoutConstraints(
            minWidth = minWidth,
            minHeight = minHeight,
            maxWidth = maxWidth,
            maxHeight = maxHeight
        )
    }
}

internal data class CalloutLayoutConstraints(
    val minWidth: Float,
    val minHeight: Float,
    val maxWidth: Float,
    val maxHeight: Float,
)
