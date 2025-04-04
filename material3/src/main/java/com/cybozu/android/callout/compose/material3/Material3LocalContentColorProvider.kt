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
package com.cybozu.android.callout.compose.material3

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.cybozu.android.callout.compose.core.LocalContentColorProvider

internal object Material3LocalContentColorProvider : LocalContentColorProvider {
    @Composable
    override fun Provide(contentColor: Color, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}
