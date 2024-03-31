package org.d3if3132.assesment01.yoquiz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.d3if3132.assesment01.yoquiz.R
import org.d3if3132.assesment01.yoquiz.model.Gender
import org.d3if3132.assesment01.yoquiz.navigation.Screen
import org.d3if3132.assesment01.yoquiz.ui.components.ErrorHint
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(navController: NavHostController, viewModel: QuizViewModel) {
    Scaffold {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Welcome to Quiz Simple", fontWeight = FontWeight.SemiBold)

                Text(text = "Masukan Identitas", fontWeight = FontWeight.Normal, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(20.dp))


                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = {
                        viewModel.onTextChangedName(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "name")
                    },
                    trailingIcon = if (viewModel.name != "") {
                        {
                            IconButton(onClick = {
                                viewModel.onEmptyName()
                            }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")
                            }
                        }
                    } else null,
                    label = {
                        Text(text = "Nama")
                    },
                    supportingText = {
                        ErrorHint(viewModel = viewModel,isError = viewModel.namaError)
                    },
                    isError = viewModel.namaError
                )


                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(
                    value = viewModel.tentangAnda,
                    onValueChange = {
                        viewModel.onTextChangedTentangAnda(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "tentang anda")
                    },
                    trailingIcon = if (viewModel.tentangAnda != "") {
                        {
                            IconButton(onClick = {
                                viewModel.onEmptyTentangAnda()
                            }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")
                            }
                        }
                    } else null,
                    label = {
                        Text(text = "Tentang anda")
                    },
                    supportingText = {
                        ErrorHint(viewModel = viewModel,isError = viewModel.tentangAndaError)
                    },
                    isError = viewModel.tentangAndaError
                )


                Spacer(modifier = Modifier.height(15.dp))


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    ExposedDropdownMenuBox(expanded = viewModel.isExpanded, onExpandedChange = {viewModel.isExpanded = !viewModel.isExpanded}) {
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor(),
                            value = viewModel.selectedChoice,
                            onValueChange = {
                                viewModel.selectedChoice = it
                            },
                            readOnly = true,
                            leadingIcon = {
                                Icon(painter = painterResource(id = viewModel.selectedIcon), contentDescription = viewModel.selectedChoice)
                            },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.isExpanded)
                            }
                        )


                        ExposedDropdownMenu(expanded = viewModel.isExpanded, onDismissRequest = {
                            viewModel.isExpanded = false
                        }) {
                            viewModel.options.forEachIndexed{ index: Int, value: Gender ->
                                DropdownMenuItem(
                                    text = {
                                        Text(text = value.gender)
                                    },
                                    onClick = {
                                        viewModel.selectedChoice = viewModel.options[index].gender
                                        viewModel.isExpanded = false
                                        viewModel.selectedIcon = viewModel.iconGender[index]
                                    },
                                    leadingIcon = {
                                        Icon(painter = painterResource(id = viewModel.iconGender[index]), contentDescription = value.gender)
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }
                    }
                    //Text(text = "Your Gender is $selectedChoice?")
                }


                Spacer(modifier = Modifier.height(15.dp))


                Box(modifier = Modifier
                    .height(48.dp)
                    .width(200.dp)
                    .clip(shape = RoundedCornerShape(5.dp)), contentAlignment = Alignment.Center){
                    Button(modifier = Modifier.fillMaxSize(),onClick = {
                        viewModel.namaError = (viewModel.name == "")
                        viewModel.tentangAndaError = (viewModel.tentangAnda == "")
                        if (viewModel.namaError || viewModel.tentangAndaError) return@Button

                        navController.navigate("${Screen.Home.route}/${viewModel.name}/${viewModel.selectedChoice}/${viewModel.tentangAnda}"){
                            popUpTo(Screen.SignIn.route){inclusive = true}
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                    ) {
                        Text(text = "JOIN", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                    }
                }
            }

            Spacer(modifier = Modifier.height(35.dp))
            
            Switch(checked = viewModel.isDarkTheme.collectAsState().value, onCheckedChange = {
                viewModel.changeTheme()
            },
                thumbContent = {
                    if (viewModel.isDarkTheme.collectAsState().value){
                        Icon(painter = painterResource(id = R.drawable.baseline_nightlight_round_24), contentDescription = stringResource(
                            id = R.string.night
                        ), modifier = Modifier.padding(3.dp))
                    }else{
                        Icon(painter = painterResource(id = R.drawable.baseline_sunny_24), contentDescription = stringResource(
                            id = R.string.day
                        ), modifier = Modifier.padding(3.dp))
                    }
                }
            )

        }
    }
}