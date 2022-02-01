package com.preprod.jettrivia.domain.repository

import com.preprod.jettrivia.data.DataOrException
import com.preprod.jettrivia.domain.model.QuestionItem

interface QuestionRepository {
    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception>
}