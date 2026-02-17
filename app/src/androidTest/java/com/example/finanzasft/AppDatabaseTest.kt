package com.example.finanzasft

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.finanzasft.data.AppDatabase
import com.example.finanzasft.data.Category
import com.example.finanzasft.data.ProductDao
import com.example.finanzasft.data.ProductEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: ProductDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.productDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndReadProductWithCategory() = runBlocking {
        val product = ProductEntity(
            name = "Gaseosa",
            brand = "Coca-Cola",
            description = "Pack 2L",
            price = 4500.0,
            quantityBought = 2,
            quantityConsumed = 1,
            category = Category.BEBIDAS
        )

        dao.insert(product)

        val allProducts = dao.getAllProducts().first()

        assertEquals(1, allProducts.size)
        val saved = allProducts[0]
        assertEquals("Gaseosa", saved.name)
        assertEquals(Category.BEBIDAS, saved.category)
        assertNotNull(saved.id)
    }
}