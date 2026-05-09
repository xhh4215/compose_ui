package com.xingchidongli.robot.compose_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min


/***
 * 普通的页面架构组件
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthCommonPageScaffold(
    titleBar: @Composable () -> Unit,
    @DrawableRes contentBgResId: Int,
    paddingTop: Int = 10,
    padding: Int = 30,
    paddingBottom: Int = 30,
    containerBg: Color = Color.Transparent,
    isRequireBg: Boolean = false,
    content: @Composable () -> Unit,
) {

    Scaffold(
        topBar = titleBar,
        modifier = Modifier.statusBarsPadding(),
        containerColor = containerBg,
        contentWindowInsets = WindowInsets.systemBars

    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .paint(painter = if (isRequireBg) painterResource(contentBgResId) else ColorPainter(Color.Transparent))
                .padding(top = paddingTop.dp, start = padding.dp, end = padding.dp, bottom = paddingBottom.dp)
        ) {
            content()
        }
    }
}

/***
 * 可以自定义中间标题区域内容的页面骨架
 */
@Composable
fun HealthAutoCenterPageScaffold(
    @DrawableRes contentBgResId: Int,
    screenCompose: @Composable () -> Unit,
    titleBar: @Composable () -> Unit
) {

    val bgRes = ImageBitmap.imageResource(contentBgResId)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .height(56.dp)
    ) {
        titleBar()
        Spacer(modifier = Modifier.size(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawImage(
                        bgRes, dstSize = IntSize(
                            width = size.width.toInt(),
                            height = size.height.toInt()
                        )
                    )

                }
                .padding(start = 15.dp, end = 15.dp)
                .weight(1f)
        ) {
            screenCompose()
        }
    }
}

/***
 * 内容在标题之下内容居中
 */
@Composable
fun HealthCenterPageScaffold(
    titleBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
    containerBg: Color = Color.Transparent,
) {
    Scaffold(
        topBar = {
            titleBar()
        },
        modifier = Modifier.statusBarsPadding(),
        containerColor = containerBg,
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content()
        }
    }
}





