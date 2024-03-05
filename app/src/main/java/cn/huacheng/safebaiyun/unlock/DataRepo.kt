package cn.huacheng.safebaiyun.unlock

import android.content.Context
import cn.huacheng.safebaiyun.util.ContextHolder

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-03-04
 */
object DataRepo {

    fun readData(): Pair<String, String> {
        val share = ContextHolder.get().getSharedPreferences("data", Context.MODE_PRIVATE)
        val mac = share.getString("mac", "") ?: ""
        val key = share.getString("key", "") ?: ""


        return mac to key
    }

    fun save(mac:String,key:String){
        val editor = ContextHolder.get().getSharedPreferences("data",Context.MODE_PRIVATE).edit()
        editor.putString("mac",mac)
        editor.putString("key",key)
        editor.apply()
    }
}
