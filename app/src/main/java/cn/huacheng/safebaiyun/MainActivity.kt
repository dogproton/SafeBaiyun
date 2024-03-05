package cn.huacheng.safebaiyun

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.huacheng.safebaiyun.theme.SafeBaiyunTheme
import cn.huacheng.safebaiyun.unlock.DataRepo
import cn.huacheng.safebaiyun.unlock.UnlockRepo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeBaiyunTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content() {

    val context = LocalContext.current

    val hasPermission = remember {
        mutableStateOf(false)
    }

    val showEditDialog = remember {
        mutableStateOf(false)
    }

    SideEffect {
        hasPermission.value = context.checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
    }

    Column {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ), title = { Text(text = stringResource(id = R.string.app_name)) }, actions = {
            IconButton(onClick = { showEditDialog.value = true }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
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
//        LogView()
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
        UnlockRepo.unlock()
    }, modifier = Modifier.size(144.dp, 56.dp)) {
        Text(text = "门禁解锁", fontSize = 18.sp)
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
            requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)

        }) {
        Text(text = "申请权限", fontSize = 18.sp)

    }
}

@Composable
private fun LogView() {
    val logList by UnlockRepo.logFlow.collectAsState()
    LazyColumn {
        items(logList, contentType = { 0 }) {
            Text(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditDialog(state: MutableState<Boolean>, initData: () -> Pair<String, String>) {
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

@Composable
@Preview
fun MainPreview() {
    Content()
}
