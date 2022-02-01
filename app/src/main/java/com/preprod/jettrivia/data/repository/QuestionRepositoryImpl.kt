package com.preprod.jettrivia.data.repository

import com.preprod.jettrivia.data.DataOrException
import com.preprod.jettrivia.data.network.QuestionApi
import com.preprod.jettrivia.domain.model.QuestionItem
import com.preprod.jettrivia.domain.repository.QuestionRepository

class QuestionRepositoryImpl(
    private val api: QuestionApi
) : QuestionRepository {
    private val dataOrException =
        DataOrException<ArrayList<QuestionItem>,
                Boolean,
                Exception>()

    override suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }
        } catch (exception: Exception) {
            dataOrException.e = exception
            dataOrException.loading = false
        }
        return dataOrException
    }

}