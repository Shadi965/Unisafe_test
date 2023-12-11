package ru.unisafe.auth.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthScreen() {
    val viewModel: AuthScreenViewModel = hiltViewModel()
    var key by viewModel.key
    val isInProgress by viewModel.isInProgress
    var isKeyVerified by viewModel.isKeyVerified
    var isKeyIncorrect by viewModel.isKeyIncorrect
    val keyLength = 6
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = key, onValueChange = {
                if (it.length <= keyLength) key = it.uppercase()
                isKeyVerified = false
                isKeyIncorrect = false
            },
            modifier = Modifier.padding(4.dp),
            enabled = !isInProgress,
            textStyle = TextStyle(fontSize = 24.sp),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
            label = {
                Text(text = "Ключ доступа")
            },
            isError = isKeyIncorrect,
            trailingIcon = {
                if (!isInProgress)
                    IconButton(
                        onClick = { viewModel.sendKey() },
                        enabled = !isKeyIncorrect
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Send key"
                        )
                    }
                else
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        )
        Button(
            onClick = { viewModel.getNewKey() },
            enabled = !isInProgress,
        ) {
            Text(
                text = "Получить \nновый ключ",
                textAlign = TextAlign.Center
            )
        }
    }
}