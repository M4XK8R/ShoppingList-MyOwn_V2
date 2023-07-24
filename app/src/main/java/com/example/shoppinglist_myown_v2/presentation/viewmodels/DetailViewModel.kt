package com.example.shoppinglist_myown_v2.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist_myown_v2.data.repository.ShopListDbRepositoryImpl
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.domain.usecases.AddShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.EditShopItemUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.GetShopItemByIdUseCase
import com.example.shoppinglist_myown_v2.domain.usecases.RoomDbAddOrEditShopItemUseCase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListDbRepositoryImpl(application)
    private val addOrEditShopItemUseCase = RoomDbAddOrEditShopItemUseCase(repository)

    // LD
    private val _isInputNameInvalidLd = MutableLiveData<Boolean>()
    val isInputNameInvalidLd: LiveData<Boolean> get() = _isInputNameInvalidLd

    private val _isInputCountInvalidLd = MutableLiveData<Boolean>()
    val isInputCountInvalidLd: LiveData<Boolean> get() = _isInputCountInvalidLd

    private val _anotherThreadsWorksIsDoneLd = MutableLiveData<Unit>()
    val anotherThreadsWorksIsDoneLd: LiveData<Unit> get() = _anotherThreadsWorksIsDoneLd


    // FUNCTIONS FOR USING FROM DETAIL FRAGMENT

    fun addOrEditShopItem(
        inputName: String?,
        inputCount: String?,
        currentShopItem: ShopItem?
    ) {
        viewModelScope.launch {
            val name = parseName(inputName)
            val count = parseCount(inputCount)
            val isFieldsValid = isInputValid(name, count)
            if (isFieldsValid) {
                val shopItem = currentShopItem?.copy(name = name, count = count)
                    ?: ShopItem(name, count)
                addOrEditShopItemUseCase.addOrEditShopItem(shopItem)
                _anotherThreadsWorksIsDoneLd.value = Unit
            }
        }
    }

    fun resetError() {
        _isInputNameInvalidLd.value = false
        _isInputCountInvalidLd.value = false
    }

    // PRIVATE FUNCTIONS

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