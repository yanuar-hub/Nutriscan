package com.example.nutriscan.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutriscan.R
import com.example.nutriscan.adapter.SearchResultAdapter
import com.example.nutriscan.databinding.FragmentSearchBinding
import com.example.nutriscan.model.Food
import com.example.nutriscan.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter : SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* activate options menu in fragments */
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        /*Implement options menu to fragments*/
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

        showLoading(true)
        searchAdapter = SearchResultAdapter()
        searchAdapter.notifyDataSetChanged()
        searchAdapter.setOnItemClickCallback(object : SearchResultAdapter.OnItemClickCallback {
            override fun onItemClicked(food: Food) {
                showMsg("Fitur detail nutrisi belum tersedia")
            }
        })
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)
        binding.apply {
            rvItemSearch.layoutManager = LinearLayoutManager(context)
            rvItemSearch.setHasFixedSize(true)
            rvItemSearch.adapter=searchAdapter
        }
        viewModel.getFood().observe(viewLifecycleOwner){
            if (it!=null){
                searchAdapter.setList(it)
                showLoading(false)
            }
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.activity_main_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_notification -> {
                showMsg("Halaman Notifikasi Tidak Tersedia")
            }
            R.id.toolbar_setting -> {
                showMsg("Halaman Setting Tidak Tersedia")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMsg(message: String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(state:Boolean){
        if (state){
            binding.pbSearch.visibility = View.VISIBLE
        }else{
            binding.pbSearch.visibility = View.GONE
        }
    }
}