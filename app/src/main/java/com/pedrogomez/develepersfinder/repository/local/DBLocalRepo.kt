package com.pedrogomez.develepersfinder.repository.local

import androidx.lifecycle.LiveData
import com.pedrogomez.develepersfinder.contracts.RepoContracts
import com.pedrogomez.develepersfinder.models.db.UserPicture
import com.pedrogomez.develepersfinder.repository.DataBaseManager
import com.pedrogomez.develepersfinder.utils.extensions.print
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DBLocalRepo(
    private val DBDao: DBDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RepoContracts.LocalDataSource {

    override suspend fun insert(userPicture: UserPicture) = withContext(ioDispatcher) {
        DBDao.insert(
                userPicture
        )
    }

    override suspend fun delete(userPicture: UserPicture) = withContext(ioDispatcher) {
        val result = DBDao.delete(
                userPicture
        )
    }

    override fun observeHits(): LiveData<List<UserPicture>> {
        return DBDao.observeHits()
    }

}