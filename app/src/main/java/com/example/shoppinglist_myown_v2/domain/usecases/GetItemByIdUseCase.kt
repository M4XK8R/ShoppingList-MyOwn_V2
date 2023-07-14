package com.example.shoppinglist_myown_v2.domain.usecases

import com.example.shoppinglist_myown_v2.domain.Item
import com.example.shoppinglist_myown_v2.domain.repository.ShoppingListRepository

class GetItemByIdUseCase(private val shoppingListRepository: ShoppingListRepository) {
    fun getItemById(itemId: Int): Item {
        return shoppingListRepository.getItemById(itemId)
    }
}