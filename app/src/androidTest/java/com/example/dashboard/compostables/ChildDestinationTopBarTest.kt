package com.example.dashboard.compostables

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.dashboard.navigation.Destination
import com.example.dashboard.utils.capitalizeFirstLetter
import org.junit.Rule
import org.junit.Test
import com.example.dashboard.R

class ChildDestinationTopBarTest {

    /*Inside of this class, we now need to define a reference to the ComposeContentTestRule
    class - this is what were going to use to set the composable content on screen, allowing
    us to perform interactions and assertions from within our tests.
    We don't need to specify any form of activity for our composables to be launched in, the test
    rule will handle that for us. So using this rule we will set the composable content to be
    composed, the test will then launch a host activity which will be used to compose our
    provided content inside of.*/
    @get:Rule
    val composeTestRule = createComposeRule()


    /* The onNodeWithText function can be used to locate a composable that is displaying the text
     that we have provided to the function. Composables will be located in the form of a semantic
     node. Because our composables are represented via semantics, in our tests we are essentially
     going to be locating nodes within our semantic tree. In this case, this is done using the
     onNodeWithText function.*/

    @Test
    fun titleDisplayed(){
        val title = capitalizeFirstLetter(Destination.Feed.path)
        composeTestRule.setContent {
            ChildDestinationTopBar(title = title) {

            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

   @Test
   fun navigationItemDisplayed(){
       composeTestRule.setContent {
           ChildDestinationTopBar(title = capitalizeFirstLetter(Destination.Feed.path), onNavigateUp = {})
           composeTestRule.onNodeWithContentDescription(stringResource(id = R.string.cd_navigate_up)).assertIsDisplayed()
       }


   }


}