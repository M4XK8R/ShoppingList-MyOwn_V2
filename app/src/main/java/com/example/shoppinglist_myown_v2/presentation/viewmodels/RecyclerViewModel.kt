package com.example.shoppinglist_myown_v2.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppinglist_myown_v2.data.ShopListRepositoryImpl
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.domain.usecases.DeleteShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.EditShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.GetShopListUseCase

class RecyclerViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeIsEnableState(shopItem: ShopItem) {
        val currentState = shopItem.isEnabled
        val newShopItem = shopItem.copy(isEnabled = !currentState)
        editShopItemUseCase.editShopItem(newShopItem)
    }
}
