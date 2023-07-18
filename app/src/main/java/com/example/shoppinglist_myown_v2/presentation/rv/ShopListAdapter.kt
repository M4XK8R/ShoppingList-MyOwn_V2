package com.example.shoppinglist_myown_v2.presentation.rv

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_myown_v2.databinding.ViewHolderItemShopDisabledBinding
import com.example.shoppinglist_myown_v2.databinding.ViewHolderItemShopEnabledBinding
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopItemViewHolder>() {
    private var countOfOnCreateViewHolder = 0


    var onShopItemLongClickListenerLambda: ((ShopItem) -> Unit)? = null
    var onShopItemClickListenerLambda: ((ShopItem) -> Unit)? = null

    var listShopItem = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder ${countOfOnCreateViewHolder++}")
        val binding = when (viewType) {
            VIEW_TYPE_ENABLED -> ViewHolderItemShopEnabledBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

            VIEW_TYPE_DISABLED -> ViewHolderItemShopDisabledBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = listShopItem[position]
        holder.binding.apply {
           root.setOnClickListener {
               onShopItemClickListenerLambda?.invoke(shopItem)
           }
            root.setOnLongClickListener {
                onShopItemLongClickListenerLambda?.invoke(shopItem)
                true
            }
            when (this) {
                is ViewHolderItemShopEnabledBinding -> {
                    tvName.text = shopItem.name
                    tvCount.text = shopItem.count.toString()
                }

                is ViewHolderItemShopDisabledBinding -> {
                    tvName.text = shopItem.name
                    tvCount.text = shopItem.count.toString()
                }
            }
        }
    }

    override fun getItemCount(): Int = listShopItem.size

    override fun getItemViewType(position: Int): Int {
        val isStateEnabled = listShopItem[position].isEnabled
        return when {
            isStateEnabled -> VIEW_TYPE_ENABLED
            !isStateEnabled -> VIEW_TYPE_DISABLED
            else -> throw RuntimeException(
                "Property isStateEnabled (now = $isStateEnabled) must be initialized"
            )
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val POOL_SIZE_SUITABLE = 10
    }
}