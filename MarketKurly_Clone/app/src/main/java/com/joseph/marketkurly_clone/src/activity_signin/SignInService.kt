package com.joseph.marketkurly_clone.src.activity_signin

import android.util.Log
import androidx.lifecycle.Lifecycle
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.ApplicationClass
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.NetworkConstants
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo
import com.joseph.marketkurly_clone.src.activity_main.network.UserInfoApi
import com.joseph.marketkurly_clone.src.activity_signin.interfaces.SignInApiEvent
import com.joseph.marketkurly_clone.src.activity_signin.network.SignInApi
import com.joseph.marketkurly_clone.src.util.setToken
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInService(var listener: SignInApiEvent) {

    private var mSignInClient = RetrofitClient.getClient(KURLY_URL).create(SignInApi::class.java)
    private var mLoadUserInfoClient = RetrofitClient.getClient(KURLY_URL).create(UserInfoApi::class.java)

    private var job = SupervisorJob()
    private var coroutineScope = CoroutineScope(Dispatchers.Main + job)

    fun signIn(userAccount: JsonObject) {
        coroutineScope.launch {
            var message: String? = null
            var userInfo: UserInfo? = null

            // JWT 토큰을 가져온다.
            var jwt = withContext(Dispatchers.IO) {
                var signInResponse = mSignInClient.signIn(userAccount).execute()
                val body = signInResponse.body()
                var token: String? = null

                val isSuccess = body?.get("is_success")?.asBoolean

                if (isSuccess!!) {
                    token = body.get("result").asJsonObject.get("jwt").asString
                    ApplicationClass.sSharedPreferences?.setToken(token)
                } else {
                    message = body.get("message").asString
                }

                token
            }

            // JWT 토큰 가져오는데 성공했으면 유저 정보를 가져온다.
            if (jwt != null) {
                userInfo = withContext(Dispatchers.IO) {
                    val loadUserResponse = mLoadUserInfoClient.loadUserInfo().execute()
                    val body = loadUserResponse.body()
                    val isSuccess = body?.get("is_success")?.asBoolean
                    var data: UserInfo? = null

                    if (isSuccess!!) {
                        data = Gson().fromJson(body.get("result"), UserInfo::class.java)

                    } else {
                        val message = body.get("message").asString
                    }

                    data
                }
            }

            if (jwt != null && userInfo != null) {
                listener.onSignInSuccess(jwt, userInfo)
            } else {
                listener.onSignInFail(message!!)
            }

        }
    }

    fun onCleared() {
        job.cancel()
    }

}

