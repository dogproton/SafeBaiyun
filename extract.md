## 提取方式

### Root方式

有 root 的Android 手机可以直接前往
`/data/data/com.huacheng.baiyunuser/databases/`目录
找到数据库文件 `(32位 hash).db`

### 无 Root 方式

使用手机的备份功能提取数据文件，以 MIUI 的备份举例
前往 设置->我的设备->备份与恢复->手机备份
只选中`平安回家`这个软件进行备份即可，备份完成之后用 MT 管理器打开
`/sdcard/MIUI/backup/AllBackup/时间/平安回家(com.huacheng.baiyunuser.bak)`压缩包
然后在压缩包中找到`apps/com.huacheng.baiyunuser/db/(32位 hash).db`将其解压出来。

### DB文件查看

随便找个支持查看 sqlite 数据库的软件，打开.db文件，查询t_device表，
其中
MAC_NUM 是 mac 地址
PRODUCT_KEY 就是加密key


