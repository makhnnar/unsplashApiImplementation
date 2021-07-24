package com.pedrogomez.develepersfinder.repository.remote

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pedrogomez.develepersfinder.contracts.RepoContracts
import com.pedrogomez.develepersfinder.models.adapter.UserPicsMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class RemoteManagerTest {

    private val PAGE = 1

    private lateinit var SUT : RemoteManager

    @Mock
    lateinit var remoteRepo : RepoContracts.RemoteDataSource


    lateinit var dataMapper : UserPicsMapper

    @Before
    fun setUp() {
        dataMapper = mock(UserPicsMapper::class.java)
        SUT = RemoteManager(
            remoteRepo,
            dataMapper
        )
    }

    @Test
    fun observeRemote() {
        runBlocking {
            SUT.observeRemote(PAGE)
            verify(remoteRepo).loadPhotos(PAGE)
        }
    }

    //added test with data mapper return
}