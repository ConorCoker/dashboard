package com.example.dashboard.compostables

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dashboard.navigation.Destination
import com.example.dashboard.utils.capitalizeFirstLetter

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    NavigationBar(modifier = modifier) {
        listOf(Destination.Feed, Destination.Contacts, Destination.Calendar).forEach {
            NavigationBarItem(
                selected = currentDestination.path == it.path,
                onClick = { onNavigate(it) },
                icon = {
                    it.icon?.let { image ->
                        Icon(
                            imageVector = image,
                            contentDescription = image.name
                        )   //it.name check to see what refers too
                    }
                },
                label = {
                    Text(text = capitalizeFirstLetter(it.path))
                })
        }
    }
}



