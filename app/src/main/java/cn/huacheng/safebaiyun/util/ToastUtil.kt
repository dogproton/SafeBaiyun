package cn.huacheng.safebaiyun.util

import android.os.Looper
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.SoftReference

/**
 *
 *@description: toast 工具
 *@author: guangzhou
 *@create: 2024-05-06
 */

var toast: SoftReference<Toast>? = null

fun showToast(msg: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        realShow(msg)
    } else {
        GlobalScope.launch(Dispatchers.Main) {
            realShow(msg)
        }
    }
}

private fun realShow(msg: String) {
    toast?.get()?.cancel()
    val toastImpl = Toast.makeText(ContextHolder.get(), msg, Toast.LENGTH_SHORT)
    toastImpl.show()
    toast = SoftReference(toastImpl)
}

