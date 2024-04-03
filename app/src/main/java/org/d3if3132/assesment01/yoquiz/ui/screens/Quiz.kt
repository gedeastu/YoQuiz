package org.d3if3132.assesment01.yoquiz.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.d3if3132.assesment01.yoquiz.R
import org.d3if3132.assesment01.yoquiz.navigation.Screen
import org.d3if3132.assesment01.yoquiz.ui.components.Answers
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(viewModel: QuizViewModel, navController: NavHostController) {
    var selectedAnswer by remember {
        mutableStateOf("")
    }
    val question = viewModel.currentQuestion
    // var showCheck by remember {
    //     mutableStateOf(false)
    // }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(text = stringResource(id = R.string.question, question.id))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                ),
            )
        },
    ){paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(20.dp)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Text(text = "${question.id}. " + question.text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Image(painter = painterResource(id = question.image), contentDescription = stringResource(
                id = R.string.image
            ), modifier = Modifier.size(250.dp))
            Spacer(modifier = Modifier.height(5.dp))

            Column(modifier = Modifier, horizontalAlignment = Alignment.Start){
                question.answers.forEach{(index,answerItem)->
                    Answers(
                        color = if (selectedAnswer == answerItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        index = index,
                        isSelected = selectedAnswer == answerItem,
                        label = answerItem,
                        textColor = if (selectedAnswer == answerItem) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .selectable(
                                selected = selectedAnswer == answerItem,
                                onClick = {
                                    selectedAnswer = answerItem
                                },
                                role = Role.RadioButton
                            )
                            .padding(10.dp))
                    //if (!showCheck){
                    //    Text(text = if (answerItem == question.correctAnswer) "Correct" else "Incorrect")
                    //}
                }
            }

            //Button(onClick = {
            //    showCheck = !showCheck
            //},
            //    enabled = selectedAnswer != "") {
            //        Text(text = "Check Answers")
            //}
            
            Button(onClick = {
                if (selectedAnswer.isNotEmpty()){
                    viewModel.submitAnswer(selectedAnswer)
                    if (viewModel.isLastQuestion()){
                        navController.navigate(Screen.Result.route){
                            popUpTo(Screen.Quiz.route){inclusive = true}
                        }
                    }else{
                        viewModel.nextQuestion()
                        selectedAnswer = ""
                    }
                    
                }
            },
                enabled = selectedAnswer != ""
            ) {
                if (viewModel.isLastQuestion()) Text(text = "Submit") else Text(text = "Next")
            }   
    }

        //        Button(onClick = {
        //            viewModel.prevQuestion()
        //        },
        //            enabled = viewModel.currentQuestion.id > 1
        //            ) {
        //            Text(text = "Previous")
        //        }
    }
}