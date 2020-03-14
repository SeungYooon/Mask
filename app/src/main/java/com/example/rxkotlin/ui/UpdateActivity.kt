package com.example.rxkotlin.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxkotlin.R
import com.example.rxkotlin.dao.MaskViewModel
import com.example.rxkotlin.databinding.ActivityUpdateBinding
import kotlinx.android.synthetic.main.activity_update.*
import org.jetbrains.anko.toast

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private val Picture = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_update
        )

        val maskViewModel = ViewModelProviders.of(this).get(MaskViewModel::class.java)
        val oldMask = intent.getStringExtra("old_mask")

        // update new mask
        maskViewModel.allMasks.observe(this, Observer { masks ->
            binding.buttonUpdate.setOnClickListener {
                maskViewModel.updateMask(
                    oldMask,
                    binding.editMaskname.text.toString(),
                    binding.editMaskdescription.text.toString(),
                    binding.editMaskprice.text.toString(),
                    binding.editImg.toString(),
                    binding.editDate.text.toString(),
                    binding.editMaskstart.text.toString()
                )
                finish()
            }
        })
        binding.imgBtn.setOnClickListener { Picture() }
    }

    // 이미지 선택
    private fun Picture() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")

        startActivityForResult(intent, Picture)
    }
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Picture) {
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