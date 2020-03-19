package com.example.corona.model
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
@Entity(tableName = "corona_mask")
data class MaskData(
    @PrimaryKey @ColumnInfo(name = "maskname") val maskName: String,
    val maskPrcie: String,
    val maskDescription: String,
    val maskImg: String,
    val maskDate: String,
    val maskStart: String
)

object MyBind {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, maskImg: String) {
        Glide.with(view.context).load(maskImg).override(1024).into(view)
    }
}
