package com.example.shoppinglist_myown_v2.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopItem(
    val name: String = "",
    val count: Int = 1,
    var isEnabled: Boolean = true,
    var id: Int = UNDEFINED_ID
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
