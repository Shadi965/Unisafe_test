package ru.unisafe.shopping_lists.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ListCreateDialog(viewModel: ShoppingListsViewModel) {
    var showDialog by viewModel.showDialog
    var fieldText by viewModel.newListName
    Dialog(onDismissRequest = { showDialog = false }) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Создать новый список",
                fontSize = 20.sp
            )
            TextField(value = fieldText, onValueChange = { fieldText = it },
                modifier = Modifier.padding(vertical = 16.dp),
                supportingText = {
                    Text(text = "Введите название списка")
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Отмена",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable { showDialog = false }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Создать",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        showDialog = false
                        viewModel.createNewList()
                    }
                )
            }
        }
    }
}