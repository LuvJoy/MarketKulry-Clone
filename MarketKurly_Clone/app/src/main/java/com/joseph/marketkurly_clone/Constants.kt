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

    val RESULT_CODE_SAVE_ADDRESS = 445
    val RESULT_CODE_REMOVE_ADDRESS = 444

    val REQUEST_CODE_LOGIN = 1001
    val REQUEST_CODE_CART = 1002
    val REQUEST_CODE_ADDRESS = 1003
    val REQUEST_CODE_ADDRESS_EDIT = 1004

    val REQUEST_CODE_ORDER_ADDRESS_CHANGE = 1007
    val REQUEST_CODE_ORDER_ADDRESS_EDIT = 1008
    val REQUEST_CODE_ORDER_PLACE = 1009
}

object NetworkConstants {
    const val X_ACCESS_TOKEN: String = "X-ACCESS-TOKEN"

    // 테스트 서버 주소
    val BASE_URL = "http://apis.newvement.com/"
    // 실서버 주소
    //val BASE_URL = "https://template.softsquared.com/";

    val KURLY_URL = "https://marketKulry.shop/"
}

object KurlyConstants {
    val STAR_SHIPPING = "샛별배송"
    val POST_SHIPPING = "택배배송"
}
