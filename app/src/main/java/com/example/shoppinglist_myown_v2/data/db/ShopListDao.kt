package com.example.shoppinglist_myown_v2.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem

@Dao
interface ShopListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItem: ShopItemDbModel)

    @Delete
    suspend fun deleteShopItem(shopItem: ShopItemDbModel)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun editShopItem(shopItem: ShopItemDbModel)

    @Query("SELECT * from shop_list_table WHERE id =:shopItemId LIMIT 1")
    suspend fun getShopItemById(shopItemId: Int): ShopItemDbModel

    @Query("SELECT * from shop_list_table")
    fun getShopList(): LiveData<List<ShopItemDbModel>>
}