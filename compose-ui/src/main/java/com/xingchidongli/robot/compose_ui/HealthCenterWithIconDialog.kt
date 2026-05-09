package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.xingchidongli.robot.utils.noRippleClickable

@Composable
fun HealthCenterWithIconDialog(
    @DrawableRes titleIcon: Int,
    @DrawableRes closeIcon: Int,
    closeIconColor: Color,
    textStyle: TextStyle,
    buttonContainerColor: Color,
    buttonTextStyle: TextStyle,
    buttonText: String,
    desc: String,
    readCharAction: () -> Unit,
    onDismissRequest: () -> Unit = {}
) {

    var currentUiState by remember { mutableStateOf(true) }
    if (currentUiState) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                modifier = Modifier
                    .width(700.dp)
                    .height(331.dp),
                shape = RoundedCornerShape(13.dp),
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .width(700.dp)
                        .height(331.dp)
                ) {
                    val (refone, reftwo, refthree, reffour) = createRefs()
                    Image(
                        painter = painterResource(titleIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(90.dp)
                            .constrainAs(refone) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top, 20.dp)
                                end.linkTo(parent.end)
                            }

                    )

                    Icon(
                        tint = closeIconColor,
                        painter = painterResource(closeIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .noRippleClickable(onDismissRequest)
                            .constrainAs(reftwo) {
                                top.linkTo(parent.top, 22.dp)
                                end.linkTo(parent.end, 22.dp)
                            }

                    )


                    Text(
                        text = desc,
                        style = textStyle,
                        modifier = Modifier
                            .constrainAs(refthree) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(refone.bottom, 20.dp)
                            }
                            .wrapContentSize(Alignment.Center)
                            .padding(start = 35.dp, end = 30.dp),
                        textAlign = TextAlign.Start,
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(reffour) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(refthree.bottom, 30.dp)
                            }, contentAlignment = Alignment.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                contentColor = buttonContainerColor,
                                containerColor = buttonContainerColor
                            ),
                            onClick = {
                                currentUiState = false
                                readCharAction()
                            },
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
                    }
                }


            }
        }
    }

}






