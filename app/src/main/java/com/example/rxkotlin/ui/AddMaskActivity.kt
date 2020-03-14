package com.example.rxkotlin.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxkotlin.R
import com.example.rxkotlin.model.Mask
import com.example.rxkotlin.dao.MaskViewModel
import com.example.rxkotlin.databinding.ActivityAddMaskBinding
import org.jetbrains.anko.toast

class AddMaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddMaskBinding
    private val Gallery = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_add_mask
        )

        val maskViewModel = ViewModelProviders.of(this).get(MaskViewModel::class.java)

        maskViewModel.allMasks.observe(this, Observer { masks ->
            binding.buttonAdd.setOnClickListener {
                maskViewModel.insert(
                    Mask(
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
                    val bitmap = binding.editImg as Bitmap
                        MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    binding.editImg.setImageBitmap(bitmap)
                    Log.v("Gallery", "success")
                } catch (e: Exception) {
                    toast("$e")
                }
            } else {
                Log.e("Gallery", "error")
            }
        }
    }
}


