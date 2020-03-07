package com.example.rxkotlin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MaskDao {

    @Query("SELECT * FROM mask_table ORDER BY maskname ASC")
    fun getAll(): List<Mask>

    @Insert(onConflict = REPLACE)
    fun insert(mask: Mask)

    @Query("DELETE from mask_table")
    fun deleteAll()
}