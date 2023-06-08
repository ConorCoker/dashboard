package com.example.dashboard.compostables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.navigation.Destination
import com.example.dashboard.tags.Tags
import com.example.dashboard.utils.capitalizeFirstLetter

@Composable
fun ContentArea(modifier: Modifier = Modifier, destination: Destination) {

    Column(
        modifier = modifier.testTag(destination.path),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        destination.icon?.let {
            Icon(modifier = Modifier.size(80.dp).testTag(Tags.CONTENT_ICON),imageVector = it, contentDescription = capitalizeFirstLetter(destination.path))
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(modifier = Modifier.testTag(Tags.CONTENT_TITLE),text = capitalizeFirstLetter(destination.path), fontSize = 18.sp)
    }

}


