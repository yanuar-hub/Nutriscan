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
import com.example.nutriscan.R
import com.example.nutriscan.databinding.FragmentJournalBinding
import com.example.nutriscan.viewmodel.JournalViewModel

class JournalFragment : Fragment() {

    private lateinit var viewModel: JournalViewModel
    private lateinit var binding:FragmentJournalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* activate options menu in fragments */
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentJournalBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(JournalViewModel::class.java)
        /*Implement options menu to fragments*/
        (activity as MainActivity).setSupportActionBar(binding.toolbar)

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
}