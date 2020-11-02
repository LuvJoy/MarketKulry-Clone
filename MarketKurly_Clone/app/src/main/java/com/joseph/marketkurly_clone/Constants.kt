package com.joseph.marketkurly_clone

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    const val TAG = "[ 로그 ]"
    const val SHARED_PREPERENCE_KEY = "MARKET_KURLY_APP"

    var DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    val MEDIA_TYPE_JSON: MediaType = "application/json; charset=uft-8".toMediaTypeOrNull()!!
    val MEDIA_TYPE_JPEG: MediaType = "image/jpeg".toMediaTypeOrNull()!!
}

object NetworkConstants {
    const val X_ACCESS_TOKEN: String = "X-ACCESS-TOKEN"

    // 테스트 서버 주소
    val BASE_URL = "http://apis.newvement.com/"
    // 실서버 주소
    //val BASE_URL = "https://template.softsquared.com/";
}

