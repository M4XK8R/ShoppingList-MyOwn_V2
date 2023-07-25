package com.example.shoppinglist_myown_v2.domain.usecases

import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.domain.repository.ShopListRepository

class RoomDbAddOrEditShopItemUseCase (private val shopListRepository: ShopListRepository) {
    suspend fun addOrEditShopItem (shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}