package com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review

import com.joseph.marketkurly_clone.src.activity_detail_product.fragments.frag_review.model.Review

object DummyData {

    val review1 = Review(
        title = "맛도 좋고 가격도 착해서 강추합니다~",
        writer = "김*미",
        date = "2020.11.03",
        imageURL = "있음",
        level =  "프렌즈"
    )

    val review2 = Review(
        title = "애들이 참 좋아하네요! 재구매 의사 100%",
        writer = "라*미",
        date = "2020.11.04",
        imageURL = "",
        level =  "프렌즈"
    )

    val review3 = Review(
        title = "크기가 조금 별로였어요",
        writer = "김*연",
        date = "2020.11.12",
        imageURL = "있음",
        level =  "프렌즈"
    )

    val review4 = Review(
        title = "배송도 엄청 빨라서 맛있게 잘 먹었네요",
        writer = "한*진",
        date = "2020.10.31",
        imageURL = "",
        level =  "프렌즈"
    )

    val review5 = Review(
        title = "3번 강추합니다",
        writer = "김*미",
        date = "2020.11.11",
        imageURL = "",
        level =  "프렌즈"
    )

    val review6 = Review(
        title = "언제나 잘 시켜먹고 있어요~",
        writer = "김*",
        date = "2020.11.10",
        imageURL = "있음",
        level =  "프렌즈"
    )

    val review7 = Review(
        title = "맛도 좋고 가격도 착해서 강추합니다~",
        writer = "김*미",
        date = "2020.10.31",
        imageURL = "있음",
        level =  "프렌즈"
    )

    var reviewList = arrayListOf<Review>(review1,review2,review3,review4,review5,review6,review7)

}