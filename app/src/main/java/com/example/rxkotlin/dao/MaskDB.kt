package com.example.rxkotlin.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Mask::class], version = 1)
abstract class MaskDB : RoomDatabase() {

    abstract fun maskDao(): MaskDao

    companion object {
        @Volatile
        private var INSTANCE: MaskDB? = null

        fun getInstance(context: Context): MaskDB? {
            if (INSTANCE == null) {
                synchronized(MaskDB::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MaskDB::class.java, "MaskDDd"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}