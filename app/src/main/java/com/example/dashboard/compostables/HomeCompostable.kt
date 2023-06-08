package com.example.dashboard.compostables

import android.annotation.SuppressLint
import android.app.LocaleConfig
import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable.Orientation
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


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

    //this is to be able to observe the state of device orientation
    var orientation by remember {
        mutableStateOf(Configuration.ORIENTATION_PORTRAIT)
    }
    val configuration = LocalConfiguration.current

    /* If our config change this will launch a new coroutine scope for it */
    LaunchedEffect(configuration) {
        //save any changes to the orientation value on the config object
        snapshotFlow {
            configuration.orientation
        }.collect {
            orientation = it
        }
    }

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
            if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
                if (currentDestination == Destination.Feed) {
                    FloatingActionButton(onClick = { navController.navigate(Destination.Creation.path) }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.cd_create_item)
                        )
                    }
                }
            }
        }, bottomBar = {
            if (orientation != Configuration.ORIENTATION_LANDSCAPE && currentDestination.isRootDestination) {
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
            }
            //This is a param of the Scaffold where we tell it what SnackBarHost we are using.
        }, snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) {
            /* PaddingValues object that gives you the right amount of padding for your top
            app bar, bottom bar, etc. */
            //No longer need this here as its now within out body
//            Navigation(modifier = modifier, navController = navController)
            Body(
                navController = navController,
                destination = currentDestination,
                orientation = orientation,
                onCreateItem = { navController.navigate(Destination.Add.path) },
                onNavigate = {
                    navController.navigate(it.path) {
                        popUpTo(Destination.Home.path) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )


        }
    }


}