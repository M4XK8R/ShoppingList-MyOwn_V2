package com.example.shoppinglist_myown_v2.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist_myown_v2.databinding.FragmentRecyclerBinding
import com.example.shoppinglist_myown_v2.presentation.viewmodels.RecyclerViewModel
import com.example.shoppinglist_myown_v2.presentation.rv.ShopListAdapter

class RecyclerFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var viewModel: RecyclerViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        viewModel = ViewModelProvider(this)[RecyclerViewModel::class.java]
        viewModel.shopList.observe(viewLifecycleOwner) {
            shopListAdapter.listShopItem = it
            Log.d("Recycler", " viewModel.shopListLd UP")
        }
    }

    private fun setUpRecyclerView() {
        shopListAdapter = ShopListAdapter()
        binding.rvShopList.apply {
            adapter = shopListAdapter
            recycledViewPool.apply {
                setMaxRecycledViews(
                    ShopListAdapter.VIEW_TYPE_ENABLED,
                    ShopListAdapter.POOL_SIZE_SUITABLE
                )
                setMaxRecycledViews(
                    ShopListAdapter.VIEW_TYPE_DISABLED,
                    ShopListAdapter.POOL_SIZE_SUITABLE
                )
            }
            shopListAdapter.onShopItemLongClickListenerLambda = {
                viewModel.changeIsEnableState(it)
            }
        }
    }
}

