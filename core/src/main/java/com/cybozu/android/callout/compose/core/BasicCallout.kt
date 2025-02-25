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
import com.cybozu.android.callout.compose.core.component.CalloutFrame
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.data.CalloutProperties

@Composable
public fun BasicCallout(
    contentColorProvider: LocalContentColorProvider = DefaultLocalContentColorProvider(),
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    verticalAlignment: CalloutAlignment.Vertical.Outer,
    horizontalAlignment: CalloutAlignment.Horizontal.Inner,
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCalloutImpl(
        contentColorProvider = contentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        alignmentContext = CalloutAlignmentContext(
            vertical = verticalAlignment,
            horizontal = horizontalAlignment
        ),
        onDismissRequest = onDismissRequest,
        content = content
    )
}

@Composable
public fun BasicCallout(
    contentColorProvider: LocalContentColorProvider = DefaultLocalContentColorProvider(),
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    verticalAlignment: CalloutAlignment.Vertical.Inner,
    horizontalAlignment: CalloutAlignment.Horizontal.Outer,
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCalloutImpl(
        contentColorProvider = contentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        alignmentContext = CalloutAlignmentContext(
            vertical = verticalAlignment,
            horizontal = horizontalAlignment
        ),
        onDismissRequest = onDismissRequest,
        content = content
    )
}

@Composable
private fun BasicCalloutImpl(
    contentColorProvider: LocalContentColorProvider,
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    alignmentContext: CalloutAlignmentContext,
    onDismissRequest: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    CalloutFrame(
        contentColorProvider = contentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        alignmentContext = alignmentContext,
        onDismissRequest = onDismissRequest,
        content = content
    )
}
