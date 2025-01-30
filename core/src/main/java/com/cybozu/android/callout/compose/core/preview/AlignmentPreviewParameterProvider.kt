package com.cybozu.android.callout.compose.core.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cybozu.android.callout.compose.core.data.Alignment
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.data.AlignmentContext.HorizontalOver
import com.cybozu.android.callout.compose.core.data.AlignmentContext.VerticalOver

internal class AlignmentPreviewParameterProvider : PreviewParameterProvider<AlignmentContext> {
    override val values: Sequence<AlignmentContext> = sequenceOf(
        VerticalOver(
            vertical = Alignment.Vertical.TopOver,
            horizontal = Alignment.Horizontal.Start
        ),
        VerticalOver(
            vertical = Alignment.Vertical.TopOver,
            horizontal = Alignment.Horizontal.Center
        ),
        VerticalOver(
            vertical = Alignment.Vertical.TopOver,
            horizontal = Alignment.Horizontal.End
        ),
        HorizontalOver(
            vertical = Alignment.Vertical.Top,
            horizontal = Alignment.Horizontal.StartOver
        ),
        HorizontalOver(
            vertical = Alignment.Vertical.Center,
            horizontal = Alignment.Horizontal.StartOver
        ),
        HorizontalOver(
            vertical = Alignment.Vertical.Bottom,
            horizontal = Alignment.Horizontal.StartOver
        ),
        VerticalOver(
            vertical = Alignment.Vertical.BottomOver,
            horizontal = Alignment.Horizontal.Start
        ),
        VerticalOver(
            vertical = Alignment.Vertical.BottomOver,
            horizontal = Alignment.Horizontal.Center
        ),
        VerticalOver(
            vertical = Alignment.Vertical.BottomOver,
            horizontal = Alignment.Horizontal.End
        ),
        HorizontalOver(
            vertical = Alignment.Vertical.Top,
            horizontal = Alignment.Horizontal.EndOver
        ),
        HorizontalOver(
            vertical = Alignment.Vertical.Center,
            horizontal = Alignment.Horizontal.EndOver
        ),
        HorizontalOver(
            vertical = Alignment.Vertical.Bottom,
            horizontal = Alignment.Horizontal.EndOver
        )
    )
}
