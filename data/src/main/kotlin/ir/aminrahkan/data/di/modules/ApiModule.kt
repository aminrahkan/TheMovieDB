package ir.aminrahkan.data.remote.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.aminrahkan.data.remote.ApiConstants
import ir.aminrahkan.data.remote.ApiService
import ir.aminrahkan.data.remote.interceptors.HeadersInterceptor
import ir.aminrahkan.data.remote.interceptors.AuthenticationInterceptor
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
            .connectTimeout(ApiConstants.ApiTimeOut, TimeUnit.SECONDS)
            .readTimeout(ApiConstants.ApiTimeOut, TimeUnit.SECONDS)
            .writeTimeout(ApiConstants.ApiTimeOut, TimeUnit.SECONDS)
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