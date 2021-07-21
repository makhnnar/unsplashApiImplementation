package com.pedrogomez.develepersfinder.repository

import androidx.lifecycle.LiveData
import com.pedrogomez.develepersfinder.models.api.HitResponse
import com.pedrogomez.develepersfinder.models.api.toPresentationModel
import com.pedrogomez.develepersfinder.models.db.HitTable
import com.pedrogomez.develepersfinder.utils.extensions.print
import com.pedrogomez.develepersfinder.view.viewmodel.SharedHitsViewModel

/**
 * this class is for get which repo is going to be consumed
 * */
class HitsProvider(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : SharedHitsViewModel.Repository {

    override suspend fun loadHits(page:Int) {
        val response = remoteDataSource.getHitsData(page)
        try{
            updateLocalWithRemote(
                response?.hits?.map(HitResponse::toPresentationModel) ?: ArrayList<HitTable>()
            )
        }catch (e:Exception){
            "Ktor_request loadHits: ${e.message}".print()
        }
    }

    override suspend fun delete(hitItem: HitTable) {
        localDataSource.delete(hitItem)
    }

    override suspend fun getAllHits() : List<HitTable> {
        return localDataSource.getAllHits()
    }

    suspend fun updateLocalWithRemote(toInsert:List<HitTable>){
        localDataSource.updateLocal(toInsert)
    }

    override fun observeHits(): LiveData<List<HitTable>> {
        return localDataSource.observeHits()
    }

    interface LocalDataSource{
        suspend fun getAllHits(): List<HitTable>
        suspend fun insert(hitTable: HitTable)
        suspend fun delete(hitTable: HitTable)
        fun observeHits(): LiveData<List<HitTable>>
        suspend fun updateLocal(toInsert:List<HitTable>)
    }

    interface RemoteDataSource{
        suspend fun getHitsData(page:Int): HitsListResponse?
    }

}