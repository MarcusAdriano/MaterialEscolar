package com.marcus.materialescolar.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.marcus.materialescolar.model.Material

/**
 * Created by Marcus on 31-Jan-18.
 *
 */
@Dao
interface MaterialDao {

    @Query("SELECT * FROM materiais")
    fun getAll() : LiveData<List<Material>>

    @Query("SELECT * FROM materiais WHERE id = :id")
    fun getById(id: Long): LiveData<Material>

    @Update
    fun update(materiais: List<Material>) : Int

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(materiais: List<Material>) : List<Long>

    @Delete
    fun delete(materiais: List<Material>)  : Int
}