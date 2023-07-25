package com.example.shoppinglist_myown_v2.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem

interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItemById(shopItemId: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}