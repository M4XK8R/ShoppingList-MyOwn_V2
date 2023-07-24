package com.example.shoppinglist_myown_v2.data.db

import com.example.shoppinglist_myown_v2.domain.entity.ShopItem

class ShopListMapper {

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem =
        ShopItem(
            name = shopItemDbModel.name,
            count = shopItemDbModel.count,
            isEnabled = shopItemDbModel.isEnabled,
            id = shopItemDbModel.id
        )

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel =
        ShopItemDbModel(
            name = shopItem.name,
            count = shopItem.count,
            isEnabled = shopItem.isEnabled,
            id = shopItem.id
        )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }


}
