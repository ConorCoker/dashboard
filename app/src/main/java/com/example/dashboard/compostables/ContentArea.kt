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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.navigation.Destination
import com.example.dashboard.utils.capitalizeFirstLetter

@Composable
fun ContentArea(modifier: Modifier = Modifier, destination: Destination) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        destination.icon?.let {
            Icon(modifier = Modifier.size(80.dp),imageVector = it, contentDescription = capitalizeFirstLetter(destination.path))
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(text = capitalizeFirstLetter(destination.path), fontSize = 18.sp)
    }

}


