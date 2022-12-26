package ir.aminrahkan.themoviedb.common.api.interceptors

import ir.aminrahkan.themoviedb.common.api.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response


// Developer : Amin Rahkan - Amin.Rahkan7@gmail.com  
// Date : 12/6/22 - 2022
// Project name : TheMovieDB


class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader(
            "Authorization",
            ApiConstants.PREFIX_TOKEN + ApiConstants.API_KEY
        ).build()
        return chain.proceed(request)
    }
}