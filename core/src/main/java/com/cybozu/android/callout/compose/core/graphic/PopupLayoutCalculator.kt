package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext

internal data class PopupLayoutContext(
    val alignment: Alignment,
    val offsetFromBaseline: Offset,
)

internal object PopupLayoutCalculator {
    fun calculate(
        parentSize: Size,
        anchorRectInParent: Rect,
        alignment: CalloutAlignmentContext,
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
        alignment: CalloutAlignmentContext,
    ): Alignment {
        val verticalBias = when (alignment.vertical) {
            CalloutAlignment.Vertical.Top, CalloutAlignment.Vertical.BottomOver -> -1f // From Top
            CalloutAlignment.Vertical.Center -> 0f // From Center
            CalloutAlignment.Vertical.Bottom, CalloutAlignment.Vertical.TopOver -> 1f // From Bottom
        }
        val horizontalBias = when (alignment.horizontal) {
            CalloutAlignment.Horizontal.Start, CalloutAlignment.Horizontal.EndOver -> -1f // From Start
            CalloutAlignment.Horizontal.Center -> 0f // From Center
            CalloutAlignment.Horizontal.End, CalloutAlignment.Horizontal.StartOver -> 1f // From End
        }
        return BiasAlignment(
            horizontalBias = horizontalBias,
            verticalBias = verticalBias
        )
    }

    private fun calculateOffsetFromBaseline(
        alignment: CalloutAlignmentContext,
        parentSize: Size,
        anchorRectInParent: Rect,
    ): Offset {
        val x = when (alignment.horizontal) {
            CalloutAlignment.Horizontal.Start -> anchorRectInParent.left
            CalloutAlignment.Horizontal.Center -> anchorRectInParent.center.x - parentSize.width / 2
            CalloutAlignment.Horizontal.End -> parentSize.width - anchorRectInParent.right
            CalloutAlignment.Horizontal.StartOver -> parentSize.width - anchorRectInParent.left
            CalloutAlignment.Horizontal.EndOver -> anchorRectInParent.right
        }
        val y = when (alignment.vertical) {
            CalloutAlignment.Vertical.Top -> anchorRectInParent.top
            CalloutAlignment.Vertical.Bottom -> parentSize.height - anchorRectInParent.bottom
            CalloutAlignment.Vertical.Center -> anchorRectInParent.center.y - parentSize.height / 2
            CalloutAlignment.Vertical.TopOver -> parentSize.height - anchorRectInParent.top
            CalloutAlignment.Vertical.BottomOver -> anchorRectInParent.bottom
        }

        return Offset(x, y)
    }
}
