package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.model.ProductCompact
import com.joseph.marketkurly_clone.src.util.setStrikeThru
import com.joseph.marketkurly_clone.src.util.toDecimalFormat

class ProductRecyclerViewHolder(private var itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    // Item 기본 뷰
    private var tvTitle: TextView = itemView.findViewById<TextView>(R.id.item_title_textview)
    private var tvPrice: TextView = itemView.findViewById<TextView>(R.id.item_price_textview)
    private var tvSalePrice: TextView = itemView.findViewById<TextView>(R.id.item_sale_price_textview)
    private var tvSalePriceDummy: TextView = itemView.findViewById<TextView>(R.id.item_sale_price_dummytext)
    private var imgProduct: ImageView = itemView.findViewById<ImageView>(R.id.item_image_imageview)

    // 세일 사인표시는 여러곳에서 쓰이기 때문에 레이아웃을 따로 만들어 두었음.
    private var layoutSalePercent: ConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.item_sale_percentage_layout)
    private var tvSalePercent: TextView = layoutSalePercent.findViewById(R.id.salesign_percent_textview)

    // 아이템 클릭 시 ProductDetail 액티비티로 상품 정보를 보내주기 위해 아이템 데이터를 가지고 있는다
    private lateinit var itemData: ProductCompact

    fun onBind(product: ProductCompact) {

        this.itemData = product

        // 세일은 하지 않을수도 있기 때문에 null 체크를 해준다.
        if(itemData.salePercentage != null) {
            tvSalePercent.text = itemData.salePercentage.toString()
            tvSalePrice.text = itemData.salePrice?.toDecimalFormat()
            tvSalePrice.setStrikeThru()
            tvSalePriceDummy.setStrikeThru()
            showSaleLayout()
        }

        tvTitle.text = itemData.title
        tvPrice.text = itemData.price.toDecimalFormat()

        /*
        Glide.with(itemView)
            .load(itemData.imageUrl)
            .into(imgProduct)
        */

    }

    fun showSaleLayout() {
        layoutSalePercent.visibility = View.VISIBLE
        tvSalePrice.visibility = View.VISIBLE
        tvSalePriceDummy.visibility = View.VISIBLE
    }

    override fun onClick(view: View?) {
//        val intent = Intent(this, ProductDetailActivity::class.java)
//        intent.startActivity(intent)
    }

}


