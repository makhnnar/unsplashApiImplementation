package com.pedrogomez.develepersfinder.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedrogomez.develepersfinder.models.db.HitTable

@Database(entities = [HitTable::class], version = 2)
abstract class HitsDataBase : RoomDatabase() {

        abstract fun hits(): HitsDao

}