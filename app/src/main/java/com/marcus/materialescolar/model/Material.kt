package com.marcus.materialescolar.model

import android.arch.persistence.room.*
import android.support.annotation.IntegerRes

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
@Entity(tableName = "materiais")
data class Material(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var name: String,
        var marca: String,
        var quantity: Int,
        var price : Double) {

}