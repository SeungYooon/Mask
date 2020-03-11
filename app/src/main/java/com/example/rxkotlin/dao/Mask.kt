package com.example.rxkotlin.dao

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide

@Entity(tableName = "corona_mask")
data class Mask(
    @PrimaryKey @ColumnInfo(name = "maskname") val maskName: String,
    val maskPrcie: String,
    val maskDescription: String,
    val maskImg: String
)

object MyBind {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImageUrl(view: ImageView, maskImg: String) {
        Glide.with(view.context).load(maskImg).override(1024).into(view)
    }
}
