package cn.huacheng.safebaiyun.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.huacheng.safebaiyun.unlock.DataRepo

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-05-10
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDialog(state: MutableState<Boolean>, initData: () -> Pair<String, String>) {
    val data = remember {
        initData()
    }
    val (mac, setMac) = remember {
        mutableStateOf(data.first)
    }
    val (key, setKey) = remember {
        mutableStateOf(data.second)
    }
    ModalBottomSheet(onDismissRequest = { state.value = false }) {
        Column(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
            OutlinedTextField(modifier = modifier, value = mac, onValueChange = setMac, label = {
                Text(text = "Mac")
            })
            OutlinedTextField(modifier = modifier, value = key, onValueChange = setKey, label = {
                Text(text = "Key")
            })
            Button(modifier = Modifier.padding(8.dp), onClick = {
                DataRepo.save(mac, key)
                state.value = false
            }) {
                Text(text = "保存")
            }
        }

    }
}
