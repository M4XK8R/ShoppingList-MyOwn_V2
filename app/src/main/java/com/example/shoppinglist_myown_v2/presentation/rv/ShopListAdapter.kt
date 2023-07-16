package com.example.shoppinglist_myown_v2.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_myown_v2.databinding.ViewHolderItemShopEnabledBinding
import com.example.shoppinglist_myown_v2.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopItemViewHolder>() {

    var listShopItem = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val binding = ViewHolderItemShopEnabledBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = listShopItem[position]
        holder.binding.apply {
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }

    override fun getItemCount(): Int = listShopItem.size
}