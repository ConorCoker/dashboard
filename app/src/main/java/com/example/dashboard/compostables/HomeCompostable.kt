package com.example.dashboard.compostables

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dashboard.R
import com.example.dashboard.navigation.Destination
import com.example.dashboard.navigation.Navigation


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun Home(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()

    val currentDestination by derivedStateOf {
        navBackStackEntry.value?.destination?.route?.let {
            Destination.fromString(it)
        } ?: run {
            Destination.Home
        }
    }


    Scaffold(modifier = modifier, topBar = {
        Text(text = "Home") //needs fixing as no bar is appearing just the text home
    }, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.cd_create_item))
        }
    }, bottomBar = {
        BottomNavigationBar(currentDestination = Destination.Home, onNavigate = {
            navController.navigate(it.path){
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true
                restoreState= true
            }
        } )
    }) {
        Navigation(navController = navController)
    }



}