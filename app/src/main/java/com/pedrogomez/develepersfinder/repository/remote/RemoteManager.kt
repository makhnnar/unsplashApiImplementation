package com.pedrogomez.develepersfinder.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedrogomez.develepersfinder.contracts.ManagerContracts
import com.pedrogomez.develepersfinder.contracts.RepoContracts
import com.pedrogomez.develepersfinder.models.adapter.UserPicsMapper
import com.pedrogomez.develepersfinder.models.db.UserPicture

class RemoteManager(
    private val remoteDataSource: RepoContracts.RemoteDataSource,
    private val userPicsMapper: UserPicsMapper
) : ManagerContracts.ApiService {

    override suspend fun observeRemote(page:Int): LiveData<List<UserPicture>> {
        remoteDataSource.loadPhotos(page)
        return MutableLiveData()
    }

}