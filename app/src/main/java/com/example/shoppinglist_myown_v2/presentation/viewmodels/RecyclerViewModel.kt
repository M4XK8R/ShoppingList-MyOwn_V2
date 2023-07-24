package com.example.shoppinglist_myown_v2.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist_myown_v2.data.repository.ShopListDbRepositoryImpl
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.domain.usecases.DeleteShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.EditShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.GetShopListUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.RoomDbAddOrEditShopItemUseCase
import kotlinx.coroutines.launch

class RecyclerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopListDbRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val addOrEditShopItemUseCase = RoomDbAddOrEditShopItemUseCase(repository)

    val shopListLd = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeIsEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val currentState = shopItem.isEnabled
            val newShopItem = shopItem.copy(isEnabled = !currentState)
            addOrEditShopItemUseCase.addOrEditShopItem(newShopItem)
        }
    }
}
