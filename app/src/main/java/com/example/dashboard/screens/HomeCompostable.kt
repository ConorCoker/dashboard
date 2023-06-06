package com.example.dashboard.screens

import android.annotation.SuppressLint
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(modifier: Modifier = Modifier) {


    Scaffold(modifier = modifier, topBar = {
        Text(text = "Home")
    }, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            //fab
        }
    }) {
        //scaffold content here
    }



}