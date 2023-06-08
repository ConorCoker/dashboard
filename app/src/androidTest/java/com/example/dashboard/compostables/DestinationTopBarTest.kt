package com.example.dashboard.compostables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.dashboard.navigation.Destination
import com.example.dashboard.tags.Tags
import org.junit.Rule
import org.junit.Test

class DestinationTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rootDestinationTopBarIsDisplayedWhenInRootDestination(){
        composeTestRule.setContent {
            DestinationTopBar(
                destination = Destination.Feed,
                onNavigateUp = { /*TODO*/ },
                onOpenDrawer = { /*TODO*/ }) {

            }

        }
        composeTestRule.onNodeWithTag(Tags.TAG_ROOT_TOP_BAR).assertIsDisplayed()
    }

    @Test
    fun rootDestinationTopBarIsNotDisplayedWhenNotInRootDestination(){
        composeTestRule.setContent {
            DestinationTopBar(
                destination = Destination.Add,
                onNavigateUp = { /*TODO*/ },
                onOpenDrawer = { /*TODO*/ }) {

            }

        }
        composeTestRule.onNodeWithTag(Tags.TAG_ROOT_TOP_BAR).assertDoesNotExist()
    }

    @Test
    fun childDestinationTopBarIsDisplayedWhenInChildDestination(){
        composeTestRule.setContent {
            DestinationTopBar(
                destination = Destination.Add,
                onNavigateUp = { /*TODO*/ },
                onOpenDrawer = { /*TODO*/ }) {

            }

        }
        composeTestRule.onNodeWithTag(Tags.TAG_CHILD_TOP_BAR).assertIsDisplayed()
    }

}