package com.example.rxkotlin.dao

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(tableName = "mask_table")
class Mask(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "maskname") var maskName: String?,
    @ColumnInfo(name = "maskprice") var maskPrice: Int,
    @ColumnInfo(name = "maskdescription") var maskDescription: String?
//    @ColumnInfo(name = "maskimage") var maskImage: Int,
//    @ColumnInfo(name = "maskurl") var maskUrl: String?,
//    @ColumnInfo(name = "masksaledate") var maskSaleDate: String?
) {
//    constructor() : this(null, "", 0, "", 0, "", "")
constructor() : this(null,"",0,"")

}