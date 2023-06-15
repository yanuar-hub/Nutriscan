package com.example.nutriscan.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import com.example.nutriscan.R
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutriscan.adapter.ScanResultAdapter
import com.example.nutriscan.databinding.ActivityCameraBinding
import com.example.nutriscan.model.Objek
import com.example.nutriscan.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private var imageCapture:ImageCapture?=null
    private lateinit var outputDirectory:File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var dialog:BottomSheetDialog
    private lateinit var rvScan: RecyclerView
    private val list=ArrayList<Objek>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        //Memanggil Data yang sudah dibuat
        list.addAll(getListFood())


        startCamera()
        outputDirectory=outputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        binding.ivBackButton.setOnClickListener{backActivity()}
        binding.fabCamera.setOnClickListener{startTakePhoto()}

    }
    //Memanggil Data yang sudah dibuat
    private fun getListFood(): ArrayList<Objek> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo_detail)
        val dataCalorie = resources.getStringArray(R.array.data_calorie)
        val dataDeskripsi = resources.getStringArray(R.array.data_name)
        val listObjek = ArrayList<Objek>()
        for (i in dataDeskripsi.indices) {
            val objek = Objek(dataPhoto.getResourceId(i, -1), dataCalorie[i], dataDeskripsi[i])
            listObjek.add(objek)
        }
        return listObjek
    }

    private fun showRecyclerList() {
        rvScan.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ScanResultAdapter(list)
        rvScan.adapter = listFoodAdapter

        listFoodAdapter.setOnItemClickCallback(object : ScanResultAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Objek) {
                showSelectedObjek(data)
            }
        })

        listFoodAdapter.setOnItemClickCallback(object : ScanResultAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Objek) {
                val intentToJournal = Intent(this@CameraActivity, JournalFragment::class.java)
                intentToJournal.putExtra("DATA", data)
                startActivity(intentToJournal)
            }
        })
    }

    private fun showSelectedObjek(objek: Objek) {
        Toast.makeText(this, "Kamu memilih " + objek.nama, Toast.LENGTH_SHORT).show()
    }

    private fun outputDirectory():File{
        val mediaDir = externalMediaDirs.firstOrNull()?.let { mFile->
            File(mFile,resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }

        return if (mediaDir!=null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun startTakePhoto(){
        imageCapture=imageCapture?:return
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(Constants.FILE_NAME_FORMAT,
            Locale.getDefault())
                .format(System
                    .currentTimeMillis())+".jpg")

        val outputOption=ImageCapture
            .OutputFileOptions
            .Builder(photoFile)
            .build()

        imageCapture!!.takePicture(
            outputOption, ContextCompat.getMainExecutor(this),
            object :ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val saveUri = Uri.fromFile(photoFile)
                    val msg = "Photo Saved"

                    Toast.makeText(
                        this@CameraActivity,
                        "$msg $saveUri",
                        Toast.LENGTH_LONG
                    ).show()
                    showBottomSheet()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(Constants.TAG,"onError:${exception.message}",exception)
                }

            }
        )
    }

    private fun showBottomSheet(){
        dialog = BottomSheetDialog(this)
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet,null)
        dialog.setContentView(dialogView)
        rvScan = dialogView.findViewById(R.id.rv_item_scan)
        showRecyclerList()
        dialog.show()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({

            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also { mPreview->
                    mPreview.setSurfaceProvider(
                        binding.viewFinder.surfaceProvider
                    )
                }

            imageCapture = ImageCapture.Builder()
                .build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            }catch (e:Exception){
                Log.d(Constants.TAG,"startCamera Fail", e)
            }
        },ContextCompat.getMainExecutor(this))
    }

    private fun backActivity(){
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}