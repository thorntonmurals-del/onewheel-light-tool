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
            Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Upper Status Header
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = boardName.ifEmpty { "Scanning for Pint X..." },
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp)
            )
            
            Text(
                text = if (isConnected) "CONNECTED" else "DISCONNECTED",
                fontSize = 14.sp,
                color = if (isConnected) Color.Green else Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Center Battery Gauge
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (batteryPercentage != null) "$batteryPercentage%" else "--",
                fontSize = 72.sp,
                color = when {
                    batteryPercentage == null -> Color.Gray
                    batteryPercentage <= 20 -> Color.Red
                    else -> Color.Green
                }
            )
            Text(
                text = "BATTERY CAPACITY",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Bottom Quick Action Control
        Button(
            onClick = onToggleLights,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333))
        ) {
            Text(
                text = "TOGGLE LIGHTOS MODE",
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.bind())
            )
        }
    }