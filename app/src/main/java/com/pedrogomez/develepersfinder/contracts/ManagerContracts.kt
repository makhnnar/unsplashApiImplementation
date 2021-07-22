package com.pedrogomez.develepersfinder.contracts

import androidx.lifecycle.LiveData
import com.pedrogomez.develepersfinder.models.db.UserPicture

class ManagerContracts {

    interface DataBase{

        suspend fun delete(hitItem: UserPicture)
        suspend fun add(hitItem: UserPicture)
        fun observeFavorites(): LiveData<List<UserPicture>>

    }

    interface ApiService{

        fun observeRemote(): LiveData<List<UserPicture>>

    }

}