package com.example.dashboard.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val path: String, val icon: ImageVector? = null,/*As soon as a user navigates to a higher level destination (such as the settings or upgrade
    composable), well not want this drawer icon to be composed. To be able to compose based on these
    properties, well need to know what is a root destination and what isn't */
    val isRootDestination: Boolean = true
) {
    object Home : Destination("home")
    object Feed : Destination("feed", Icons.Default.List)
    object Contacts : Destination("contacts", Icons.Default.Person)
    object Calendar : Destination("calendar", Icons.Default.DateRange)
    object Settings : Destination("settings", Icons.Default.Settings, isRootDestination = false)
    object Upgrade : Destination("upgrade", Icons.Default.Star, isRootDestination = false)
    object Creation : Destination("creation", isRootDestination = false)
    object Add : Destination("add", Icons.Default.Add, isRootDestination = false)

    companion object {
        fun fromString(route: String): Destination {
            return when (route) {
                Feed.path -> Feed
                Calendar.path -> Calendar
                Contacts.path -> Contacts
                Settings.path -> Settings
                Upgrade.path -> Upgrade
                Creation.path -> Creation
                Add.path -> Add
                else -> Home
            }
        }
    }

}
