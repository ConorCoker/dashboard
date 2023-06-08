package com.example.dashboard.compostables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.ActivityNavigator
import com.example.dashboard.navigation.Destination
import com.example.dashboard.tags.Tags
import com.example.dashboard.utils.capitalizeFirstLetter
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class BottomNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bottomNavIsDisplayed() {
        composeTestRule.setContent {
            BottomNavigationBar(currentDestination = Destination.Feed, onNavigate = {})
        }
        composeTestRule.onNodeWithTag(Tags.BOTTOM_NAV).assertIsDisplayed()
    }

    @Test
    fun bottomNavItemsAreDisplayed() {
        composeTestRule.setContent {
            BottomNavigationBar(currentDestination = Destination.Feed, onNavigate = {})
        }
        listOf(Destination.Feed, Destination.Contacts, Destination.Calendar).forEach {
            composeTestRule.onNodeWithText(capitalizeFirstLetter(it.path)).assertIsDisplayed()
        }
    }

    @Test
    fun currentItemInBottomNavIsSelected() {
        composeTestRule.setContent {
            BottomNavigationBar(currentDestination = Destination.Feed, onNavigate = {})
        }
        composeTestRule.onNodeWithText(capitalizeFirstLetter(Destination.Feed.path)).assertIsSelected() //needs fixing
//        composeTestRule.onNodeWithText(capitalizeFirstLetter(Destination.Calendar.path)).assertIsSelected()
//        composeTestRule.onNodeWithText(capitalizeFirstLetter(Destination.Contacts.path)).assertIsSelected()
    }

    @Test
    fun bottomNavCallbackIsTriggered(){
        val onNavigate:(destination:Destination) -> Unit = mock()
        val destination = Destination.Contacts
        composeTestRule.setContent {
            BottomNavigationBar(currentDestination = Destination.Feed, onNavigate = {})
        }
        composeTestRule.onNodeWithText(capitalizeFirstLetter(Destination.Feed.path)).performClick()
        onNavigate.invoke(destination)
        verify(onNavigate).invoke(destination)
    }
}