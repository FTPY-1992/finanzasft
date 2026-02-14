package com.example.finanzasft.data

import androidx.appcompat.resources.R

enum class Category(val displayName: String, val iconResId: Int? = null) {
    COMIDA("Comida",R.drawable.ic_food_bank),
    BEBIDAS("Bebidas",R.drawable.ic_local_drink),
    HIGIENE_PERSONAL("Higiene/Personal",R.drawable.ic_spa),
    SALUD("Salud",R.drawable.ic_local_hospital),
    SERVICIOS("Servicios",R.drawable.ic_settings_input_antenna),
    OTROS("Otros",R.drawable.ic_more_horiz),
    LIMPIEZA_HOGAR("Limpieza/Hogar",R.drawable.ic_cleaning_services),
    ROPA("Ropa",R.drawable.ic_pets),
    MASCOTAS("Mascotas",R.drawable.ic_pets),
    DIVERSION("Diversion",R.drawable.sports_esports),
    TRASNPORTE("Transporte",R.drawable.ic_directions_car)
}