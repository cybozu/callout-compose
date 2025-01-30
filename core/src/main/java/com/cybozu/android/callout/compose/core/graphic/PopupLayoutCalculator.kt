package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp

public data class PopupLayoutContext(
    val alignment: Alignment,
    val offsetFromBaseline: Alignment,
    val minWidth: Dp,
    val minHeight: Dp,
    val maxWidth: Dp,
    val maxHeight: Dp,
)

public class PopupLayoutCalculator
