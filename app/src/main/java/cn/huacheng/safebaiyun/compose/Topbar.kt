package cn.huacheng.safebaiyun.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import cn.huacheng.safebaiyun.R

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-05-10
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(onEditClick: () -> Unit, onHelperClick: () -> Unit) {

    val showMenu = remember {
        mutableStateOf(false)
    }
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary
    ), title = { Text(text = stringResource(id = R.string.app_name)) }, actions = {
        IconButton(onClick = onEditClick) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        IconButton(onClick = { showMenu.value = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        DropdownMenu(
            expanded = showMenu.value,
            onDismissRequest = { showMenu.value = false }) {
            DropdownMenuItem(text = { Text(text = "使用说明") }, onClick = {
                showMenu.value = false
                onHelperClick.invoke()
            })
        }
    })
}

@Preview
@Composable
private fun MainTopBarPreview() {
    MainTopBar({}, {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelperTopBar(navController: NavController) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary
    ),
        title = {
            Text(text = "使用说明")
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        })
}
