package com.preprod.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preprod.jettrivia.data.DataOrException
import com.preprod.jettrivia.domain.model.QuestionItem
import com.preprod.jettrivia.domain.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {
    private val _data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))
    val data: State<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> = _data
    val error = mutableStateOf(false)

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            _data.value = repository.getAllQuestions()
            if (_data.value.e != null) {
                error.value = true
            }
        }
    }
}