package com.xingchidongli.robot.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

open class NetworkStateViewModel : ViewModel() {
    private val networkMonitor = NetWorkMonitor()

    val networkStatus = networkMonitor.networkStatusFlow.stateIn(
        viewModelScope, started = SharingStarted.WhileSubscribed(5000), NetworkStatus.CONNECT
    )

}