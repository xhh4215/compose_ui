package com.xingchidongli.robot.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.xingchidongli.robot.theme.BackComposeStyle.backComposeTextColor
import com.xingchidongli.robot.theme.CommonUiListStyle.listItemHeaderTextColor


object CommonUiListStyle {

    private val listItemHeaderTextColor: Color = color_e45

    //列表头的字体样式
    val listItemHeaderTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 19.sp,
        color = listItemHeaderTextColor
    )
}



object BackComposeStyle {
    private val backComposeTextColor: Color = color_e45
    //列表头的字体样式
    val backComposeTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 21.sp,
        lineHeight = 24.sp,
        color = backComposeTextColor,
        letterSpacing = 0.5.sp
    )
    val riTitleBarTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        color = backComposeTextColor,
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
}

object DialogComposeStyle {
    private val dialogComposeTextColor: Color = color_333
    val dialogContentTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 21.sp,
        lineHeight = 24.sp,
        color = dialogComposeTextColor,
        letterSpacing = 0.5.sp
    )
    val dialogButtonTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 21.sp,
        lineHeight = 20.sp,
        color = color_while,
        letterSpacing = 0.5.sp
    )
}


object RetryComposeStyle {

    val retryButtonTextStyle = TextStyle(
        color = Color.White,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
    )
    val retryTitleTextStyle = TextStyle(
        color = Color(0x99282E45),
        fontSize = 16.sp,
    )

}
