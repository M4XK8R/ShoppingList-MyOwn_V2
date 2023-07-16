package com.example.shoppinglist_myown_v2.presentation.rv

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_myown_v2.R
import com.example.shoppinglist_myown_v2.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopItemViewHolder>() {

    var listShopItem = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_item_shop_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = listShopItem[position]
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemCount(): Int = listShopItem.size
}