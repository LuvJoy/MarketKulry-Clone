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

    fun checkValidationID(text: String) {
        val isNum = Regex("(^[0-9]*$)")
        mValidationHash["ID_COMBINATION"] = text.matches(isNum)
    }

    fun checkValidationPW(text: String) {

    }

    fun checkValidationSamePw(text: String) {

    }

    fun checkIdValidationBIRTH(text: String) {

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
