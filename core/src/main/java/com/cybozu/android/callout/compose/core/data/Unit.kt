package com.cybozu.android.callout.compose.core.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val base = 12.dp

internal val Int.dpu: Dp
    get() = base * this

internal val Float.dpu: Dp
    get() = base * this

internal val Double.dpu: Dp
    get() = base * this.toFloat()
