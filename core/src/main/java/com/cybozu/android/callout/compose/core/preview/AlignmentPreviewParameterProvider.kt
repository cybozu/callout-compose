package com.cybozu.android.callout.compose.core.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext.HorizontalOver
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext.VerticalOver

internal class AlignmentPreviewParameterProvider : PreviewParameterProvider<CalloutAlignmentContext> {
    override val values: Sequence<CalloutAlignmentContext> = sequenceOf(
        VerticalOver(
            vertical = CalloutAlignment.Vertical.TopOver,
            horizontal = CalloutAlignment.Horizontal.Start
        ),
        VerticalOver(
            vertical = CalloutAlignment.Vertical.TopOver,
            horizontal = CalloutAlignment.Horizontal.Center
        ),
        VerticalOver(
            vertical = CalloutAlignment.Vertical.TopOver,
            horizontal = CalloutAlignment.Horizontal.End
        ),
        HorizontalOver(
            vertical = CalloutAlignment.Vertical.Top,
            horizontal = CalloutAlignment.Horizontal.StartOver
        ),
        HorizontalOver(
            vertical = CalloutAlignment.Vertical.Center,
            horizontal = CalloutAlignment.Horizontal.StartOver
        ),
        HorizontalOver(
            vertical = CalloutAlignment.Vertical.Bottom,
            horizontal = CalloutAlignment.Horizontal.StartOver
        ),
        VerticalOver(
            vertical = CalloutAlignment.Vertical.BottomOver,
            horizontal = CalloutAlignment.Horizontal.Start
        ),
        VerticalOver(
            vertical = CalloutAlignment.Vertical.BottomOver,
            horizontal = CalloutAlignment.Horizontal.Center
        ),
        VerticalOver(
            vertical = CalloutAlignment.Vertical.BottomOver,
            horizontal = CalloutAlignment.Horizontal.End
        ),
        HorizontalOver(
            vertical = CalloutAlignment.Vertical.Top,
            horizontal = CalloutAlignment.Horizontal.EndOver
        ),
        HorizontalOver(
            vertical = CalloutAlignment.Vertical.Center,
            horizontal = CalloutAlignment.Horizontal.EndOver
        ),
        HorizontalOver(
            vertical = CalloutAlignment.Vertical.Bottom,
            horizontal = CalloutAlignment.Horizontal.EndOver
        )
    )
}
