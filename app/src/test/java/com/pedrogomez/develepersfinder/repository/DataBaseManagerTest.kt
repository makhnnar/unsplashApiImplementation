package com.pedrogomez.develepersfinder.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pedrogomez.develepersfinder.models.api.toPresentationModel
import com.pedrogomez.develepersfinder.models.db.UserPicture
import com.pedrogomez.develepersfinder.util.DataHelper
import com.pedrogomez.develepersfinder.util.getOrAwaitValue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

import org.junit.After
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataBaseManagerTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var SUT: DataBaseManager

    val PAGE = 1

    var remoteDataSourceTD = RemoteDataSourceTD()
    var localDataSourceTD =  LocalDataSourceTD()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        SUT = DataBaseManager(
            remoteDataSourceTD,
            localDataSourceTD
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun onLoadHits_getHitsData_passedParamsSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.loadHits(PAGE)
                remoteDataSourceTD.loadPhotos(PAGE)
                assertEquals(remoteDataSourceTD.page,PAGE)
            }
        }
    }

    @Test
    fun onLoadHits_getHitsData_successResponse() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.loadHits(PAGE)
                val response = remoteDataSourceTD.loadPhotos(PAGE)
                assertEquals(response, DataHelper.HITSRESPONSE)
            }
        }
    }

    @Test
    fun onLoadHits_getHitsData_failedResponse() {
        failed()
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.loadHits(PAGE)
                val response = remoteDataSourceTD.loadPhotos(PAGE)
                assertNull(response)
            }
        }
    }

    @Test
    fun getAllHits_getAllHits_returnedSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                val list = SUT.getAllHits()
                assertEquals(
                        list,
                        DataHelper.HITSLIST
                )
            }
        }
    }

    @Test
    fun delete_delete_passedParamsSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.delete(DataHelper.HITTABLE)
                assertEquals(
                        localDataSourceTD.hitToDelete,
                        DataHelper.HITTABLE
                )
            }
        }
    }

    @Test
    fun observeHits_observeHits_returnedSuccess() {
        runBlocking {
            onDataChange()
            val list = SUT.observeFavorites()
            assertEquals(
                list.getOrAwaitValue(),
                DataHelper.HITSLIST
            )
        }
    }

    @Test
    fun updateLocalWithRemote_insert_passedParamsSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                SUT.updateLocalWithRemote(
                        DataHelper.HITSRESPONSE.hits.map {
                             it.toPresentationModel()
                         }
                )
                assertEquals(
                        localDataSourceTD.listHits,
                        DataHelper.HITSRESPONSE.hits.map {
                            it.toPresentationModel()
                        }
                )
            }
        }
    }

    private fun failed() {
        remoteDataSourceTD.failed = true
    }

    private fun onDataChange() {
        localDataSourceTD.hitsMutLivDat.postValue(
                DataHelper.HITSLIST
        )
    }

    class RemoteDataSourceTD : DataBaseManager.RemoteDataSource {

        var page = 0

        var failed = false

        override suspend fun loadPhotos(page: Int): HitsListResponse? {
            this.page = page
            return if(failed) null else DataHelper.HITSRESPONSE
        }

    }

    class LocalDataSourceTD : DataBaseManager.LocalDataSource{

        var hitToDelete : UserPicture? = null

        var hitsMutLivDat = MutableLiveData<List<UserPicture>>()

        var inserted : UserPicture? = null

        var listHits = ArrayList<UserPicture>()

        override suspend fun getAllHits(): List<UserPicture> {
            return DataHelper.HITSLIST
        }

        override suspend fun insert(userPicture: UserPicture) {
            inserted = userPicture
        }

        override suspend fun delete(userPicture: UserPicture) {
            hitToDelete = userPicture
        }

        override fun observeHits(): LiveData<List<UserPicture>> {
            return hitsMutLivDat
        }

        override suspend fun updateLocal(toInsert: List<UserPicture>) {
            listHits.addAll(toInsert)
        }

    }

}