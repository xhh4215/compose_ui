package com.xingchidongli.robot.compose_ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp


// 定义边框方向枚举
enum class BorderDirection {
    Top, Bottom, Left, Right
}

// 自定义 Modifier 扩展函数
fun Modifier.border(
    width: Dp, color: Color, direction: BorderDirection
) = drawWithContent {
    drawContent() // 先绘制内容

    // 根据方向绘制边框
    when (direction) {
        BorderDirection.Top -> drawTopBorder(width, color)
        BorderDirection.Bottom -> drawBottomBorder(width, color)
        BorderDirection.Left -> drawLeftBorder(width, color)
        BorderDirection.Right -> drawRightBorder(width, color)
    }
}

// 绘制各方向边框的扩展函数
private fun DrawScope.drawTopBorder(width: Dp, color: Color) {
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(size.width, 0f),
        strokeWidth = width.toPx()
    )
}

private fun DrawScope.drawBottomBorder(width: Dp, color: Color) {
    drawLine(
        color = color,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = width.toPx()
    )
}

private fun DrawScope.drawLeftBorder(width: Dp, color: Color) {
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(0f, size.height),
        strokeWidth = width.toPx()
    )
}

private fun DrawScope.drawRightBorder(width: Dp, color: Color) {
    drawLine(
        color = color,
        start = Offset(size.width, 0f),
        end = Offset(size.width, size.height),
        strokeWidth = width.toPx()
    )
}