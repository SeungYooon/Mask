package com.example.rxkotlin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MaskDao {

    @Query("SELECT * FROM corona_mask")
    fun getAll(): List<Mask>

    @Insert(onConflict = REPLACE)
    fun insert(mask: Mask)

    @Query("DELETE from corona_mask")
    fun deleteAll()
}