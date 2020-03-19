package com.example.corona.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.corona.model.MaskData

@Dao
interface MaskDao {

    @Query("SELECT * FROM corona_mask")
    fun getAll(): LiveData<MutableList<MaskData>>

    // 중복될 경우 덮어쓴다.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mask: MaskData)

    @Query("DELETE FROM corona_mask")
    fun deleteAll()

    @Query("DELETE FROM corona_mask WHERE maskName=:maskName")
    fun deleteByMaskName(maskName: String)

    @Query("UPDATE corona_mask SET maskName=:newMask, maskPrcie=:newMaskPrice, maskDescription=:newMaskDescrption, maskImg=:newMaskImg, maskDate=:newMaskDate, maskStart=:newMaskStart WHERE maskName=:oldMask")
    fun updateMask(
        oldMask: String,
        newMask: String,
        newMaskPrice: String,
        newMaskDescrption: String,
        newMaskImg: String,
        newMaskDate: String,
        newMaskStart: String
    )

}