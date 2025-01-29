package com.cybozu.android.callout.compose.core.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.graphic.CalloutShape

public class CalloutModifier

internal fun Modifier.calloutShape(
    anchorSize: Size,
    alignment: AlignmentContext,
    borderWidth: Dp,
    borderColor: Color,
    backgroundColor: Color,
    shadowColor: Color,
    elevation: Dp,
): Modifier = this
    .border(
        width = borderWidth,
        color = borderColor,
        shape = CalloutShape(
            anchorSize = anchorSize,
            alignment = alignment
        )
    )
    .shadow(
        elevation = elevation,
        shape = CalloutShape(
            anchorSize = anchorSize,
            alignment = alignment
        ),
        spotColor = shadowColor,
        ambientColor = shadowColor,
        clip = false
    )
    .background(
        color = backgroundColor,
        shape = CalloutShape(
            anchorSize = anchorSize,
            alignment = alignment
        )
    )
