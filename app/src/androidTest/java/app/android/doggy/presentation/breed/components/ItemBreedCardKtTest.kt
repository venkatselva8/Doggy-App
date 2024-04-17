package app.android.doggy.presentation.breed.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import app.android.doggy.data.remote.model.Breed
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ItemBreedCardKtTest{

    private lateinit var breed: Breed

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        breed = Breed(name = "akita")
        composeRule.setContent {
            ItemBreedCard(breed = breed,{})
        }
    }

    @Test
    fun validateBreedCardVisible(){
        composeRule.apply {
            onNodeWithTag("cardBreed").assertIsDisplayed()
        }
    }

    @Test
    fun validateBreedName(){
        composeRule.apply{
            onNodeWithText("Akita")
        }
    }
}