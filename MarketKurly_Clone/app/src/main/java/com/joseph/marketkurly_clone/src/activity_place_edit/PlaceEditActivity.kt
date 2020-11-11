package com.joseph.marketkurly_clone.src.activity_place_edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import kotlinx.android.synthetic.main.actionbar_inner_page_top.view.*
import kotlinx.android.synthetic.main.activity_place_edit.*

class PlaceEditActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_edit)

        initActionbar()
        initListener()
    }

    fun initActionbar() {
        place_edit_actionbar.ab_inner_toolbar.title = "받으실 장소 입력"
        place_edit_actionbar.ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initListener() {
        place_edit_check_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.place_edit_check_button -> {

                var checkedPlace = ""
                var entranceType = ""
                var entrancePassword = ""

                when(place_edit_place_radiogroup.checkedRadioButtonId) {
                    R.id.place_edit_place_door_radiobutton -> {
                        checkedPlace = "문 앞"
                        when(place_edit_place_door_radiogroup.checkedRadioButtonId){
                            R.id.place_edit_place_door_password_radiobutton -> {
                                entranceType = "공동현관 비밀번호"
                                entrancePassword = place_edit_place_door_password_edittext.text.toString()
                            }
                            R.id.place_edit_place_door_etc_radiobutton -> {

                            }
                            R.id.place_edit_place_door_free_radiobutton ->{

                            }

                        }
                    }

                    R.id.place_edit_place_office_radiobutton -> {
                        checkedPlace = "경비실"
                    }
                    R.id.place_edit_place_etc_radiobutton -> {
                        checkedPlace = "기타 장소"
                    }
                    R.id.place_edit_place_postbox_radiobutton -> {
                        checkedPlace = "택배함"
                    }
                }


                var place = OrderPlace(
                    entranceEtc = "",
                    entranceFree = "",
                    entrancePw = entrancePassword,
                    etcInfo = "",
                    mailboxInfo = "",
                    place = checkedPlace,
                    placeInfo = "$checkedPlace [$entranceType]",
                    securityInfo = ""
                )

                var intent = Intent()
                intent.putExtra("placeData", place)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}