package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.dpu

internal fun buildCalloutPath(
    alignment: CalloutAlignmentContext,
    anchorSize: Size,
    selfSize: Size,
    density: Density,
): Path = buildBoxPath(
    alignment = alignment,
    selfSize = selfSize,
    density = density
) xor buildPointerPath(
    alignment = alignment,
    anchorSize = anchorSize,
    selfSize = selfSize,
    density = density
)

private fun buildBoxPath(
    alignment: CalloutAlignmentContext,
    selfSize: Size,
    density: Density,
): Path = buildPath(
    alignment = alignment,
    size = selfSize,
    density = density
) {
    addRoundRect(
        RoundRect(
            Rect(shiftedOffset(0f, 0f), shiftedOffset(selfSize.width, selfSize.height)),
            CornerRadius(1.dpu.toPx(), 1.dpu.toPx())
        )
    )
}

private fun buildPointerPath(
    alignment: CalloutAlignmentContext,
    anchorSize: Size,
    selfSize: Size,
    density: Density,
): Path = buildPath(
    alignment = alignment,
    size = selfSize,
    density = density
) {
    when (alignment) {
        is CalloutAlignmentContext.HorizontalOver -> {
            when (alignment.horizontal) {
                CalloutAlignment.Horizontal.EndOver -> {
                    startPointerPath(
                        anchorSize = anchorSize,
                        verticalAlignment = alignment.vertical
                    )
                }

                CalloutAlignment.Horizontal.StartOver -> {
                    endPointerPath(
                        anchorSize = anchorSize,
                        verticalAlignment = alignment.vertical
                    )
                }
            }
        }

        is CalloutAlignmentContext.VerticalOver -> {
            when (alignment.vertical) {
                CalloutAlignment.Vertical.TopOver -> {
                    bottomPointerPath(
                        anchorSize = anchorSize,
                        horizontalAlignment = alignment.horizontal
                    )
                }

                CalloutAlignment.Vertical.BottomOver -> {
                    topPointerPath(
                        anchorSize = anchorSize,
                        horizontalAlignment = alignment.horizontal
                    )
                }
            }
        }
    }
}

private fun PathScope.topPointerPath(
    anchorSize: Size,
    horizontalAlignment: CalloutAlignment.Horizontal.Inner,
) {
    when (horizontalAlignment) {
        CalloutAlignment.Horizontal.Start -> {
            moveTo(shiftedPoint(2.dpu.toPx(), 0f))
        }

        CalloutAlignment.Horizontal.Center -> {
            moveTo(shiftedPoint(size.width / 2f - 0.5.dpu.toPx(), 0f))
        }

        CalloutAlignment.Horizontal.End -> {
            moveTo(shiftedPoint(size.width - 3.dpu.toPx(), 0f))
        }
    }
    val offset = when (horizontalAlignment) {
        CalloutAlignment.Horizontal.Start -> {
            (2.5.dpu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Horizontal.Center -> {
            0f
        }

        CalloutAlignment.Horizontal.End -> {
            -(2.5.dpu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(0.5.dpu.toPx() - offset, -1.dpu.toPx()))
    relativeLineTo(point(0.5.dpu.toPx() + offset, 1.dpu.toPx()))
    relativeLineTo(point(-1.dpu.toPx(), 0f))
}

private fun PathScope.bottomPointerPath(
    anchorSize: Size,
    horizontalAlignment: CalloutAlignment.Horizontal.Inner,
) {
    when (horizontalAlignment) {
        CalloutAlignment.Horizontal.Start -> {
            moveTo(shiftedPoint(2.dpu.toPx(), size.height))
        }

        CalloutAlignment.Horizontal.Center -> {
            moveTo(shiftedPoint(size.width / 2f - 0.5.dpu.toPx(), size.height))
        }

        CalloutAlignment.Horizontal.End -> {
            moveTo(shiftedPoint(size.width - 3.dpu.toPx(), size.height))
        }
    }
    val offset = when (horizontalAlignment) {
        CalloutAlignment.Horizontal.Start -> {
            (2.5.dpu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Horizontal.Center -> {
            0f
        }

        CalloutAlignment.Horizontal.End -> {
            -(2.5.dpu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(0.5.dpu.toPx() - offset, 1.dpu.toPx()))
    relativeLineTo(point(0.5.dpu.toPx() + offset, -1.dpu.toPx()))
    relativeLineTo(point(-1.dpu.toPx(), 0f))
}

private fun PathScope.startPointerPath(
    anchorSize: Size,
    verticalAlignment: CalloutAlignment.Vertical.Inner,
) {
    when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            moveTo(shiftedPoint(0f, 2.dpu.toPx()))
        }

        CalloutAlignment.Vertical.Center -> {
            moveTo(shiftedPoint(0f, size.height / 2f - 0.5.dpu.toPx()))
        }

        CalloutAlignment.Vertical.Bottom -> {
            moveTo(shiftedPoint(0f, size.height - 3.dpu.toPx()))
        }
    }
    val offset = when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            (2.5.dpu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Vertical.Center -> {
            0f
        }

        CalloutAlignment.Vertical.Bottom -> {
            -(2.5.dpu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(-1.dpu.toPx(), 0.5.dpu.toPx() - offset))
    relativeLineTo(point(1.dpu.toPx(), 0.5.dpu.toPx() + offset))
    relativeLineTo(point(0f, -1.dpu.toPx()))
}

private fun PathScope.endPointerPath(
    anchorSize: Size,
    verticalAlignment: CalloutAlignment.Vertical.Inner,
) {
    when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            moveTo(shiftedPoint(size.width, 2.dpu.toPx()))
        }

        CalloutAlignment.Vertical.Center -> {
            moveTo(shiftedPoint(size.width, size.height / 2f - 0.5.dpu.toPx()))
        }

        CalloutAlignment.Vertical.Bottom -> {
            moveTo(shiftedPoint(size.width, size.height - 3.dpu.toPx()))
        }
    }
    val offset = when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            (2.5.dpu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Vertical.Center -> {
            0f
        }

        CalloutAlignment.Vertical.Bottom -> {
            -(2.5.dpu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(1.dpu.toPx(), 0.5.dpu.toPx() - offset))
    relativeLineTo(point(-1.dpu.toPx(), 0.5.dpu.toPx() + offset))
    relativeLineTo(point(0f, -1.dpu.toPx()))
}

private fun buildPath(alignment: CalloutAlignmentContext, size: Size, density: Density, builder: PathScope.() -> Unit = {}): Path = PathScope(
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
    val size: Size,
    private val alignment: CalloutAlignmentContext,
    private val density: Density,
    private val inner: Path,
) {
    fun Dp.toPx(): Float = with(density) { toPx() }

    fun point(x: Float, y: Float): Point = Point(
        x = x,
        y = y
    )

    fun shiftedPoint(x: Float, y: Float): Point {
        val newX = when (alignment) {
            is CalloutAlignmentContext.HorizontalOver -> {
                when {
                    alignment.horizontal is CalloutAlignment.Horizontal.StartOver && x >= (size.width / 2f) -> x - 1.dpu.toPx()
                    alignment.horizontal is CalloutAlignment.Horizontal.EndOver && x <= (size.width / 2f) -> x + 1.dpu.toPx()
                    else -> x
                }
            }

            is CalloutAlignmentContext.VerticalOver -> x
        }
        val newY = when (alignment) {
            is CalloutAlignmentContext.VerticalOver -> {
                when {
                    alignment.vertical is CalloutAlignment.Vertical.TopOver && y >= (size.height / 2f) -> y - 1.dpu.toPx()
                    alignment.vertical is CalloutAlignment.Vertical.BottomOver && y <= (size.height / 2f) -> y + 1.dpu.toPx()
                    else -> y
                }
            }

            is CalloutAlignmentContext.HorizontalOver -> y
        }
        return Point(
            x = newX,
            y = newY
        )
    }

    fun shiftedOffset(x: Float, y: Float): Offset {
        val (newX, newY) = shiftedPoint(x, y)
        return Offset(
            x = newX,
            y = newY
        )
    }

    fun shiftedSize(width: Float, height: Float): Size = Size(
        width = when (alignment) {
            is CalloutAlignmentContext.HorizontalOver -> width - 1.dpu.toPx()
            is CalloutAlignmentContext.VerticalOver -> width
        },
        height = when (alignment) {
            is CalloutAlignmentContext.HorizontalOver -> height
            is CalloutAlignmentContext.VerticalOver -> height - 1.dpu.toPx()
        }
    )

    fun moveTo(point: Point) {
        val (x, y) = point
        inner.moveTo(x, y)
    }

    fun relativeMoveTo(point: Point) {
        val (newX, newY) = point
        inner.relativeMoveTo(newX, newY)
    }

    fun lineTo(point: Point) {
        val (x, y) = point
        inner.lineTo(x, y)
    }

    fun relativeLineTo(point: Point) {
        val (newX, newY) = point
        inner.relativeLineTo(newX, newY)
    }

    fun addRoundRect(roundRect: RoundRect) {
        inner.addRoundRect(roundRect)
    }

    fun build(): Path = inner
}
