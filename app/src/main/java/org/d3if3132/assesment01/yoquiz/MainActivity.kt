package org.d3if3132.assesment01.yoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3132.assesment01.yoquiz.navigation.SetupNavGraph
import org.d3if3132.assesment01.yoquiz.ui.theme.YoQuizTheme
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController: NavHostController = rememberNavController()

            val viewModel = viewModel<QuizViewModel>(
                //Untuk Pemanggilan Parameter
                //                factory = object : ViewModelProvider.Factory{
                //                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                //                        return QuizViewModel() as T
                //                    }
                //                }
            )
            YoQuizTheme(darkTheme = viewModel.isDarkTheme.collectAsState().value){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    SetupNavGraph(
                        navHostController = navHostController, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YoQuizTheme {
        Greeting("Android")
    }
}