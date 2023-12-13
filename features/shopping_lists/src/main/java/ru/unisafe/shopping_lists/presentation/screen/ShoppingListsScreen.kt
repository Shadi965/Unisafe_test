package ru.unisafe.shopping_lists.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ShoppingListsScreen() {
    val viewModel: ShoppingListsViewModel = hiltViewModel()
    val list by viewModel.list.collectAsState(emptyList())
    val checkedMode by viewModel.checkedMode.collectAsState()
    Column {
        if (!checkedMode)
            ListTopBar(viewModel = viewModel)
        else
            ListChangedModeTopBar(viewModel = viewModel)
        LazyColumn(userScrollEnabled = list != null) {
            if (list != null)
                items(list!!) {
                    if (!it.isInDeleting)
                        ListItem(
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
}
