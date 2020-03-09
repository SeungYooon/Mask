package com.example.rxkotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxkotlin.dao.Mask
import com.example.rxkotlin.dao.MaskDB
import com.example.rxkotlin.databinding.ActivityMainBinding
import com.example.rxkotlin.util.MaskAdapter
import org.jetbrains.anko.startActivity
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private var maskDb: MaskDB? = null
    private var maskList = ArrayList<Mask>()
    private lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: MaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        maskDb = MaskDB.getInstance(this)
        mAdapter = MaskAdapter(this, maskList)
        binding.recyclerView.adapter = mAdapter

        maskDb = MaskDB.getInstance(this)

        val r = Runnable {
            try {
                maskList = maskDb?.maskDao()?.getAll()!! as ArrayList<Mask>
                mAdapter = MaskAdapter(this, maskList)
                mAdapter.notifyDataSetChanged()

                // 초기 데이터 삽입
                maskList.add(
                    Mask(
                        null,
                        getString(R.string.aeremall),
                        getString(R.string.akmall),
                        "3000원",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSMqiB-IKRhbIEgAA_qx9FlUgaYdDVMPgH_-wb1obxAdUH7OV9H"
                    )
                )
                binding.recyclerView.adapter = mAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.setHasFixedSize(true)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        this,
                        DividerItemDecoration.VERTICAL
                    )
                )
            } catch (e: Exception) {
                Log.v("Runnable Error", "error: + $e")
            }
        }

        val thread = Thread(r)
        thread.start()

        binding.fabBtn.setOnClickListener {
            startActivity<AddMaskActivity>()
            finish()
        }
    }

    override fun onDestroy() {
        MaskDB.destroyInstance()
        maskDb = null
        super.onDestroy()
    }
}


