package com.example.shoppinglist_myown_v2.presentation.screens.recycler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist_myown_v2.R
import com.example.shoppinglist_myown_v2.databinding.FragmentRecyclerBinding
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.presentation.screens.detail.DetailFragment
import com.example.shoppinglist_myown_v2.presentation.rv.ShopListAdapter

class RecyclerFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var shopListAdapter: ShopListAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[RecyclerViewModel::class.java]
    }

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
        setUpRecyclerElementListeners()

        viewModel.shopListLd.observe(viewLifecycleOwner) {
            shopListAdapter.listShopItem = it
        }

        binding.buttonAdd.setOnClickListener { launchDetailFragmentInAddMode() }
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

    private fun setUpRecyclerElementListeners() {
        setUpClickListener()
        setUpLongClickListener()
        setUpOnSwipeListener()
    }

    private fun setUpOnSwipeListener() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopItem = shopListAdapter.listShopItem[viewHolder.adapterPosition]
                viewModel.deleteShopItem(shopItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvShopList)
    }


    private fun setUpClickListener() {
        shopListAdapter.onShopItemClickListenerLambda = {
            launchDetailFragmentInEditMode(it)
        }
    }

    private fun setUpLongClickListener() {
        shopListAdapter.onShopItemLongClickListenerLambda = {
            viewModel.changeIsEnableState(it)
        }
    }

    private fun launchDetailFragmentInEditMode(shopItem: ShopItem) {
//        val args = Bundle().apply {
//            putParcelable(DetailFragment.KEY_ARG_SHOP_ITEM, shopItem)
//        }
        findNavController().navigate(
            RecyclerFragmentDirections.actionRecyclerFragmentToDetailFragment(shopItem)
        )
    }

    private fun launchDetailFragmentInAddMode() {
        findNavController().navigate(RecyclerFragmentDirections.actionRecyclerFragmentToDetailFragment())
    }
}




