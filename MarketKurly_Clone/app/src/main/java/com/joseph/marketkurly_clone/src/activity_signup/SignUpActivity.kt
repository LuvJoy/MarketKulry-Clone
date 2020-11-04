package com.joseph.marketkurly_clone.src.activity_signup

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.KurlyConstants.POST_SHIPPING
import com.joseph.marketkurly_clone.KurlyConstants.STAR_SHIPPING
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_signup.interfaces.AddressApiEvent
import com.joseph.marketkurly_clone.src.activity_signup.manager.AddressApiManager
import com.joseph.marketkurly_clone.src.activity_signup.manager.SignUpValidationManager
import com.joseph.marketkurly_clone.src.activity_signup.network.SignUpAPI
import com.joseph.marketkurly_clone.src.util.getShippingType
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.layout_signup_adress.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity(), View.OnFocusChangeListener, AddressApiEvent {

    val TAG = "[ 로그 ]"
    private lateinit var mSignUpValidationManager: SignUpValidationManager
    private var mRetrofitClient = RetrofitClient.getClient(KURLY_URL).create(SignUpAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initAcitivty()
        initManager()
        settingsActionBar()
        settingsEditTextListener()
        settingsRadioButtonListener()
        settingsCheckBox()

    }


    fun initAcitivty() {
        signup_play_signup_button.setOnClickListener(this)
        signup_id_check_button.setOnClickListener(this)
        signup_address_textview.setOnClickListener(this)
        address_layout_drop_button.setOnClickListener(this)
        address_layout_input_button.setOnClickListener(this)
        address_layout_back_button.setOnClickListener(this)
        signup_shipping_check_button.setOnClickListener(this)

        signup_id_edittext.onFocusChangeListener = this
        signup_pw_edittext.onFocusChangeListener = this
        signup_pw_check_edittext.onFocusChangeListener = this
        signup_name_edittext.onFocusChangeListener = this
        signup_email_edittext.onFocusChangeListener = this
        signup_phone_num_edittext.onFocusChangeListener = this
        signup_birth_year_edittext.onFocusChangeListener = this
        signup_birth_month_edittext.onFocusChangeListener = this
        signup_birth_day_edittext.onFocusChangeListener = this
    }

    fun initManager() {
        mSignUpValidationManager = SignUpValidationManager(this)
    }

    fun settingsActionBar() {
        ab_inner_toolbar.title = "회원가입"
        ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    fun settingsEditTextListener() {
        signup_id_edittext.addTextChangedListener {
            val text = it.toString()
            Log.d(TAG, "[SignUpActivity] - TextChangedListener() : ${text}")

            mSignUpValidationManager.checkIdCombination(text, signup_id_validation_combination)
        }

        signup_pw_edittext.addTextChangedListener {
            val text = it.toString()

            mSignUpValidationManager.checkPwLength(text, signup_pw_validation_length)
            mSignUpValidationManager.checkPwCombination(text, signup_pw_validation_combination)
            mSignUpValidationManager.checkPwSameNumber(text, signup_pw_validation_same_number)
        }

        signup_pw_check_edittext.addTextChangedListener {
            val text = it.toString()
            val password = signup_pw_edittext.text.toString()
            mSignUpValidationManager.checkPwSame(text, password, signup_pw_check_validation_same)
        }

        signup_birth_year_edittext.addTextChangedListener {
            val text = it.toString()

            if (text != "") {
                val isValidation = mSignUpValidationManager.checkYearValidation(text)
                if (isValidation) {
                    signup_birth_validation_layout.setGone()
                } else {
                    signup_birth_validation_layout.setVisible()
                    mSignUpValidationManager.setTextViewNotSuccess(signup_birth_validation, "생년월일을 다시 확인해 주세요")
                }
            }
        }

        signup_birth_month_edittext.addTextChangedListener {
            val text = it.toString()

            if (text != "") {
                val isValidation = mSignUpValidationManager.checkMonthValidation(text)
                if (isValidation) {
                    signup_birth_validation_layout.setGone()
                } else {
                    signup_birth_validation_layout.setVisible()
                    mSignUpValidationManager.setTextViewNotSuccess(signup_birth_validation, "태어난 월을 정확하게 입력해주세요")
                }
            }
        }

        signup_birth_day_edittext.addTextChangedListener {
            val text = it.toString()

            if (text != "") {
                val isValidation = mSignUpValidationManager.checkDayValidation(text)
                if (isValidation) {
                    signup_birth_validation_layout.setGone()
                } else {
                    signup_birth_validation_layout.setVisible()
                    mSignUpValidationManager.setTextViewNotSuccess(signup_birth_validation, "태어난 일을 정확하게 입력해주세요")
                }
            }
        }


    }

    fun settingsRadioButtonListener() {
        signup_additional_id_radiobutton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                signup_additional_id_edittext.setVisible()
            } else {
                signup_additional_id_edittext.setGone()
            }
        }

        signup_additional_event_radiobutton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                signup_additional_event_edittext.setVisible()
            } else {
                signup_additional_event_edittext.setGone()
            }
        }
    }

    fun settingsCheckBox() {
        // 전체동의 체크박스
        signup_consent_all_radiobutton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                signup_consent_al1_radiobutton.isChecked = true
                signup_consent_al2_radiobutton.isChecked = true
                signup_consent_al3_radiobutton.isChecked = true
                signup_consent_al4_radiobutton.isChecked = true
            } else {
                signup_consent_al1_radiobutton.isChecked = false
                signup_consent_al2_radiobutton.isChecked = false
                signup_consent_al3_radiobutton.isChecked = false
                signup_consent_al4_radiobutton.isChecked = false
            }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {

            // 회원가입하는 버튼
            R.id.signup_play_signup_button -> {

            }

            // ID 중복체크 버튼
            R.id.signup_id_check_button -> {
                checkDuplicateID()
            }

            R.id.signup_address_textview -> {
                signup_address_layout.setVisible()
                address_layout_webview.setVisible()
                val addressLayout = AddressApiManager(address_layout_webview, this)
                addressLayout.initWebView()
            }

            R.id.address_layout_drop_button -> {
                signup_address_layout.setGone()
            }

            R.id.address_layout_input_button -> {
                signup_address_layout.setGone()
                val addressNum = address_layout_addnumber_textview.text.toString()
                var address = address_layout_address_textview.text.toString()
                var detailAddress = address_layout_details_edittext.text.toString()

                // 샛별배송인지 택배배송인지 정한다.
                val shippingType = address.getShippingType()!!
                address += "\n$shippingType"
                signup_address_textview.text = address
                setAddressTextView(signup_address_textview)

                signup_address_detail_edittext.setText(detailAddress)
                signup_address_detail_edittext.setVisible()

                // 샛별배송인지 택배배송인지 구분한다.
                setShippingLayout(shippingType)
                signup_shipping_layout.setVisible()
            }

            R.id.address_layout_back_button -> {
                address_layout_input_detail_layout.setGone()
                address_layout_webview.setVisible()
            }

            R.id.signup_shipping_check_button -> {
                signup_shipping_layout.setGone()
            }

        }
    }

    // EditText가 클릭됐는지 안됐는지를 감지한다.
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            when (v?.id) {
                R.id.signup_id_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_id_edittext")
                    signup_id_validation_layout.setVisible()
                }

                R.id.signup_pw_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_pw_edittext")
                    signup_pw_validation_layout.setVisible()
                }

                R.id.signup_pw_check_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_pw_check_edittext")
                    signup_pw_check_validation_layout.setVisible()
                }

                R.id.signup_name_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_name_edittext")
                }

                R.id.signup_email_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_email_edittext")
                }

                R.id.signup_phone_num_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_phone_num_edittext")
                }

                R.id.signup_birth_year_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_birth_year_edittext")
                }

                R.id.signup_birth_month_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_birth_month_edittext")
                }

                R.id.signup_birth_day_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_birth_day_edittext")
                }

            }
        }

        if (!hasFocus) {
            when (v?.id) {
                R.id.signup_pw_edittext -> {
                    if ((mSignUpValidationManager.mValidationHash["PW_LENGTH"] == false)
                            && (mSignUpValidationManager.mValidationHash["PW_COMBINATION"] == false)) {
                        signup_pw_edittext.background.setTint(mSignUpValidationManager.COLOR_NOT_SUCCESS_RED)
                    }
                }

                R.id.signup_pw_check_edittext -> {
                    if (mSignUpValidationManager.mValidationHash["PW_CHECK"] == false) {
                        signup_pw_check_edittext.background.setTint(mSignUpValidationManager.COLOR_NOT_SUCCESS_RED)
                    }
                }

            }
        }
    }

    // 아이디 중복체크
    fun checkDuplicateID() {
        val id = signup_id_edittext.text.toString()
        val json: JsonObject = JsonObject()
        json.addProperty("id", id)
        mRetrofitClient.checkDuplicateID(json).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val isExist = response.body()?.get("is_exist")?.asString!!
                if (isExist == "Y") {
                    showCustomToast("아이디가 이미 존재합니다.")
                    mSignUpValidationManager.mValidationHash["ID_DUPLICATE"] = false
                    mSignUpValidationManager.setTextViewNotSuccess(signup_id_validation_duplicate)
                } else {
                    showCustomToast("사용가능한 아이디입니다.")
                    mSignUpValidationManager.mValidationHash["ID_DUPLICATE"] = true
                    mSignUpValidationManager.setTextViewSuccess(signup_id_validation_duplicate)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun setAddressTextView(view: TextView) {

        var addressText = view.text
        Log.d(TAG, "[SignUpActivity] - editAddressTextView() : start: $addressText")
        val spanString = SpannableString(addressText)

        if (addressText.contains(STAR_SHIPPING)) {
            val startPoint = addressText.indexOf(STAR_SHIPPING)
            val endPoint = startPoint + STAR_SHIPPING.length

            Log.d(TAG, "[SignUpActivity] - editAddressTextView() : start: $startPoint, end: $endPoint")
            spanString.apply {
                setSpan(ForegroundColorSpan(resources.getColor(R.color.kurly_purple)), startPoint, endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(0.7f), startPoint, endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        } else {
            val startPoint = addressText.indexOf(POST_SHIPPING)
            val endPoint = startPoint + POST_SHIPPING.length
            spanString.apply {
                setSpan(ForegroundColorSpan(resources.getColor(R.color.default_gray)), startPoint, endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(0.7f), startPoint, endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        view.text = spanString
    }

    fun setShippingLayout(type: String) {
        if (type == STAR_SHIPPING) {
            signup_shipping_type_textview.text = "샛별배송 지역입니다."
            signup_shipping_explain_textview.text = "오늘 밤 11시 전까지 주문 시\n다음날 아침 7시 이전 도착합니다!"
            signup_shipping_notice_textview.text = "샛별배송은 휴무 없이 매일 배송합니다"
        } else {
            signup_shipping_type_textview.text = "택배배송 지역입니다."
            signup_shipping_explain_textview.text = "밤 8시 전까지 주문 시\n다음날 도착합니다!"
            signup_shipping_notice_textview.text = "일요일은 배송  휴무로 토요일에는 주문 불가"
        }
    }

    override fun onAddressSelected(address: String, addressNum: String) {
        address_layout_webview.setGone()
        address_layout_address_textview.text = address
        address_layout_addnumber_textview.text = addressNum

        address_layout_input_detail_layout.setVisible()
    }
}