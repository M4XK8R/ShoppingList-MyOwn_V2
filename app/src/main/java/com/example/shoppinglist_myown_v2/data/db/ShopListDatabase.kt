package com.example.shoppinglist_myown_v2.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database([ShopItemDbModel::class], version = 1)
abstract class ShopListDatabase : RoomDatabase() {

    abstract fun getShopListDao(): ShopListDao

    companion object {

        private var INSTANCE: ShopListDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "db"

        fun getInstance(application: Application): ShopListDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                application,
                ShopListDatabase::class.java,
                DB_NAME
            ).build()
            INSTANCE = db
            return db
        }
    }
}









