package com.pedrogomez.develepersfinder.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedrogomez.develepersfinder.models.db.UserPicture

@Database(entities = [UserPicture::class], version = 2)
abstract class DataBase : RoomDatabase() {

        abstract fun hits(): DBDao

}