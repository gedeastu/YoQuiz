package org.d3if3132.assesment01.yoquiz.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.d3if3132.assesment01.yoquiz.R
import org.d3if3132.assesment01.yoquiz.navigation.Screen
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    viewModel: QuizViewModel,
    navController: NavHostController,
    namaRoute: String,
    selectedChoiceRoute: String,
    tentangAndaRoute: String
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                IconButton(onClick = { drawerState.isClosed }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(painter = painterResource(if (selectedChoiceRoute == "Pria") R.drawable.baseline_face_24 else R.drawable.baseline_face_4_24), contentDescription = selectedChoiceRoute, modifier = Modifier
                        .height(100.dp)
                        .width(100.dp))
                    Text(text = namaRoute, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                    Text(text = tentangAndaRoute)
                }
                NavigationDrawerItem(
                    label = {
                        Text(text = "About", fontWeight = FontWeight.Medium)
                    },
                    icon = {
                           Icon(imageVector = Icons.Filled.Info, contentDescription = "Tentang")
                    },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.About.route)
                    }
                )
                NavigationDrawerItem(
                    label = {
                        Text(text = "Sign Out", fontWeight = FontWeight.Medium)
                    },
                    icon = {
                        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "", tint = MaterialTheme.colorScheme.tertiary)
                    },
                    selected = false,
                    onClick = {
                        viewModel.onEmptyName()
                        viewModel.onEmptyTentangAnda()
                        navController.navigate(Screen.SignIn.route){
                            popUpTo(Screen.Home.route){inclusive = true}
                        }
                    }
                )
        }},
        modifier = Modifier.width(200.dp),
        gesturesEnabled = true,
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(text = "Home")
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    navigationIcon = {
                                     IconButton(modifier = Modifier.padding(5.dp),onClick = {
                                         scope.launch {
                                             drawerState.apply {
                                                 if (isClosed) open() else close()
                                             }
                                         }
                                     }) {
                                         Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
                                     }
                    },

                    actions = {
                        Switch(modifier = Modifier.padding(10.dp),checked = viewModel.isDarkTheme.collectAsState().value, onCheckedChange = {
                            viewModel.changeTheme()
                        },
                            thumbContent = {
                                if (viewModel.isDarkTheme.collectAsState().value){
                                    Icon(painter = painterResource(id = R.drawable.baseline_nightlight_round_24), contentDescription = stringResource(
                                        id = R.string.night
                                    ), modifier = Modifier.padding(3.dp), )
                                }else{
                                    Icon(painter = painterResource(id = R.drawable.baseline_sunny_24), contentDescription = stringResource(
                                        id = R.string.day
                                    ), modifier = Modifier.padding(3.dp))
                                }
                            }
                        )
                    },
                )
            }
        ){ paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(painter = painterResource(id = R.drawable.light_bulb__2_), contentDescription = "logo", modifier = Modifier
                        .height(125.dp)
                        .width(125.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Quiz Simple", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = MaterialTheme.colorScheme.primary)
                }

                //Text(text = "Hello $nama\nwith $selectedChoice\n$tentangAnda")


                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    navController.navigate(Screen.Quiz.route)
                }) {
                    Text(text = "Start Quiz")
                }

            }
        }
    }
}