package com.joseph.marketkurly_clone.src.db

import androidx.room.*

@Dao
interface CartDAO  {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCart(vararg carts: Cart)

    @Delete
    fun deleteCart(vararg carts: Cart)

    @Query("DELETE FROM cart")
    fun deleteAllCart()

    @Query("SELECT * FROM cart")
    fun loadAllCart(): List<Cart>


}