package com.joseph.marketkurly_clone.src.activity_signup.interfaces

interface SignUpValidationEvent {

    fun onCheckIdSuccess(result: String) {

    }
    fun onCheckIdFail(message: String) {

    }

    fun onCheckPhoneNumSuccess(result: String) {

    }

    fun onCheckPhoneNumFail(message: String) {

    }

}