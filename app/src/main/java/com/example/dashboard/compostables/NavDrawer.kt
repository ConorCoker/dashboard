package com.example.dashboard.compostables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.R
import com.example.dashboard.navigation.Destination
import com.example.dashboard.utils.capitalizeFirstLetter


/* Scaffolds drawerContent param seems to be deprecated so i am using ModalNavDrawer and the content trailing lambda,
* will contain our scaffold so that the scaffolds does not overlap the drawer*/
@Preview
@Composable
fun ModalNavDrawer(
    drawerState: DrawerState = DrawerState(initialValue = DrawerValue.Open),onNavigationClicked: (Destination) -> Unit = {},
    onLogOutClicked : () -> Unit = {},content: @Composable () -> Unit = {}
) {
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet() {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.label_name),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.label_email),
                fontSize = 16.sp
            )
            /* The divider is so that we can create some visible separation between the header of our
        navigation drawer, and the items that
        will follow it. Well use the fill max width modifier to ensure the divider fills the
        width of the drawer*/
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            DrawerItem(modifier = Modifier.fillMaxWidth(),label = Destination.Upgrade.path){
                //onClicked from DrawerItem
                onNavigationClicked(Destination.Upgrade)
            }
            Spacer(modifier = Modifier.height(8.dp))
            DrawerItem(modifier = Modifier.fillMaxWidth(),label = Destination.Settings.path){
                //onClicked from DrawerItem
                onNavigationClicked(Destination.Settings)
            }
            /*A weight of 1 via the weight modifier - this will force the spacer to use all of the
            remaining vertical space, pushing the logout item to the bottom of the drawer */
            Spacer(modifier = Modifier.weight(1f))
            DrawerItem(modifier = Modifier.fillMaxWidth(),label = stringResource(id = R.string.label_logout)){
                //onLogOutClicked lambda to pass up that we want to log out
                onLogOutClicked()
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }, drawerState = drawerState, scrimColor = Color.White) {
        content()
    }
}

//@Preview(showBackground = true)
@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String = "Label",
    onClicked: () -> Unit = {}
) {

    Text(modifier = modifier
        .clickable {
            //when label is clicked
            onClicked()
        }
        .padding(16.dp), text = capitalizeFirstLetter(label))

}