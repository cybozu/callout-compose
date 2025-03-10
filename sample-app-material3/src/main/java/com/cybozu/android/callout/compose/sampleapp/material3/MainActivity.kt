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
package com.cybozu.android.callout.compose.sampleapp.material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(R.string.app_name))
                            }
                        )
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
                calloutHorizontalAlignment = CalloutAlignment.Horizontal.End.outer()
            )
            AnchoredCallout(
                anchorAlignment = Alignment.Center,
                calloutVerticalAlignment = CalloutAlignment.Vertical.Bottom.outer(),
                calloutHorizontalAlignment = CalloutAlignment.Horizontal.End
            )
            AnchoredCallout(
                anchorAlignment = Alignment.BottomEnd,
                calloutVerticalAlignment = CalloutAlignment.Vertical.Top.outer(),
                calloutHorizontalAlignment = CalloutAlignment.Horizontal.End
            )
        }
    }
}

@Composable
private fun BoxScope.AnchoredCallout(
    anchorAlignment: Alignment,
    calloutVerticalAlignment: CalloutAlignment.Vertical.Inner,
    calloutHorizontalAlignment: CalloutAlignment.Horizontal.Outer,
) {
    val calloutState = rememberCalloutState()
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
    calloutVerticalAlignment: CalloutAlignment.Vertical.Outer,
    calloutHorizontalAlignment: CalloutAlignment.Horizontal.Inner,
) {
    val calloutState = rememberCalloutState()
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
            .background(MaterialTheme.colorScheme.onSurface)
    )
}
