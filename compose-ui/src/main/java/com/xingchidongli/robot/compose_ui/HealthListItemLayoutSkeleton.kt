package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.xingchidongli.robot.theme.color_5aa
import com.xingchidongli.robot.theme.color_e45
import com.xingchidongli.robot.utils.safeClickable


/***
 * 健康App内部使用的列表的item的布局
 */
@Composable
fun <T> HealthListItemLayoutSkeleton(
    @DrawableRes itemTopIcon: Int,
    @DrawableRes itemBgRes: Int,
    @DrawableRes itemToRes: Int,
    itemInfo: String,
    itemDate: String,
    itemTip: String,
    itemParamData: T,
    clickAction: (title: String, url: T) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .width(290.dp)
            .height(82.dp)
            .background(color = Color.Transparent, shape = RoundedCornerShape(15.dp))
            .safeClickable {
                clickAction.invoke(itemInfo, itemParamData)
            }) {

        val (refOne, refTwo, refThree, refFour, refFive) = createRefs()
        Image(
            painter = painterResource(itemBgRes), modifier = Modifier
                .size(
                    width = 290.dp,
                    height = 82.dp
                )
                .constrainAs(refOne) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }, contentDescription = null
        )

        Image(
            painterResource(itemTopIcon),
            modifier = Modifier
                .size(51.dp)
                .constrainAs(refTwo) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            contentDescription = null
        )

        Text(
            text = itemInfo.safeTitleText,
            fontWeight = FontWeight.W600,
            maxLines = 1,
            modifier = Modifier.constrainAs(refThree) {
                top.linkTo(refTwo.top)
                start.linkTo(refTwo.end, 10.dp)
            },
            fontSize = 19.sp,
            color = color_e45
        )

        Text(
            text = "${itemTip}${itemDate}",
            modifier = Modifier.constrainAs(refFour) {
                bottom.linkTo(refTwo.bottom)
                start.linkTo(refTwo.end, 10.dp)
            },
            fontWeight = FontWeight.W400,
            color = color_e45,
            fontSize = 14.sp
        )

        Icon(
            tint = color_5aa,
            painter = painterResource(itemToRes),
            contentDescription = null,
            modifier = Modifier
                .rotate(180f)
                .size(width = 30.dp, height = 16.dp)
                .constrainAs(refFive) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, 10.dp)
                }
        )
    }
}


/***
 * 返回前7个字符
 */
val String.safeTitleText: String
    get() = try {
        if (this.length > 7) {
            this.take(7) + "..."
        } else {
            this

        }
    } catch (e: java.lang.Exception) {
        this
    }
