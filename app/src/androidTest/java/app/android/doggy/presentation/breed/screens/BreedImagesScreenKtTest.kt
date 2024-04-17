package app.android.doggy.presentation.breed.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.android.doggy.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalTestApi::class)
class BreedImagesScreenKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateBreedImagesProgressBarVisible() {
        composeRule.apply {
            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedList").onChildAt(0).performClick()
            onNodeWithTag("progressBar").assertIsDisplayed()
        }
    }

    @Test
    fun validateBreedImagesTopBarBackButtonVisible() {
        composeRule.apply {
            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedList").onChildAt(0).performClick()
            onNodeWithTag("breedImagesBackButton").assertIsDisplayed()
        }
    }

    @Test
    fun validateBreedImagesTitleVisible() {
        composeRule.apply {
            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedList").onChildAt(0).performClick()
            onNodeWithText("Affenpinscher").assertIsDisplayed()
        }
    }

    @Test
    fun validateBreedImagesViewVisible() {
        composeRule.apply {
            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedList").onChildAt(0).performClick()

            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedImages").assertIsDisplayed()
        }
    }

    @Test
    fun validateBreedImagesDotIndicatorVisible() {
        composeRule.apply {
            waitUntilDoesNotExist(hasTestTag("progressBar"), 3000)
            onNodeWithTag("breedList").onChildAt(0).performClick()

            waitUntilAtLeastOneExists(hasTestTag("breedImages"), 3000)
            onNodeWithTag("dotIndicator").assertIsDisplayed()
        }
    }
}
