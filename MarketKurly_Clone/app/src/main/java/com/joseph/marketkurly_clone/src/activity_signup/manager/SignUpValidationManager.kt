package com.joseph.marketkurly_clone.src.activity_signup.manager

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.joseph.marketkurly_clone.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class SignUpValidationManager(private var context: Context) {

     val COLOR_NOT_SUCCESS_RED = ContextCompat.getColor(context, R.color.not_success_red)
     val COLOR_SUCCESS_GREEN = ContextCompat.getColor(context, R.color.success_green)
    private val ICON_SUCCESS = ContextCompat.getDrawable(context, R.drawable.ic_check)
    private val ICON_NOT_SUCCESS = ContextCompat.getDrawable(context, R.drawable.ic_cross)

    var mValidationHash: HashMap<String, Boolean> = hashMapOf<String, Boolean>(
            Pair("ID_COMBINATION", false),
            Pair("ID_DUPLICATE", false),
            Pair("PW_LENGTH", false),
            Pair("PW_COMBINATION", false),
            Pair("PW_SAME_NUMBER", false),
            Pair("PW_CHECK", false),
            Pair("BIRTH_YEAR", false),
            Pair("BIRTH_MONTH", false),
            Pair("BIRTH_DAY", false),
            Pair("PHONE_NUMBER", false),
            Pair("PHONE_DUPLICATE", false),
            Pair("EMAIL", false)
    )

    // 아이디 조합 & 길이 체크  -> [6자 이상 16자 미만의 길이로 영문과 숫자 조합]
    fun checkIdCombination(text: String, view: TextView) {
        val patternCombination = Regex("^(?=.*\\d)(?=.*[a-z])([^\\s]){6,16}\$")
        mValidationHash["ID_COMBINATION"] = text.matches(patternCombination)
        if (mValidationHash["ID_COMBINATION"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    // 비밀번호 길이 체크 -> [10 자이상 ~ 20자 이하]
    fun checkPwLength(text: String, view: TextView) {
        val patternLength = Regex("^([^\\s]){10,20}\$")
        mValidationHash["PW_LENGTH"] = text.matches(patternLength)
        if (mValidationHash["PW_LENGTH"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    // 비밀번호 정규식 -> [특수문자, 문자, 숫자 중 2가지 포함 ]
    fun checkPwCombination(text: String, view: TextView) {
        val patternCombination = Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{0,}\$")
        mValidationHash["PW_COMBINATION"] = text.matches(patternCombination)
        if (mValidationHash["PW_COMBINATION"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    // 비밀번호 정규식 -> [비밀번호 연속된 숫자가 3자리 있는지 체크]
    fun checkPwSameNumber(text: String, view: TextView) {
        val patternCombination = Regex("(\\d)\\1\\1")
        mValidationHash["PW_SAME_NUMBER"] = !text.matches(patternCombination)
        if (mValidationHash["PW_SAME_NUMBER"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    // 비밀번호 확인
    fun checkPwSame(text: String, password: String, view: TextView) {
        if (password == "") {
            mValidationHash["PW_CHECK"] = false
        } else {
            mValidationHash["PW_CHECK"] = password == text
        }

        if (mValidationHash["PW_CHECK"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    // 생년월일 년도가 올바른지
    fun checkYearValidation(text: String): Boolean {
        val AGE_LIMIT = 14
        val year = text.toInt()
        val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)

        mValidationHash["BIRTH_YEAR"] = (year >= 1920) && (year <= currentYear - AGE_LIMIT)

        return mValidationHash["BIRTH_YEAR"]!!
    }

    // 생년월일 월이 올바른지
    fun checkMonthValidation(text: String): Boolean {
        val month = text.toInt()
        mValidationHash["BIRTH_MONTH"] = (month >= 1) && (month <= 12)

        return mValidationHash["BIRTH_MONTH"]!!
    }

    // 생년월이 일이 올바른지
    fun checkDayValidation(text: String): Boolean {
        val day = text.toInt()
        mValidationHash["BIRTH_DAY"] = (day >= 1) && (day <= 31)

        return mValidationHash["BIRTH_DAY"]!!
    }

    fun checkPhoneNumber(text: String) {
        val pattern = Regex("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})\$")
        mValidationHash["PHONE_NUMBER"] = text.matches(pattern)
    }

    fun checkEmailAddress(text: String) {
        val pattern = Regex("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}\$")
        mValidationHash["EMAIL"] = text.matches(pattern)
    }




    // 모든 조건들이 부합하는지 체크
    fun isAllPropertyValidate(): String {
        val unValidationList: ArrayList<String> = arrayListOf()
        var isValidationAll = true

        mValidationHash.keys.forEach {
            if (mValidationHash[it] == false) {
                unValidationList.add(it)
                isValidationAll = false
            }
        }
        if (!isValidationAll) {
            return unValidationList.first()
        }

        return "Validation Success"
    }

    fun setTextViewSuccess(view: TextView, text: String = "") {
        view.setTextColor(COLOR_SUCCESS_GREEN)
        view.setCompoundDrawablesWithIntrinsicBounds(ICON_SUCCESS, null, null, null)  //체크표시로 해야함
        view.compoundDrawables[0].setTint(COLOR_SUCCESS_GREEN)
        if (text != "") {
            view.text = text
        }
    }

    fun setTextViewNotSuccess(view: TextView, text: String = "") {
        view.setTextColor(COLOR_NOT_SUCCESS_RED)
        view.setCompoundDrawablesWithIntrinsicBounds(ICON_NOT_SUCCESS, null, null, null)  //체크표시로 해야함
        view.compoundDrawables[0].setTint(COLOR_NOT_SUCCESS_RED)
        if (text != "") {
            view.text = text
        }
    }

}
