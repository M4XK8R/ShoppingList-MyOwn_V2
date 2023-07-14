package com.example.shoppinglist_myown_v2.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shoppinglist_myown_v2.domain.Item
import com.example.shoppinglist_myown_v2.domain.repository.ShoppingListRepository

class GetItemListUseCase(private val shoppingListRepository: ShoppingListRepository) {
    fun getItemList(): LiveData<List<Item>> {
        return shoppingListRepository.getItemList()
    }
}