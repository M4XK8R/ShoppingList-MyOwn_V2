package com.example.shoppinglist_myown_v2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist_myown_v2.data.repositoryimpl.ShoppingListRepositoryImpl
import com.example.shoppinglist_myown_v2.domain.Item
import com.example.shoppinglist_myown_v2.domain.usecases.DeleteItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.EditItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.GetItemListUseCase

class MainViewModel : ViewModel() {

    private val repository = ShoppingListRepositoryImpl

    private val getItemListUseCase = GetItemListUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)

    private val _itemListLd = MutableLiveData<List<Item>>()
    val itemListLd: LiveData<List<Item>> get() = _itemListLd

    fun deleteItem(item: Item) {
        deleteItemUseCase.deleteItem(item)
    }

    fun changeEnableState(item: Item){
        val currentState = item.isEnabled
        val newItem = item.copy(isEnabled = !currentState)
        editItemUseCase.editItem(newItem)
    }
}