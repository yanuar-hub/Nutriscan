package com.example.nutriscan.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nutriscan.R
import androidx.fragment.app.Fragment
import com.example.nutriscan.databinding.ActivityMainBinding
import com.example.nutriscan.utils.Constanta

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())
        binding.navView.background = null // hide abnormal layer in bottom nav
        binding.navView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navigation_home ->{
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_journal ->{
                    loadFragment(JournalFragment())
                    true
                }
                R.id.navigation_search ->{
                    loadFragment(SearchFragment())
                    true
                }
                R.id.navigation_profile ->{
                    loadFragment(ProfileFragment())
                    true
                }else->{
                    false
                }
            }
        }

        /* ask permission for camera first before launch camera */
        binding.fab.setOnClickListener {
            if (isPermissionGranted(this, Manifest.permission.CAMERA)) {
                val intent = Intent(this@MainActivity, CameraActivity::class.java)
                startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    Constanta.CAMERA_PERMISSION_CODE
                )

            }
        }





    }

    fun isPermissionGranted(context: Context, permission: String) =
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}