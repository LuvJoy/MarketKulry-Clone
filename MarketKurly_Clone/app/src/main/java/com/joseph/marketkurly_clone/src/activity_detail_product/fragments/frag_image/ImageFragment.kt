package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_image

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.BaseFragment
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_detail_product.ProductObject
import com.joseph.marketkurly_clone.src.activity_image_viewer.ImageViewerActivity
import kotlinx.android.synthetic.main.fragment_detail_tab_image.*
import java.io.ByteArrayOutputStream


class ImageFragment : BaseFragment(R.layout.fragment_detail_tab_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail_image_imageview.setOnClickListener(this)

        Glide.with(this)
            .load(ProductObject.data?.detailImgUrl)
            .into(detail_image_imageview)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.detail_image_imageview -> {
                val intent = Intent(requireContext(), ImageViewerActivity::class.java)
                intent.putExtra("url", ProductObject.data?.detailImgUrl)
                startActivity(intent)
            }
        }
    }

}