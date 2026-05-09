package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HealthStartMeasureLayoutSkeleton(
    blueIsConnect: Boolean,
    list: List<Triple<String, Int, () -> Unit>>,
    @DrawableRes enableRes: Int,
    @DrawableRes completeRes: Int,
    leftCompose: @Composable () -> Unit,
    rightCompose: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()

        ) {
            if (list.isEmpty()) {
                Spacer(modifier = Modifier.size(20.dp))
            } else {
                list.forEachIndexed { index, element ->
                    if (index == 0) {
                        DeviceConnectStatusChipComponent(
                            isEnable = blueIsConnect,
                            enableImgRes = enableRes,
                            disEnableRes = element.second,
                            trailingRes = completeRes,
                            onClickAction = element.third,
                            contentText = element.first,
                        )
                    } else {
                        DeviceConnectCommonChipComponent(
                            onClickAction = element.third,
                            contentText = element.first,
                            imgRes = element.second
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.size(10.dp))

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(372.dp)
            ) {
                leftCompose.invoke()
                Spacer(modifier = Modifier.size(21.dp))
                rightCompose.invoke()

            }
        }


    }

}
