package com.example.rxkotlin.dao

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.example.rxkotlin.R

@Entity(tableName = "corona_mask")
data class Mask(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    var maskName: String = "",
    var maskPrice: String = "",
    var maskDescription: String = "",
    var maskImg: String = "https://t1.daumcdn.net/cfile/tistory/2511E03B577BB58733"
)

object MyBind {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImageUrl(view: ImageView, maskImg: String) {
        Glide.with(view.context).load(maskImg).into(view)
    }
}
//    @PrimaryKey(autoGenerate = true) var id: Long?,
//    @ColumnInfo(name = "maskname") var maskName: String?,
//    @ColumnInfo(name = "maskprice") var maskPrice: Int,
//    @ColumnInfo(name = "maskdescription") var maskDescription: String?,
//    @ColumnInfo(name = "maskimage") var maskImage: Int
////    @ColumnInfo(name = "maskurl") var maskUrl: String?,
////    @ColumnInfo(name = "masksaledate") var maskSaleDate: String?
//) {
//    //    constructor() : this(null, "", 0, "", 0, "", "")
//    constructor() : this(null, "", 0, "", 0)

