package com.example.shoppinglist_myown_v2.domain.entity

data class ShopItem(
    val name: String,
    val count: Int,
    var isEnabled: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
