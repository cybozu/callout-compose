package com.cybozu.android.callout.compose.sampleapp.material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.CalloutAlignment
import com.cybozu.android.callout.compose.core.modifier.anchoredCallout
import com.cybozu.android.callout.compose.core.rememberCalloutState
import com.cybozu.android.callout.compose.material3.Callout
import com.cybozu.android.callout.compose.sampleapp.material3.ui.theme.CalloutcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalloutcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
            val calloutState = rememberCalloutState(
                isVisible = true
            )
            Anchor(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .anchoredCallout(
                        state = calloutState
                    )
            )
            Callout(
                calloutState = calloutState,
                verticalAlignment = CalloutAlignment.Vertical.Top,
                horizontalAlignment = CalloutAlignment.Horizontal.End.over()
            ) {
                Text(text = "Hello, Callout!")
            }
        }
    }
}

@Composable
private fun Anchor(
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .background(MaterialTheme.colorScheme.onSurface)
    )
}
