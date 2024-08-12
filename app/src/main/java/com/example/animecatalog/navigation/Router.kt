package com.example.animecatalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class Router @Inject constructor() {
    private lateinit var navController: NavController

    fun init(navController: NavController) {
        this.navController = navController
    }

    fun navigateTo(navDirections: NavDirections) {
        navController.navigate(navDirections)
    }

    fun back() {
        navController.popBackStack()
    }
}