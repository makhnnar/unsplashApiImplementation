package com.pedrogomez.develepersfinder.repository

import androidx.lifecycle.LiveData
import com.pedrogomez.develepersfinder.contracts.ManagerContracts
import com.pedrogomez.develepersfinder.contracts.RepoContracts
import com.pedrogomez.develepersfinder.models.db.UserPicture

/**
 * this class is for get which repo is going to be consumed
 * */
class DataBaseManager(
    private val localDataSource: RepoContracts.LocalDataSource
) : ManagerContracts.DataBase{

    override suspend fun delete(hitItem: UserPicture) {
        localDataSource.delete(hitItem)
    }

    override suspend fun add(hitItem: UserPicture) {

    }

    override fun observeFavorites(): LiveData<List<UserPicture>> {
        return localDataSource.observeHits()
    }

}