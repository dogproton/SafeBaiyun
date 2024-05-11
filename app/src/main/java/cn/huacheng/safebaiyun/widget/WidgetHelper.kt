package cn.huacheng.safebaiyun.widget

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import cn.huacheng.safebaiyun.R
import cn.huacheng.safebaiyun.ShortcutActivity
import cn.huacheng.safebaiyun.util.ContextHolder


/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-05-10
 */
object WidgetHelper {

    fun requestPermission() {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", ContextHolder.get().packageName, null)
        intent.setData(uri)
        ContextHolder.get().startActivity(intent)
    }

    fun createShortcut() {
        val context = ContextHolder.get()
        val intent = Intent(context, ShortcutActivity::class.java)
        intent.setAction(Intent.ACTION_VIEW)
        val shortcut = ShortcutInfoCompat.Builder(context, "Unlock").setIcon(
            IconCompat.createWithResource(context, R.mipmap.ic_launcher)
        ).setShortLabel("开门").setLongLabel("解锁门禁").setIntent(intent).build()
        ShortcutManagerCompat.requestPinShortcut(context, shortcut, null)
    }

}
