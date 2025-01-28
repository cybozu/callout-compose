package com.cybozu.android.callout.compose.material2

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.cybozu.android.callout.compose.core.BasicCallout
import com.cybozu.android.callout.compose.core.data.CalloutProperties

@Composable
public fun Callout() {
    BasicCallout(
        calloutProperties = CalloutProperties(
            borderColor = MaterialTheme.colors.onSurface,
            contentColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

@Composable
public fun Callout(calloutProperties: CalloutProperties) {
    BasicCallout(
        calloutProperties = calloutProperties
    )
}
