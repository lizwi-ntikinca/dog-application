package com.lizwin.dog_app.dog_details.view_dog_details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lizwin.dog_app.R
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.common.presentation.ui.ErrorScreen
import com.lizwin.dog_app.common.presentation.ui.LoadingDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetailsScreen(
    id: String,
    onBackClick: () -> Unit,
    viewModel: DogDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(DogDetailsScreenUiEvent.Load(id))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.dog_details_title),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                state.isLoading -> {
                    LoadingDialog()
                }

                !state.isSuccess -> ErrorScreen(
                    onTryAgainClicked = {
                        viewModel.onEvent(DogDetailsScreenUiEvent.Load(id))
                    }
                )

                state.isSuccess -> {
                    DogDetailsScreenContent(state.dogInformation.response)
                }
            }
        }
    }
}

@Composable
fun DogDetailsScreenContent(
    dog: Dog
){
    val breed = dog.breeds.firstOrNull()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = dog.url,
            contentDescription = stringResource(R.string.dog_description),
            modifier = Modifier
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.large)
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        breed?.name?.let {
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            )
        }

        Text(
            text = generateDogBreedDescription(
                name = breed?.name?.ifBlank { stringResource(R.string.unknown_breed) } ?: stringResource(R.string.unknown_breed),
                bredFor = breed?.bredFor?.ifBlank { stringResource(R.string.general_companionship) } ?: stringResource(R.string.general_companionship),
                breedGroup = breed?.breedGroup?.ifBlank { stringResource(R.string.unknown_group) } ?: stringResource(R.string.unknown_group),
                origin = breed?.origin?.ifBlank { stringResource(R.string.unknown_origin) } ?: stringResource(R.string.unknown_origin) ,
                lifeSpan = breed?.lifeSpan?.ifBlank { stringResource(R.string.life_span_not_available) } ?: stringResource(R.string.life_span_not_available),
                temperament = breed?.temperament?.ifBlank { stringResource(R.string.temperament_information_not_available) } ?: stringResource(R.string.temperament_information_not_available),
                weightMetric = breed?.weight?.metric?.ifBlank { stringResource(R.string.not_specified) } ?: stringResource(R.string.not_specified),
                heightMetric = breed?.height?.metric?.ifBlank { stringResource(R.string.not_specified) } ?: stringResource(R.string.not_specified),
                imageWidth = dog.width,
                imageHeight = dog.height
            ),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun generateDogBreedDescription(
    name: String,
    bredFor: String,
    breedGroup: String,
    origin: String,
    lifeSpan: String,
    temperament: String,
    weightMetric: String,
    heightMetric: String,
    imageWidth: Int,
    imageHeight: Int
): String {
    return """
        🐾 $name — Breed Overview
        
        The $name is a distinguished and elegant breed originally from $origin. 
        Historically bred for $bredFor, this breed belongs to the $breedGroup group 
        and is known for its temperament: $temperament.
        
        With an average life span of $lifeSpan, $name dogs are cherished for their unique personality and presence.
        
        📏 Breed Height: $heightMetric cm  
        ⚖️ Weight: $weightMetric kg  
        🖼️ Image Size: ${imageWidth}×${imageHeight} pixels
        
        Their iconic appearance and regal demeanor make the $name one of the most recognizable breeds in the world.
    """.trimIndent()
}