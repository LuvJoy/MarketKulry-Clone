package com.joseph.marketkurly_clone.src.util

import android.content.SharedPreferences
import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.joseph.marketkurly_clone.KurlyConstants
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
fun SharedPreferences.setToken(token: String?) {
    this.edit().apply {
        putString("X-ACCESS-TOKEN", token)
        apply()
    }
}

fun SharedPreferences.getToken(): String? {
    return this.getString("X-ACCESS-TOKEN", null)
}

// 뷰의 보이게하기 안보이게 하기
fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}


// 주소가 샛별배송인지 아닌지를 리턴해준다.
fun String.getShippingType(): String? {

    val isStarShipping = this.contains("인천") ||
            this.contains("서울") ||
            this.contains("경기")

    return if (isStarShipping) KurlyConstants.STAR_SHIPPING else KurlyConstants.POST_SHIPPING
}

// 문자열이 동의인지 아닌지로 변환해준다.
fun Boolean.isAgree(): String {
    return if (this) "Y" else "N"
}

fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop*8)       // "8" was obtained experimentally
}