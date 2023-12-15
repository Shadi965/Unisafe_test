package ru.unisafe.shopping_lists.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import ru.unisafe.shopping_lists.presentation.screen.list_item.ListItem
import ru.unisafe.shopping_lists.presentation.screen.list_item.ShimmerAnimatedStubItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ShoppingListsScreen() {
    val viewModel: ShoppingListsViewModel = hiltViewModel()
    val list by viewModel.list.collectAsState(emptyList())
    val checkedMode by viewModel.checkedMode.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val showDialog by viewModel.showDialog
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!checkedMode)
                ListTopBar(viewModel = viewModel, scrollBehavior = scrollBehavior)
            else
                ListChangedModeTopBar(viewModel = viewModel, scrollBehavior = scrollBehavior)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            userScrollEnabled = list != null
        ) {
            if (list != null)
                items(list!!, key = { it.list.id }) {
                    if (!it.isInDeleting)
                        ListItem(
                            modifier = Modifier.animateItemPlacement(),
                            item = it,
                            viewModel = viewModel,
                            checkedMode = checkedMode
                        )
                }
            else
                items(15) {
                    ShimmerAnimatedStubItem()
                }
        }
    }
    if (showDialog)
        ListCreateDialog(viewModel = viewModel)
}
