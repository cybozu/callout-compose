package com.cybozu.android.callout.compose.core.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public data class CalloutProperties(
    val border: BorderProperties,
    val color: ColorProperties,
    val elevation: Dp,
    val isFocusable: Boolean,
    val animationDurationMillis: Int,
) {
    public constructor(
        borderColor: Color,
        contentColor: Color,
        backgroundColor: Color,
    ) : this(
        borderColor = borderColor,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        shadowColor = backgroundColor,
        elevation = 6.dp
    )

    public constructor(
        borderColor: Color,
        contentColor: Color,
        backgroundColor: Color,
        elevation: Dp,
    ) : this(
        borderColor = borderColor,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        shadowColor = backgroundColor,
        elevation = elevation
    )

    public constructor(
        borderColor: Color,
        contentColor: Color,
        backgroundColor: Color,
        shadowColor: Color,
        elevation: Dp,
    ) : this(
        border = BorderProperties(borderColor),
        color = ColorProperties(
            contentColor = contentColor,
            backgroundColor = backgroundColor,
            shadowColor = shadowColor
        ),
        elevation = elevation,
        isFocusable = false,
        animationDurationMillis = 500
    )
}

public data class BorderProperties(
    val width: Dp,
    val color: Color,
) {
    public constructor(color: Color) : this(1.dp, color)
}

public data class ColorProperties(
    val contentColor: Color,
    val backgroundColor: Color,
    val shadowColor: Color,
)
