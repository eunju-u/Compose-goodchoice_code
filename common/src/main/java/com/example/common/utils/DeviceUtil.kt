package com.example.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceUtil @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun isNetworkAvailable(): Boolean {
        val info = getNetworkInfo(context) ?: return false
        return when {
            info.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            info.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            info.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun getNetworkInfo(context: Context?): NetworkCapabilities? =
        context?.run {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.getNetworkCapabilities(cm.activeNetwork)
        }

    fun isWifi(context: Context?): Boolean =
        context?.run {
            getNetworkInfo(this)?.run {
                // WIFI 를 사용하는지 확인한다.
                this.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false
        } ?: false

}