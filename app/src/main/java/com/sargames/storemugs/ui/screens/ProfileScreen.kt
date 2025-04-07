package com.sargames.storemugs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    onSignOut: () -> Unit
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
        // Información del usuario
        item {
            UserInfoSection()
        }

        // Opciones del perfil
        item {
            ProfileOptionsCard()
        }

        // Botón de cerrar sesión
        item {
            SignOutButton(onSignOut = onSignOut)
        }
    }
}

@Composable
private fun UserInfoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar del usuario
        UserAvatar()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Información del usuario
        UserDetails()
    }
}

@Composable
private fun UserAvatar() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(60.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.Person,
            contentDescription = "Avatar",
            modifier = Modifier.size(60.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun UserDetails() {
    // Mostrar el usuario temporal
    Text(
        text = "Usuario de Prueba",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    
    Text(
        text = "test@store.com",
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun ProfileOptionsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProfileOptionsList()
        }
    }
}

@Composable
private fun ProfileOptionsList() {
    profileOptions.forEachIndexed { index, option ->
        ProfileOption(
            icon = option.icon,
            title = option.title,
            subtitle = option.subtitle,
            onClick = option.onClick
        )
        
        if (index < profileOptions.size - 1) {
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
private fun ProfileOption(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Icon(
            Icons.Default.ArrowForward,
            contentDescription = "Ir a $title",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SignOutButton(onSignOut: () -> Unit) {
    Button(
        onClick = onSignOut,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
        )
    ) {
        Icon(
            Icons.Default.ExitToApp,
            contentDescription = "Cerrar Sesión",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Cerrar Sesión")
    }
}

private data class ProfileOptionItem(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val onClick: () -> Unit = {}
)

private val profileOptions = listOf(
    ProfileOptionItem(
        icon = Icons.Default.ShoppingCart,
        title = "Mis Pedidos",
        subtitle = "Ver historial de compras"
    ),
    ProfileOptionItem(
        icon = Icons.Default.Favorite,
        title = "Lista de Deseos",
        subtitle = "Productos guardados"
    ),
    ProfileOptionItem(
        icon = Icons.Default.LocationOn,
        title = "Direcciones",
        subtitle = "Gestionar direcciones de envío"
    ),
    ProfileOptionItem(
        icon = Icons.Default.Settings,
        title = "Configuración",
        subtitle = "Preferencias de la cuenta"
    )
) 