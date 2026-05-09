package com.xingchidongli.robot.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/***
 * tabRow 的自定义Indicator
 */
@Composable
fun PrimaryBrushIndicator(
    modifier: Modifier = Modifier,
    width: Dp = 24.dp,
    height: Dp = 4.dp,
    brush: Brush,
    shape: Shape = RoundedCornerShape(4.dp),
) {
    Spacer(
        modifier
            .requiredHeight(height)
            .requiredWidth(width)
            .background(brush = brush, shape = shape)
    )
}
