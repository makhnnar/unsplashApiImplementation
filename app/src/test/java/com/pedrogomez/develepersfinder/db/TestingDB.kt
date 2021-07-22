package com.pedrogomez.develepersfinder.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pedrogomez.develepersfinder.repository.local.DBDao
import com.pedrogomez.develepersfinder.repository.local.DataBase
import com.pedrogomez.develepersfinder.util.DataHelper
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestingDB {

    private lateinit var DBDao: DBDao
    private lateinit var db: DataBase

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun createDb() {
        Dispatchers.setMain(mainThreadSurrogate)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            DataBase::class.java
        ).build()
        DBDao = db.hits()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndRedHits() {
        runBlocking {
            launch(Dispatchers.Main) {
                val hitTable = DataHelper.HITTABLE
                DBDao.insert(hitTable)
                val hits = DBDao.getAllHits()
                Assert.assertEquals(hits,DataHelper.HITSLIST)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeAndDeleteHitWithReturnValue() {
        runBlocking {
            launch(Dispatchers.Main) {
                val hitTable = DataHelper.HITTABLE
                DBDao.insert(hitTable)
                hitTable.isDeleted = true
                val result = DBDao.delete(hitTable)
                Assert.assertEquals(result,1)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeAndDeleteHitCheckEmptyListSuccess() {
        runBlocking {
            launch(Dispatchers.Main) {
                val hitTable = DataHelper.HITTABLE
                DBDao.insert(hitTable)
                hitTable.isDeleted = true
                DBDao.delete(hitTable)
                val hits = DBDao.getAllHits()
                Assert.assertEquals(hits,DataHelper.EMPTYHISTS)
            }
        }
    }

}