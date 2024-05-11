package cn.huacheng.safebaiyun.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cn.huacheng.safebaiyun.unlock.UnlockRepo

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-05-10
 */


@Composable
fun LogView() {
    val logList by UnlockRepo.logFlow.collectAsState()
    LazyColumn {
        items(logList, contentType = { 0 }) {
            Text(it)
        }
    }
}
