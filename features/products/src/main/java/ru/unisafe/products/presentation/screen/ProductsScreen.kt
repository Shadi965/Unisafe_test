package ru.unisafe.products.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.unisafe.products.presentation.screen.product_item.ProductItem
import ru.unisafe.products.presentation.screen.product_item.ShimmerAnimatedStubItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductsScreen() {
    val viewModel: ProductsViewModel = hiltViewModel()
    val list by viewModel.products.collectAsState(initial = null)
    val checkedMode by viewModel.checkedMode.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val showDialog by viewModel.showDialog
    val showCrossedProducts by viewModel.showCrossedProducts
    val isInLoading by viewModel.isInLoading.collectAsState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!checkedMode)
                ProductsListTopBar(viewModel = viewModel, scrollBehavior = scrollBehavior)
            else
                ProductsListChangedModeTopBar(viewModel = viewModel, scrollBehavior = scrollBehavior)
        }
    ) { paddingValues ->
        if (list?.isEmpty() == true && !isInLoading)
            Text(
                text = "Список пуст",
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 16.dp
                    ))
        LazyColumn(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
            userScrollEnabled = list != null
        ) {
            if (list != null)
                items(list!!, key = { it.item.id }) {
                    if (!it.isInProgress && (!it.item.isCrossed || showCrossedProducts))
                        ProductItem(
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
        ProductAddDialog(viewModel = viewModel)
}