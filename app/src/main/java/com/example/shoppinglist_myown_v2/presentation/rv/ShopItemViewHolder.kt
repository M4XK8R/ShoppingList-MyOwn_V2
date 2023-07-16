package com.example.shoppinglist_myown_v2.presentation.rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_myown_v2.R

class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName: TextView
    val tvCount: TextView

    init {
        tvName = view.findViewById(R.id.tvName)
        tvCount = view.findViewById(R.id.tvCount)
    }
}