package com.cybozu.android.callout.compose.material3

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.cybozu.android.callout.compose.core.BasicCallout
import com.cybozu.android.callout.compose.core.data.CalloutProperties

@Composable
public fun Callout() {
    BasicCallout(
        calloutProperties = CalloutProperties(
            borderColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
public fun Callout(calloutProperties: CalloutProperties) {
    BasicCallout(
        calloutProperties = calloutProperties
    )
}
