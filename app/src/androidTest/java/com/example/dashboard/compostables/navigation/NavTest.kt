package com.example.dashboard.compostables.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.example.dashboard.navigation.Destination
import com.example.dashboard.navigation.Navigation
import org.junit.Rule
import org.junit.Test

class NavTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun feedDisplayedByDefault(){
        composeTestRule.setContent {
            Navigation(navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag(Destination.Feed.path).assertIsDisplayed()
    }

    private fun assertNavigation(destination: Destination){
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(destination.path)
        }
        composeTestRule.onNodeWithTag(destination.path).assertIsDisplayed()
    }

    @Test
    fun testNav(){
        assertNavigation(Destination.Upgrade)
        assertNavigation(Destination.Calendar)
        assertNavigation(Destination.Add) //also broken try fix
        assertNavigation(Destination.Settings)
        assertNavigation(Destination.Contacts)
    }
}