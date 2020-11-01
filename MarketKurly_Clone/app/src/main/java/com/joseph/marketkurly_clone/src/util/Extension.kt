package com.joseph.marketkurly_clone.src.util

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