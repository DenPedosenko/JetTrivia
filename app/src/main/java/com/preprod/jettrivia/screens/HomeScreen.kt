package com.preprod.jettrivia.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.preprod.jettrivia.component.Questions

@Composable
fun HomeScreen(viewModel: QuestionsViewModel = hiltViewModel()) {
    Questions(viewModel = viewModel)

}