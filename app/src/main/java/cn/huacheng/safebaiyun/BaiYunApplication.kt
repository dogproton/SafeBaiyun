package cn.huacheng.safebaiyun

import android.app.Application
import cn.huacheng.safebaiyun.util.ContextHolder

class BaiYunApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        ContextHolder.init(this)
    }
}
