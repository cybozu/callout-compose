package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.preview.AlignmentPreviewParameterProvider

internal fun CalloutShape(
    alignment: AlignmentContext,
): Shape = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        buildOutline(
            alignment = alignment,
            size = size,
            layoutDirection = layoutDirection,
            density = density
        )

    override fun toString(): String = "CalloutShape"
}

private fun buildOutline(
    alignment: AlignmentContext,
    size: Size,
    layoutDirection: LayoutDirection,
    density: Density,
): Outline {
    val path = buildPath(
        alignment = alignment,
        size = size,
        layoutDirection = layoutDirection,
        density = density
    )

    return Outline.Generic(path)
}

@Preview
@Composable
private fun CalloutShapePreview(
    @PreviewParameter(AlignmentPreviewParameterProvider::class) alignment: AlignmentContext,
) {
    Box(
        Modifier
            .background(Color.White)
            .padding(16.dp)
            .size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .border(
                    width = 1.dp,
                    color = Color.Blue,
                    shape = CalloutShape(
                        alignment = alignment
                    )
                )
                .shadow(
                    elevation = 3.dp,
                    shape = CalloutShape(
                        alignment = alignment
                    ),
                    spotColor = Color.Blue,
                    ambientColor = Color.Blue,
                    clip = false
                )
                .background(
                    color = Color.White,
                    shape = CalloutShape(
                        alignment = alignment
                    )
                )
                .matchParentSize()
        ) {
        }
    }
}
