package com.joseph.marketkurly_clone.src.activity_signup

import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

class SignUpValidationManager {

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
        val duplicatePattern = Pattern.compile("")


    }

    fun checkValidationPW(text: String) {

    }

    fun checkValidationSamePw(text: String) {

    }

    fun checkIdValidationBIRTH(text: String) {

    }


}
