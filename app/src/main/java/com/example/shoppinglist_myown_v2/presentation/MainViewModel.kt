package com.example.shoppinglist_myown_v2.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shoppinglist_myown_v2.data.ShopListRepositoryImpl
import com.example.shoppinglist_myown_v2.domain.DeleteShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.EditShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.GetShopListUseCase
import com.example.shoppinglist_myown_v2.domain.ShopItem

class MainViewModel : ViewModel() {
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
