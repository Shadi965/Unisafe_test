package ru.unisafe.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.unisafe.data.KeyDataRepository
import ru.unisafe.example.navigation.Navigator
import ru.unisafe.example.navigation.Screen
import ru.unisafe.example.ui.theme.UnisafeTestTheme
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentKey by viewModel.currentKey
            UnisafeTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(currentKey == null)
                        viewModel.getNavigator()
                            .Register(navController = navController, startDestination = Screen.Auth)
                    else
                        viewModel.getNavigator()
                            .Register(navController = navController, startDestination = Screen.ShoppingLists)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.getNavigator().unRegister()
    }
}