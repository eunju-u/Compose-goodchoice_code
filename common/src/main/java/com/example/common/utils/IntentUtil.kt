package com.example.common.utils

import android.content.Intent
import android.os.Build
import java.io.Serializable

object IntentUtil {

    // API 33 이후 getSerializable()가 deprecated 되어서 만든 함수
    fun <T: Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }
}