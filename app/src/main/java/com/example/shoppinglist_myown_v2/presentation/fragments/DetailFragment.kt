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
import com.example.shoppinglist_myown_v2.R
import com.example.shoppinglist_myown_v2.databinding.FragmentDetailBinding
import com.example.shoppinglist_myown_v2.domain.entity.ShopItem
import com.example.shoppinglist_myown_v2.presentation.viewmodels.DetailViewModel

private const val KEY_ARG_SHOP_ITEM = "shop_item"

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    private var shopItem: ShopItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            bundle.getParcelable<ShopItem>(KEY_ARG_SHOP_ITEM)?.let { shopItemFromArgs ->
                shopItem = shopItemFromArgs
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpTextFields()
        setUpEtNameTextChangedListener()
        setUpEtCountTextChangedListener()

        binding.saveButton.setOnClickListener { addOrEditShopItem() }

        // LD Observe
        viewModel.anotherThreadsWorksIsDoneLd.observe(viewLifecycleOwner) {
            closeCurrentFragment()
        }

        viewModel.isInputNameInvalidLd.observe(viewLifecycleOwner) {
            if (it) {
                val errorMessage = context?.getString(R.string.error_input_name)
                setNameInputError(errorMessage)
            } else {
                setNameInputError(null)
            }
        }

        viewModel.isInputCountInvalidLd.observe(viewLifecycleOwner) {
            if (it) {
                val errorMessage = context?.getString(R.string.error_input_count)
                setCountInputError(errorMessage)
            } else {
                setCountInputError(null)
            }
        }
    }


    // PRIVATE FUNCTIONS
    private fun setNameInputError(errorMessage: String?) {
        binding.tilName.error = errorMessage
    }

    private fun setCountInputError(errorMessage: String?) {
        binding.tilCount.error = errorMessage
    }

    private fun setUpEtCountTextChangedListener() {
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetError()
            }
        })
    }

    private fun setUpEtNameTextChangedListener() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetError()
            }
        })
    }

    private fun setUpTextFields() {
        binding.etName.setText(shopItem?.name)
        binding.etCount.setText(shopItem?.count?.toString())
    }

    private fun closeCurrentFragment() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun addOrEditShopItem() {
        viewModel.addOrEditShopItem(
            binding.etName.text.toString(),
            binding.etCount.text.toString(),
            shopItem
        )
    }

    companion object {
        @JvmStatic
        fun newInstanceAddMode() = DetailFragment()

        @JvmStatic
        fun newInstanceEditMode(shopItem: ShopItem) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_ARG_SHOP_ITEM, shopItem)
            }
        }
    }
}
