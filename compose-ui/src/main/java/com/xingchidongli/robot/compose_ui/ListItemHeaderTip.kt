package com.xingchidongli.robot.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/***
 * 渐变顶部list的tab
 */
@Composable
fun ListItemHeaderTip(
    label: String,
    textSpan: Dp = 10.dp,
    topSpan: Dp = 0.dp,
    bottomSpan: Dp = 0.dp,
    textStyle: TextStyle,
    shape: RoundedCornerShape = RoundedCornerShape(2.dp),
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(top = topSpan, bottom = bottomSpan),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = modifier
                .size(width = 4.dp, height = 15.dp)
                .padding(top = 2.dp)
                .background(brush = brush, shape = shape)
        )
        Spacer(modifier = modifier.size(textSpan))
        Text(label, style = textStyle)
    }
}
