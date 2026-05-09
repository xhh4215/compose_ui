package com.xingchidongli.robot.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/***
 * 全局使用的弹出的对话框
 */
@Composable
fun HealthCenterDialog(
    title: String,
    desc: String,
    buttonContainerColor: Color,
    buttonTextStyle: TextStyle,
    buttonText: String,
    itemTextStyle: TextStyle,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(

            modifier = Modifier
                .width(700.dp)
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(15.dp),

            ) {
            Column(
                modifier = Modifier
                    .width(700.dp)
                    .wrapContentHeight()
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = title,
                    style = itemTextStyle,
                    maxLines = 1,
                    modifier = Modifier.wrapContentSize()
                )
                Spacer(modifier = Modifier.size(30.dp))
                Text(
                    text = desc,
                    style = itemTextStyle,
                    modifier = Modifier
                        .height(130.dp)
                        .padding(start = 30.dp, end = 30.dp)
                        .verticalScroll(rememberScrollState()),
                    textAlign = TextAlign.Start,
                )
                Spacer(modifier = Modifier.size(30.dp))
                Button(
                    onClick = onDismissRequest,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = buttonContainerColor,
                        containerColor = buttonContainerColor
                    )
                ) {
                    Text(
                        text = buttonText,
                        textAlign = TextAlign.Center,
                        style = buttonTextStyle,
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(210.dp)
                    )
                }
                Spacer(modifier = Modifier.size(25.dp))
            }


        }
    }
}




