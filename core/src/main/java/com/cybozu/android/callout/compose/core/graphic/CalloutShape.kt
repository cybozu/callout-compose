package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.AlignmentContext

internal fun CalloutShape(
    alignment: AlignmentContext,
): Shape = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        OutlineBuilder.build(alignment, size, layoutDirection, density)

    override fun toString(): String = "CalloutShape"
}

internal fun TextShape(path: Path): Shape = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        Outline.Generic(path)

    override fun toString(): String = "TextShape"
}

private object OutlineBuilder {

    fun build(
        alignment: AlignmentContext,
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path().apply {
            // TODO
        }

        return Outline.Generic(path)
    }
}

private enum class Quadrant {
    Top,
    Start,
    End,
    Bottom,
}

@Preview
@Composable
private fun CalloutShapePreview() {
    Surface {
        val path1 = Path().apply {
            addRoundRect(
                RoundRect(
                    Rect(Offset(25f, 25f), Offset(100f, 75f)),
                    CornerRadius(10f, 10f)
                )
            )
        }
        val path2 = Path().apply {
            moveTo(25f, 35f)
            lineTo(15f, 45f)
            lineTo(25f, 55f)
            lineTo(25f, 35f)
        }

        Box(
            Modifier.border(
                width = 1.dp,
                color = Color.Blue,
                shape = TextShape(
                    path1 xor path2
                )
            )
        ) {
            Column {
                Text("Hello, World!")
                Text("Hello, World!")
            }
        }
    }
}
