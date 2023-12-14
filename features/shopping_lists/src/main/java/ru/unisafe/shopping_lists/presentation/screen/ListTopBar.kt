package ru.unisafe.shopping_lists.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTopBar(viewModel: ShoppingListsViewModel, scrollBehavior: TopAppBarScrollBehavior) {
    val key by viewModel.currentKey.collectAsState()
    val isLoading by viewModel.isInLoading.collectAsState()
    var showDialog by viewModel.showDialog
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = key)
                },
        actions = {
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { showDialog = true },
                imageVector = Icons.Filled.Add, contentDescription = "Create new list")
            Spacer(modifier = Modifier.size(28.dp))
            Box(modifier = Modifier.size(36.dp)) {
                if (!isLoading)
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { viewModel.loadAgain() },
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Update list"
                    )
                else
                    CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.size(28.dp))
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { viewModel.logout() },
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = "Log out",
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListChangedModeTopBar(viewModel: ShoppingListsViewModel, scrollBehavior: TopAppBarScrollBehavior) {
    val key by viewModel.currentKey.collectAsState()
    val isAllChecked by viewModel.isCheckedAll.collectAsState()
    val toggleableState =
        if (!isAllChecked) ToggleableState.Indeterminate
        else ToggleableState.On
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = key)
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clickable { viewModel.deleteCheckedLists() },
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete selected lists"
            )
            TriStateCheckbox(
                state = toggleableState,
                onClick = { viewModel.checkAllLists() }
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppBarPreview() {
    val isLoading: Boolean = true
    TopAppBar(
        title = {
            Text(text = "Ключ")
        },
        actions = {
            Box(modifier = Modifier.size(36.dp)) {
                if (!isLoading)
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { },
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Update list"
                    )
                else
                    CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.size(28.dp))
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { },
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = "Log out",
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    )
}