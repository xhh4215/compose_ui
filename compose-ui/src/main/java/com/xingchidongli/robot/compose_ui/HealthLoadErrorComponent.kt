package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xingchidongli.robot.theme.RetryComposeStyle.retryButtonTextStyle
import com.xingchidongli.robot.theme.RetryComposeStyle.retryTitleTextStyle
import com.xingchidongli.robot.utils.noRippleClickable

@Composable
fun HealthLoadErrorComponent(
    errorMessage: String,
    errorTextStyle: TextStyle = retryTitleTextStyle,
    retryTextStyle: TextStyle = retryButtonTextStyle,
    retryText: String = "重新加载",
    retryButtonWidth: Dp = 210.dp,
    retryButtonHeight: Dp = 45.dp,
    retryButtonColor: Color,
    retryButtonCornerRadius: Dp = 32.dp,
    @DrawableRes bgRes: Int,
    @DrawableRes iconRes: Int,
    retryAction: (() -> Unit) = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .noRippleClickable {},// 拦截点击,
        contentAlignment = Alignment.Center
    ) {
        // 背景图
        Image(
            painter = painterResource(bgRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(210.dp)) {
                Image(
                    modifier = Modifier
                        .width(210.dp)
                        .height(180.dp),
                    painter = painterResource(iconRes),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    style = errorTextStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(178.dp)

                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Box(
                modifier = Modifier
                    .width(retryButtonWidth)
                    .height(retryButtonHeight)
                    .background(
                        color = retryButtonColor, shape = RoundedCornerShape(retryButtonCornerRadius)
                    )
                    .clickable {
                        retryAction()
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = retryText,
                    textAlign = TextAlign.Center,
                    style = retryTextStyle,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
        }
    }
}

