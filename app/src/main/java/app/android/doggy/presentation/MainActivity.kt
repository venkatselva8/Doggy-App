package app.android.doggy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.android.doggy.presentation.navigation.DoggyNavigation
import app.android.doggy.presentation.ui.theme.DoggyTheme
import dagger.hilt.android.AndroidEntryPoint

// MainActivity: Entry point of the Android application
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoggyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DoggyNavigation()
                }
            }
        }
    }
}