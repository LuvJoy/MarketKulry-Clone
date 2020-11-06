package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_image

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.src.activity_image_viewer.ImageViewerActivity
import kotlinx.android.synthetic.main.fragment_detail_tab_image.*
import java.io.ByteArrayOutputStream


class ImageFragment : Fragment(R.layout.fragment_detail_tab_image), View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail_image_imageview.setOnClickListener(this)
        
        Glide.with(this)
            .load("https://sub.marketkulry.shop/img/product1_detail.png")
            .into(detail_image_imageview)

        var a = detail_image_imageview.drawable

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.detail_image_imageview -> {
                val intent = Intent(requireContext(), ImageViewerActivity::class.java)
                startActivity(intent)
            }
        }
    }

}