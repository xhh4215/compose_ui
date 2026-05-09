package com.xingchidongli.robot.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
  import com.xingchidongli.robot.network.NetworkStateViewModel
import com.xingchidongli.robot.network.NetworkStatus

/***
 * @author 栾桂明
 * @desc 网络状态监听组件
 */
@Composable
fun NetWorkStatusMonitorComponent(
    viewModel: NetworkStateViewModel = viewModel(),
    onNetworkConnectedAction: @Composable () -> Unit,
    onNetworkDisconnectedAction: @Composable () -> Unit,
) {
    var currentNetworkStatus by remember {
        mutableStateOf(NetworkStatus.CONNECT)
    }
    val networkStatus = viewModel.networkStatus.collectAsStateWithLifecycle()
    currentNetworkStatus = if (networkStatus.value == NetworkStatus.CONNECT) {
        NetworkStatus.CONNECT
    } else {
        NetworkStatus.DISCONNECT
    }
    if (currentNetworkStatus == NetworkStatus.CONNECT) {
        onNetworkConnectedAction()
    } else {
        onNetworkDisconnectedAction()
    }
}