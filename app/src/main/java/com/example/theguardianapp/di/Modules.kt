package com.example.theguardianapp.di

import android.content.Context
import androidx.room.Room
import com.example.theguardianapp.repo.local.ArticleDatabase
import com.example.theguardianapp.repo.remote.ApiService
import com.example.theguardianapp.repo.remote.ArticleRemoteDataSource
import com.example.theguardianapp.repo.remote.ArticleRemoteDataSourceImpl
import com.example.theguardianapp.usecase.ArticleDataUseCase
import com.example.theguardianapp.usecase.ArticleDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideArticlesDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "articles.db"
        ).build()
    }

    @Provides
    fun provideBaseUrl(): String = "https://content.guardianapis.com/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideArticlesApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("api-key", "test").build()
            chain.proceed(request)
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModules {
    @Binds
    abstract fun bindArticlesRepository(articlesRepositoryImpl: ArticleRemoteDataSourceImpl): ArticleRemoteDataSource
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModules {
    @Binds
    abstract fun bindArticlesUseCase(articlesUseCaseImpl: ArticleDataUseCaseImpl): ArticleDataUseCase
}