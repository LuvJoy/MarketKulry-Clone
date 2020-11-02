package com.joseph.marketkurly_clone

import android.util.Log
import com.joseph.marketkurly_clone.ApplicationClass.Companion.sSharedPreferences
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.NetworkConstants.X_ACCESS_TOKEN
import com.joseph.marketkurly_clone.src.util.isJsonArray
import com.joseph.marketkurly_clone.src.util.isJsonObject

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

// 싱글턴
object MovieRetrofitClient {
    // 레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit {
        Log.d(TAG, "RetrofitClient - getClient() called")

        // [okHTTP에 인터셉터 추가]----------------------------------------------------------------
        // okhttp 인스턴스 생성
        val okHttpClient = OkHttpClient.Builder()

        // - 로그를 찍기 위해 [로깅인터셉터] 추가 (전반적인 retrofit 통신의 모든 통신내용들 볼 수 있음)
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(TAG, "RetrofitClient - log() called / message: $message")

                when {
                    message.isJsonObject() -> {
                        Log.d(TAG, JSONObject(message).toString(4))
                    }
                    message.isJsonArray() -> {
                        Log.d(TAG, JSONObject(message).toString(4))
                    }
                    else -> {
                        try {
                            Log.d(TAG, JSONObject(message).toString(4))
                        } catch (e: Exception) {
                            Log.d(TAG, message)
                        }
                    }
                }
            }
        })

        val xAccessTokenInterceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val builder: Request.Builder = chain.request().newBuilder()
                val jwtToken: String =
                    sSharedPreferences?.getString(X_ACCESS_TOKEN, null).toString()
                if (jwtToken != null) {
                    builder.addHeader("X-ACCESS-TOKEN", jwtToken)
                }
                return chain.proceed(builder.build())
            }
        }

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.

        okHttpClient.apply {
            // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
            addInterceptor(loggingInterceptor)
            // 커넥션 타임아웃
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)

            // 헤더 인터셉터 (jwt 토큰 헤더에 붙여줌)
            addNetworkInterceptor(xAccessTokenInterceptor)
        }

        // ------------------------------------------------------[okHttp클라이언트 인터셉터 추가]

        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                // 위에서 설정한 클라이언트로 레트로핏 클라이언트 설정
                .build()
        }

        return retrofitClient!!
    }

}