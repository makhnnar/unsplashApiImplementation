package com.pedrogomez.develepersfinder.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pedrogomez.develepersfinder.models.db.UserPicture

@Dao
interface DBDao {

    @Query(value = "SELECT * from hit_table WHERE hit_table.isDeleted=:filter ORDER BY created_at_i DESC")
    fun getAllHits(filter:Boolean = false): List<UserPicture>

    @Query(value = "SELECT * from hit_table WHERE hit_table.isDeleted=:filter ORDER BY created_at_i DESC")
    fun observeHits(filter:Boolean = false): LiveData<List<UserPicture>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userPicture: UserPicture)

    //@Query(value = "UPDATE hit_table SET isDeleted='true' WHERE hit_table.objectID=:objectID")
    @Update
    fun delete(
            //objectID:String
        userPicture: UserPicture
    ):Int

}