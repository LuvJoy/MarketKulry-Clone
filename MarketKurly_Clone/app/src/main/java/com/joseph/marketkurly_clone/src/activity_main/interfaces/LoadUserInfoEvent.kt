package com.joseph.marketkurly_clone.src.activity_main.interfaces

import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo

interface LoadUserInfoEvent {

    fun onLoadUserInfoSuccess(user: UserInfo)

    fun onLoadUserInfoFail(message: String)
}