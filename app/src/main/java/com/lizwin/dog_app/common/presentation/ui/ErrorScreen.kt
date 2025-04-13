package com.lizwin.dog_app.common.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lizwin.dog_app.R

@Composable
fun ErrorScreen(
    errorMessage: String = stringResource(R.string.empty),
    onTryAgainClicked: () -> Unit
) {
    Box {
        Card(
            colors = CardDefaults.cardColors(contentColor = Color.White),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.something_went_wrong),
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W700
                )

                if(errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        modifier = Modifier.padding(24.dp),
                        fontSize = 14.sp,
                        color = Color(0xFF717171),
                        textAlign = TextAlign.Center
                    )
                }

                Button(
                    onClick = { onTryAgainClicked() },
                    modifier = Modifier,
                    colors = ButtonColors(
                        containerColor = Color(0xFF81D4FA),              // Soft Light Blue
                        contentColor = Color(0xFF0D47A1),                // Deep Blue text
                        disabledContainerColor = Color(0xFFEEEEEE),      // Very light grey background
                        disabledContentColor = Color(0xFFB0BEC5)         // Cool grey text
                    )
                ) {
                    Text(
                        text = stringResource(R.string.try_again),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}