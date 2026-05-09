package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xingchidongli.robot.theme.color_084
import com.xingchidongli.robot.theme.color_e45
import com.xingchidongli.robot.theme.color_while

@Composable
fun DeviceConnectCommonChipComponent(
     onClickAction: () -> Unit,
     @DrawableRes imgRes: Int,
     defaultColor: Color = color_while,
     contentText: String
) {
    Row(modifier = Modifier.wrapContentSize()) {
        AssistChip(
            onClick = onClickAction,
            label = {
                Text(
                    contentText,
                    color = color_e45,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                )
            },
            modifier = Modifier.wrapContentSize(),

            leadingIcon = {
                Image(
                    painter = painterResource(imgRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                 )
            },

            shape = RoundedCornerShape(20.dp),
            colors = AssistChipDefaults.assistChipColors(containerColor =defaultColor),
            border = BorderStroke(width = 0.dp, color = Color.Transparent)
        )
        Spacer(modifier = Modifier.size(11.dp))

    }


}
@Composable
fun DeviceConnectStatusChipComponent(
    isEnable: Boolean = false,
    onClickAction: () -> Unit,
    @DrawableRes disEnableRes: Int,
    @DrawableRes enableImgRes: Int = 0,
    @DrawableRes trailingRes: Int = 0,
    defaultColor: Color = color_while,
    activeColor: Color = color_084,
    contentText: String
) {
    Row(modifier = Modifier.wrapContentSize()) {
        AssistChip(
            onClick = onClickAction,
            label = {
                Text(
                    contentText,
                    color = if (isEnable) color_while else color_e45 ,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                )
            },
            modifier = Modifier.wrapContentSize(),

            leadingIcon = {
                Image(
                    painter = painterResource(if (isEnable)enableImgRes else disEnableRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                 )
            },
            trailingIcon = {

                if (trailingRes != 0&&isEnable) {
                    Image(
                        painter = painterResource(trailingRes),
                        contentDescription = "Localized description",
                        Modifier
                            .size(18.dp)
                    )
                }
            },
            shape = RoundedCornerShape(20.dp),
            colors = AssistChipDefaults.assistChipColors(containerColor = if (!isEnable) defaultColor else activeColor),
            border = BorderStroke(width = 0.dp, color = Color.Transparent)
        )
        Spacer(modifier = Modifier.size(11.dp))

    }


}
 

@Composable
fun LineComponent(
    paddingStart: Dp = 30.dp,
    textSize: Int = 16,
    index: String,
    text: String,
    lineLong: Dp,
    textLong: Dp = 10.dp,
    topMargin: Dp = 0.dp,
    bgColor: Color
) {
    Row(modifier = Modifier.padding(top = paddingStart, start = 15.dp, end = 15.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(4.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp)
                    .background(bgColor, shape = RoundedCornerShape(9.dp)),
            ) {

                Text(
                    index,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = color_while,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Spacer(modifier = Modifier.size(textLong))
        Column {
            Text(
                text,
                fontSize = textSize.sp,
                fontWeight = FontWeight.Bold,
                color = color_e45,
            )
        }
    }
}