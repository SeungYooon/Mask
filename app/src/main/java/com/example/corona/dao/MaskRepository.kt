package com.example.corona.dao

import androidx.lifecycle.LiveData
import com.example.corona.model.MaskData

class MaskRepository(private val maskDao: MaskDao) {
    val allMasks: LiveData<MutableList<MaskData>> = maskDao.getAll()

    suspend fun insert(mask: MaskData) {
        maskDao.insert(mask)
    }

    fun deleteMasks() {
        maskDao.deleteAll()
    }

    fun updateMask(
        oldMask: String,
        newMask: String,
        newMaskPrice: String,
        newMaskDescription: String,
        newMaskImg: String,
        newMaskDate: String,
        newMaskStart: String
    ) {
        maskDao.updateMask(
            oldMask,
            newMask,
            newMaskPrice,
            newMaskDescription,
            newMaskImg,
            newMaskDate,
            newMaskStart
        )
    }
}