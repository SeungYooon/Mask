package com.example.rxkotlin.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxkotlin.R
import com.example.rxkotlin.dao.Mask


class MaskAdapter(private var context: Context, private val maskitems: List<Mask>) :
    RecyclerView.Adapter<MaskAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskAdapter.ViewHolder {
//        return ViewHolder(
//            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.mask_item, parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int = maskitems.size
//
//    override fun onBindViewHolder(holder: MaskAdapter.ViewHolder, position: Int) {
//        val item = maskitems[position]
//        holder.apply {
//            bind(item)
//            itemView.tag = item
//        }
//    }
//
//    class ViewHolder(private val binding: MaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: Mask) {
//            binding.apply {
//                mask?.maskName = item.maskName
//                mask?.maskPrice = item.maskPrice
//                mask?.maskDescription = item.maskDescription
//            }
//        }
//
//    }
//}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mask_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = maskitems.size

    override fun onBindViewHolder(holder: MaskAdapter.ViewHolder, position: Int) {
        holder.bind(maskitems[position])
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val Mname = itemView?.findViewById<TextView>(R.id.mask_name)
        val Mprice = itemView?.findViewById<TextView>(R.id.mask_price)
        val Mdescription = itemView?.findViewById<TextView>(R.id.mask_description)

        fun bind(mask: Mask) {
            Mname?.text = mask.maskName
            Mprice?.text = mask.maskPrice.toString()
            Mdescription?.text = mask.maskDescription
        }

    }
}



