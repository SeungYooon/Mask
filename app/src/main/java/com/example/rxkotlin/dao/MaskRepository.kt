package com.example.rxkotlin.dao

import androidx.lifecycle.LiveData

class MaskRepository(private val maskDao: MaskDao) {
    val allMasks: LiveData<MutableList<Mask>> = maskDao.getAll()

    suspend fun insert(mask: Mask) {
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