package com.example.dashboard.compostables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dashboard.R
import com.example.dashboard.navigation.Destination
import com.example.dashboard.navigation.Navigation
import com.example.dashboard.utils.capitalizeFirstLetter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
    "RememberReturnType"
)
@Preview
@Composable
fun Home(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()

    /*derivedStateOf function, this will allow us to create a state object based on the calculation
     of our destination.
     The value will not be recalculated across compositions unless the value of the navBackStackEntry
     reference changes. If null sets currentDestination to Home */
    val currentDestination by derivedStateOf {
        navBackStackEntry.value?.destination?.route?.let {
            Destination.fromString(it)
        } ?: run {
            Destination.Home
        }
    }
    /*By retaining the state of the SnackBarHost, it helps prevent SnackBars from disappearing
     or reappearing over the old ones. This ensures that SnackBars are displayed in the correct
     order without unexpected overlapping or stacking behavior. */
    val snackBarHostState = remember { SnackbarHostState() }

    /*The coroutineScope is used in relation to the snackbar to launch a coroutine that shows the
    SnackBar message. By using the coroutineScope, the SnackBar can be displayed asynchronously
    without blocking the main UI thread. */
    val coroutineScope = rememberCoroutineScope()

    //We need to declare this here as we wont have access to string resources within coroutine scope.
    val snackBarMessage = stringResource(R.string.not_available_yet)
    val snackBarMessageLogOut = stringResource(R.string.label_logout)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavDrawer(drawerState = drawerState, onLogOutClicked = {
        //log out
        coroutineScope.launch {
            snackBarHostState.showSnackbar(snackBarMessageLogOut)
        }
        coroutineScope.launch {
            drawerState.close()
        }
    }, onNavigationClicked = {
        navController.navigate(it.path)
        coroutineScope.launch {
            drawerState.close()
        }
    }) {
        Scaffold(modifier = modifier, topBar = {
            DestinationTopBar(
                destination = currentDestination,
                onNavigateUp = { navController.popBackStack() },
                onOpenDrawer = {
                    coroutineScope.launch {
                        //RootTopAppBar lambda called to open drawer
                        drawerState.open()
                    }
                }, showSnackBar = {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(snackBarMessage)
                    }
                })
        }, floatingActionButton = {
            if (currentDestination == Destination.Feed) {
                FloatingActionButton(onClick = { navController.navigate(Destination.Creation.path) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.cd_create_item)
                    )
                }
            }
        }, bottomBar = {
            BottomNavigationBar(currentDestination = Destination.Home, onNavigate = {
                /*PopUpTo will pop the back stack up until a defined destination, which allows
                us to avoid building up a large collection of entries within our
                back stack */
                navController.navigate(it.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    /*This means that each destination can only have one back stack entry, this avoids
                    duplicate copies existing in our
                    back stack should a destination be reselected */
                    launchSingleTop = true
                    //This means that if a previously selected item is reselected, its state will be restored.
                    restoreState = true
                }
            })
            //This is a param of the Scaffold where we tell it what SnackBarHost we are using.
        }, snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) {
            /* PaddingValues object that gives you the right amount of padding for your top
            app bar, bottom bar, etc. */
            Navigation(modifier = modifier, navController = navController)


        }
    }


}