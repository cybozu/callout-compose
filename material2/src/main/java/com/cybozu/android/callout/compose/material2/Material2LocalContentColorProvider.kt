package com.cybozu.android.callout.compose.material2

import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.cybozu.android.callout.compose.core.LocalContentColorProvider

internal object Material2LocalContentColorProvider : LocalContentColorProvider {
    @Composable
    override fun Provide(contentColor: Color, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}
