package cn.huacheng.safebaiyun.unlock

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import cn.huacheng.safebaiyun.util.ContextHolder

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-03-04
 */
object DataRepo {

    private val preferences: SharedPreferences by lazy {
        ContextHolder.get().getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    fun readData(): Pair<String, String> {
        val mac = preferences.getString("mac", "") ?: ""
        val key = preferences.getString("key", "") ?: ""


        return mac to key
    }

    fun save(mac: String, key: String) {
        preferences.edit {
            putString("mac", mac)
            putString("key", key)
        }
    }
}
