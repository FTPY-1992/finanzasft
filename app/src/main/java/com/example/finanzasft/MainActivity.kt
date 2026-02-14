package com.example.finanzasft

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzasft.ui.adapter.ProductAdapter
import com.example.finanzasft.data.AppDatabase
import com.example.finanzasft.data.ProductEntity
import com.example.finanzasft.ui.theme.FinanzasftTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            // Config el RecyclerView
            val recycler = findViewById<RecyclerView>(R.id.recyclerProducts)
            val adapter = ProductAdapter()
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this)

            //Datos de prueba a ver si se muestra
            val testProducts = listOf(
                ProductEntity(name = "Gaseosa", brand = "Coca Cola", description = "Pack 2L x6", price = 4500.0, quantityBought = 2, quantityConsumed = 1, category = Bebidas),
                ProductEntity(name = "Leche", brand = "La Serenisima", description = "Entera 1L", price = 1200.0, quantityConsumed = 5, quantityBought = 3)
            )

            adapter.updateProducts(testProducts)

        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinanzasftTheme {
        Greeting("Android")
    }
}


