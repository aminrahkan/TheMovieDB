package ir.aminrahkan.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response


// Developer : Amin Rahkan - Amin.Rahkan7@gmail.com  
// Date : 12/6/22 - 2022
// Project name : TheMovieDB


class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader("Content-Type", "application/json;charset=utf-8").build()
        return chain.proceed(request)
    }
}