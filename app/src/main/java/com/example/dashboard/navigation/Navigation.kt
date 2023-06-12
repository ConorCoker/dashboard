package com.example.dashboard.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dashboard.compostables.ContentArea


@Composable
fun Navigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        /* We were previously using the feed as the startDestination of our nav host, using this
        home destination will have the same effect as we are now referencing the nested navigation
        graph which in turn has the feed as its starting destination.*/
        startDestination = Destination.Home.path
    ) {
        /*Nested Navigation, This will be used for the root level destinations within
        our scaffold - the feed, calendar and contacts*/
        navigation(startDestination = Destination.Feed.path, route = Destination.Home.path) {
            composable(Destination.Feed.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Feed)
            }
            composable(Destination.Contacts.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Contacts)
            }
            composable(Destination.Calendar.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Calendar)
            }
        }
        //Outside that nest ^ not inside any nest as no need.
        composable(route = Destination.Settings.path) {
            ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Settings)
        }
        composable(route = Destination.Upgrade.path) {
            ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Upgrade)
        }
        navigation(startDestination = Destination.Creation.path, route = Destination.Add.path) {
            composable(route = Destination.Creation.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), destination = Destination.Add)
            }
        }
    }
}
