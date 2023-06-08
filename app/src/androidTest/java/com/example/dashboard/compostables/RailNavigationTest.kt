package com.example.dashboard.compostables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.dashboard.navigation.Destination
import com.example.dashboard.tags.Tags
import org.junit.Rule
import org.junit.Test

class RailNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test //failing i think due to screen rotation? have to check
    fun navigationRailIsDisplayed() {
        composeTestRule.setContent {
            RailNavigationBar(
                currentDestination = Destination.Feed,
                onNavigate = {},
                onCreateItem = {})
        }
        composeTestRule.onNodeWithTag(Tags.TAG_RAIL_NAV).assertIsDisplayed()
    }


}