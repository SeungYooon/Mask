package com.example.corona.dao

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.corona.model.MaskData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MaskRepository
    val allMasks: LiveData<MutableList<MaskData>>

    init {
        val maskDao = MaskDB.getDatabase(application, viewModelScope).maskDao()
        repository = MaskRepository(maskDao)
        allMasks = repository.allMasks
    }

    // AAC의 room이라 IO 처리
    fun insert(mask: MaskData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(mask)
    }

    fun deleteMasks() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteMasks()
    }

    fun updateMask(
        oldMask: String,
        newMask: String,
        newMaskDescription: String,
        newMaskPrice: String,
        newMaskimg: String,
        newMaskDate: String,
        newMaskStart: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateMask(
            oldMask,
            newMask,
            newMaskPrice,
            newMaskDescription,
            newMaskimg,
            newMaskDate,
            newMaskStart
        )
    }
}