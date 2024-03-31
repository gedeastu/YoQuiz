package org.d3if3132.assesment01.yoquiz.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.d3if3132.assesment01.yoquiz.R
import org.d3if3132.assesment01.yoquiz.viewmodel.QuizViewModel

@Composable
fun ErrorHint(isError: Boolean, viewModel: QuizViewModel){
    if (isError){
        Text(text = stringResource(id = R.string.input_invalid))
    }
}