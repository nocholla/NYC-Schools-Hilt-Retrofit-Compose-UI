package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.di

import com.google.gson.Gson
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.api.OkHttpClientFactory
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.api.SchoolsApiService
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.repository.SchoolRepositoryImpl
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.repository.SchoolRepository
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.usecase.GetSchoolsUseCase
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.domain.usecase.GetScoresUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpClientFactory: OkHttpClientFactory): OkHttpClient {
        return okHttpClientFactory.createOkHttpClient()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideSchoolsApiService(retrofit: Retrofit): SchoolsApiService {
        return retrofit.create(SchoolsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSchoolRepository(schoolsApiService: SchoolsApiService): SchoolRepository {
        return SchoolRepositoryImpl(schoolsApiService)
    }

    @Provides
    fun provideGetSchoolsUseCase(repository: SchoolRepository): GetSchoolsUseCase {
        return GetSchoolsUseCase(repository)
    }

    @Provides
    fun provideGetScoresUseCase(repository: SchoolRepository): GetScoresUseCase {
        return GetScoresUseCase(repository)
    }
}