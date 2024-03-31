package org.d3if3132.assesment01.yoquiz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3132.assesment01.yoquiz.navigation.Screen
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

@Composable
fun ResultScreen(viewModel: QuizViewModel, navController: NavHostController) {
    //Membuat jumlah benar dan salah jawaban
    val correctCount = viewModel.calculateScore()
    val incorrectCount = viewModel.answersValueSize() - correctCount
    val totalQuestion = viewModel.questionSize()
    Scaffold {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Total Questions : $totalQuestion", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Correct: $correctCount", style = MaterialTheme.typography.bodyMedium)
            Text("Incorrect: $incorrectCount", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                viewModel.clearAnswers()
                navController.navigate(Screen.Quiz.route){
                    popUpTo(Screen.Result.route){inclusive = true}
                }
            }) {
                Text("Restart Quiz")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {

            }) {
                Text("Share Result")
            }

            Button(onClick = {
                navController.navigate("${Screen.Home.route}/${viewModel.name}/${viewModel.selectedChoice}/${viewModel.tentangAnda}"){
                    popUpTo(Screen.Result.route){inclusive = true}
                }
            }) {
                Text(text = "Finish")
            }
        }
    }
}