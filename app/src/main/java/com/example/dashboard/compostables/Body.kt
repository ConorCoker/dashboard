package com.example.dashboard.compostables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.dashboard.navigation.Destination
import com.example.dashboard.navigation.Navigation

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: Destination,
    orientation: Int,
    onCreateItem: () -> Unit,
    onNavigate: (destination:Destination) -> Unit
) {
    Row(modifier) {   //int that represents device orientation so we know which nav bar to show
        if (destination.isRootDestination && orientation == Configuration.ORIENTATION_LANDSCAPE){
            RailNavigationBar(currentDestination = destination, onNavigate = onNavigate, onCreateItem = onCreateItem)
        }
//        else{
//            BottomNavigationBar(currentDestination = destination, onNavigate = onNavigate )
//        }
        Navigation(modifier.fillMaxSize(), navController = navController)
    }

}