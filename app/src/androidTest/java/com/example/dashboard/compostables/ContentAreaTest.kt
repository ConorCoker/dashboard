package com.example.dashboard.compostables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.dashboard.navigation.Destination
import com.example.dashboard.tags.Tags
import com.example.dashboard.utils.capitalizeFirstLetter
import org.junit.Rule
import org.junit.Test

class ContentAreaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun correctDestinationIsDisplayed(){
        val destination = Destination.Feed
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(destination.path).assertIsDisplayed()
    }

    @Test
    fun contentTitleIsDisplayed(){
        val destination = Destination.Calendar
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(Tags.CONTENT_TITLE).assertTextEquals(capitalizeFirstLetter(destination.path))
    }

    @Test
    fun contentIconIsDisplayed(){
        val destination = Destination.Calendar
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(Tags.CONTENT_ICON).assertIsDisplayed()
    }
}