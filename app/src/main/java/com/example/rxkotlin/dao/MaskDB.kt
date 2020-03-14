package com.example.rxkotlin.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rxkotlin.model.Mask
import kotlinx.coroutines.CoroutineScope

// shema가 바꼈을때 version 업데이트 해줘야하나봄
@Database(entities = [Mask::class], version = 2, exportSchema = false)
abstract class MaskDB : RoomDatabase() {

    abstract fun maskDao(): MaskDao

    companion object {
        @Volatile
        private var INSTANCE: MaskDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MaskDB {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MaskDB::class.java,
                        "mask_db"
                    ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
