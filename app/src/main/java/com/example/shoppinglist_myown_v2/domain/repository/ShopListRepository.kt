package com.example.shoppinglist_myown_v2.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItemById(shopItemId: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
}