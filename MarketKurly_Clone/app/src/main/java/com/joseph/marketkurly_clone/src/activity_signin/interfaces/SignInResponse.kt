package com.joseph.marketkurly_clone.src.activity_signin.interfaces

interface SignInResponse {

    fun onSignInSuccess(token: String)
    fun onSignInFail(message: String)

}