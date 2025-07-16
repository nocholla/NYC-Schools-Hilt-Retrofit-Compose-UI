package com.nocholla.nyc.schools.hilt.retrofit.compose.ui.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.api.OkHttpClientFactory
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.api.SchoolsApiService
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao.SchoolDao
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.dao.ScoreDao
import com.nocholla.nyc.schools.hilt.retrofit.compose.ui.data.local.database.SchoolDatabase
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
            .baseUrl("https://data.cityofnewyork.us/resource/")
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
    fun provideSchoolDatabase(app: Application): SchoolDatabase {
        return Room.databaseBuilder(
            app,
            SchoolDatabase::class.java,
            "school_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSchoolDao(database: SchoolDatabase): SchoolDao {
        return database.schoolDao()
    }

    @Provides
    @Singleton
    fun provideScoreDao(database: SchoolDatabase): ScoreDao {
        return database.scoreDao()
    }

    @Provides
    @Singleton
    fun provideSchoolRepository(
        schoolsApiService: SchoolsApiService,
        schoolDao: SchoolDao,
        scoreDao: ScoreDao
    ): SchoolRepository {
        return SchoolRepositoryImpl(schoolsApiService, schoolDao, scoreDao)
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