package app.android.doggy.ui.component.dogBreedsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.android.doggy.R
import app.android.doggy.data.model.DogBreed
import app.android.doggy.util.capitalizeFirstLetter

@Composable
fun ItemDogCard(dog: DogBreed, onItemClicked: (dog: DogBreed) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(dog) }),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Image(
                modifier = Modifier
                    .size(40.dp, 40.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = R.drawable.ic_breed),
                alignment = Alignment.CenterStart,
                contentDescription = dog.name,
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = dog.name.capitalizeFirstLetter(),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
