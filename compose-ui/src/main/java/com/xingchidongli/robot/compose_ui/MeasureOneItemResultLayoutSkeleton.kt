package com.xingchidongli.robot.compose_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MeasureOneItemResultLayoutSkeleton(
    userInfoComponent: @Composable () -> Unit,
    resultComponent: @Composable () -> Unit = {},
    suggestComponent: @Composable () -> Unit = {},
    tipComponent: @Composable () -> Unit = {},
) {
    Row {
        Column(modifier = Modifier.weight(6f)) {
            userInfoComponent()
            Spacer(modifier = Modifier.size(15.dp))
            Box(modifier = Modifier.weight(1f)) {
                resultComponent()
            }
            Spacer(modifier = Modifier.size(10.dp))
            tipComponent()
        }
        Box(
            modifier = Modifier
                .weight(4f)
        ) {
            suggestComponent.invoke()

        }
    }
}



