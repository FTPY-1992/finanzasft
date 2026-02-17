package com.example.finanzasft.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(defaultValue = "Null") val brand: String?,
    @ColumnInfo(defaultValue = "Null") val description: String?,
    val price: Double,
    val quantityBought: Int,
    val quantityConsumed: Int,
    @ColumnInfo(defaultValue = "Null") val photoPath: String? = null,
    @ColumnInfo(defaultValue = "Null") val category: Category? = null
)