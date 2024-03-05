package cn.huacheng.safebaiyun.util

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ContextHolder {

    lateinit var context: Context

    fun init(context: Context){
        ContextHolder.context = context
    }

    fun get() = context
}
