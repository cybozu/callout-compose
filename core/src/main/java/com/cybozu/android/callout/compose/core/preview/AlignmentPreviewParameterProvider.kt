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
package com.cybozu.android.callout.compose.core.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext.HorizontalOuter
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext.VerticalOuter

internal class AlignmentPreviewParameterProvider : PreviewParameterProvider<CalloutAlignmentContext> {
    override val values: Sequence<CalloutAlignmentContext> = sequenceOf(
        VerticalOuter(
            vertical = CalloutAlignment.Vertical.TopOuter,
            horizontal = CalloutAlignment.Horizontal.Start
        ),
        VerticalOuter(
            vertical = CalloutAlignment.Vertical.TopOuter,
            horizontal = CalloutAlignment.Horizontal.Center
        ),
        VerticalOuter(
            vertical = CalloutAlignment.Vertical.TopOuter,
            horizontal = CalloutAlignment.Horizontal.End
        ),
        HorizontalOuter(
            vertical = CalloutAlignment.Vertical.Top,
            horizontal = CalloutAlignment.Horizontal.StartOuter
        ),
        HorizontalOuter(
            vertical = CalloutAlignment.Vertical.Center,
            horizontal = CalloutAlignment.Horizontal.StartOuter
        ),
        HorizontalOuter(
            vertical = CalloutAlignment.Vertical.Bottom,
            horizontal = CalloutAlignment.Horizontal.StartOuter
        ),
        VerticalOuter(
            vertical = CalloutAlignment.Vertical.BottomOuter,
            horizontal = CalloutAlignment.Horizontal.Start
        ),
        VerticalOuter(
            vertical = CalloutAlignment.Vertical.BottomOuter,
            horizontal = CalloutAlignment.Horizontal.Center
        ),
        VerticalOuter(
            vertical = CalloutAlignment.Vertical.BottomOuter,
            horizontal = CalloutAlignment.Horizontal.End
        ),
        HorizontalOuter(
            vertical = CalloutAlignment.Vertical.Top,
            horizontal = CalloutAlignment.Horizontal.EndOuter
        ),
        HorizontalOuter(
            vertical = CalloutAlignment.Vertical.Center,
            horizontal = CalloutAlignment.Horizontal.EndOuter
        ),
        HorizontalOuter(
            vertical = CalloutAlignment.Vertical.Bottom,
            horizontal = CalloutAlignment.Horizontal.EndOuter
        )
    )
}
