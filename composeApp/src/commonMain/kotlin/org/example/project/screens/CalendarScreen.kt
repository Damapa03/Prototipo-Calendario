package org.example.project.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.Calendar
import org.example.project.XYSamplePlot

class HomeScreen:Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            Scaffold(bottomBar = {
                BottomNavigationBar()
            }) {
                Column {
                    Calendar()
                    XYSamplePlot()
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar() {
        var selected by remember { mutableStateOf(0) }
        val items = listOf("Home", "Calendar", "Notifications", "Profile")
        val icons = listOf(
            Icons.Filled.Home,
            Icons.Filled.DateRange,
            Icons.Filled.Notifications,
            Icons.Filled.Person
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
                .graphicsLayer { shadowElevation = 10f }
        ) {
            FloatingActionButton(onClick = {},
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-30).dp), // Posición del FAB sobre el hueco
                shape = CircleShape,
                contentColor = Color(0xFF7F57FF)
            ){
                Icon(Icons.Filled.Add, contentDescription = "Menu")
            }
            Canvas(modifier = Modifier.matchParentSize()) {
                val width = size.width
                val height = size.height
                val curveSize = 100f
                val curveHeight = 140f

                val path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo((width / 2) - curveSize, 0f)
                    quadraticBezierTo(width / 2, curveHeight, (width / 2) + curveSize, 0f)
                    lineTo(width, 0f)
                    lineTo(width, height)
                    lineTo(0f, height)
                    close()
                }
                drawPath(path, color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    IconButton(onClick = { selected = index }) {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                            tint = if (selected == index) Color(0xFF7F57FF) else Color.Gray,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}