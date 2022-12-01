package ir.aminrahkan.themoviedb.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.aminrahkan.data.api.ApiService
import ir.aminrahkan.themoviedb.app.Constants
import okhttp3.OkHttpClient
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
            .connectTimeout(Constants.TimeOut, TimeUnit.SECONDS)
            .readTimeout(Constants.TimeOut, TimeUnit.SECONDS)
            .writeTimeout(Constants.TimeOut, TimeUnit.SECONDS)
            .addInterceptor {
                val oldRequest = it.request()
                val newRequestBuilder = oldRequest.newBuilder()
                //TODO Token must be set here
                newRequestBuilder.addHeader("Content-Type", "application/json")
                newRequestBuilder.method(oldRequest.method, oldRequest.body)

                return@addInterceptor it.proceed(newRequestBuilder.build())
            }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient).baseUrl(Constants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}