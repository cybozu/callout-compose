package com.cybozu.android.callout.compose.core.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.BasicCallout
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.data.CalloutProperties
import com.cybozu.android.callout.compose.core.modifier.anchoredTooltip
import com.cybozu.android.callout.compose.core.rememberCalloutState

@Preview
@Composable
private fun CalloutPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val state = rememberCalloutState(
            isVisible = true
        )
        Box(
            modifier = Modifier
                .background(Color.Black)
                .size(100.dp)
                .align(Alignment.Center)
                .anchoredTooltip(
                    state = state
                )
        )
        BasicCallout(
            calloutState = state,
            calloutProperties = CalloutProperties(
                borderColor = Color.Black,
                contentColor = Color.Black,
                backgroundColor = Color.White
            ),
            verticalAlignment = CalloutAlignment.Vertical.Bottom.over(),
            horizontalAlignment = CalloutAlignment.Horizontal.Start
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}
