package org.d3if3132.assesment01.yoquiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Answers(isSelected: Boolean, label: String, modifier:Modifier, index:Char, color:Color, textColor:Color) {
    Row(modifier = modifier.background(color).width(350.dp).height(55.dp).border(1.dp,MaterialTheme.colorScheme.primary).padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
        Text(text = "$index", color = textColor, maxLines = 1, minLines = 1,overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = label, color = textColor)
        Spacer(modifier = Modifier.width(10.dp))
        RadioButton(selected = isSelected, onClick = null, colors = androidx.compose.material3.RadioButtonDefaults.colors(
            selectedColor = Color.Transparent,
            unselectedColor = Color.Transparent
        )
        )
    }
}