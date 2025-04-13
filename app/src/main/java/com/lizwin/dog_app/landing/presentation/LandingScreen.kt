package com.lizwin.dog_app.landing.presentation


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.lizwin.dog_app.R
import com.lizwin.dog_app.common.presentation.ui.ErrorScreen
import com.lizwin.dog_app.common.presentation.ui.LoadingDialog
import com.lizwin.dog_app.landing.domain.model.Dog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LandingScreen(
    viewModel: DogLandingScreenViewModel = hiltViewModel()
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

        state.isSuccess -> LandingScreenContent(dogList = state.dogData.response)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreenContent(
    dogList: List<Dog>
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.landing_title),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    ),
                placeholder = { Text(stringResource(R.string.search)) },
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search),
                        modifier = Modifier
                            .padding(6.dp),
                        tint = Color.Black
                    )
                },
                singleLine = true
            )

            Text(
                text = stringResource(R.string.results),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
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
                        Toast
                            .makeText(
                                context,
                                context.getString(
                                    R.string.item_clicked_toast_holder,
                                    dog.breeds.firstOrNull()?.name
                                ), Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                }
            }
        }
    }
}