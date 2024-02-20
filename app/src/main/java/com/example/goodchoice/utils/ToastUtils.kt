package com.example.goodchoice.utils

import android.widget.Toast
import com.example.goodchoice.GoodChoiceApplication

object ToastUtils {
    private var toast: Toast? = null

    fun showToast(str: String) {
        toast?.cancel()
        if (toast == null) {
            toast = Toast.makeText(
                GoodChoiceApplication.instance, str, Toast.LENGTH_LONG
            )
        } else {
            toast?.setText(str)
        }
        toast?.show()
    }

    fun cancelToast() {
        toast?.cancel()
    }
}