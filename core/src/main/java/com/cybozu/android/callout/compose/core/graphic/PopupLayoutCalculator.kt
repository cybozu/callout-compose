/*
 * Copyright 2025 Cybozu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.PopupLayoutContext

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
            CalloutAlignment.Vertical.Top, CalloutAlignment.Vertical.BottomOuter -> -1f // From Top
            CalloutAlignment.Vertical.Center -> 0f // From Center
            CalloutAlignment.Vertical.Bottom, CalloutAlignment.Vertical.TopOuter -> 1f // From Bottom
        }
        val horizontalBias = when (alignment.horizontal) {
            CalloutAlignment.Horizontal.Start, CalloutAlignment.Horizontal.EndOuter -> -1f // From Start
            CalloutAlignment.Horizontal.Center -> 0f // From Center
            CalloutAlignment.Horizontal.End, CalloutAlignment.Horizontal.StartOuter -> 1f // From End
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
            CalloutAlignment.Horizontal.StartOuter -> parentSize.width - anchorRectInParent.left
            CalloutAlignment.Horizontal.EndOuter -> anchorRectInParent.right
        }
        val y = when (alignment.vertical) {
            CalloutAlignment.Vertical.Top -> anchorRectInParent.top
            CalloutAlignment.Vertical.Bottom -> parentSize.height - anchorRectInParent.bottom
            CalloutAlignment.Vertical.Center -> anchorRectInParent.center.y - parentSize.height / 2
            CalloutAlignment.Vertical.TopOuter -> parentSize.height - anchorRectInParent.top
            CalloutAlignment.Vertical.BottomOuter -> anchorRectInParent.bottom
        }
        val xBias = alignment.horizontal.bias
        val yBias = alignment.vertical.bias

        return Offset(x * xBias, y * yBias)
    }

    private val CalloutAlignment.Vertical.bias: Float
        get() = when (this) {
            CalloutAlignment.Vertical.Top, CalloutAlignment.Vertical.BottomOuter -> 1f // From Top
            CalloutAlignment.Vertical.Center -> 1f // From Center
            CalloutAlignment.Vertical.Bottom, CalloutAlignment.Vertical.TopOuter -> -1f // From Bottom
        }

    private val CalloutAlignment.Horizontal.bias: Float
        get() = when (this) {
            CalloutAlignment.Horizontal.Start, CalloutAlignment.Horizontal.EndOuter -> 1f // From Start
            CalloutAlignment.Horizontal.Center -> 1f // From Center
            CalloutAlignment.Horizontal.End, CalloutAlignment.Horizontal.StartOuter -> -1f // From End
        }
}
