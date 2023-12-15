package ru.unisafe.products.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.unisafe.products.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsListTopBar(viewModel: ProductsViewModel, scrollBehavior: TopAppBarScrollBehavior) {
    val name = viewModel.listName
    val isLoading by viewModel.isInLoading.collectAsState()
    val connectionError by viewModel.connectionError
    var showDialog by viewModel.showDialog
    var showCrossedProducts by viewModel.showCrossedProducts
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 12.dp)
            )
        },
        actions = {
            if (connectionError)
                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { viewModel.launchDbSync() },
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Sync list"
                )
            if (isLoading)
                CircularProgressIndicator(modifier = Modifier.size(28.dp),
                    color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.size(28.dp))
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { showDialog = true },
                imageVector = Icons.Filled.Add, contentDescription = "Add new product")
            Spacer(modifier = Modifier.size(28.dp))
            if (showCrossedProducts)
                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { showCrossedProducts = !showCrossedProducts },
                    painter = painterResource(id = R.drawable.visibility_off),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Show crossed products",
                )
            else
                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { showCrossedProducts = !showCrossedProducts },
                    painter = painterResource(id = R.drawable.visibility),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Show crossed products",
                )
            Spacer(modifier = Modifier.size(16.dp))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsListChangedModeTopBar(viewModel: ProductsViewModel, scrollBehavior: TopAppBarScrollBehavior) {
    val name = viewModel.listName
    val isAllChecked by viewModel.isCheckedAll.collectAsState()
    val toggleableState =
        if (!isAllChecked) ToggleableState.Indeterminate
        else ToggleableState.On
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 12.dp)
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clickable { viewModel.crossOffEveryCheckedProducts() },
                imageVector = Icons.Filled.Close,
                contentDescription = "Cross off selected products",
            )
            Spacer(modifier = Modifier.size(16.dp))
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clickable { viewModel.deleteEveryCheckedProducts() },
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete selected products"
            )
            Spacer(modifier = Modifier.size(12.dp))
            TriStateCheckbox(
                state = toggleableState,
                onClick = { viewModel.checkAllProducts() }
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    )
}