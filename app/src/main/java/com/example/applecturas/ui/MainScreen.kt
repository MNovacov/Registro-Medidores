package com.example.applecturas.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applecturas.ui.screens.FormScreen
import com.example.applecturas.ui.screens.ListScreen
import com.example.applecturas.viewmodel.GastoViewModel

@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()
    val context = LocalContext.current

    val viewModel: GastoViewModel = viewModel(factory = viewModelFactory {
        initializer {
            GastoViewModel(context.applicationContext as Application)
        }
    })


    viewModel.gastos.collectAsState()

    NavHost(navController = navController, startDestination = "listado") {
        composable("listado") {
            ListScreen(viewModel = viewModel) {
                navController.navigate("formulario")
            }
        }
        composable("formulario") {
            FormScreen(viewModel = viewModel) {
                navController.popBackStack()
            }
        }
    }
}
