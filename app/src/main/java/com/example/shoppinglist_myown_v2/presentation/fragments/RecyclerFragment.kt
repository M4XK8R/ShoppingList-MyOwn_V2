package com.example.shoppinglist_myown_v2.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist_myown_v2.R
import com.example.shoppinglist_myown_v2.databinding.FragmentRecyclerBinding
import com.example.shoppinglist_myown_v2.presentation.viewmodels.RecyclerViewModel
import com.example.shoppinglist_myown_v2.presentation.rv.ShopListAdapter

class RecyclerFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var shopListAdapter: ShopListAdapter

    private lateinit var viewModel: RecyclerViewModel

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
        setUpRecyclerElementsListeners()

        viewModel = ViewModelProvider(this)[RecyclerViewModel::class.java]
        viewModel.shopList.observe(viewLifecycleOwner) {
            shopListAdapter.listShopItem = it
            Log.d("Recycler", " viewModel.shopListLd UP")
        }

        binding.buttonAdd.setOnClickListener {
            launchDetailFragmentInAddMode()
        }
    }

    // PRIVATE FUNCTIONS

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
        }
    }

    private fun setUpRecyclerElementsListeners() {
        setUpClickListener()
        setUpLongClickListener()
    }

    private fun setUpClickListener() {
        shopListAdapter.onShopItemClickListenerLambda = {
            launchDetailFragmentInEditMode(it.id)
        }
    }

    private fun setUpLongClickListener() {
        shopListAdapter.onShopItemLongClickListenerLambda = {
            viewModel.changeIsEnableState(it)
        }
    }

    private fun launchDetailFragmentInEditMode(shopItemId: Int) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragmentContainer, DetailFragment.newInstanceEditMode(shopItemId))
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailFragmentInAddMode() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragmentContainer, DetailFragment.newInstanceAddMode())
            .addToBackStack(null)
            .commit()
    }


}




