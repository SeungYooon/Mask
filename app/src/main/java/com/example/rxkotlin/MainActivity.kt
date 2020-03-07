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
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private var maskDb: MaskDB? = null
    private var maskList = listOf<Mask>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        maskDb = MaskDB.getInstance(this)
        val adapter = MaskAdapter(this, maskList)
        binding.recyclerView.adapter = adapter

        maskDb = MaskDB.getInstance(this)

        val r = Runnable {
            try {
                maskList = maskDb?.maskDao()?.getAll()!!
                val adapter = MaskAdapter(this, maskList)
                adapter.notifyDataSetChanged()

                binding.recyclerView.adapter = adapter
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

        fab_btn.setOnClickListener {
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


