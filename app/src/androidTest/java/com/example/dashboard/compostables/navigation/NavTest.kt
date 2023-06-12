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

//    private fun assertNavigation(destination: Destination){
//        composeTestRule.setContent {
//            val navController = rememberNavController()
//            Navigation(navController = navController)
//            navController.navigate(destination.path)
//        }
//        composeTestRule.onNodeWithTag(destination.path).assertIsDisplayed()
//    }

    @Test
    fun testNavToUpgrade(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(Destination.Upgrade.path)
        }
        composeTestRule.onNodeWithTag(Destination.Upgrade.path).assertIsDisplayed()
    }

    @Test
    fun testNavToFeed(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(Destination.Upgrade.path)
            navController.navigate(Destination.Feed.path)
        }
        composeTestRule.onNodeWithTag(Destination.Feed.path).assertIsDisplayed()
    }

    @Test
    fun testNavToContacts(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(Destination.Contacts.path)
        }
        composeTestRule.onNodeWithTag(Destination.Contacts.path).assertIsDisplayed()
    }

    @Test
    fun testNavToSettings(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(Destination.Settings.path)
        }
        composeTestRule.onNodeWithTag(Destination.Settings.path).assertIsDisplayed()
    }

    @Test
    fun testNavToCalender(){
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(Destination.Calendar.path)
        }
        composeTestRule.onNodeWithTag(Destination.Calendar.path).assertIsDisplayed()
    }
}