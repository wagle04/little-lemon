package com.example.littlelemon.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.HomeScreen
import com.example.littlelemon.OnboardingScreen
import com.example.littlelemon.ProfileScreen
import com.example.littlelemon.utils.SP_APPNAME
import com.example.littlelemon.utils.SP_LOGGEDIN

@Composable
fun AppNavigator(database: AppDatabase) {
    var navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(SP_APPNAME, Context.MODE_PRIVATE)

    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Onboarding.route) {
            val loggedIn = sharedPreferences.getBoolean(SP_LOGGEDIN, false)

            if (loggedIn) {
                HomeScreen(navController,database)
            } else {
                OnboardingScreen(navController)
            }
        }
        composable(Home.route) {
            HomeScreen(navController,database)
        }
        composable(Profile.route) {
            ProfileScreen(navController)
        }
    }
}

