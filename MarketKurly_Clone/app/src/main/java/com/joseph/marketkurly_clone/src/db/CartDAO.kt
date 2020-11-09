package com.joseph.marketkurly_clone.src.db

import androidx.room.*

@Dao
interface CartDAO  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(vararg carts: Cart)

    @Delete
    suspend fun deleteCart(vararg carts: Cart)

    @Query("DELETE FROM cart")
    suspend fun deleteAllCart()

    @Query("SELECT * FROM cart")
    suspend fun loadAllCart(): List<Cart>


}