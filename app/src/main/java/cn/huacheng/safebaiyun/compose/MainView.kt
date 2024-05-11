package cn.huacheng.safebaiyun.compose

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cn.huacheng.safebaiyun.R
import cn.huacheng.safebaiyun.unlock.DataRepo
import cn.huacheng.safebaiyun.unlock.UnlockRepo
import cn.huacheng.safebaiyun.util.showToast

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-05-10
 */

@Composable
fun MainView(navController: NavHostController) {

    val context = LocalContext.current

    val hasPermission = remember {
        mutableStateOf(false)
    }

    val showEditDialog = remember {
        mutableStateOf(false)
    }

    SideEffect {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            hasPermission.value =
                context.checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
        } else {
            hasPermission.value = true
        }
    }

    Column {
        MainTopBar(onEditClick = {
            showEditDialog.value = true
        }, onHelperClick = {
            navController.navigate("helper")
        })
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            if (hasPermission.value) {
                UnlockView()
            } else {
                PermissionView(hasPermission)
            }
        }
        if (showEditDialog.value) {
            EditDialog(state = showEditDialog) {
                DataRepo.readData()
            }
        }
    }

}

@Composable
private fun UnlockView() {
    Button(onClick = {
        showToast("开始解锁门禁")
        UnlockRepo.unlock()
    }, modifier = Modifier.size(144.dp, 56.dp)) {
        Text(text = stringResource(id = R.string.unlock_door), fontSize = 18.sp)
    }
}

@Composable
private fun PermissionView(hasPermission: MutableState<Boolean>) {
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            hasPermission.value = isGranted
        }

    Button(modifier = Modifier.size(144.dp, 56.dp),
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
            }

        }) {
        Text(text = stringResource(id = R.string.request_permission), fontSize = 18.sp)

    }
}

