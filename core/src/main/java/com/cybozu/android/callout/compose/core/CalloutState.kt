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
package com.cybozu.android.callout.compose.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

public sealed interface CalloutState

internal class CalloutStateImpl : CalloutState {

    val isAnchored by derivedStateOf {
        parentSize != null && anchorPositionInWindow != null && anchorRectInParent != null
    }

    var parentSize: Size? by mutableStateOf(null)
    var anchorPositionInWindow: Offset? by mutableStateOf(null)
    var anchorRectInParent: Rect? by mutableStateOf(null)
}

@Composable
public fun rememberCalloutState(): CalloutState = remember {
    CalloutStateImpl()
}
