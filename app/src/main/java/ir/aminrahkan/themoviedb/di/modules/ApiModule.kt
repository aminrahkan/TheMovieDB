package ir.aminrahkan.themoviedb.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.aminrahkan.data.api.ApiService
import ir.aminrahkan.themoviedb.api.HeadersInterceptor
import ir.aminrahkan.themoviedb.api.ApiConstants
import ir.aminrahkan.themoviedb.api.AuthenticationInterceptor
import ir.aminrahkan.themoviedb.app.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


// Developer : Amin Rahkan - Amin.Rahkan7@gmail.com  
// Date : 11/30/22 - 2022
// Project name : TheMovieDB


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(AppConstants.TimeOut, TimeUnit.SECONDS)
            .readTimeout(AppConstants.TimeOut, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.TimeOut, TimeUnit.SECONDS)
            .addInterceptor(AuthenticationInterceptor())
            .addInterceptor(HeadersInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient).baseUrl(ApiConstants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}