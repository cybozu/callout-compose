package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.PopupLayoutContext
import org.junit.Test

class PopupLayoutCalculatorTest {
    @Test
    fun popupLayoutCalculatorReturnsTheCorrectPopupLayoutContext() {
        val parentSize = Size(width = 400f, height = 1000f)
        val anchorRectInParent = Rect(
            offset = Offset(x = 100f, y = 400f),
            size = Size(width = 100f, height = 100f)
        )

        val testCases = mapOf(
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.TopOuter,
                horizontal = CalloutAlignment.Horizontal.Start
            ) to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = -1f, verticalBias = 1f),
                offsetFromBaseline = Offset(x = 100f, y = -600f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.TopOuter,
                horizontal = CalloutAlignment.Horizontal.Center
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = 0f, verticalBias = 1f),
                offsetFromBaseline = Offset(-50f, -600f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.TopOuter,
                horizontal = CalloutAlignment.Horizontal.End
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = 1f, verticalBias = 1f),
                offsetFromBaseline = Offset(x = -200f, y = -600f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.BottomOuter,
                horizontal = CalloutAlignment.Horizontal.Start
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = -1f, verticalBias = -1f),
                offsetFromBaseline = Offset(x = 100f, y = 500f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.BottomOuter,
                horizontal = CalloutAlignment.Horizontal.Center
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = 0f, verticalBias = -1f),
                offsetFromBaseline = Offset(x = -50.0f, y = 500f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.BottomOuter,
                horizontal = CalloutAlignment.Horizontal.End
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = 1f, verticalBias = -1f),
                offsetFromBaseline = Offset(x = -200f, y = 500f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.Top,
                horizontal = CalloutAlignment.Horizontal.StartOuter
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = 1.0f, verticalBias = -1f),
                offsetFromBaseline = Offset(x = -300f, y = 400f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.Center,
                horizontal = CalloutAlignment.Horizontal.StartOuter
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = 1f, verticalBias = 0f),
                offsetFromBaseline = Offset(x = -300f, y = -50f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.Bottom,
                horizontal = CalloutAlignment.Horizontal.StartOuter
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = 1f, verticalBias = 1f),
                offsetFromBaseline = Offset(x = -300f, y = -500f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.Top,
                horizontal = CalloutAlignment.Horizontal.EndOuter
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = -1f, verticalBias = -1f),
                offsetFromBaseline = Offset(x = 200.0f, y = 400f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.Center,
                horizontal = CalloutAlignment.Horizontal.EndOuter
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = -1f, verticalBias = 0f),
                offsetFromBaseline = Offset(x = 200f, y = -50f)
            ),
            CalloutAlignmentContext(
                vertical = CalloutAlignment.Vertical.Bottom,
                horizontal = CalloutAlignment.Horizontal.EndOuter
            )to PopupLayoutContext(
                alignment = BiasAlignment(horizontalBias = -1f, verticalBias = 1f),
                offsetFromBaseline = Offset(x = 200f, y = -500f)
            )
        )

        testCases.forEach { (input, expect) ->
            val result = PopupLayoutCalculator.calculate(
                parentSize = parentSize,
                anchorRectInParent = anchorRectInParent,
                alignment = input
            )
            assert(result == expect) {
                "alignment: $input\nexpect: $expect\nactual: $result"
            }
        }
    }
}
