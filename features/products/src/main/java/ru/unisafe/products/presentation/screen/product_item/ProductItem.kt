package ru.unisafe.products.presentation.screen.product_item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.unisafe.products.domain.entities.ProductView
import ru.unisafe.products.presentation.screen.ProductsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItem(item: ProductView,
                viewModel: ProductsViewModel,
                checkedMode: Boolean,
                modifier: Modifier = Modifier) {
    val product = item.item
    val isCrossed = product.isCrossed
    var layoutModifier = modifier.fillMaxWidth()

    if (isCrossed)
        layoutModifier = layoutModifier.alpha(0.4f)

    layoutModifier = layoutModifier.padding(8.dp)

    layoutModifier =
        if (checkedMode) {
            if (item.isChecked) {
                layoutModifier = layoutModifier.border(3.dp, Color.LightGray)
            }
            layoutModifier.clickable {
                viewModel.checkProduct(product.id)
            }
        }
        else {
            layoutModifier.combinedClickable(onLongClick = {
                viewModel.checkProduct(product.id)
            }) {}
        }

    ConstraintLayout(modifier = layoutModifier
        .padding(8.dp)) {

        val (name, count, id, crossIt) = createRefs()

        Text(text = product.name, fontSize = 24.sp,
            modifier = Modifier.constrainAs(name) {}
        )

        Text(text = "Количество: ${product.count}", fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(count) {
                    top.linkTo(name.bottom)
                }
                .padding(top = 4.dp)
        )

        Text(text = "Id: ${product.id}", fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(id) {
                    end.linkTo(crossIt.start)
                    bottom.linkTo(count.bottom)
                }
                .padding(end = 32.dp)
        )

        IconButton(
            onClick = { viewModel.crossProductOff(product.id) },
            modifier = Modifier
                .constrainAs(crossIt) {
                    end.linkTo(parent.end)
                    top.linkTo(name.top)
                    bottom.linkTo(count.bottom)
                }
                .size(52.dp)
        ) {
            AnimatedVisibility(visible = !isCrossed) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Cross it off or on"
                )
            }
            AnimatedVisibility(visible = isCrossed) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Cross it off or on"
                )
            }

        }
    }
}