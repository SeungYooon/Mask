package com.example.rxkotlin

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxkotlin.dao.Mask
import com.example.rxkotlin.dao.MaskViewModel
import com.example.rxkotlin.databinding.ActivityAddMaskBinding
import kotlinx.android.synthetic.main.activity_add_mask.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.util.jar.Manifest

class AddMaskActivity : AppCompatActivity() {
k
    lateinit var binding: ActivityAddMaskBinding
    private val Gallery = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_mask)

        val maskViewModel = ViewModelProviders.of(this).get(MaskViewModel::class.java)

        maskViewModel.allMasks.observe(this, Observer { masks ->
            binding.buttonAdd.setOnClickListener {
                maskViewModel.insert(
                    Mask(
                        (binding.editMaskname.text.toString()),
                        binding.editMaskprice.text.toString(),
                        binding.editMaskdescription.text.toString(),
                        binding.editImg.drawable.toString()
                    )
                )
                finish()
            }
        })
        binding.imgBtn.setOnClickListener { openGalleryForImage() }
    }

    // 이미지 선택
    private fun openGalleryForImage() {
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
                    val bitmap: Bitmap =
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

//    private var maskDb: MaskDB? = null
//    private lateinit var binding: ActivityAddMaskBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_mask)
//
//        maskDb = MaskDB.getInstance(this)
//
//        val addRunnable = Runnable {
//            val newMask = Mask()
//            newMask.maskName = edit_name.text.toString()
//            newMask.maskPrice = edit_price.text.toString()
//            newMask.maskDescription = edit_description.text.toString()
//            newMask.maskImg = img_select.adjustViewBounds.toString()
//
//            maskDb?.maskDao()?.insert(newMask)
//        }
//
//        binding.btnAdd.setOnClickListener {
//            val addThread = Thread(addRunnable)
//            addThread.start()
//
//            startActivity<MainActivity>()
//            finish()
//        }
//        binding.imgSelect.setOnClickListener {
//            toast("Click")
//        }
//    }
//
//    override fun onDestroy() {
//        MaskDB.destroyInstance()
//        super.onDestroy()
//    }
//}
//
//
