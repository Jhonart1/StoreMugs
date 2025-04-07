package com.sargames.storemugs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sargames.storemugs.ui.data.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreDashboard(
    onSignOut: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }
    var showAllProducts by remember { mutableStateOf(false) }
    
    if (showAllProducts) {
        AllProductsScreen(
            onBackClick = { showAllProducts = false },
            paddingValues = PaddingValues()
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { 
                        Text(
                            when (selectedTab) {
                                0 -> "StoreMugs"
                                1 -> "Explorar"
                                2 -> "Carrito"
                                3 -> "Perfil"
                                else -> "StoreMugs"
                            }
                        ) 
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.padding(bottom = 0.dp)
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 0.dp)
                ) {
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                Icons.Default.Home, 
                                contentDescription = "Inicio",
                                tint = if (selectedTab == 0) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        label = { 
                            Text(
                                "Inicio",
                                color = if (selectedTab == 0) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black,
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                Icons.Default.Search, 
                                contentDescription = "Explorar",
                                tint = if (selectedTab == 1) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        label = { 
                            Text(
                                "Explorar",
                                color = if (selectedTab == 1) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black,
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                Icons.Default.ShoppingCart, 
                                contentDescription = "Carrito",
                                tint = if (selectedTab == 2) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        label = { 
                            Text(
                                "Carrito",
                                color = if (selectedTab == 2) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black,
                            indicatorColor = Color.Transparent
                        )
                    )
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                Icons.Default.Person, 
                                contentDescription = "Perfil",
                                tint = if (selectedTab == 3) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        label = { 
                            Text(
                                "Perfil",
                                color = if (selectedTab == 3) 
                                    Color.White 
                                else 
                                    Color.Black
                            ) 
                        },
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        ) { scaffoldPadding ->
            when (selectedTab) {
                0 -> HomeContent(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    paddingValues = scaffoldPadding,
                    onViewAllProducts = { showAllProducts = true }
                )
                1 -> ExploreContent(
                    paddingValues = scaffoldPadding,
                    onViewAllProducts = { showAllProducts = true }
                )
                2 -> CartScreen(
                    onBackClick = { selectedTab = 0 },
                    paddingValues = scaffoldPadding
                )
                3 -> ProfileScreen(
                    paddingValues = scaffoldPadding,
                    onSignOut = onSignOut
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    paddingValues: PaddingValues,
    onViewAllProducts: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        // Banner deslizable
        item {
            BannerCarousel()
        }

        // Sección de búsqueda
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Buscar productos...") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )
        }

        // Sección de categorías
        item {
            Text(
                text = "Categorías",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(category)
                }
            }
        }

        // Sección de productos destacados
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Productos Destacados",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onViewAllProducts) {
                    Text("Ver todos")
                }
            }
            
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(featuredProducts) { product ->
                    ProductCard(product)
                }
            }
        }

        // Sección de diseños disponibles
        item {
            Text(
                text = "Diseños Disponibles",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(availableDesigns) { design ->
                    DesignCard(design)
                }
            }
        }
    }
}

@Composable
fun BannerCarousel() {
    var currentPage by remember { mutableStateOf(0) }
    val banners = listOf(
        Banner(
            title = "¡Ofertas Especiales!",
            description = "Hasta 50% de descuento en tazas seleccionadas",
            backgroundColor = MaterialTheme.colorScheme.primary
        ),
        Banner(
            title = "Nuevos Diseños",
            description = "Explora nuestra nueva colección de tazas personalizadas",
            backgroundColor = MaterialTheme.colorScheme.secondary
        ),
        Banner(
            title = "Envío Gratis",
            description = "En compras superiores a $100",
            backgroundColor = MaterialTheme.colorScheme.tertiary
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        // Banner actual
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = banners[currentPage].backgroundColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = banners[currentPage].title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = banners[currentPage].description,
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // Indicadores de página
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            banners.indices.forEach { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            if (index == currentPage) Color.White
                            else Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }

    // Cambio automático de banner
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Cambiar cada 3 segundos
            currentPage = (currentPage + 1) % banners.size
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreContent(
    paddingValues: PaddingValues,
    onViewAllProducts: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        // Barra de búsqueda
        item {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Buscar productos...") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                ),
                singleLine = true
            )
        }

        // Filtros
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(exploreFilters) { filter ->
                    FilterChip(
                        onClick = { },
                        label = { Text(filter) },
                        selected = false,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }
            }
        }

        // Grid de productos
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Todos los Productos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onViewAllProducts) {
                    Text("Ver todos")
                }
            }
            
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(exploreProducts) { product ->
                    ExploreProductCard(product)
                }
            }
        }

        // Sección de tendencias
        item {
            Text(
                text = "Tendencias",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(trendingProducts) { product ->
                    TrendingProductCard(product)
                }
            }
        }
    }
}

@Composable
fun ExploreProductCard(product: Product) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Imagen")
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            
            Text(
                text = "$${String.format("%,.0f", product.price)}",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TrendingProductCard(product: Product) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Imagen")
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = product.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            
            Text(
                text = "$${String.format("%,.0f", product.price)}",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        item {
            Text(
                text = "Carrito de Compras",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun CategoryCard(category: String) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Imagen")
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            
            Text(
                text = "$${String.format("%,.0f", product.price)}",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun OfferCard(offer: Offer) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = offer.description,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DesignCard(design: Design) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(220.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(design.previewText)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = design.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            
            Text(
                text = design.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Datos de ejemplo
private val categories = listOf(
    "Tazas",
    "Tazones",
    "Jarras",
    "Accesorios",
    "Colecciones"
)

private val featuredProducts = listOf(
    Product("Taza Clásica", 29.99),
    Product("Tazón Premium", 39.99),
    Product("Jarra Vintage", 49.99),
    Product("Set de Tazas", 59.99)
)

private val specialOffers = listOf(
    Offer("¡20% OFF en todas las tazas!"),
    Offer("Combo familiar - 30% descuento"),
    Offer("Envío gratis en compras superiores a $100")
)

private val availableDesigns = listOf(
    Design(
        name = "Diseño Clásico",
        description = "Elegante y atemporal",
        previewText = "Clásico"
    ),
    Design(
        name = "Diseño Moderno",
        description = "Contemporáneo y minimalista",
        previewText = "Moderno"
    ),
    Design(
        name = "Diseño Vintage",
        description = "Retro y nostálgico",
        previewText = "Vintage"
    ),
    Design(
        name = "Diseño Personalizado",
        description = "Crea tu propio diseño",
        previewText = "Personalizado"
    )
)

// Datos adicionales para la sección de explorar
private val exploreFilters = listOf(
    "Todos",
    "Tazas",
    "Tazones",
    "Jarras",
    "Accesorios",
    "Ofertas",
    "Nuevos"
)

private val exploreProducts = listOf(
    Product("Taza Clásica", 29.99),
    Product("Tazón Premium", 39.99),
    Product("Jarra Vintage", 49.99),
    Product("Set de Tazas", 59.99),
    Product("Taza Personalizada", 34.99),
    Product("Tazón de Cerámica", 44.99),
    Product("Jarra de Diseño", 54.99),
    Product("Set Familiar", 69.99)
)

private val trendingProducts = listOf(
    Product("Taza Minimalista", 32.99),
    Product("Tazón Artesanal", 42.99),
    Product("Jarra Moderna", 52.99),
    Product("Set de Regalo", 62.99),
    Product("Taza Ecológica", 37.99),
    Product("Tazón Premium", 47.99)
)

data class Offer(
    val description: String
)

data class Design(
    val name: String,
    val description: String,
    val previewText: String
)

data class Banner(
    val title: String,
    val description: String,
    val backgroundColor: Color
) 