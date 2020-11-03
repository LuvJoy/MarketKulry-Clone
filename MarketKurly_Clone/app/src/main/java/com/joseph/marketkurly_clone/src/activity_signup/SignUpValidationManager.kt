package com.joseph.marketkurly_clone.src.activity_signup

import android.content.Context
import android.graphics.ColorFilter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.joseph.marketkurly_clone.R

class SignUpValidationManager(private var context: Context) {

    private val COLOR_NOT_SUCCESS_RED = ContextCompat.getColor(context, R.color.not_success_red)
    private val COLOR_SUCCESS_GREEN = ContextCompat.getColor(context, R.color.success_green)
    private val ICON_SUCCESS = ContextCompat.getDrawable(context, R.drawable.ic_check)
    private val ICON_NOT_SUCCESS = ContextCompat.getDrawable(context, R.drawable.ic_cross)

    // 아이디 조합, 중복체크 여부
    var ID_COMBINATION_VALIDATION: Boolean = false
    var ID_DUPLICATE_VALIDATION: Boolean = false

    // 비밀번호 길이, 조합, 같은숫자 여부
    var PW_LENGTH_VALIDATION: Boolean = false
    var PW_COMBINATION_VALIDATION: Boolean = false
    var PW_SAME_NUMBER_VALIDATION: Boolean = false

    // 비밀번호 확인부분
    var CHECK_SAME_PW_VALIDATION: Boolean = false

    // 생일 년, 월, 일이 유효하는지 여부
    var BIRTH_YEAR_VALIDATION: Boolean = false
    var BIRTH_MONTH_VALIDATION: Boolean = false
    var BIRTH_DAY_VALIDATION: Boolean = false

    var ALL_PROPERTY_VALIDATION: Boolean = false

    init {
        // 아이디 조합, 중복체크 여부
        ID_COMBINATION_VALIDATION = false
        ID_DUPLICATE_VALIDATION = false

        // 비밀번호 길이, 조합, 같은숫자 여부
        PW_LENGTH_VALIDATION = false
        PW_COMBINATION_VALIDATION = false
        PW_SAME_NUMBER_VALIDATION = false

        // 비밀번호 확인부분
        CHECK_SAME_PW_VALIDATION = false

        // 생일 년, 월, 일이 유효하는지 여부
        BIRTH_YEAR_VALIDATION = false
        BIRTH_MONTH_VALIDATION = false
        BIRTH_DAY_VALIDATION = false

        ALL_PROPERTY_VALIDATION = true
    }

    fun checkValidationID(text: String) {
        val isNum = Regex("(^[0-9]*$)")
        ID_COMBINATION_VALIDATION = text.matches(isNum)
    }

    fun checkValidationPW(text: String) {

    }

    fun checkValidationSamePw(text: String) {

    }

    fun checkIdValidationBIRTH(text: String) {

    }


    fun setSuccess(view: TextView, text: String = "") {
        view.setTextColor(COLOR_SUCCESS_GREEN)
        view.setCompoundDrawablesWithIntrinsicBounds(ICON_SUCCESS, null, null, null)  //체크표시로 해야함
        view.compoundDrawables[0].setTint(COLOR_SUCCESS_GREEN)
        if (text != "") {
            view.text = text
        }
    }

    fun setNotSuccess(view: TextView, text: String = "") {
        view.setTextColor(COLOR_NOT_SUCCESS_RED)
        view.setCompoundDrawablesWithIntrinsicBounds(ICON_NOT_SUCCESS, null, null, null)  //체크표시로 해야함
        view.compoundDrawables[0].setTint(COLOR_NOT_SUCCESS_RED)
        if (text != "") {
            view.text = text
        }
    }

}
