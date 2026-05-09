package com.xingchidongli.robot.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xingchidongli.robot.data.ChooseMonthStateData
import com.xingchidongli.robot.data.MonthType
import com.xingchidongli.robot.utils.noRippleClickable

@Composable
fun ScrollerChooseMonthComponent(
    targetIndex: Int,
    currentYear: Int,
    yearTextColor: Color,
    changeYearIcon: Int,
    activeColor: Color,
    inActiveColor: Color,
    chooseColor: Color,
    availableMonth: List<ChooseMonthStateData>,
    containerColor: Color = Color.White,
    onItemClickAction: (currentMonth: Int) -> Unit,
    onChooseYearClickAction: () -> Unit,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(availableMonth, targetIndex) {
        // 只有当目标月份 > 9 时才滚动
        if (targetIndex >= 8) { // index 从 0 开始，第 9 个是 index = 8
            listState.scrollToItem(targetIndex)
        }
    }

    Card(
        colors = CardDefaults.cardColors(contentColor = containerColor, containerColor = containerColor),
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            LazyRow(
                state = listState,
                modifier = Modifier
                    .width(90.dp * 9)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                itemsIndexed(availableMonth) { _, monthReport ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width(90.dp)
                            .height(36.dp)
                            .padding(horizontal = 10.dp)
                            .background(
                                if (monthReport.lsSelect) chooseColor.copy(0.44f) else Color.Transparent,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .noRippleClickable {
                                onItemClickAction.invoke(monthReport.month)
                            }) {
                        Text(
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(width = 90.dp),
                            overflow = TextOverflow.Ellipsis,
                            text = "${monthReport.month}月",
                            color = if (monthReport.type == MonthType.current || monthReport.lsSelect || monthReport.type == MonthType.available_current) activeColor else inActiveColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                        )
                        Spacer(modifier = Modifier.size(3.dp))
                        if (monthReport.type == MonthType.available || monthReport.type == MonthType.available_current) {
                            Box(
                                modifier = Modifier
                                    .size(3.dp)
                                    .background(activeColor, shape = RoundedCornerShape(1.5.dp))
                            )
                        }
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "${currentYear}年",
                    modifier = Modifier
                        .wrapContentSize()
                        .noRippleClickable { onChooseYearClickAction.invoke() },
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = yearTextColor
                )
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(changeYearIcon),
                    contentDescription = null
                )
            }
        }


    }
}