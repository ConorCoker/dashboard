package com.example.dashboard.compostables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.dashboard.R
import com.example.dashboard.models.NavigationBarItem
import com.example.dashboard.navigation.Destination
import com.example.dashboard.tags.Tags
import com.example.dashboard.utils.capitalizeFirstLetter

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier.testTag(Tags.BOTTOM_NAV),
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    NavigationBar(modifier = modifier, containerColor = Color.Blue) {
        buildNavigationBarItems(currentDestination, onNavigate).forEach {
            NavigationBarItem(
                selected = it.selected,
                onClick = { it.onClick() },
                icon = it.icon,
                label = it.label
            )
        }
    }
}

@Composable
fun RailNavigationBar(modifier: Modifier = Modifier,currentDestination: Destination,onNavigate: (destination: Destination) -> Unit,onCreateItem:() -> Unit){
    NavigationRail(header = {
        FloatingActionButton(onClick = { onCreateItem() }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.cd_create_item)
            )
        }
    }, modifier = Modifier.testTag(Tags.TAG_RAIL_NAV)) {
        buildNavigationBarItems(currentDestination, onNavigate).map{
            NavigationRailItem(selected = it.selected, onClick = { it.onClick() }, label = {it.label()}, icon = {it.icon()})
        }
    }
}

fun buildNavigationBarItems(
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
): List<NavigationBarItem> {
    return listOf(
        Destination.Feed,
        Destination.Contacts,
        Destination.Calendar
    ).map { destination ->
        NavigationBarItem(
            icon = {
                destination.icon?.let { image ->
                    Icon(
                        imageVector = image,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text(text = capitalizeFirstLetter(destination.path))
            }, selected = destination == currentDestination, onClick = {
                onNavigate(destination)
            }
        )
    }
}





