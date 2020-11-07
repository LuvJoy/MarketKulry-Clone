package com.joseph.marketkurly_clone.src.activity_signin.interfaces

import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo

interface SignInApiEvent {

    fun onSignInSuccess(jwt: String, userInfo: UserInfo)
    fun onSignInFail(message: String)

}