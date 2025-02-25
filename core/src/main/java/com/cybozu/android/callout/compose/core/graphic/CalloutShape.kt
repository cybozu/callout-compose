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
package com.cybozu.android.callout.compose.core.graphic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cybozu.android.callout.compose.core.data.CalloutAlignmentContext
import com.cybozu.android.callout.compose.core.modifier.calloutShape
import com.cybozu.android.callout.compose.core.preview.AlignmentPreviewParameterProvider

internal data class CalloutShape(
    private val anchorSize: Size,
    private val alignment: CalloutAlignmentContext,
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        buildOutline(
            alignment = alignment,
            anchorSize = anchorSize,
            selfSize = size,
            density = density
        )

    override fun toString(): String = "CalloutShape"
}

private fun buildOutline(
    alignment: CalloutAlignmentContext,
    anchorSize: Size,
    selfSize: Size,
    density: Density,
): Outline {
    val path = buildCalloutPath(
        alignment = alignment,
        anchorSize = anchorSize,
        selfSize = selfSize,
        density = density
    )

    return Outline.Generic(path)
}

@Preview
@Composable
private fun CalloutShapePreview(
    @PreviewParameter(AlignmentPreviewParameterProvider::class) alignment: CalloutAlignmentContext,
) {
    val density = LocalDensity.current
    Box(
        Modifier
            .background(Color.White)
            .padding(16.dp)
            .size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .calloutShape(
                    anchorSize = with(density) { Size(24.dp.toPx(), 24.dp.toPx()) },
                    alignment = alignment,
                    borderWidth = 1.dp,
                    borderColor = Color.Blue,
                    backgroundColor = Color.White,
                    shadowColor = Color.Blue,
                    elevation = 6.dp
                )
                .matchParentSize()
        ) {
        }
    }
}
