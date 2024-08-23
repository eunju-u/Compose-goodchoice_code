package com.example.data.local.preference

import android.content.Context

abstract class DefaultPreference(context: Context) {

    private val GOOD_CHOICE_PREF_NAME = "goodchoicePreference"

    private val sharedPreference =
        context.getSharedPreferences(GOOD_CHOICE_PREF_NAME, Context.MODE_PRIVATE)

    fun setPreference(key: String?, value: Any?) {
        if (key == null || value == null) {
            return
        }
        try {
            val editor = sharedPreference.edit()

            when (value) {
                is Int -> editor.putInt(key, value)
                is String -> editor.putString(key, value.trim())
                is Boolean -> editor.putBoolean(key, value)
                is Long -> editor.putLong(key, value)
                else -> return
            }
            editor.commit()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun getPreferenceBoolean(key: String = "", defaultValue: Boolean = false): Boolean {
        return if (key.isNotEmpty()) {
            sharedPreference.getBoolean(key, defaultValue)
        } else false
    }

    fun getPreferenceString(key: String = "", defaultValue: String = ""): String? {
        return if (key.isNotEmpty()) {
            sharedPreference.getString(key, defaultValue)?.run {
                this.trim { it <= ' ' }
            } ?: sharedPreference.getString(key, "")
        } else {
            " "
        }
    }

    fun getPreferenceInt(key: String = "", defaultValue: Int = 0): Int {
        return if (key.isNotEmpty()) {
            sharedPreference.getInt(key, defaultValue)
        } else {
            0
        }
    }

    fun remove(key: String?) {
        key?.run {
            val editor = sharedPreference.edit()
            editor.remove(key)
            editor.commit()
        }
    }
}