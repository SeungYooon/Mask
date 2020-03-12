package com.example.rxkotlin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxkotlin.dao.MaskViewModel
import com.example.rxkotlin.databinding.ActivityMainBinding
import com.example.rxkotlin.util.MaskAdapter
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

//    private var maskDb: MaskDB? = null
//    private var maskList = ArrayList<Mask>()
//    private var deleteList = null
//    private lateinit var binding: ActivityMainBinding
//    lateinit var mAdapter: MaskAdapter
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//
//        maskDb = MaskDB.getInstance(this)
////        mAdapter = MaskAdapter(this, maskList)
////        binding.recyclerView.adapter = mAdapter
//
//        maskDb = MaskDB.getInstance(this)
//
//        val r = Runnable {
//            try {
//                maskList = maskDb?.maskDao()?.getAll()!! as ArrayList<Mask>
//                mAdapter = MaskAdapter(this, maskList)
//                mAdapter.notifyDataSetChanged()
//
//                // 초기 데이터 삽입
//                maskList.add(
//                    Mask(
//                        0,
//                        getString(R.string.aeremall),
//                        getString(R.string.akmall),
//                        "3000원",
//                        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSMqiB-IKRhbIEgAA_qx9FlUgaYdDVMPgH_-wb1obxAdUH7OV9H"
//                    )
//                )
//                binding.recyclerView.adapter = mAdapter
//                binding.recyclerView.layoutManager = LinearLayoutManager(this)
//                binding.recyclerView.setHasFixedSize(true)
//
//                binding.recyclerView.addItemDecoration(
//                    DividerItemDecoration(
//                        this,
//                        DividerItemDecoration.VERTICAL
//                    )
//                )
//            } catch (e: Exception) {
//                Log.v("Runnable Error", "error: + $e")
//            }
//        }
//
//        val thread = Thread(r)
//        thread.start()
//
//        val d = Runnable {
//            try {
//                deleteList = maskDb?.maskDao()?.deleteAll() as Nothing
//                mAdapter = MaskAdapter(this, maskList)
//                mAdapter.notifyDataSetChanged()
//
//            } catch (e: Exception) {
//                Log.v("Runnable Error", "error: + $e")
//            }
//        }
//
//
//        binding.deleteBtn.setOnClickListener {
//            val del_thread = Thread(d)
//            del_thread.start()
//        }
//        binding.fabBtn.setOnClickListener {
//            startActivity<AddMaskActivity>()
//            finish()
//        }
//    }
//
//
//    override fun onDestroy() {
//        MaskDB.destroyInstance()
//        maskDb = null
//        super.onDestroy()
//    }
//}


