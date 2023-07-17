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
import com.example.shoppinglist_myown_v2.domain.ShopItem
import com.example.shoppinglist_myown_v2.presentation.MainViewModel
import com.example.shoppinglist_myown_v2.presentation.rv.ShopListAdapter

private lateinit var binding: FragmentRecyclerBinding
private lateinit var viewModel: MainViewModel
private lateinit var shopListAdapter: ShopListAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}