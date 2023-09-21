package com.example.goodchoice.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object DeviceUtil {
    fun isNetworkAvailable(context: Context?): Boolean {
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

}