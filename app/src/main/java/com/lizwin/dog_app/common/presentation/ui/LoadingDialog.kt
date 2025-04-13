package com.lizwin.dog_app.common.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.window.Dialog
import com.lizwin.dog_app.R

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Card(
            modifier = modifier
                .width(300.dp)
                .height(86.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = modifier.size(40.dp),
                    color = Color(0xFF1AAAD7)
                )

                Text(
                    text = stringResource(R.string.loading),
                    modifier = Modifier.padding(start = 16.dp),
                    color = Color(0xFF717171),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.2.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )
            }
        }
    }
}