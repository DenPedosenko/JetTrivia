package com.preprod.jettrivia.di

import com.preprod.jettrivia.data.network.QuestionApi
import com.preprod.jettrivia.data.repository.QuestionRepositoryImpl
import com.preprod.jettrivia.domain.repository.QuestionRepository
import com.preprod.jettrivia.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQuestionApi(): QuestionApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(api: QuestionApi): QuestionRepository {
        return QuestionRepositoryImpl(api)
    }
}