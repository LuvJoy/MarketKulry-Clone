package com.joseph.marketkurly_clone.src.activity_signup

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.joseph.marketkurly_clone.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class SignUpValidationManager(private var context: Context) {

    private val COLOR_NOT_SUCCESS_RED = ContextCompat.getColor(context, R.color.not_success_red)
    private val COLOR_SUCCESS_GREEN = ContextCompat.getColor(context, R.color.success_green)
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
    )

    fun checkIdCombination(text: String, view: TextView) {
        val patternCombination = Regex("^(?=.*\\d)(?=.*[a-z])([^\\s]){6,16}\$")
        mValidationHash["ID_COMBINATION"] = text.matches(patternCombination)
        if (mValidationHash["ID_COMBINATION"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    fun checkPwLength(text: String, view: TextView) {
        val patternLength = Regex("^([^\\s]){10,20}\$")
        mValidationHash["PW_LENGTH"] = text.matches(patternLength)
        if (mValidationHash["PW_LENGTH"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    fun checkPwCombination(text: String, view: TextView) {
        val patternCombination = Regex("^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W))\$")
        mValidationHash["PW_COMBINATION"] = text.matches(patternCombination)
        if (mValidationHash["PW_COMBINATION"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }


    fun checkPwSameNumber(text: String, view: TextView) {
        val patternCombination = Regex("^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W))\$")
        mValidationHash["PW_COMBINATION"] = text.matches(patternCombination)
        if (mValidationHash["PW_COMBINATION"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    fun checkPwSame(text: String, password: String, view: TextView) {
        mValidationHash["PW_COMBINATION"] = password == text
        if (mValidationHash["PW_COMBINATION"]!!) {
            setTextViewSuccess(view)
        } else {
            setTextViewNotSuccess(view)
        }
    }

    fun checkYearValidation(text: String): Boolean {
        val year = text.toInt()
        val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)

        mValidationHash["BIRTH_YEAR"] = (year >= 1900) && (year <= currentYear)

        return mValidationHash["BIRTH_YEAR"]!!
    }

    fun checkMonthValidation(text: String): Boolean {
        val month = text.toInt()
        mValidationHash["BIRTH_MONTH"] = (month >= 1) && (month <= 12)

        return mValidationHash["BIRTH_MONTH"]!!
    }

    fun checkDayValidation(text: String): Boolean {
        val day = text.toInt()
        mValidationHash["BIRTH_DAY"] = (day >= 1) && (day <= 31)

        return mValidationHash["BIRTH_DAY"]!!
    }



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
