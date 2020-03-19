package com.example.corona.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.corona.R
import com.example.corona.dao.MaskViewModel
import com.example.corona.databinding.ActivityAddMaskBinding
import com.example.corona.model.MaskData
import kotlinx.android.synthetic.main.activity_add_mask.*
import org.jetbrains.anko.toast
import java.util.jar.Manifest

class AddMaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddMaskBinding
    private val Gallery = 1
    private val Camera = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_add_mask
        )


        val maskViewModel = ViewModelProviders.of(this).get(MaskViewModel::class.java)

        maskViewModel.allMasks.observe(this, Observer { masks ->
            binding.buttonAdd.setOnClickListener {
                maskViewModel.insert(
                    MaskData(
                        (binding.editMaskname.text.toString()),
                        binding.editMaskprice.text.toString(),
                        binding.editMaskdescription.text.toString(),
                        binding.editImg.toString(),
                        binding.editDate.text.toString(),
                        binding.editMaskstart.text.toString()
                    )
                )
                finish()
            }
        })
        binding.imgBtn.setOnClickListener { Gallery() }
        binding.cameraBtn.setOnClickListener { TakePicture() }
        permissionCheck()
    }

    private fun permissionCheck() {
        var cameraPermission : Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        var writeExternalStoragePermission : Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(cameraPermission == PackageManager.PERMISSION_GRANTED && writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED) {
        } else {
           // ActivityCompat.requestPermissions(this ,this, null)
        }
    }

    // 카메라 촬영
    private fun TakePicture() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Camera)
    }

    // 이미지 선택
    private fun Gallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")

        startActivityForResult(intent, Gallery)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Gallery) {
                val dataUri = data?.data
                try {
//                    val bitmap = binding.editImg as Bitmap
//                    MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
//                    binding.editImg.setImageBitmap(bitmap)
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, dataUri)
                    binding.editImg.setImageBitmap(bitmap)
                    Log.v("Gallery@@@", "success")
                } catch (e: Exception) {
                    toast("$e")
                }
            } else {
                Log.e("Gallery@@@", "error")
            }
        }
    }
}


