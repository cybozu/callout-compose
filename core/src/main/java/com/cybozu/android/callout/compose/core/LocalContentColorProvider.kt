package com.cybozu.android.callout.compose.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

public interface LocalContentColorProvider {
    @Composable
    public fun Provide(contentColor: Color, content: @Composable () -> Unit)
}

internal class DefaultLocalContentColorProvider : LocalContentColorProvider {
    @Composable
    override fun Provide(contentColor: Color, content: @Composable () -> Unit) {
        content()
    }
}
