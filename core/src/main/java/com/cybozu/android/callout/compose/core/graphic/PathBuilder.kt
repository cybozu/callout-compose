package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.data.HorizontalAlignment
import com.cybozu.android.callout.compose.core.data.VerticalAlignment

internal fun buildPath(
    alignment: AlignmentContext,
    size: Size,
    layoutDirection: LayoutDirection,
    density: Density,
): Path = buildPath(
    alignment = alignment,
    size = size,
    density = density
) {
    addRoundRect(
        RoundRect(
            Rect(offset(0f, 0f), offset(size.width, size.height)),
            CornerRadius(8f, 8f)
        )
    )
}

private fun buildPath(alignment: AlignmentContext, size: Size, density: Density, builder: PathScope.() -> Unit = {}): Path = PathScope(
    alignment = alignment,
    size = size,
    density = density,
    inner = Path()
).apply {
    builder()
}.build()

private data class Point(
    val x: Float,
    val y: Float,
)

private data class PathScope(
    private val alignment: AlignmentContext,
    private val size: Size,
    private val density: Density,
    private val inner: Path,
) {
    fun Dp.toPx(): Float = with(density) { toPx() }

    fun offset(x: Float, y: Float): Offset {
        val newX = when (alignment) {
            is AlignmentContext.HorizontalOver -> {
                when {
                    alignment.horizontal is HorizontalAlignment.StartOver && x >= (size.width / 2f) -> x - 8.dp.toPx()
                    alignment.horizontal is HorizontalAlignment.EndOver && x <= (size.width / 2f) -> x + 8.dp.toPx()
                    else -> x
                }
            }

            is AlignmentContext.VerticalOver -> x
        }
        val newY = when (alignment) {
            is AlignmentContext.VerticalOver -> {
                when {
                    alignment.vertical is VerticalAlignment.TopOver && y >= (size.height / 2f) -> y - 8.dp.toPx()
                    alignment.vertical is VerticalAlignment.BottomOver && y <= (size.height / 2f) -> y + 8.dp.toPx()
                    else -> y
                }
            }

            is AlignmentContext.HorizontalOver -> y
        }
        return Offset(
            x = newX,
            y = newY
        )
    }

    fun size(width: Float, height: Float): Size = Size(
        width = when (alignment) {
            is AlignmentContext.HorizontalOver -> width - 8.dp.toPx()
            is AlignmentContext.VerticalOver -> width
        },
        height = when (alignment) {
            is AlignmentContext.HorizontalOver -> height
            is AlignmentContext.VerticalOver -> height - 8.dp.toPx()
        }
    )

    fun moveTo(point: Point) {
        val (x, y) = point
        inner.moveTo(x, y)
    }

    fun lineTo(point: Point) {
        val (x, y) = point
        inner.lineTo(x, y)
    }

    fun addRoundRect(roundRect: RoundRect) {
        inner.addRoundRect(roundRect)
    }

    fun build(): Path = inner
}
