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
import org.junit.Assert.assertTrue

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
    fun emptyDatabaseReturnsEmptyList() = runBlocking {
        val products = dao.getAllProducts().first()
        assertEquals(0,products.size)
    }

    @Test
    fun insertMultipleAndReadAll() = runBlocking {
        dao.insert(ProductEntity(name = "Pizza", price = 8500.0, quantityBought = 1, quantityConsumed = 1, category = Category.COMIDA))
        dao.insert(ProductEntity(name = "Cerveza", price = 2500.0, quantityBought = 6, quantityConsumed = 2, category = Category.BEBIDAS))
        dao.insert(ProductEntity(name = "Aspirina", price = 800.0, quantityBought = 2, quantityConsumed = 1, category = Category.SALUD))

        val products = dao.getAllProducts().first()

        assertEquals(3, products.size)

        val pizza = products.first { it.name == "Pizza" }
        assertEquals(Category.COMIDA, pizza.category)

        val cerveza = products.first { it.name == "Cerveza" }
        assertEquals(Category.BEBIDAS, cerveza.category)

        val aspirina = products.first { it.name == "Aspirina" }
        assertEquals(Category.SALUD, aspirina.category)
    }

    @Test
    fun filterByCategoryReturnsCorrectItems() = runBlocking {
        dao.insert(ProductEntity(name = "Gaseosa", price = 4500.0, quantityBought = 2, quantityConsumed = 1, category = Category.BEBIDAS))
        dao.insert(ProductEntity(name = "Agua", price = 1000.0, quantityBought = 4, quantityConsumed = 3, category = Category.BEBIDAS))
        dao.insert(ProductEntity(name = "Pan", price = 1500.0, quantityBought = 3, quantityConsumed = 2, category = Category.COMIDA))

        val bebidas = dao.getProductsByCategory(Category.BEBIDAS).first()
        assertEquals(2, bebidas.size)
        assertTrue(bebidas.all { it.category == Category.BEBIDAS })

        val comida = dao.getProductsByCategory(Category.COMIDA).first()
        assertEquals(1, comida.size)
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

    @Test
    fun filterProductsByCategory() = runBlocking {
        //Insertamos productos de diferentes categorias
        dao.insert(ProductEntity(name = "Gaseosa", price = 4500.0, quantityBought = 2, quantityConsumed = 1, category = Category.BEBIDAS))
        dao.insert(ProductEntity(name = "Pizza", price = 8500.0, quantityBought = 1, quantityConsumed = 1, category = Category.COMIDA))
        dao.insert(ProductEntity(name = "Shampoo", price = 3200.0, quantityBought = 3, quantityConsumed = 2, category = Category.HIGIENE_PERSONAL))

        val bebidas = dao.getProductsByCategory(Category.BEBIDAS).first()

        assertEquals(1,bebidas.size)
        assertEquals("Gaseosa",bebidas[0].name)
        assertEquals(Category.BEBIDAS,bebidas[0].category)

        val comida = dao.getProductsByCategory(Category.COMIDA).first()

        assertEquals(1,comida.size)
        assertEquals("Pizza",comida[0].name)
    }

    @Test
    fun updateConsumedQuantity() = runBlocking {
        val original = ProductEntity(name = "Leche", price = 1200.0, quantityBought = 5, quantityConsumed = 2, category = Category.BEBIDAS)
        dao.insert(original)

        val saved = dao.getAllProducts().first()[0]
        val updated = saved.copy(quantityConsumed = saved.quantityConsumed + 1)

        dao.update(updated)

        val after = dao.getAllProducts().first()[0]
        assertEquals(3, after.quantityConsumed)
    }

    @Test
    fun deleteProduct() = runBlocking {
        val product = ProductEntity(name = "Arroz", price = 2000.0, quantityBought = 4, quantityConsumed = 1, category = Category.COMIDA)
        dao.insert(product)

        val saved = dao.getAllProducts().first()[0]
        dao.delete(saved)

        val after = dao.getAllProducts().first()
        assertEquals(0, after.size)
    }
}