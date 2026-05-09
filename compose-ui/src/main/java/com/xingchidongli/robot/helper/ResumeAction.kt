package com.xingchidongli.robot.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

/***
 * compose 中监听 onresume
 */
@Composable
fun OnResumeEffect(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current, onResume: () -> Unit
) {
    // 监听 Lifecycle 变化
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                onResume()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        // 清理资源
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}





