package com.pedrogomez.develepersfinder.repository.remote

import androidx.lifecycle.LiveData
import com.pedrogomez.develepersfinder.contracts.ManagerContracts
import com.pedrogomez.develepersfinder.contracts.RepoContracts
import com.pedrogomez.develepersfinder.models.db.UserPicture

class RemoteManager(
    private val remoteDataSource: RepoContracts.RemoteDataSource
) : ManagerContracts.ApiService {

    override fun observeRemote(): LiveData<List<UserPicture>> {

    }

}