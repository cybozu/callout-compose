package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.Alignment
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.data.dpu
import kotlin.math.abs

internal data class PopupLayoutContext(
    val alignment: androidx.compose.ui.Alignment,
    val offsetFromBaseline: Offset,
    val constraints: PopupLayoutConstraints,
) {
    val minWidth: Float get() = constraints.minWidth
    val minHeight: Float get() = constraints.minHeight
    val maxWidth: Float get() = constraints.maxWidth
    val maxHeight: Float get() = constraints.maxHeight
}

internal data class PopupLayoutConstraints(
    val minWidth: Float,
    val minHeight: Float,
    val maxWidth: Float,
    val maxHeight: Float,
)

internal class PopupLayoutCalculator {
    fun calculate(
        density: Density,
        parentSize: IntSize,
        anchorRectInParent: Rect,
        alignment: AlignmentContext,
    ): PopupLayoutContext {
        val popupAlignment = calculateAlignment(alignment)
        val offsetFromBaseline = calculateOffsetFromBaseline(
            alignment = alignment,
            parentSize = parentSize,
            anchorRectInParent = anchorRectInParent
        )
        val constraints = calculateConstraints(
            density = density,
            alignment = alignment,
            parentSize = parentSize,
            anchorRectInParent = anchorRectInParent
        )
        return PopupLayoutContext(
            alignment = popupAlignment,
            offsetFromBaseline = offsetFromBaseline,
            constraints = constraints
        )
    }

    private fun calculateAlignment(
        alignment: AlignmentContext,
    ): androidx.compose.ui.Alignment {
        val verticalBias = when (alignment.vertical) {
            Alignment.Vertical.Top, Alignment.Vertical.BottomOver -> -1f // From Top
            Alignment.Vertical.Center -> 0f // From Center
            Alignment.Vertical.Bottom, Alignment.Vertical.TopOver -> 1f // From Bottom
        }
        val horizontalBias = when (alignment.horizontal) {
            Alignment.Horizontal.Start, Alignment.Horizontal.EndOver -> -1f // From Start
            Alignment.Horizontal.Center -> 0f // From Center
            Alignment.Horizontal.End, Alignment.Horizontal.StartOver -> 1f // From End
        }
        return BiasAlignment(
            horizontalBias = horizontalBias,
            verticalBias = verticalBias
        )
    }

    private fun calculateOffsetFromBaseline(
        alignment: AlignmentContext,
        parentSize: IntSize,
        anchorRectInParent: Rect,
    ): Offset {
        val x = when (alignment.horizontal) {
            Alignment.Horizontal.Start -> anchorRectInParent.left
            Alignment.Horizontal.Center -> anchorRectInParent.center.x - parentSize.width / 2
            Alignment.Horizontal.End -> parentSize.width - anchorRectInParent.right
            Alignment.Horizontal.StartOver -> parentSize.width - anchorRectInParent.left
            Alignment.Horizontal.EndOver -> anchorRectInParent.right
        }
        val y = when (alignment.vertical) {
            Alignment.Vertical.Top -> anchorRectInParent.top
            Alignment.Vertical.Bottom -> parentSize.height - anchorRectInParent.bottom
            Alignment.Vertical.Center -> anchorRectInParent.center.y - parentSize.height / 2
            Alignment.Vertical.TopOver -> parentSize.height - anchorRectInParent.top
            Alignment.Vertical.BottomOver -> anchorRectInParent.bottom
        }

        return Offset(x, y)
    }

    private fun calculateConstraints(
        density: Density,
        alignment: AlignmentContext,
        parentSize: IntSize,
        anchorRectInParent: Rect,
    ): PopupLayoutConstraints {
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
        return PopupLayoutConstraints(
            minWidth = minWidth,
            minHeight = minHeight,
            maxWidth = maxWidth,
            maxHeight = maxHeight
        )
    }
}
