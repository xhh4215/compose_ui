package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun HealthLoadEmptyComponent(
    tip: String = "暂无数据",
    @DrawableRes res: Int,
    containerColor: Color = Color.Transparent,
    iconSize: Dp = 210.dp,
    textStyle: TextStyle
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ConstraintLayout(
            modifier = Modifier
                .size(210.dp)
                .background(color = containerColor)
        ) {

            val (refOne, refTwo) = createRefs()

            Image(
                modifier = Modifier
                    .width(iconSize)
                    .height(iconSize-30.dp)
                    .constrainAs(refOne) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                painter = painterResource(id = res),
                contentDescription = null
            )

            Text(
                tip,
                maxLines = 2,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(width = 178.dp)
                    .constrainAs(refTwo) {
                        start.linkTo(refOne.start)
                        end.linkTo(refOne.end)
                        bottom.linkTo(parent.bottom, (20).dp)
                    },
                style = textStyle,

                )
        }
    }
}

