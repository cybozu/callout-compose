package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.cybozu.android.callout.compose.core.data.Alignment
import com.cybozu.android.callout.compose.core.data.AlignmentContext

internal data class PopupLayoutContext(
    val alignment: androidx.compose.ui.Alignment,
    val offsetFromBaseline: Offset,
)

internal object PopupLayoutCalculator {
    fun calculate(
        parentSize: Size,
        anchorRectInParent: Rect,
        alignment: AlignmentContext,
    ): PopupLayoutContext {
        val popupAlignment = calculateAlignment(alignment)
        val offsetFromBaseline = calculateOffsetFromBaseline(
            alignment = alignment,
            parentSize = parentSize,
            anchorRectInParent = anchorRectInParent
        )
        return PopupLayoutContext(
            alignment = popupAlignment,
            offsetFromBaseline = offsetFromBaseline
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
        parentSize: Size,
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
}
