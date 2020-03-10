package com.example.rxkotlin.dao

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.example.rxkotlin.R

@Entity(tableName = "corona_mask")
//data class Mask(
//    @PrimaryKey(autoGenerate = true) val id: Long = 0,
//    var maskName: String = "",
//    var maskPrice: String = "",
//    var maskDescription: String = "",
//    var maskImg: String = ""
//)
data class Mask(
    @PrimaryKey @ColumnInfo(name="maskname") val maskName: String,
    val maskPrcie: String,
    val maskDescription: String
//    val maskImg: String
)

object MyBind {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImageUrl(view: ImageView, maskImg: String) {
        Glide.with(view.context).load(maskImg).into(view)
    }
}
