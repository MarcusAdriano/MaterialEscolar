package com.marcus.materialescolar.model

import android.arch.persistence.room.*

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
@Entity(tableName = "materiais")
data class Material (
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "marca") val marca: String,
        @ColumnInfo(name = "quantity") val quantity: Int = 1,
        @ColumnInfo(name = "price") val unityPrice : Double
)