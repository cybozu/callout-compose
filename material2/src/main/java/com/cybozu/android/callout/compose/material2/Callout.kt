package com.cybozu.android.callout.compose.material2

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.cybozu.android.callout.compose.core.BasicCallout
import com.cybozu.android.callout.compose.core.CalloutState
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutProperties

@Composable
public fun Callout(
    calloutState: CalloutState,
    verticalAlignment: CalloutAlignment.Vertical.Outer = CalloutAlignment.Vertical.Bottom.outer(),
    horizontalAlignment: CalloutAlignment.Horizontal.Inner = CalloutAlignment.Horizontal.Start,
    calloutProperties: CalloutProperties = CalloutProperties(
        borderColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.onSurface,
        backgroundColor = MaterialTheme.colors.surface
    ),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCallout(
        contentColorProvider = Material2LocalContentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        verticalAlignment = verticalAlignment,
        horizontalAlignment = horizontalAlignment,
        onDismissRequest = onDismissRequest,
        content = content
    )
}

@Composable
public fun Callout(
    calloutState: CalloutState,
    verticalAlignment: CalloutAlignment.Vertical.Inner,
    horizontalAlignment: CalloutAlignment.Horizontal.Outer,
    calloutProperties: CalloutProperties = CalloutProperties(
        borderColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.onSurface,
        backgroundColor = MaterialTheme.colors.surface
    ),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCallout(
        contentColorProvider = Material2LocalContentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        verticalAlignment = verticalAlignment,
        horizontalAlignment = horizontalAlignment,
        onDismissRequest = onDismissRequest,
        content = content
    )
}
