package com.example.shoppinglist_myown_v2.domain

data class Item(
    val name: String,
    val count: Float,
    val isEnabled: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object {
        const val UNDEFINED_ID = -1
    }
}