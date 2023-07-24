package com.example.shoppinglist_myown_v2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shop_list_table")
data class ShopItemDbModel(
    val name: String = "",
    val count: Int = 0,
    var isEnabled: Boolean = true,
    @PrimaryKey(true)
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0 // Room autogenerate id and add new item only if id = 0
    }
}