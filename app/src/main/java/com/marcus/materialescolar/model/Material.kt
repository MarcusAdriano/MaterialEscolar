package com.marcus.materialescolar.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marcus on 30-Jan-18.
 *
 */
@Entity(tableName = "materiais")
open class Material {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    /**
     * Name/description
     */
    @ColumnInfo(name = "name")
    lateinit var name: String

    /**
     * Number of this material
     */
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

    /**
     * unity price
     */
    @ColumnInfo(name = "price")
    var price: Double = 0.0

    @Ignore
    constructor()

    @Ignore
    constructor(name: String, quantity: Int, price: Double) {
        this.name = name
        this.quantity = quantity
        this.price = price
    }

    constructor(id: Long, name: String, quantity: Int, price: Double) {
        this.id = id
        this.name = name
        this.quantity = quantity
        this.price = price
    }
}