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
        windowSize: Size,
        anchorRectInWindow: Rect,
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
            CalloutAlignment.Horizontal.Start -> windowSize.width - anchorRectInWindow.left
            CalloutAlignment.Horizontal.Center -> windowSize.width - abs(windowSize.width / 2f - anchorRectInWindow.center.x)
            CalloutAlignment.Horizontal.End -> anchorRectInWindow.right
            CalloutAlignment.Horizontal.StartOuter -> anchorRectInWindow.left
            CalloutAlignment.Horizontal.EndOuter -> windowSize.width - anchorRectInWindow.right
        }
        val maxHeight = when (alignment.vertical) {
            CalloutAlignment.Vertical.Top -> windowSize.height - anchorRectInWindow.top
            CalloutAlignment.Vertical.Center -> windowSize.height - abs(windowSize.height / 2f - anchorRectInWindow.center.y)
            CalloutAlignment.Vertical.Bottom -> anchorRectInWindow.bottom
            CalloutAlignment.Vertical.TopOuter -> anchorRectInWindow.top
            CalloutAlignment.Vertical.BottomOuter -> windowSize.height - anchorRectInWindow.bottom
        }
        return CalloutLayoutConstraints(
            minWidth = minWidth,
            minHeight = minHeight,
            maxWidth = maxWidth,
            maxHeight = maxHeight
        )
    }
}
