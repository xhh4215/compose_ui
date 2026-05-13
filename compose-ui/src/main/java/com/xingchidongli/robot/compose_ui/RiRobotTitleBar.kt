package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.FlowStyle
import com.xingchidongli.robot.theme.BackComposeStyle
import com.xingchidongli.robot.theme.BackComposeStyle.riTitleBarTextStyle
import com.xingchidongli.robot.theme.color_5aa
import com.xingchidongli.robot.theme.color_e45
import com.xingchidongli.robot.utils.noRippleClickable

object RiRobotTitleBarFactory {
    @Composable
    fun commonBackCompose(
        iconText: String,
        @DrawableRes iconRes: Int,
        iconColor: Color,
        titleTextStyle: TextStyle,
        backAction: () -> Unit
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier
                    .height(17.dp)
                    .wrapContentWidth(Alignment.Start)
                    .noRippleClickable(onClick = { backAction.invoke() }),
                tint = iconColor,
                painter = painterResource(iconRes),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                iconText,
                style = titleTextStyle,
                modifier = Modifier.noRippleClickable({ backAction.invoke() }),
                color = iconColor
            )
        }

    }

    @Composable
    fun commonContentCompose(text: String, style: TextStyle = riTitleBarTextStyle) {
        Text(text, style = style)
    }

    @Composable
    fun commonTitleBar(
        iconRes: Int = R.drawable.ic_back,
        iconText: String = stringResource(R.string.string_back),
        iconColor: Color,
        iconTextStyle: TextStyle = BackComposeStyle.backComposeTextStyle,
        titleBarHeight: Dp = 50.dp,
        startPadding: Dp = 30.dp,
        endPadding: Dp = 30.dp,
        title: String,
        titleBarColor: Color = Color.Transparent,
        titleTextStyle: TextStyle = BackComposeStyle.backComposeTextStyle,
        backAction: () -> Unit,

        ) {
        RiRobotComposeBar(
            startPadding = startPadding,
            endPadding = endPadding,
            showAction = false,
            modifier = Modifier
                .background(titleBarColor)
                .height(titleBarHeight)
                .fillMaxWidth(),
            contentCompose = {
                commonContentCompose(title, titleTextStyle)
            },
            backCompose = {
                commonBackCompose(
                    iconRes = iconRes,
                    iconColor = iconColor,
                    iconText = iconText,
                    titleTextStyle = iconTextStyle,
                    backAction = backAction,
                )
            }
        )
    }

    @Composable
    fun actionTitleBar(
        backAction: () -> Unit,
        text: String,
        titleBarHeight: Dp = 50.dp,
        startPadding: Dp = 30.dp,
        endPadding: Dp = 30.dp,
        action: @Composable () -> Unit
    ) {
        RiRobotComposeBar(
            showAction = true,
            startPadding = startPadding,
            endPadding = endPadding,
            modifier = Modifier
                .background(Color.Transparent)
                .height(titleBarHeight)
                .fillMaxWidth(),
            contentCompose = {
                commonContentCompose(text)

            },
            backCompose = {
                commonBackCompose(
                    iconRes = R.drawable.ic_back,
                    iconColor = color_5aa,
                    iconText = stringResource(R.string.string_back),
                    titleTextStyle = BackComposeStyle.backComposeTextStyle,
                    backAction = backAction,
                )
            },
            actionCompose = {
                action()
            }
        )
    }

    @Composable
    fun actionContentBar(
        showAction: Boolean,
        backAction: () -> Unit,
        content: @Composable () -> Unit,
        action: @Composable () -> Unit = {}
    ) {
        RiRobotComposeBar(
            showAction = showAction,
            modifier = Modifier
                .background(Color.Transparent)
                .wrapContentHeight()
                .fillMaxWidth(),
            contentCompose = {
                content.invoke()
            },
            backCompose = {
                commonBackCompose(
                    iconRes = R.drawable.ic_back,
                    iconColor = color_5aa,
                    iconText = stringResource(R.string.string_back),
                    titleTextStyle = BackComposeStyle.backComposeTextStyle,
                    backAction = backAction,
                )
            },
            actionCompose = {
                action()
            }
        )
    }
}


/***
 * 标题中中间区域可以自定义
 */
@Composable
fun RiRobotComposeBar(
    showAction: Boolean,
    modifier: Modifier = Modifier,
    startPadding: Dp = 30.dp,
    endPadding: Dp = 30.dp,
    contentCompose: @Composable BoxScope.() -> Unit,
    actionCompose: @Composable BoxScope.() -> Unit = {},
    backCompose: @Composable BoxScope.() -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (refOne, refTwo, refThree) = createRefs()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.constrainAs(refOne) {
                start.linkTo(parent.start, startPadding)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }) {
            backCompose()

        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.constrainAs(refThree) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                // 2. 关键：将宽度设置为 fillToConstraints (相当于 0dp)
                // 这样它会拉伸以匹配 start 和 end 的约束，从而实现真正居中
                width = Dimension.fillToConstraints
            }) {
            contentCompose()
        }
        if (showAction) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.constrainAs(refTwo) {
                    end.linkTo(parent.end, endPadding)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)

                }) {
                actionCompose()
            }
        }


    }
}


