package com.example.rxkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxkotlin.dao.Mask
import com.example.rxkotlin.dao.MaskDB
import com.example.rxkotlin.databinding.ActivityAddMaskBinding
import kotlinx.android.synthetic.main.activity_add_mask.*
import org.jetbrains.anko.startActivity

class AddMaskActivity : AppCompatActivity() {

    private var maskDb: MaskDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddMaskBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_mask)


        maskDb = MaskDB.getInstance(this)

        val addRunnable = Runnable {
            val newMask = Mask()
            newMask.maskName = edit_name.text.toString()
            newMask.maskPrice = edit_price.textAlignment.toString().toInt()
            newMask.maskDescription = edit_description.text.toString()
            maskDb?.maskDao()?.insert(newMask)
        }

        binding.btnAdd.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onDestroy() {
        MaskDB.destroyInstance()
        super.onDestroy()
    }
}


