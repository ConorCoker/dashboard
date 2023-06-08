package com.example.dashboard.compostables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.dashboard.R
import com.example.dashboard.navigation.Destination
import com.example.dashboard.utils.capitalizeFirstLetter

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    destination: Destination,
    onNavigateUp: () -> Unit,
    onOpenDrawer: () -> Unit,
    showSnackBar: () -> Unit
) {
    if (destination.isRootDestination) {
        RootTopAppBar(
            modifier,
            currentDestination = destination,
            openDrawer = onOpenDrawer,
            showSnackBar = showSnackBar
        )
    } else {
        ChildDestinationTopBar(
            modifier,
            title = capitalizeFirstLetter(destination.path),
            onNavigateUp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootTopAppBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    showSnackBar: () -> Unit,
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = capitalizeFirstLetter(currentDestination.path))
        },
        actions = {
            if (currentDestination != Destination.Feed) {
                IconButton(onClick = {
                    showSnackBar()
                }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.cd_more_info),
                        tint = Color.LightGray
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.LightGray
        ), navigationIcon = {
            if (currentDestination.isRootDestination) {
                IconButton(onClick = {
                    openDrawer()
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(
                            id = R.string.cd_open_menu
                        )
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildDestinationTopBar(modifier: Modifier = Modifier, title: String,onNavigateUp:() -> Unit) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        }, navigationIcon = { //onNavigateUp() informs caller to navigate "up" to root package
            IconButton(onClick = { onNavigateUp()}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.cd_navigate_up) )
            }
        }
    )
}