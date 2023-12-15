package ru.unisafe.products.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.unisafe.products.presentation.screen.ProductsViewModel

@Composable
fun ProductAddDialog(viewModel: ProductsViewModel) {
    var showDialog by viewModel.showDialog
    var productName by viewModel.newProductName
    var productCount by viewModel.newProductCount
    var fieldError by viewModel.productCountErrorInput
    Dialog(onDismissRequest = {
        showDialog = false
        fieldError = false
    }) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Добавить новый товар",
                fontSize = 20.sp
            )
            TextField(value = productName, onValueChange = { productName = it },
                supportingText = {
                    Text(text = "Введите название товара")
                }
            )
            TextField(value = productCount, onValueChange = { productCount = it },
                modifier = Modifier.padding(vertical = 16.dp).width(160.dp),
                isError = fieldError,
                supportingText = {
                    Text(text = "Введите количество")
                }
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Отмена",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        showDialog = false
                        fieldError = false
                    }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Добавить",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        viewModel.addProduct()
                    }
                )
            }
        }
    }
}