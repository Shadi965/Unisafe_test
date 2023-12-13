package ru.unisafe.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.unisafe.example.navigation.Screen
import ru.unisafe.example.ui.theme.UnisafeTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            viewModel.getNavigator().register(navController)
            val currentKey by viewModel.currentKey
            UnisafeTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(currentKey == null)
                        viewModel.getNavigator()
                            .AddNavHost(startDestination = Screen.Auth.route)
                    else
                        viewModel.getNavigator()
                            .AddNavHost(startDestination = Screen.ShoppingLists.route)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getNavigator().unRegister()
    }
}