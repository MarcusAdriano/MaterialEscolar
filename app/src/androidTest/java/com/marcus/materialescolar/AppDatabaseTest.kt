package com.marcus.materialescolar

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.marcus.materialescolar.dao.MaterialDao
import com.marcus.materialescolar.db.AppDatabase
import com.marcus.materialescolar.db.AppDatabaseManager
import com.marcus.materialescolar.model.Material
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Marcus on 31-Jan-18.
 *
 */
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    val lista = ListaEscolar
    lateinit var appDb : AppDatabase
    lateinit  var materialDb : MaterialDao

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getTargetContext()
        //appDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
        AppDatabaseManager.init(appContext)
        appDb = AppDatabaseManager.instance.database

        lista.addMaterial(Material("", 2, 20.0))
        lista.addMaterial(Material("", 3, 10.0))

        materialDb = appDb.materialDao()
    }

    @Test
    fun addAllMaterial() {
        materialDb.addAll(lista.getAll())
        assert(materialDb.getAll().size == lista.size())
    }

    @Test
    fun deleteMaterial() {
        materialDb.delete(lista.get(0))
        lista.deleteMaterial(0)
        assert(materialDb.getAll().size == lista.size())
    }

    @After
    @Test
    fun closeDb() {
        appDb.close()
        assert(AppDatabaseManager.instance.closed == true)
    }

}