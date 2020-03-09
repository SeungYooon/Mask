package com.example.rxkotlin.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxkotlin.MainActivity
import com.example.rxkotlin.R
import com.example.rxkotlin.dao.Mask
import com.example.rxkotlin.databinding.MaskItemBinding
import org.jetbrains.anko.AnkoAsyncContext


class MaskAdapter(private var context: Context, private val maskitems: ArrayList<Mask>) :
    RecyclerView.Adapter<MaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.mask_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = maskitems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mask = maskitems[position]
        holder.apply {
            bind(mask)
            itemView.tag = mask
        }

    }

    class ViewHolder(private val binding: MaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mask: Mask) {
            binding.apply {
                maskitem = mask
            }
        }
    }
}




