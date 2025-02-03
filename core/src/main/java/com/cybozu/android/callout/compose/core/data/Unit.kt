package com.cybozu.android.callout.compose.core.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Callout Compose Unit

private val base = 12.dp

internal val Int.ccu: Dp
    get() = base * this

internal val Float.ccu: Dp
    get() = base * this

internal val Double.ccu: Dp
    get() = base * this.toFloat()
