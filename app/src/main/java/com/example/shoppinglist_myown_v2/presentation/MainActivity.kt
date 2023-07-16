package com.example.shoppinglist_myown_v2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist_myown_v2.databinding.ActivityMainBinding
import com.example.shoppinglist_myown_v2.presentation.rv.ShopListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        shopListAdapter = ShopListAdapter()
//        binding.rvShopList.adapter = shopListAdapter
//
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        viewModel.shopList.observe(this) {
//            shopListAdapter.listShopItem = it
//        }
    }

    private fun setUpRecyclerView() {}
}
