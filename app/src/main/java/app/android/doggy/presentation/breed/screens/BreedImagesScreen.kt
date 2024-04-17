package app.android.doggy.presentation.breed.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.android.doggy.R
import app.android.doggy.core.utils.Constants
import app.android.doggy.core.utils.capitalizeFirstLetter
import app.android.doggy.presentation.breed.components.ErrorView
import app.android.doggy.presentation.breed.components.ProgressBarView
import app.android.doggy.presentation.breed.viewmodels.BreedImagesViewModel
import coil.compose.AsyncImage
import coil.imageLoader
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

// BreedImagesScreen: Displays images for a selected dog breed
@Composable
fun BreedImagesScreen(
    navController: NavController,
    breedImagesViewModel: BreedImagesViewModel = hiltViewModel(),
    name: String
) {
    Scaffold(
        topBar = {
            TopBar(navController, name)
        },
        content = { padding ->
            val breedImagesStateHolder = breedImagesViewModel.breedImagesStateHolderHolder
            if (breedImagesStateHolder.value.isLoading) {
                ProgressBarView()
            }

            breedImagesStateHolder.value.error.takeIf { it.isNotBlank() }?.let {
                ErrorView(it)
            }

            breedImagesStateHolder.value.breedImages?.let {
                if (it.isNotEmpty()) {
                   // ErrorView(Constants.ERROR_MESSAGE)
                    ImagesView(images = it, paddingValues = padding)
                } else {
                    ErrorView(Constants.ERROR_MESSAGE)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, name: String) {
    TopAppBar(
        title = {
            Text(
                text = name.capitalizeFirstLetter(),
                modifier = Modifier.padding(PaddingValues(start = 10.dp)),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp, 24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .testTag("breedImagesBackButton")
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagesView(
    images: List<String>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            val state = rememberPagerState()
            HorizontalPager(
                count = images.size,
                state = state
            ) { page ->
                AsyncImage(
                    model = images[page].toUri(),
                    contentDescription = "Image",
                    imageLoader = LocalContext.current.imageLoader,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .testTag("breedImages"),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(id = R.drawable.ic_dog_placeholder),
                    error = painterResource(id = R.drawable.ic_dog_error)
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))

            DotsIndicator(
                totalDots = images.size,
                selectedIndex = state.currentPage,
                selectedColor = MaterialTheme.colorScheme.secondary,
                unSelectedColor = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .testTag("dotIndicator")
            .wrapContentHeight()

    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}
