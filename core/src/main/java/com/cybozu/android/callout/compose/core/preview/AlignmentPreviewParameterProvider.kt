package com.cybozu.android.callout.compose.core.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.data.AlignmentContext.HorizontalOver
import com.cybozu.android.callout.compose.core.data.AlignmentContext.VerticalOver
import com.cybozu.android.callout.compose.core.data.HorizontalAlignment
import com.cybozu.android.callout.compose.core.data.VerticalAlignment

internal class AlignmentPreviewParameterProvider : PreviewParameterProvider<AlignmentContext> {
    override val values: Sequence<AlignmentContext> = sequenceOf(
        VerticalOver(
            vertical = VerticalAlignment.TopOver,
            horizontal = HorizontalAlignment.Start
        ),
        VerticalOver(
            vertical = VerticalAlignment.TopOver,
            horizontal = HorizontalAlignment.Center
        ),
        VerticalOver(
            vertical = VerticalAlignment.TopOver,
            horizontal = HorizontalAlignment.End
        ),
        HorizontalOver(
            vertical = VerticalAlignment.Top,
            horizontal = HorizontalAlignment.StartOver
        ),
        HorizontalOver(
            vertical = VerticalAlignment.Center,
            horizontal = HorizontalAlignment.StartOver
        ),
        HorizontalOver(
            vertical = VerticalAlignment.Bottom,
            horizontal = HorizontalAlignment.StartOver
        ),
        VerticalOver(
            vertical = VerticalAlignment.BottomOver,
            horizontal = HorizontalAlignment.Start
        ),
        VerticalOver(
            vertical = VerticalAlignment.BottomOver,
            horizontal = HorizontalAlignment.Center
        ),
        VerticalOver(
            vertical = VerticalAlignment.BottomOver,
            horizontal = HorizontalAlignment.End
        ),
        HorizontalOver(
            vertical = VerticalAlignment.Top,
            horizontal = HorizontalAlignment.EndOver
        ),
        HorizontalOver(
            vertical = VerticalAlignment.Center,
            horizontal = HorizontalAlignment.EndOver
        ),
        HorizontalOver(
            vertical = VerticalAlignment.Bottom,
            horizontal = HorizontalAlignment.EndOver
        )
    )
}
