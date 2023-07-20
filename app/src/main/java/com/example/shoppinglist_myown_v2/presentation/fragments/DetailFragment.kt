package com.example.shoppinglist_myown_v2.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist_myown_v2.databinding.FragmentDetailBinding
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.presentation.viewmodels.DetailViewModel

private const val KEY_ARG_SHOP_ITEM_ID = "shop_item_id"
private const val INVALID_VALUE = -1

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    // TODO: Rename and change types of parameters
    private var shopItemId: Int = ShopItem.UNDEFINED_ID
    private val isShopItemCanBeInitialized get() = shopItemId != ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shopItemId = it.getInt(KEY_ARG_SHOP_ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isShopItemCanBeInitialized) {
            setUpTextFields()
        }
        binding.saveButton.setOnClickListener {
            if (isShopItemCanBeInitialized) editShopItem() else addShopItem()

        }
        viewModel.anotherThreadsWorksIsDoneLd.observe(viewLifecycleOwner) {
            closeCurrentFragment()
        }

        viewModel.isInputNameInvalidLd.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.tilName.error = "NAME MUST BE AT LEAST ONE SYMBOL"
                Log.d("DetailFragment", "isInputNameInvalidLd value = $it")
            } else {
                binding.tilName.error = null
            }
        }
        viewModel.isInputCountInvalidLd.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.tilCount.error =
                    "COUNT MUST BE AT LEAST ONE SYMBOL AND BE A POSITIVE NUMBER"
            } else {
                binding.tilCount.error = null
            }
        }

        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetError()
            }
        })
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetError()
            }
        })
    }

    // PRIVATE FUNCTIONS
    private fun setUpTextFields() {
        val shopItem = viewModel.getActualShopItemLd(shopItemId).value
        binding.etName.setText(shopItem?.name)
        binding.etCount.setText(shopItem?.count.toString())
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
