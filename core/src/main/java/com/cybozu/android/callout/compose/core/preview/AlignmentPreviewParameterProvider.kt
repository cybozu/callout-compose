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
