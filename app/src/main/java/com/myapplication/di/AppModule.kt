package com.myapplication.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.myapplication.ImageListService
import com.myapplication.remote.ApiController
import com.myapplication.remote.NetworkConnectionInterceptor
import com.myapplication.repository.ImageListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(NetworkConnectionInterceptor(context)) // Interceptor for network connectivity
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiController(retrofit: Retrofit): ApiController {
        return retrofit.create(ApiController::class.java)
    }

    @Singleton
    @Provides
    fun provideImageService(apiController: ApiController): ImageListService {
        return ImageListService(apiController)
    }

    @Singleton
    @Provides
    fun provideImageListRepository(imageListService: ImageListService): ImageListRepository {
        return ImageListRepository(imageListService)
    }
}