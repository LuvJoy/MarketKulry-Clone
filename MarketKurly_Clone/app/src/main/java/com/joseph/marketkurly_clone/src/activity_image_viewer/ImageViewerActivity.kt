package com.joseph.marketkurly_clone.src.activity_image_viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.davemorrissey.labs.subscaleview.ImageSource
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import kotlinx.android.synthetic.main.activity_image_viewer.*

class ImageViewerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        val imgUrl = intent.extras?.getString("imageUrl")

        imageviewer_scale_imageview.setImage(ImageSource.resource(R.drawable.dummy_image2))
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.imageviewer_close_button -> {
                onBackPressed()
            }
        }
    }
}