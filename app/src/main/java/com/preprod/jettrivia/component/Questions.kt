package com.preprod.jettrivia.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.preprod.jettrivia.domain.model.QuestionItem
import com.preprod.jettrivia.screens.QuestionsViewModel
import com.preprod.jettrivia.util.AppColors

@Composable
fun Questions(viewModel: QuestionsViewModel = hiltViewModel()) {
    val questions = viewModel.data.value

    val questionIndex = remember {
        mutableStateOf(0)
    }
    if (questions.loading == true) {
        CircularProgressIndicator(modifier = Modifier.padding(4.dp))
    } else {
        val question = try {
            questions.data?.get(questionIndex.value)
        } catch (ex: Exception) {
            null
        }
        if (question != null) {
            QuestionDisplay(
                question = question,
                questionIndex = questionIndex,
                totalQuestionCount = questions.data?.size?:0,
                onNextClicked = {
                    questionIndex.value += 1
                }
            )
        }
    }
}


@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    totalQuestionCount: Int = 0,
    onNextClicked: () -> Unit
) {

    val score = remember {
        mutableStateOf(0)
    }

    val choicesState = remember(question) {
        question.choices.toMutableList()
    }

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(question) {
        mutableStateOf(false)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == question.answer
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = AppColors.mDarkPurple
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            ScoreMeter(score.value)
            QuestionTracker(counter = questionIndex.value, outOf = totalQuestionCount)
            DottedLine(pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
            Column {
                Text(
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    text = question.question,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                    color = AppColors.mOffWhite
                )
                choicesState.forEachIndexed { index, answer ->
                    AnswerItem(updateAnswer, index, answerState, answer)
                }
                Button(
                    onClick = {
                        if (correctAnswerState.value) {
                            score.value += 1
                        }
                        onNextClicked()
                    },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(35.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppColors.mLightBlue
                    )
                ) {
                    Text(
                        text = "Next",
                        modifier = Modifier.padding(4.dp),
                        color = AppColors.mOffWhite,
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DottedLine(pathEffect: PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
    ) {
        drawLine(
            color = AppColors.mLightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}