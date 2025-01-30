package com.cybozu.android.callout.compose.material3

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.cybozu.android.callout.compose.core.BasicCallout
import com.cybozu.android.callout.compose.core.CalloutState
import com.cybozu.android.callout.compose.core.data.Alignment
import com.cybozu.android.callout.compose.core.data.CalloutProperties

@Composable
public fun Callout(
    calloutState: CalloutState,
    verticalAlignment: Alignment.Vertical.Over = Alignment.Vertical.Bottom.over(),
    horizontalAlignment: Alignment.Horizontal.Inner = Alignment.Horizontal.Start,
    calloutProperties: CalloutProperties = CalloutProperties(
        borderColor = MaterialTheme.colorScheme.onSurface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        backgroundColor = MaterialTheme.colorScheme.surface
    ),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCallout(
        contentColorProvider = Material3LocalContentColorProvider,
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
    verticalAlignment: Alignment.Vertical.Inner = Alignment.Vertical.Top,
    horizontalAlignment: Alignment.Horizontal.Over = Alignment.Horizontal.End.over(),
    calloutProperties: CalloutProperties = CalloutProperties(
        borderColor = MaterialTheme.colorScheme.onSurface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        backgroundColor = MaterialTheme.colorScheme.surface
    ),
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCallout(
        contentColorProvider = Material3LocalContentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        verticalAlignment = verticalAlignment,
        horizontalAlignment = horizontalAlignment,
        onDismissRequest = onDismissRequest,
        content = content
    )
}
