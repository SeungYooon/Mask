package com.example.rxkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxkotlin.dao.Mask
import com.example.rxkotlin.dao.MaskDB
import com.example.rxkotlin.databinding.ActivityAddMaskBinding
import kotlinx.android.synthetic.main.activity_add_mask.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AddMaskActivity : AppCompatActivity() {

    private var maskDb: MaskDB? = null
    private lateinit var binding: ActivityAddMaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_mask)

        maskDb = MaskDB.getInstance(this)

        val addRunnable = Runnable {
            val newMask = Mask()
            newMask.maskName = edit_name.text.toString()
            newMask.maskPrice = edit_price.text.toString()
            newMask.maskDescription = edit_description.text.toString()
            newMask.maskImg = img_select.adjustViewBounds.toString()

            maskDb?.maskDao()?.insert(newMask)
        }

        binding.btnAdd.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            startActivity<MainActivity>()
            finish()
        }
        binding.imgSelect.setOnClickListener {
            toast("Click")
        }
    }

    override fun onDestroy() {
        MaskDB.destroyInstance()
        super.onDestroy()
    }
}


