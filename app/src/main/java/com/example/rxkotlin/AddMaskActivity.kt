package com.example.rxkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxkotlin.dao.Mask
import com.example.rxkotlin.dao.MaskViewModel
import com.example.rxkotlin.databinding.ActivityAddMaskBinding

class AddMaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddMaskBinding

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
                        binding.editMaskdescription.text.toString()
                    )
                )
                finish()
            }
        })
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
