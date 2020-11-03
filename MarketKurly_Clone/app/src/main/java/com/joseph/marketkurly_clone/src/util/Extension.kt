package com.joseph.marketkurly_clone.src.util

import android.content.SharedPreferences
import android.graphics.Paint
import android.view.View
import android.widget.TextView
import java.text.DecimalFormat

// 문자열이 JSON 형태인지, JSON 배열 형태인지
fun String?.isJsonObject(): Boolean {
    return this?.startsWith("{") == true && this.endsWith("}")
}

fun String?.isJsonArray(): Boolean {
    return this?.startsWith("[") == true && this.endsWith("]")
}


// 천단위 구분기호 변환 포멧
fun Int.toDecimalFormat(): String {
    val myFormatter = DecimalFormat("###,###")
    return myFormatter.format(this)
}

// Product에서 원래가격을 취소선 처리해줌
fun TextView.setStrikeThru() {
    this.paintFlags = this.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
}

// SharedPreferences 에 JWT 토큰 저장, 불러오기
fun SharedPreferences.setToken(token: String) {
    this.edit().apply{
        putString("X-ACCESS-TOKEN", token)
        apply()
    }
}

fun SharedPreferences.getToken(): String? {
    return this.getString("X-ACCESS-TOKEN", null)
}

// 뷰의 보이게하기 안보이게 하기
fun View.setVisible(){
    this.visibility = View.VISIBLE
}

fun View.setInVisible(){
    this.visibility = View.INVISIBLE
}

fun View.setGone(){
    this.visibility = View.GONE
}
