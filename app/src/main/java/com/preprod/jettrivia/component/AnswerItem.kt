package com.preprod.jettrivia.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.preprod.jettrivia.util.AppColors

@Composable
fun AnswerItem(
    updateAnswer: (Int) -> Unit,
    index: Int,
    answerState: MutableState<Int?>,
    answer: String
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(45.dp)
            .clickable { updateAnswer(index) }
            .border(
                width = 4.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mOffDarkPurple,
                        AppColors.mOffDarkPurple
                    )
                ), shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(50))
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = answerState.value == index, onClick = {
                updateAnswer(index)
            },
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = answer,
            modifier = Modifier.padding(6.dp),
            color = AppColors.mOffWhite
        )
    }
}