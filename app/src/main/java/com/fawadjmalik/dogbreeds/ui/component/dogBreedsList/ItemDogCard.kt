package com.fawadjmalik.dogbreeds.ui.component.dogBreedsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.fawadjmalik.dogbreeds.domain.model.DogBreed
import com.fawadjmalik.dogbreeds.utils.Constants.COMMA
import com.fawadjmalik.dogbreeds.utils.Constants.ONE
import com.fawadjmalik.dogbreeds.utils.Constants.ZERO
import com.fawadjmalik.dogbreeds.utils.extensions.generateSubBreedsText

/**
 * Author: Muhammad Fawad Jawaid Malik
 * Composables for each dog breed item in the list.
 */
@Composable
fun ItemDogCard(dog: DogBreed, onItemClicked: (dog: DogBreed) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(dog) }),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            val image: Painter = rememberAsyncImagePainter(dog.imageUrl)
            Image(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = image,
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = dog.name,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = getSubBreedsCountText(dog.subBreeds),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    style = typography.caption
                )

            }

            if (dog.isFavourite) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    FavouriteTag()
                }
            }
        }
    }
}

private fun getSubBreedsCountText(dogSubBreeds: String): String {
    var subBreedsCount = ZERO
    if (dogSubBreeds.isNotEmpty() && dogSubBreeds.contains(COMMA)) {
        subBreedsCount = dogSubBreeds.split(COMMA).size.toString()
    } else if (dogSubBreeds.isNotEmpty() && !dogSubBreeds.contains(COMMA)) {
        subBreedsCount = ONE
    }
    return subBreedsCount.generateSubBreedsText()
}
