package cn.huacheng.safebaiyun.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cn.huacheng.safebaiyun.widget.WidgetHelper

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-05-10
 */
@Composable
fun HelpView(navController: NavController) {
    Column {
        HelperTopBar(navController)

        LazyColumn(modifier = Modifier.padding(12.dp)) {
            item { AppHelper() }
            item { ShortcutHelper() }
            item { WidgetHelper() }
        }
    }
}

@Composable
fun AppHelper() {
    Text(text = "关于软件", style = MaterialTheme.typography.titleLarge)
    Text(text = "本软件是广州市白云区蓝牙门禁的离线版本，只需要门禁的mac地址以及加密key即可开门，无需网络。")
    Text(
        text = "使用方法",
        modifier = Modifier.padding(top = 8.dp),
        style = MaterialTheme.typography.titleLarge
    )
    Text(text = "1. 提取MAC地址及加密密钥")
    Text(text = "1.1 Root方式提取")
    Text(text = "有 root 的Android 手机可以直接前往 /data/data/com.huacheng.baiyunuser/databases/目录 找到数据库文件 (32位 hash).db")
    Text(text = "1.2 无Root方式提取")
    Text(text = "使用MIUI的备份功能提取数据文件，前往 设置->我的设备->备份与恢复->手机备份 只选中平安回家这个软件进行备份即可，备份完成之后用 MT 管理器打开 /sdcard/MIUI/backup/AllBackup/时间/平安回家(com.huacheng.baiyunuser.bak)压缩包 然后在压缩包中找到apps/com.huacheng.baiyunuser/db/(32位 hash).db将其解压出来。")
    Text(text = "1.3 查看DB文件")
    Text(text = "随便找个支持查看 sqlite 数据库的软件，打开.db文件，查询t_device表， 其中 MAC_NUM 是 mac 地址 PRODUCT_KEY 就是加密key")
    Text(text = "2. 点击软件右上角编辑按钮，将Mac地址及Key填进去，保存")
    Text(text = "3. 开门", fontWeight = FontWeight.Medium)
}


@Composable
private fun ShortcutHelper() {
    Text(
        "快捷方式",
        modifier = Modifier.padding(top = 8.dp),
        style = MaterialTheme.typography.titleLarge
    )
    Text(text = "快捷方式在桌面上跟普通App长的差不多，使用快捷方式开门只需点击图标即可。在大部分国产系统如OPPO,MIUI上需要手动授予“创建桌面快捷方式”权限。")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            WidgetHelper.createShortcut()
        }) {
            Text(text = "创建快捷方式")
        }
        Button(onClick = {
            WidgetHelper.requestPermission()
        }) {
            Text(text = "授予权限")
        }
    }
}


@Composable
private fun WidgetHelper() {
    Text(
        text = "桌面小部件",
        modifier = Modifier.padding(top = 8.dp),
        style = MaterialTheme.typography.titleLarge
    )
    Text(text = "桌面小部件类似于快捷方式，但是可以有更多的样式。本App提供了大中三种样式。可以在桌面长按空白处，然后选择“添加小部件”")
    Text(text = "在低于Android12的手机上小部件不会正常显示，建议不要使用。")

}

@Preview
@Composable
private fun WidgetPreview() {
    HelpView(navController = rememberNavController())
}
