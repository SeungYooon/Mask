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

//class MaskAdapter(private var context: Context, private val maskitems: ArrayList<Mask>) :
//    RecyclerView.Adapter<MaskAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        return ViewHolder(
//            DataBindingUtil.inflate(
//                LayoutInflater.from(context),
//                R.layout.mask_item,
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun getItemCount(): Int = maskitems.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val mask = maskitems[position]
//        holder.apply {
//            bind(mask)
//            itemView.tag = mask
//        }
//
//    }
//
//    class ViewHolder(private val binding: MaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(mask: Mask) {
//            binding.apply {
//                maskitem = mask
//            }
//        }
//    }
//}
class MaskAdapter internal constructor(
    context: Context,
    private val updateMaskListener: UpdateMaskListener
) :
    RecyclerView.Adapter<MaskAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var masks: MutableList<Mask> = ArrayList<Mask>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val maskName: TextView = itemView.findViewById(R.id.mask_name)
        val maskPrice: TextView = itemView.findViewById(R.id.mask_price)
        val maskDescription: TextView = itemView.findViewById(R.id.mask_description)

        val updateIcon: ImageView = itemView.findViewById(R.id.img_mask)

        init {

            // img 클릭시 mask update
            updateIcon.setOnClickListener {
                updateMaskListener.getOldMask(
                    maskName.text.toString(),
                    maskPrice.text.toString(),
                    maskDescription.text.toString()
                )
                updateMaskListener.updateMask(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskAdapter.ViewHolder {
        val itemView = inflater.inflate(R.layout.mask_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = masks.size

    override fun onBindViewHolder(holder: MaskAdapter.ViewHolder, position: Int) {
        val current = masks[position]
        holder.maskName.text = current.maskName
        holder.maskPrice.text = current.maskPrcie
        holder.maskDescription.text = current.maskDescription
    }

    internal fun setMasks(masks: MutableList<Mask>) {
        this.masks = masks
        notifyDataSetChanged()
    }

    interface UpdateMaskListener {
        fun getOldMask(maskName: String, maskPrice: String, maskDescription: String)
        fun updateMask(position: Int)
    }
}



