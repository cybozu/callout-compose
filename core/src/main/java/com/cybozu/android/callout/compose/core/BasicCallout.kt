package com.cybozu.android.callout.compose.core

import androidx.compose.runtime.Composable
import com.cybozu.android.callout.compose.core.data.Alignment
import com.cybozu.android.callout.compose.core.data.AlignmentContext
import com.cybozu.android.callout.compose.core.data.CalloutProperties

@Composable
public fun BasicCallout(
    localContentColorProvider: LocalContentColorProvider = DefaultLocalContentColorProvider(),
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    verticalAlignment: Alignment.Vertical.Over,
    horizontalAlignment: Alignment.Horizontal.Inner,
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCalloutImpl(
        localContentColorProvider = localContentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        alignmentContext = AlignmentContext(
            vertical = verticalAlignment,
            horizontal = horizontalAlignment
        ),
        onDismissRequest = onDismissRequest,
        content = content
    )
}

@Composable
public fun BasicCallout(
    localContentColorProvider: LocalContentColorProvider = DefaultLocalContentColorProvider(),
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    verticalAlignment: Alignment.Vertical.Inner,
    horizontalAlignment: Alignment.Horizontal.Over,
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    BasicCalloutImpl(
        localContentColorProvider = localContentColorProvider,
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        alignmentContext = AlignmentContext(
            vertical = verticalAlignment,
            horizontal = horizontalAlignment
        ),
        onDismissRequest = onDismissRequest,
        content = content
    )
}

@Composable
private fun BasicCalloutImpl(
    localContentColorProvider: LocalContentColorProvider,
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    alignmentContext: AlignmentContext,
    onDismissRequest: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    localContentColorProvider.Provide(
        contentColor = calloutProperties.contentColor
    ) {
        content()
    }
}
