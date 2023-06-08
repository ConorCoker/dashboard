package com.example.dashboard.compostables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.example.dashboard.navigation.Destination
import com.example.dashboard.utils.capitalizeFirstLetter
import org.junit.Rule
import org.junit.Test
import com.example.dashboard.R
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class RootDestinationTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleDisplayed() {
        composeTestRule.setContent {
            RootTopAppBar(
                currentDestination = Destination.Home,
                showSnackBar = { /*TODO*/ },
                openDrawer = {})
        }
        composeTestRule.onNodeWithText(capitalizeFirstLetter(Destination.Home.path))
            .assertIsDisplayed()
    }

    @Test
    fun menuIconDisplayed() {
        composeTestRule.setContent {
            RootTopAppBar(
                currentDestination = Destination.Home,
                showSnackBar = { /*TODO*/ },
                openDrawer = {})
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_open_menu
            )
        ).assertIsDisplayed()
    }

    @Test
    fun menuItemClickTriggersCallback() {
        val openDrawer: () -> Unit = mock()
        composeTestRule.setContent {
            RootTopAppBar(
                currentDestination = Destination.Home,
                showSnackBar = { /*TODO*/ },
                openDrawer = openDrawer
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_open_menu
            )
        ).performClick()
        verify(openDrawer).invoke()
    }

    @Test
    fun infoIconIsDisplayed() {
        composeTestRule.setContent {
            RootTopAppBar(
                currentDestination = Destination.Contacts,
                showSnackBar = { /*TODO*/ },
                openDrawer = {})
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_more_info
            )
        ).assertIsDisplayed()
    }

    @Test
    fun infoIconIsNotDisplayed() {
        composeTestRule.setContent {
            RootTopAppBar(
                currentDestination = Destination.Feed,
                showSnackBar = { /*TODO*/ },
                openDrawer = {})
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_more_info
            )
        )
            .assertDoesNotExist() //isNotDisplayed() failed possibly as it could not be found due to not existing
    }

    @Test
    fun infoIconTriggersCallback() {
        val showSnackBar: () -> Unit = mock()
        composeTestRule.setContent {
            RootTopAppBar(
                currentDestination = Destination.Home,
                showSnackBar = showSnackBar,
                openDrawer = {})
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_more_info
            )
        ).performClick()
        verify(showSnackBar).invoke(
//            InstrumentationRegistry.getInstrumentation().targetContext.getString(
//                R.string.not_available_yet
            )


    }

}