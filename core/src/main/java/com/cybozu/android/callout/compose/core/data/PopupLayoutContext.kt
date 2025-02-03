package com.cybozu.android.callout.compose.core.data

import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset

internal data class PopupLayoutContext(
    val alignment: Alignment,
    val offsetFromBaseline: Offset,
)
