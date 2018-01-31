package com.marcus.materialescolar.db.dao

import android.arch.persistence.room.*
import com.marcus.materialescolar.model.Material

/**
 * Created by Marcus on 31-Jan-18.
 *
 */
@Dao
interface MaterialDao {

    @Query("SELECT * FROM materiais")
    fun getAll() : List<Material>

    @Query("SELECT * FROM materiais WHERE id = :id")
    fun getById(id: Int): Material

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun add(material: Material)

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun addAll(materiais: List<Material>)

    @Delete
    fun delete(material: Material)

    @Delete()
    fun deleteAll(materiais: List<Material>)
}