package com.sargames.storemugs.ui.data

import androidx.compose.ui.graphics.Color

// Categorías disponibles
val categories = listOf(
    "Todos",
    "Tazas",
    "Tazones",
    "Jarras",
    "Accesorios",
    "Ofertas",
    "Nuevos"
)

// Lista completa de productos
val allProducts = listOf(
    Product("Taza Clásica", 49900.0),
    Product("Tazón Premium", 69900.0),
    Product("Jarra Vintage", 89900.0),
    Product("Set de Tazas", 119900.0),
    Product("Taza Personalizada", 59900.0),
    Product("Tazón de Cerámica", 79900.0),
    Product("Jarra de Diseño", 99900.0),
    Product("Set Familiar", 149900.0),
    Product("Taza Minimalista", 54900.0),
    Product("Tazón Artesanal", 74900.0),
    Product("Jarra Moderna", 94900.0),
    Product("Set de Regalo", 129900.0),
    Product("Taza Ecológica", 64900.0),
    Product("Tazón Premium", 84900.0),
    Product("Taza Retro", 54900.0),
    Product("Jarra Clásica", 89900.0)
)

// Productos destacados
val featuredProducts = listOf(
    Product("Taza Clásica", 49900.0),
    Product("Tazón Premium", 69900.0),
    Product("Jarra Vintage", 89900.0),
    Product("Set de Tazas", 119900.0)
)

// Productos para explorar
val exploreProducts = listOf(
    Product("Taza Personalizada", 59900.0),
    Product("Tazón de Cerámica", 79900.0),
    Product("Jarra de Diseño", 99900.0),
    Product("Set Familiar", 149900.0)
)

// Ofertas especiales
val specialOffers = listOf(
    Offer("Hasta 50% de descuento en tazas seleccionadas"),
    Offer("Envío gratis en compras superiores a $200.000")
)

// Diseños disponibles
val availableDesigns = listOf(
    Design("Diseño Clásico", "Elegante y atemporal", "Clásico"),
    Design("Diseño Moderno", "Contemporáneo y minimalista", "Moderno"),
    Design("Diseño Vintage", "Retro y nostálgico", "Vintage"),
    Design("Diseño Personalizado", "Crea tu propio diseño", "Personalizado")
)

data class Product(
    val name: String,
    val price: Double
)

data class Offer(
    val description: String
)

data class Design(
    val name: String,
    val description: String,
    val previewText: String
) 