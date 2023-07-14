package com.example.shoppinglist_myown_v2.domain.usecases

import com.example.shoppinglist_myown_v2.domain.Item
import com.example.shoppinglist_myown_v2.domain.repository.ShoppingListRepository

class EditItemUseCase(private val shoppingListRepository: ShoppingListRepository) {
    fun editItem(item: Item) {
        shoppingListRepository.editItem(item)
    }
}