package org.d3if3132.assesment01.yoquiz.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.d3if3132.assesment01.yoquiz.R
import org.d3if3132.assesment01.yoquiz.navigation.Screen
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(viewModel: QuizViewModel, navController: NavHostController) {
    val context = LocalContext.current
    //Membuat jumlah benar dan salah jawaban
    val correctCount = viewModel.calculateScore()
    val incorrectCount = viewModel.answersValueSize() - correctCount
    val totalQuestion = viewModel.questionSize()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.result))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                ),
            )
        },
    ){paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text( text = stringResource(id = R.string.total_questions, totalQuestion), style = MaterialTheme.typography.titleMedium, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(id = R.string.correct, correctCount), style = MaterialTheme.typography.titleMedium, fontSize = 20.sp)
            Text(stringResource(id = R.string.incorrect, incorrectCount), style = MaterialTheme.typography.titleMedium, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                viewModel.clearAnswers()
                navController.navigate(Screen.Quiz.route){
                    popUpTo(Screen.Result.route){inclusive = true}
                }
            }) {
                Text(stringResource(id = R.string.restart_quiz))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                shareData(context = context, message = context.getString(R.string.share_result_template,
                    totalQuestion.toString(), correctCount.toString(), incorrectCount.toString()
                    ))
            }) {
                Text(stringResource(id = R.string.share_result))
            }

            Button(onClick = {
                viewModel.clearAnswers()
                navController.navigate("${Screen.Home.route}/${viewModel.name}/${viewModel.selectedChoice}/${viewModel.tentangAnda}"){
                    popUpTo(Screen.Result.route){inclusive = true}
                }
            }) {
                Text(text = stringResource(id = R.string.finish))
            }
        }
    }
}

private fun shareData(context: Context, message:String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}