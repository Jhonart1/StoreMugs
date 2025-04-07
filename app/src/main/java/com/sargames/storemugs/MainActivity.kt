package com.sargames.storemugs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.sargames.storemugs.ui.screens.LoginScreen
import com.sargames.storemugs.ui.screens.RegisterScreen
import com.sargames.storemugs.ui.screens.StoreDashboard
import com.sargames.storemugs.ui.theme.StoreMugsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreMugsTheme {
                var isLoggedIn by remember { mutableStateOf(false) }
                var showRegister by remember { mutableStateOf(false) }

                when {
                    isLoggedIn -> {
                        StoreDashboard(
                            onSignOut = {
                                isLoggedIn = false
                            }
                        )
                    }
                    showRegister -> {
                        RegisterScreen(
                            onRegisterSuccess = {
                                isLoggedIn = true
                                showRegister = false
                            },
                            onBackToLogin = {
                                showRegister = false
                            }
                        )
                    }
                    else -> {
                        LoginScreen(
                            onLoginSuccess = {
                                isLoggedIn = true
                            },
                            onNavigateToRegister = {
                                showRegister = true
                            }
                        )
                    }
                }
            }
        }
    }
}