package com.lizwin.dog_app.common.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.lizwin.dog_app.R
import com.lizwin.dog_app.api_key.presentation.ApiKeyEvent

@Composable
fun ApiKeyScreen(
    apiKeyEvent: (ApiKeyEvent) -> Unit,
    navigator: Navigator
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var apiKey by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }

            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = stringResource(R.string.image_description),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.title_api_key_screen),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 28.sp
                )
            )

            Text(
                text = stringResource(R.string.description_api_key_screen),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                style = TextStyle(
                    color = Color(0xFF757575),
                    fontWeight = FontWeight.W400,
                    fontSize = 20.sp,
                )
            )

            Text(
                text = stringResource(id = R.string.enter_api_key),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            OutlinedTextField(
                value = apiKey,
                onValueChange = {
                    apiKey = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = stringResource(id = R.string.toggle_password_visibility))
                    }
                },
                singleLine = true
            )

            Button(
                onClick = {
                    apiKeyEvent(ApiKeyEvent.OnDoneButtonClicked(apiKey))
                    navigator.navigateToHomeScreen()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                enabled = apiKey.isNotEmpty(),
                colors = ButtonColors(
                    contentColor = Color(0xFFFFFFFF),
                    containerColor = Color(0xFF00A0D2),
                    disabledContainerColor = Color(0xFF757575),
                    disabledContentColor = Color(0xFFFFFFFF)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.done)
                )
            }
        }
    }
}