package com.pedrogomez.develepersfinder.contracts

import androidx.lifecycle.LiveData
import com.pedrogomez.develepersfinder.models.api.CandidatesResponse
import com.pedrogomez.develepersfinder.models.db.UserPicture

class RepoContracts {

    interface LocalDataSource{
        suspend fun insert(userPicture: UserPicture)
        suspend fun delete(userPicture: UserPicture)
        fun observeHits(): LiveData<List<UserPicture>>
    }

    interface RemoteDataSource{
        suspend fun loadPhotos(page:Int): CandidatesResponse?
    }

}