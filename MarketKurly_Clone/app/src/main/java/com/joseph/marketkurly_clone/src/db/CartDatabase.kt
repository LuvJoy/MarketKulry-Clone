package com.joseph.marketkurly_clone.src.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Cart::class)], version = 1, exportSchema = false)
abstract class CartDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDAO

    companion object {

        private var mAppDatabase: CartDatabase? = null

        fun getInstance(context: Context): CartDatabase? {
            if(mAppDatabase == null) {
                mAppDatabase = Room.databaseBuilder(context.applicationContext,
                    CartDatabase::class.java, "cart-cb").build()

            }
            return mAppDatabase
        }
    }
}
