package com.example.shoppinglist_myown_v2.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.example.shoppinglist_myown_v2.data.db.ShopListDao
import com.example.shoppinglist_myown_v2.data.db.ShopListDatabase
import com.example.shoppinglist_myown_v2.data.db.ShopListMapper
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.domain.repository.ShopListRepository

class ShopListDbRepositoryImpl(application: Application) : ShopListRepository {

    private val shopListDao = ShopListDatabase.getInstance(application).getShopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItemById(shopItemId: Int): ShopItem {
        val searchedShopItemDbModel = shopListDao.getShopItemById(shopItemId)
        return mapper.mapDbModelToEntity(searchedShopItemDbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        val currentLiveData = shopListDao.getShopList()
        val transformedLiveData = currentLiveData.map {
            mapper.mapListDbModelToListEntity(it)
        }
        return transformedLiveData
    }


//    override fun getShopList(): LiveData<List<ShopItem>> =
//        MediatorLiveData<List<ShopItem>>().apply {
//            addSource(shopListDao.getShopList()) {
//                value = mapper.mapListDbModelToListEntity(it)
//            }
//        }
}