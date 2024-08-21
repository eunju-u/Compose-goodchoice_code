package com.example.common.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    private var toast: Toast? = null

    fun showToast(context: Context, str: String) {
        toast?.cancel()
        if (toast == null) {
            toast = Toast.makeText(
                context, str, Toast.LENGTH_LONG
            )
        } else {
            toast?.setText(str)
        }
        toast?.show()
    }

    fun showToast(context: Context, resource: Int) {
        toast?.cancel()
        if (toast == null) {
            toast = Toast.makeText(
                context,
                context.getText(resource),
                Toast.LENGTH_LONG
            )
        } else {
            toast?.setText(context.getText(resource))
        }
        toast?.show()
    }

    fun cancelToast() {
        toast?.cancel()
    }
}