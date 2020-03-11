package com.example.rxkotlin.util

import android.content.ClipDescription
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxkotlin.MainActivity
import com.example.rxkotlin.R
import com.example.rxkotlin.dao.Mask
import com.example.rxkotlin.databinding.MaskItemBinding
import org.jetbrains.anko.AnkoAsyncContext

class MaskAdapter internal constructor(
    private var context: Context,
    private val updateMaskListener: UpdateMaskListener
) :
    RecyclerView.Adapter<MaskAdapter.ViewHolder>() {

    private var masks: MutableList<Mask> = ArrayList<Mask>()

    inner class ViewHolder(private val binding: MaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.conUpdate.setOnClickListener {
                updateMaskListener.getOldMask(
                    binding.maskName.text.toString(),
                    binding.maskPrice.text.toString(),
                    binding.maskDescription.text.toString(),
                    binding.imgMask.adjustViewBounds.toString()
                )
                updateMaskListener.updateMask(adapterPosition)
            }
        }

        fun bind(mask: Mask) {
            binding.apply {
                maskitem = mask

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.mask_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = masks.size

    override fun onBindViewHolder(holder: MaskAdapter.ViewHolder, position: Int) {
        val current = masks[position]
//
        holder.apply {
            bind(current)
            itemView.tag = current
        }
    }

    internal fun setMasks(masks: MutableList<Mask>) {
        this.masks = masks
        notifyDataSetChanged()
    }

    interface UpdateMaskListener {
        fun getOldMask(
            maskName: String,
            maskPrice: String,
            maskDescription: String,
            maskImg: String
        )

        fun updateMask(position: Int)
    }
}



