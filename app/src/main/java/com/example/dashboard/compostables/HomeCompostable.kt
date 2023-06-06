package com.example.dashboard.compostables

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dashboard.R
import com.example.dashboard.navigation.Destination
import com.example.dashboard.navigation.Navigation
import com.example.dashboard.utils.capitalizeFirstLetter
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "RememberReturnType"
)
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
     MediumTopAppBar(title = {
         Text(text = capitalizeFirstLetter(currentDestination.path))
     }, actions = {
         if (currentDestination != Destination.Feed){
             IconButton(onClick = {

             }) {
                 Icon(imageVector = Icons.Default.Info, contentDescription = stringResource(id = R.string.cd_more_info))
             }
         }
     })
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