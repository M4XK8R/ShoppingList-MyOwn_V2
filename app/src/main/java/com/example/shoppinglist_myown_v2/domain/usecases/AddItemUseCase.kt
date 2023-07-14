package com.example.shoppinglist_myown_v2.domain.usecases

import com.example.shoppinglist_myown_v2.domain.Item
import com.example.shoppinglist_myown_v2.domain.repository.ShoppingListRepository

class AddItemUseCase(private val shoppingListRepository: ShoppingListRepository) {
    fun addItem(item: Item) {
        shoppingListRepository.addItem(item)
    }
}