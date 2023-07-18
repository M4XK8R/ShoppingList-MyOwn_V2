package com.example.shoppinglist_myown_v2.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist_myown_v2.databinding.FragmentDetailBinding
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.presentation.viewmodels.DetailViewModel

private const val KEY_ARG_SHOP_ITEM_ID = "shop_item_id"

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    // TODO: Rename and change types of parameters
    private var shopItemId: Int = ShopItem.UNDEFINED_ID
    private val isShopItemCanBeInitialized get() = shopItemId != ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shopItemId = it.getInt(KEY_ARG_SHOP_ITEM_ID)
        }
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        if (isShopItemCanBeInitialized) {
            viewModel.setValueToShopItemLdById(shopItemId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.saveButton.setOnClickListener {
            if (isShopItemCanBeInitialized) editShopItem() else addShopItem()
        }
        viewModel.anotherThreadsWorksIsDoneLd.observe(viewLifecycleOwner) {
            closeCurrentFragment()
        }
    }

    private fun closeCurrentFragment() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun addShopItem() {
        viewModel.addShopItem(
            binding.etName.text.toString(),
            binding.etCount.text.toString()
        )
    }

    private fun editShopItem() {
        viewModel.editShopItem(
            binding.etName.text.toString(),
            binding.etCount.text.toString()
        )
    }


    companion object {
        @JvmStatic
        fun newInstanceAddMode() = DetailFragment()

        @JvmStatic
        fun newInstanceEditMode(shopItemId: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ARG_SHOP_ITEM_ID, shopItemId)
            }
        }
    }
}
