package com.example.onewheellight

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnewheelToolScreen(
    boardName: String,
    batteryPercentage: Int?,
    isConnected: Boolean,
    onToggleLights: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "ONEWHEEL",
                style = MaterialTheme.typography.h6.copy(color = Color.White, letterSpacing = 2.sp)
            )
            Text(
                text = if (isConnected) "CONNECTED" else "SEARCHING...",
                style = MaterialTheme.typography.caption.copy(color = if (isConnected) Color.White else Color.Gray)
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = batteryPercentage?.let { "$it%" } ?: "--",
                style = MaterialTheme.typography.h1.copy(fontSize = 72.sp, color = Color.White)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onToggleLights,
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Black)
            ) {
                Text(
                    text = "TOGGLE HEADLIGHTS", 
                    style = MaterialTheme.typography.button.copy(color = Color.White)
                )
            }
        }
    }
}