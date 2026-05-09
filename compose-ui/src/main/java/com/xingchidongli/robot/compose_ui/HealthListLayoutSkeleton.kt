package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlin.collections.forEach


@Composable
fun <T> HealthListLayoutSkeleton(
    tipTimeText: String,
    listData: List<Triple<String, String, T>>,
    headerTip: @Composable () -> Unit,
    itemCompose: @Composable (
        itemInfo: String,
        itemDate: String,
        tipTimeText: String,
        data: T,
        clickAction: (
            title: String, data: T
        ) -> Unit
    ) -> Unit,
    clickAction: (
        title: String, data: T
    ) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        headerTip()
        FlowRow(
            maxItemsInEachRow = 3,
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listData.forEach { element ->
                itemCompose(
                    element.first, element.second, tipTimeText,
                    element.third,
                    clickAction
                )
            }


        }
    }


}



