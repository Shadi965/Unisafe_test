package ru.unisafe.shopping_lists.presentation.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import ru.unisafe.shopping_lists.domain.entities.ShoppingListView

@Composable
fun ShoppingListsScreen() {
    val viewModel: ShoppingListsViewModel = hiltViewModel()
    val list by viewModel.list.collectAsState(emptyList())
    val progressStatus by viewModel.isInLoading.collectAsState()
    val checkedMode by viewModel.checkedMode.collectAsState()
    if (!checkedMode)
        ListTopBar(viewModel = viewModel)
    else
        ListChangedModeTopBar(viewModel = viewModel)
    LazyColumn(userScrollEnabled = !progressStatus) {
        if (progressStatus)
            items(15) {
                ShimmerAnimatedStubItem()
            }
        else
            items(list) {
                if (!it.isInDeleting)
                    ListItem(
                        item = it,
                        viewModel = viewModel,
                        checkedMode = checkedMode
                    )
            }

    }
}
