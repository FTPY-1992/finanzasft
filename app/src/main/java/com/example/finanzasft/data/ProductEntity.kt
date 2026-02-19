package com.example.finanzasft.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val brand: String? = null,
    val description: String? = null,
    val price: Double,
    val quantityBought: Int,
    val quantityConsumed: Int,
    val photoPath: String? = null,
    val category: Category? = null
) {
    //Constructor secundario para uso manual
    constructor(
        name: String,
        brand: String? = null,
        description: String? = null,
        price: Double,
        quantityBought: Int,
        quantityConsumed: Int,
        photoPath: String? = null,
        category: Category? = null
    ) : this(0,name,brand,description,price,quantityBought,quantityConsumed,photoPath,category)
}