package com.xingchidongli.robot.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


enum class NetworkStatus {
    CONNECT, DISCONNECT
}

class NetWorkMonitor {
    val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
//        val connectivityManger =
//            globalContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.CONNECT)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.DISCONNECT)
            }
        }

//        val initialStatus = getCurrentNetworkStatus(connectivityManger)
//        trySend(initialStatus)
//
//        connectivityManger.registerNetworkCallback(networkRequest, callback)
//
//        awaitClose {
//            connectivityManger.unregisterNetworkCallback(callback)
//        }
    }


//    private fun getCurrentNetworkStatus(connectivityManager: ConnectivityManager): NetworkStatus {
//        val activeNetwork = connectivityManager.activeNetwork ?: return NetworkStatus.DISCONNECT
//        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
//            ?: return NetworkStatus.DISCONNECT
//        return if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
//            NetworkStatus.CONNECT
//        } else {
//            NetworkStatus.DISCONNECT
//        }
//    }
}





