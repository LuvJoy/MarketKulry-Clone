package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_explain.manager

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import kotlinx.android.synthetic.main.dialog_share.*
import kotlinx.android.synthetic.main.dialog_share.view.*

class ShareDialogManager (private var context: Context, private var fragment: BaseFragment) {

    fun showDialog(product: ProductDetail) {
        val bottomDialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
        bottomDialog.setOnShowListener { dialog ->
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
            BottomSheetBehavior.from(bottomSheet).skipCollapsed = true
            BottomSheetBehavior.from(bottomSheet).isHideable = true
        }

        val bottomDialogView = LayoutInflater.from(context).inflate(
            R.layout.dialog_share, fragment.bottomSheetContainer)
        bottomDialogView.findViewById<LinearLayout>(R.id.share_dialog_kakao_button).setOnClickListener {
            fragment.showCustomToast("kakao")
        }
        bottomDialogView.findViewById<LinearLayout>(R.id.share_dialog_facebook_button).setOnClickListener {
            fragment.showCustomToast("facebook")
        }
        bottomDialogView.findViewById<LinearLayout>(R.id.share_dialog_line_button).setOnClickListener {
            fragment.showCustomToast("line")
        }
        bottomDialogView.findViewById<LinearLayout>(R.id.share_dialog_twitter_button).setOnClickListener {
            fragment.showCustomToast("twitter")
        }

        Glide.with(fragment)
            .load(product.thumbnailUrl)
            .into(bottomDialogView.share_dialog_product_imageview)

        bottomDialogView.share_dialog_name_textview.text = product.name
        bottomDialogView.share_dialog_overall_textview.text = product.shortContent

        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.show()
    }
}