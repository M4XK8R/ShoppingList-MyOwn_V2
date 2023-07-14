package com.example.shoppinglist_myown_v2.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_myown_v2.databinding.ItemShopDisabledBinding
import com.example.shoppinglist_myown_v2.domain.Item

class ItemListAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private val itemList = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemShopDisabledBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        val binding = holder.binding.apply {
            root.setOnClickListener { TODO() }
            root.setOnLongClickListener { TODO() }
            tvName.text = item.name
            tvCount.text = item.count.toString()
        }
    }

    override fun getItemCount(): Int = itemList.size
}