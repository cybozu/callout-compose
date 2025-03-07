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

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.CalloutLayoutConstraints
import com.cybozu.android.callout.compose.core.data.ccu
import kotlin.math.abs

internal object CalloutLayoutConstraintsCalculator {
    fun calculate(
        density: Density,
        alignment: CalloutAlignmentContext,
        parentSize: Size,
        anchorRectInParent: Rect,
    ): CalloutLayoutConstraints {
        val minWidth = when (alignment.vertical) {
            is CalloutAlignment.Vertical.Inner -> 0.dp
            is CalloutAlignment.Vertical.Outer -> 5.ccu
        }.let {
            with(density) { it.toPx() }
        }
        val minHeight = when (alignment.horizontal) {
            is CalloutAlignment.Horizontal.Inner -> 0.dp
            is CalloutAlignment.Horizontal.Outer -> 5.ccu
        }.let {
            with(density) { it.toPx() }
        }

        val maxWidth = when (alignment.horizontal) {
            CalloutAlignment.Horizontal.Start -> parentSize.width - anchorRectInParent.left
            CalloutAlignment.Horizontal.Center -> parentSize.width - abs(parentSize.width / 2f - anchorRectInParent.center.x)
            CalloutAlignment.Horizontal.End -> anchorRectInParent.right
            CalloutAlignment.Horizontal.StartOuter -> anchorRectInParent.left
            CalloutAlignment.Horizontal.EndOuter -> parentSize.width - anchorRectInParent.right
        }
        val maxHeight = when (alignment.vertical) {
            CalloutAlignment.Vertical.Top -> parentSize.height - anchorRectInParent.top
            CalloutAlignment.Vertical.Center -> parentSize.height - abs(parentSize.height / 2f - anchorRectInParent.center.y)
            CalloutAlignment.Vertical.Bottom -> anchorRectInParent.bottom
            CalloutAlignment.Vertical.TopOuter -> anchorRectInParent.top
            CalloutAlignment.Vertical.BottomOuter -> parentSize.height - anchorRectInParent.bottom
        }
        return CalloutLayoutConstraints(
            minWidth = minWidth,
            minHeight = minHeight,
            maxWidth = maxWidth,
            maxHeight = maxHeight
        )
    }
}
