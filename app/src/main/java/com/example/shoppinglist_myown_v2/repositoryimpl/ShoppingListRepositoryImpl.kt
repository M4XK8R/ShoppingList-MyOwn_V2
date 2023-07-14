package com.example.shoppinglist_myown_v2.data.repositoryimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist_myown_v2.domain.Item
import com.example.shoppinglist_myown_v2.domain.repository.ShoppingListRepository

object ShoppingListRepositoryImpl : ShoppingListRepository {

    private val itemList = mutableListOf<Item>()

    private val itemListLd = MutableLiveData<List<Item>>()

    private var autoIncrementId = 0

    override fun addItem(item: Item) {
        if (item.id == Item.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        itemList.add(item)
        updateList()
    }

    override fun deleteItem(item: Item) {
        itemList.remove(item)
        updateList()
    }

    override fun editItem(item: Item) {
        val currentItem = getItemById(item.id)
        itemList.remove(currentItem)
        addItem(item)
    }

    override fun getItemById(itemId: Int): Item {
        val searchedItem = itemList.find {
            it.id == itemId
        }
        return searchedItem ?: throw RuntimeException("Element with id $itemId not found")
    }

    override fun getItemList(): LiveData<List<Item>> {
        return itemListLd
    }

    private fun updateList(){
        itemListLd.value = itemList.toList()
    }
}