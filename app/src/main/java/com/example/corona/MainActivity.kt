package com.example.corona

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.corona.dao.MaskViewModel
import com.example.corona.databinding.ActivityMainBinding
import com.example.corona.ui.AddMaskActivity
import com.example.corona.ui.MaskAdapter
import com.example.corona.ui.UpdateActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), MaskAdapter.UpdateMaskListener {

    lateinit var oldMask: String
    lateinit var oldMaskPrice: String
    lateinit var oldMaskDescription: String
    lateinit var oldMaskImg: String
    lateinit var oldMaskDate: String
    lateinit var oldMaskStart: String
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = initRecycler()

        val maskViewModel = ViewModelProviders.of(this).get(MaskViewModel::class.java)
        maskViewModel.allMasks.observe(this, Observer { masks ->
            masks?.let {
                adapter.setMasks(it)
            }

            // 모든 데이터 삭제
            binding.deleteBtn.setOnClickListener {
                alert("삭제", "목록 삭제?") {
                    positiveButton("Yes") { maskViewModel.deleteMasks().let { toast("Yes") } }

                    negativeButton("No") { toast("No") }
                }.show()
            }
        })
        // 새로운 데이터 추가
        binding.fabBtn.setOnClickListener {
            startActivity<AddMaskActivity>()
            toast("add mask")
        }
    }

    // adapter, recyclrview 초기화
    private fun initRecycler(): MaskAdapter {

        val adapter = MaskAdapter(this, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        return adapter
    }


    // update mask
    override fun updateMask(position: Int) {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("old_mask", oldMask)
        startActivity(intent)
    }

    override fun getOldMask(
        maskName: String,
        maskPrice: String,
        maskDescription: String,
        maskImg: String,
        maskDate: String,
        maskStart: String
    ) {
        oldMask = maskName
        oldMaskPrice = maskPrice
        oldMaskDescription = maskDescription
        oldMaskImg = maskImg
        oldMaskDate = maskDate
        oldMaskStart = maskStart
    }
}



