package org.d3if3132.assesment01.yoquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.d3if3132.assesment01.yoquiz.ui.screens.AboutScreen
import org.d3if3132.assesment01.yoquiz.ui.screens.Home
import org.d3if3132.assesment01.yoquiz.ui.screens.QuizScreen
import org.d3if3132.assesment01.yoquiz.ui.screens.ResultScreen
import org.d3if3132.assesment01.yoquiz.ui.screens.SignIn
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

@Composable
fun SetupNavGraph(
    viewModel: QuizViewModel, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.SignIn.route){

        composable(route = Screen.SignIn.route){
            SignIn(navController = navHostController, viewModel = viewModel)
        }

        navigation(startDestination = "${Screen.Home.route}/{nama}/{selectedChoice}/{tentangAnda}", route= Screen.Home.route){
            composable(route = "${Screen.Home.route}/{nama}/{selectedChoice}/{tentangAnda}"){ entry ->
                val namaRoute = entry.arguments?.getString("nama") ?: ""
                val selectedChoiceRoute = entry.arguments?.getString("selectedChoice") ?: ""
                val tentangAndaRoute = entry.arguments?.getString("tentangAnda") ?: ""
                Home(
                    viewModel = viewModel,
                    navController = navHostController,
                    namaRoute = namaRoute,
                    selectedChoiceRoute = selectedChoiceRoute,
                    tentangAndaRoute = tentangAndaRoute
                )
            }

            composable(route = Screen.About.route){
                AboutScreen(navController = navHostController)
            }

            composable(route = Screen.Quiz.route){
                QuizScreen(viewModel = viewModel, navController = navHostController)
            }

            composable(route = Screen.Result.route){
                ResultScreen(viewModel = viewModel, navController = navHostController)
            }
        }


    }
}