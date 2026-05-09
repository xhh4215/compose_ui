package com.xingchidongli.robot.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

/***
 * composed 允许你在 Modifier 中
 * 使用 remember、effect、状态
 * 并且 每个使用点都有自己的生命周期
 *  页面点击防抖工具函数
 */
@OptIn(FlowPreview::class)
fun Modifier.debouncedClickable(
    debounceTime: Long = 500L,
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {

    val scope = rememberCoroutineScope()
    val clicks = remember { MutableSharedFlow<Unit>() }

    LaunchedEffect(Unit) {
        clicks
            .debounce(debounceTime)
            .collect {
                onClick()
            }
    }

    clickable(enabled = enabled) {
        scope.launch {
            clicks.emit(Unit)
        }
    }
}

fun Modifier.safeClickable(debounceMillis: Long = 400L, action: () -> Unit): Modifier = composed {

    var lastClickTime by remember { mutableStateOf(0L) }
    clickable {
        val now = System.currentTimeMillis()
        if (now - lastClickTime >= debounceMillis) {
            lastClickTime = now
            action.invoke()
        }
    }


}

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier {
    return this.then(
        Modifier.clickable(
            indication = null,   // ❌ 不使用波纹效果
            interactionSource = remember { MutableInteractionSource() }, // ❌ 没有“按压黑影”
            onClick = onClick
        )
    )
}
//可复用的点击监听 Modifier
fun Modifier.multiClickFor(
    clickCountTarget: Int,
    intervalMillis: Long = 1_000L,
    onTriggered: () -> Unit
) = pointerInput(Unit) {
    awaitEachGesture {
        var clickCount = 0
        var lastClickTime = 0L

        while (true) {
            val down = awaitFirstDown()
            val now = System.currentTimeMillis()

            if (now - lastClickTime > intervalMillis) {
                clickCount = 0
            }

            clickCount++
            lastClickTime = now

            if (clickCount >= clickCountTarget) {
                onTriggered()
                clickCount = 0
                break
            }
        }
    }
}


fun createVerticalBrush(
    colors: List<Color>,
) = Brush.verticalGradient(colors = colors)


fun createHorizontalBrush(
    colorStops: List<Pair<Float, Color>>,
): Brush = Brush.horizontalGradient(
    colorStops = colorStops.toTypedArray()
)

fun DrawScope.drawDashedLine(
    color: Color,
    start: Offset,
    end: Offset,
    strokeWidth: Float,
    intervals: FloatArray
) {
    drawLine(
        color = color,
        start = start,
        end = end,
        strokeWidth = strokeWidth,
        pathEffect = PathEffect.dashPathEffect(intervals)
    )
}


