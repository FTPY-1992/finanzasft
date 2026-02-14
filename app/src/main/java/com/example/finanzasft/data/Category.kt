package com.example.finanzasft.data

import androidx.annotation.DrawableRes
import com.example.finanzasft.R


enum class Category(
    val displayName: String,
    @DrawableRes val iconResId: Int) {
    COMIDA("Comida", R.drawable.restaurant_24dp),
    BEBIDAS("Bebidas",R.drawable.local_bar_24dp),
    HIGIENE_PERSONAL("Higiene/Personal",R.drawable.spa_24dp),
    SALUD("Salud",R.drawable.local_hospital_24dp),
    SERVICIOS("Servicios",R.drawable.linked_services_24dp),
    OTROS("Otros",R.drawable.more_horiz_24dp),
    LIMPIEZA_HOGAR("Limpieza/Hogar",R.drawable.cleaning_24dp),
    ROPA("Ropa",R.drawable.checkroom_24dp),
    MASCOTAS("Mascotas",R.drawable.pets_24dp),
    DIVERSION("Diversion",R.drawable.sports_esports_24dp),
    TRASNPORTE("Transporte",R.drawable.directions_car_24dp)
}