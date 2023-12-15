package ru.unisafe.shopping_lists.presentation.screen.list_item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.unisafe.shopping_lists.domain.entities.ShoppingListView
import ru.unisafe.shopping_lists.presentation.screen.ShoppingListsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListItem(
    item: ShoppingListView,
    viewModel: ShoppingListsViewModel,
    checkedMode: Boolean,
    modifier: Modifier = Modifier
) {

    val list = item.list

    var layoutModifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(
            3.dp, Brush.linearGradient(
                listOf(
                    Color.LightGray.copy(alpha = 0.6f),
                    Color.LightGray.copy(alpha = 0.2f),
                    Color.LightGray.copy(alpha = 0.6f)
                )
            ), shape = RectangleShape
        )

    layoutModifier = if (!checkedMode)
        layoutModifier.combinedClickable(
            onLongClick = { viewModel.checkTheList(list.id) },
            onClick = { viewModel.openProductsScreen(list.id, list.name) }
        )
    else layoutModifier.clickable { viewModel.checkTheList(list.id) }

    layoutModifier = layoutModifier.padding(8.dp)

    if (item.isChecked)
        layoutModifier = layoutModifier.alpha(0.5f)

    ConstraintLayout(modifier = layoutModifier) {

        val (name, date, id) = createRefs()

        Text(text = list.name, fontSize = 24.sp,
            modifier = Modifier.constrainAs(name) {}
        )

        Text(text = list.createdAt, fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(date) {
                    top.linkTo(name.bottom)
                }
                .padding(top = 4.dp)
        )

        Text(text = "Id: ${list.id}", fontSize = 20.sp,
            modifier = Modifier.constrainAs(id) {
                end.linkTo(parent.end)
                bottom.linkTo(date.bottom)
            }
        )
    }
}