package com.example.rxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rxkotlin.dao.MaskViewModel
import com.example.rxkotlin.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)

        val maskViewModel = ViewModelProviders.of(this).get(MaskViewModel::class.java)
        val oldMask = intent.getStringExtra("old_mask")

        // update new mask
        maskViewModel.allMasks.observe(this, Observer { masks ->
            binding.buttonUpdate.setOnClickListener {
                maskViewModel.updateMask(
                    oldMask,
                    binding.editNewmask.text.toString(),
                    binding.editNewprice.text.toString(),
                    binding.editNewdescription.text.toString(),
                    binding.editImg.drawable.toString()
                )
                finish()
            }
        })
    }
}
