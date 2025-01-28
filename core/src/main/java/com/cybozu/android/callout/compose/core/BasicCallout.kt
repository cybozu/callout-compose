package com.cybozu.android.callout.compose.core

import androidx.compose.runtime.Composable
import com.cybozu.android.callout.compose.core.data.CalloutProperties
import com.cybozu.android.callout.compose.core.data.HorizontalAlignment
import com.cybozu.android.callout.compose.core.data.VerticalAlignment

@Composable
public fun BasicCallout(
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    verticalAlignment: VerticalAlignment.Over,
    horizontalAlignment: HorizontalAlignment,
) {
    BasicCalloutImpl(
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        verticalAlignment = verticalAlignment,
        horizontalAlignment = horizontalAlignment
    )
}

@Composable
public fun BasicCallout(
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    verticalAlignment: VerticalAlignment,
    horizontalAlignment: HorizontalAlignment.Over,
) {
    BasicCalloutImpl(
        calloutState = calloutState,
        calloutProperties = calloutProperties,
        verticalAlignment = verticalAlignment,
        horizontalAlignment = horizontalAlignment
    )
}

@Composable
private fun BasicCalloutImpl(
    calloutState: CalloutState,
    calloutProperties: CalloutProperties,
    verticalAlignment: VerticalAlignment,
    horizontalAlignment: HorizontalAlignment,
) {
}
