package com.example.shoppinglist_myown_v2.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist_myown_v2.data.ShopListRepositoryImpl
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.domain.usecases.AddShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.EditShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.GetShopItemByIdUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.GetShopListUseCase

class DetailViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopItemByIdUseCase = GetShopItemByIdUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    // LD
    private val _isInputNameInvalidLd = MutableLiveData<Boolean>()
    val isInputNameInvalidLd: LiveData<Boolean> get() = _isInputNameInvalidLd

    private val _isInputCountInvalidLd = MutableLiveData<Boolean>()
    val isInputCountInvalidLd: LiveData<Boolean> get() = _isInputCountInvalidLd

    private val _shopItemLd = MutableLiveData<ShopItem>()
    val shopItemLd: LiveData<ShopItem> get() = _shopItemLd

    // FUNCTIONS FOR USING FROM DETAIL FRAGMENT
    fun setValueToShopItemLdById(shopItemId: Int) {
        val shopItem = getShopItemById(shopItemId)
        _shopItemLd.value = shopItem
    }

    private fun getShopItemById(shopItemId: Int): ShopItem {
        return getShopItemByIdUseCase.getShopItemById(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isFieldsValid = isInputValid(name, count)
        if (isFieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
//            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isFieldsValid = isInputValid(name, count)
        if (isFieldsValid) {
            _shopItemLd.value?.let {
                val newShopItem = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(newShopItem)
//                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String = inputName?.trim() ?: ""

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun isInputValid(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _isInputNameInvalidLd.value = true
            result = false
        }
        if (count <= 0) {
            _isInputCountInvalidLd.value = true
            result = false
        }
        return result
    }
}