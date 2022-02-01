package com.preprod.jettrivia.data.network

import com.preprod.jettrivia.domain.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestions(): Question
}