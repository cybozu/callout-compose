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
