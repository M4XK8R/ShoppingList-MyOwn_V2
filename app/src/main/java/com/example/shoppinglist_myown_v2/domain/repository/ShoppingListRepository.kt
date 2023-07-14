package com.example.shoppinglist_myown_v2.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist_myown_v2.domain.Item

interface ShoppingListRepository {
    fun addItem(item: Item)
    fun deleteItem(item: Item)
    fun editItem(item: Item)
    fun getItemById(itemId: Int): Item
    fun getItemList(): LiveData<List<Item>>
}