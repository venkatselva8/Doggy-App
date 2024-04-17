package app.android.doggy.presentation.breed.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import app.android.doggy.presentation.MainActivity
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class BreedsScreenKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateTopBarVisible() {
        composeRule.onNodeWithText("Doggy").assertIsDisplayed()
    }

    @Test
    fun validateProgressBarVisible() {
        composeRule.onNodeWithTag("progressBar").assertIsDisplayed()
    }

    @Test
    fun validateBreedListVisible() {
        composeRule.apply {
            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedList").assertIsDisplayed()
        }
    }

    @Test
    fun validateNavigationFromBreedListToBreedImages() {
        composeRule.apply {
            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedList").onChildAt(0).performClick()
            onNodeWithText("Affenpinscher").assertIsDisplayed()
        }
    }
}
