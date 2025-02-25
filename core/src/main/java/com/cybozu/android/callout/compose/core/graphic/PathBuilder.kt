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
import com.cybozu.android.callout.compose.core.data.ccu

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
            CornerRadius(1.ccu.toPx(), 1.ccu.toPx())
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
        is CalloutAlignmentContext.HorizontalOuter -> {
            when (alignment.horizontal) {
                CalloutAlignment.Horizontal.EndOuter -> {
                    startPointerPath(
                        anchorSize = anchorSize,
                        verticalAlignment = alignment.vertical
                    )
                }

                CalloutAlignment.Horizontal.StartOuter -> {
                    endPointerPath(
                        anchorSize = anchorSize,
                        verticalAlignment = alignment.vertical
                    )
                }
            }
        }

        is CalloutAlignmentContext.VerticalOuter -> {
            when (alignment.vertical) {
                CalloutAlignment.Vertical.TopOuter -> {
                    bottomPointerPath(
                        anchorSize = anchorSize,
                        horizontalAlignment = alignment.horizontal
                    )
                }

                CalloutAlignment.Vertical.BottomOuter -> {
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
            moveTo(shiftedPoint(2.ccu.toPx(), 0f))
        }

        CalloutAlignment.Horizontal.Center -> {
            moveTo(shiftedPoint(size.width / 2f - 0.5.ccu.toPx(), 0f))
        }

        CalloutAlignment.Horizontal.End -> {
            moveTo(shiftedPoint(size.width - 3.ccu.toPx(), 0f))
        }
    }
    val offset = when (horizontalAlignment) {
        CalloutAlignment.Horizontal.Start -> {
            (2.5.ccu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Horizontal.Center -> {
            0f
        }

        CalloutAlignment.Horizontal.End -> {
            -(2.5.ccu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(0.5.ccu.toPx() - offset, -1.ccu.toPx()))
    relativeLineTo(point(0.5.ccu.toPx() + offset, 1.ccu.toPx()))
    relativeLineTo(point(-1.ccu.toPx(), 0f))
}

private fun PathScope.bottomPointerPath(
    anchorSize: Size,
    horizontalAlignment: CalloutAlignment.Horizontal.Inner,
) {
    when (horizontalAlignment) {
        CalloutAlignment.Horizontal.Start -> {
            moveTo(shiftedPoint(2.ccu.toPx(), size.height))
        }

        CalloutAlignment.Horizontal.Center -> {
            moveTo(shiftedPoint(size.width / 2f - 0.5.ccu.toPx(), size.height))
        }

        CalloutAlignment.Horizontal.End -> {
            moveTo(shiftedPoint(size.width - 3.ccu.toPx(), size.height))
        }
    }
    val offset = when (horizontalAlignment) {
        CalloutAlignment.Horizontal.Start -> {
            (2.5.ccu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Horizontal.Center -> {
            0f
        }

        CalloutAlignment.Horizontal.End -> {
            -(2.5.ccu.toPx() - (anchorSize.width / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(0.5.ccu.toPx() - offset, 1.ccu.toPx()))
    relativeLineTo(point(0.5.ccu.toPx() + offset, -1.ccu.toPx()))
    relativeLineTo(point(-1.ccu.toPx(), 0f))
}

private fun PathScope.startPointerPath(
    anchorSize: Size,
    verticalAlignment: CalloutAlignment.Vertical.Inner,
) {
    when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            moveTo(shiftedPoint(0f, 2.ccu.toPx()))
        }

        CalloutAlignment.Vertical.Center -> {
            moveTo(shiftedPoint(0f, size.height / 2f - 0.5.ccu.toPx()))
        }

        CalloutAlignment.Vertical.Bottom -> {
            moveTo(shiftedPoint(0f, size.height - 3.ccu.toPx()))
        }
    }
    val offset = when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            (2.5.ccu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Vertical.Center -> {
            0f
        }

        CalloutAlignment.Vertical.Bottom -> {
            -(2.5.ccu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(-1.ccu.toPx(), 0.5.ccu.toPx() - offset))
    relativeLineTo(point(1.ccu.toPx(), 0.5.ccu.toPx() + offset))
    relativeLineTo(point(0f, -1.ccu.toPx()))
}

private fun PathScope.endPointerPath(
    anchorSize: Size,
    verticalAlignment: CalloutAlignment.Vertical.Inner,
) {
    when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            moveTo(shiftedPoint(size.width, 2.ccu.toPx()))
        }

        CalloutAlignment.Vertical.Center -> {
            moveTo(shiftedPoint(size.width, size.height / 2f - 0.5.ccu.toPx()))
        }

        CalloutAlignment.Vertical.Bottom -> {
            moveTo(shiftedPoint(size.width, size.height - 3.ccu.toPx()))
        }
    }
    val offset = when (verticalAlignment) {
        CalloutAlignment.Vertical.Top -> {
            (2.5.ccu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }

        CalloutAlignment.Vertical.Center -> {
            0f
        }

        CalloutAlignment.Vertical.Bottom -> {
            -(2.5.ccu.toPx() - (anchorSize.height / 2f)).coerceAtLeast(0f)
        }
    }
    relativeLineTo(point(1.ccu.toPx(), 0.5.ccu.toPx() - offset))
    relativeLineTo(point(-1.ccu.toPx(), 0.5.ccu.toPx() + offset))
    relativeLineTo(point(0f, -1.ccu.toPx()))
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
            is CalloutAlignmentContext.HorizontalOuter -> {
                when {
                    alignment.horizontal is CalloutAlignment.Horizontal.StartOuter && x >= (size.width / 2f) -> x - 1.ccu.toPx()
                    alignment.horizontal is CalloutAlignment.Horizontal.EndOuter && x <= (size.width / 2f) -> x + 1.ccu.toPx()
                    else -> x
                }
            }

            is CalloutAlignmentContext.VerticalOuter -> x
        }
        val newY = when (alignment) {
            is CalloutAlignmentContext.VerticalOuter -> {
                when {
                    alignment.vertical is CalloutAlignment.Vertical.TopOuter && y >= (size.height / 2f) -> y - 1.ccu.toPx()
                    alignment.vertical is CalloutAlignment.Vertical.BottomOuter && y <= (size.height / 2f) -> y + 1.ccu.toPx()
                    else -> y
                }
            }

            is CalloutAlignmentContext.HorizontalOuter -> y
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
            is CalloutAlignmentContext.HorizontalOuter -> width - 1.ccu.toPx()
            is CalloutAlignmentContext.VerticalOuter -> width
        },
        height = when (alignment) {
            is CalloutAlignmentContext.HorizontalOuter -> height
            is CalloutAlignmentContext.VerticalOuter -> height - 1.ccu.toPx()
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
