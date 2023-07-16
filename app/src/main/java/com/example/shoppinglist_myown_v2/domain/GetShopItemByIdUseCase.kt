package com.example.shoppinglist_myown_v2.domain

class GetShopItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemById(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItemById(shopItemId)
    }
}