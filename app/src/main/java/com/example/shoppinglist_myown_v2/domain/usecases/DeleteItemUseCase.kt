package com.example.shoppinglist_myown_v2.domain.usecases

import com.example.shoppinglist_myown_v2.domain.Item
import com.example.shoppinglist_myown_v2.domain.repository.ShoppingListRepository

class DeleteItemUseCase(private val shoppingListRepository: ShoppingListRepository) {
    fun deleteItem(item: Item) {
        shoppingListRepository.deleteItem(item)
    }

}