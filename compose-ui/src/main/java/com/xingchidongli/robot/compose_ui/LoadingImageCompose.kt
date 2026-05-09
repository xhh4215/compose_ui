package com.xingchidongli.robot.compose_ui

import android.os.Build
import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide


@Composable
fun GifFileImage(
    gifName: String,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current

    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

    }
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data("file:///android_asset/${gifName}")
            .build(),
        contentDescription = "GIF from assets",
        imageLoader = imageLoader,
        modifier = modifier
    )
}

@Composable
fun GifAssetImage(modifier: Modifier = Modifier) {

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            ImageView(ctx).apply {
                scaleType = ImageView.ScaleType.FIT_CENTER

                // 使用 Glide 加载 asset 中的 GIF
                Glide.with(this)
                    .asGif()
                    .load("file:///android_asset/my_gif.gif")
                    .into(this)
            }
        }
    )
}


/**
 * 基础网络图片加载组件
 * @param imageUrl 网络图片URL
 */
@Composable
fun BasicNetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    clickAction: () -> Unit = {}
) {
    AsyncImage(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                clickAction.invoke()
            },
        model = imageUrl, // 图片URL（支持网络、本地文件等）
        contentDescription = "网络图片", // 无障碍描述（必填）
        contentScale = contentScale, // 缩放模式（类似ImageView的scaleType）
    )
}

/**
 * 基础网络图片加载组件
 * @param imageUrl 网络图片URL
 */
@Composable
fun BasicFullNetworkImage(imageUrl: String, clickAction: () -> Unit) {
    AsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                clickAction.invoke()
            },
        model = imageUrl, // 图片URL（支持网络、本地文件等）
        contentDescription = "网络图片", // 无障碍描述（必填）
        contentScale = ContentScale.Fit, // 缩放模式（类似ImageView的scaleType）
    )
}

