package org.example.project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.widgetsScreen.BottomNavigationBar
import org.example.project.widgetsScreen.Calendar
import org.example.project.widgetsScreen.XYSamplePlot

class CalendarScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MaterialTheme {
            Scaffold(bottomBar = {
                BottomNavigationBar(navigator)
            }) { innerPadding ->
                Column(Modifier.padding(innerPadding)) {
                    Calendar()
                    XYSamplePlot()
                }
            }
        }
    }
}