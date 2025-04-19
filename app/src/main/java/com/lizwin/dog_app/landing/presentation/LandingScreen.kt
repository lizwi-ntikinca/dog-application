package com.lizwin.dog_app.landing.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.lizwin.dog_app.R
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.common.presentation.navigation.Navigator
import com.lizwin.dog_app.common.presentation.ui.ErrorScreen
import com.lizwin.dog_app.common.presentation.ui.LoadingDialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LandingScreen(
    viewModel: DogLandingScreenViewModel = hiltViewModel(),
    navigator: Navigator
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(LandingScreenUiEvent.FetchDogData)
    }

    when {
        state.isLoading -> LoadingDialog()
        !state.isSuccess -> ErrorScreen(
            onTryAgainClicked = {
                viewModel.onEvent(LandingScreenUiEvent.FetchDogData)
            }
        )

        state.isSuccess -> LandingScreenContent(
            dogList = state.filteredDogList,
            uiEvent = viewModel::onEvent,
            navigator = navigator
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreenContent(
    dogList: List<Dog>,
    uiEvent: (LandingScreenUiEvent) -> Unit,
    navigator: Navigator
) {
    var searchQuery by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.landing_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    uiEvent(LandingScreenUiEvent.Search(searchQuery))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    ),
                textStyle = TextStyle(
                    color = colorScheme.outline
                ),
                placeholder = { Text(stringResource(R.string.search)) },
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search),
                        modifier = Modifier
                            .padding(6.dp),
                        tint = colorScheme.outline
                    )
                },
                singleLine = true,
            )

            Text(
                text = stringResource(R.string.results),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = colorScheme.primary
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(dogList) { dog ->
                    LandingItem(dog) {
                        navigator.navigateToDogDetailsScreen(dog.id)
                    }
                }
            }
        }
    }
}