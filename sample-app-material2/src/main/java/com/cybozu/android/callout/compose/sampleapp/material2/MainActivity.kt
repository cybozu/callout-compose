package com.cybozu.android.callout.compose.sampleapp.material2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.modifier.anchoredCallout
import com.cybozu.android.callout.compose.core.rememberCalloutState
import com.cybozu.android.callout.compose.material2.Callout
import com.cybozu.android.callout.compose.sampleapp.material2.ui.theme.CalloutcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalloutcomposeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.statusBarsPadding()
                        ) {
                            Text(stringResource(R.string.app_name))
                        }
                    }
                ) { innerPadding ->
                    CalloutScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun CalloutScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            AnchoredCallout(
                anchorAlignment = Alignment.TopStart,
                calloutVerticalAlignment = CalloutAlignment.Vertical.Top,
                calloutHorizontalAlignment = CalloutAlignment.Horizontal.End.over()
            )
            AnchoredCallout(
                anchorAlignment = Alignment.Center,
                calloutVerticalAlignment = CalloutAlignment.Vertical.Bottom.over(),
                calloutHorizontalAlignment = CalloutAlignment.Horizontal.End
            )
            AnchoredCallout(
                anchorAlignment = Alignment.BottomEnd,
                calloutVerticalAlignment = CalloutAlignment.Vertical.Top.over(),
                calloutHorizontalAlignment = CalloutAlignment.Horizontal.End
            )
        }
    }
}

@Composable
private fun BoxScope.AnchoredCallout(
    anchorAlignment: Alignment,
    calloutVerticalAlignment: CalloutAlignment.Vertical.Inner,
    calloutHorizontalAlignment: CalloutAlignment.Horizontal.Over,
) {
    val calloutState = rememberCalloutState(
        isVisible = true
    )
    Anchor(
        modifier = Modifier
            .align(anchorAlignment)
            .anchoredCallout(
                state = calloutState
            )
    )
    Callout(
        calloutState = calloutState,
        verticalAlignment = calloutVerticalAlignment,
        horizontalAlignment = calloutHorizontalAlignment
    ) {
        Text(text = "Hello, Callout!")
    }
}

@Composable
private fun BoxScope.AnchoredCallout(
    anchorAlignment: Alignment,
    calloutVerticalAlignment: CalloutAlignment.Vertical.Over,
    calloutHorizontalAlignment: CalloutAlignment.Horizontal.Inner,
) {
    val calloutState = rememberCalloutState(
        isVisible = true
    )
    Anchor(
        modifier = Modifier
            .align(anchorAlignment)
            .anchoredCallout(
                state = calloutState
            )
    )
    Callout(
        calloutState = calloutState,
        verticalAlignment = calloutVerticalAlignment,
        horizontalAlignment = calloutHorizontalAlignment
    ) {
        Text(text = "Hello, Callout!")
    }
}

@Composable
private fun Anchor(
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .background(MaterialTheme.colors.onSurface)
    )
}
