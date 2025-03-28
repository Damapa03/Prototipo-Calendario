package org.example.project

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@Composable
fun Calendar() {
    var fechaActual by remember {
        mutableStateOf(
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
    }

    val diasDelMes = obtenerDiasDelMes(fechaActual.year, fechaActual.monthNumber)
    val primerDiaSemana = obtenerPrimerDiaSemana(fechaActual.year, fechaActual.monthNumber)

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { fechaActual = fechaActual.minus(DatePeriod(months = 1)) }) {
                Text("<", fontSize = 24.sp)
            }
            Text("${fechaActual.month.name} ${fechaActual.year}", fontSize = 20.sp)
            IconButton(onClick = { fechaActual = fechaActual.plus(DatePeriod(months = 1)) }) {
                Text(">", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val diasSemana = listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            diasSemana.forEach { dia ->
                Text(text = dia, fontSize = 16.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(primerDiaSemana) { Spacer(modifier = Modifier.size(40.dp)) }

            // Días del mes
            items(diasDelMes) { dia ->
                val day = dia + 1

                if (day == fechaActual.dayOfMonth){
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Magenta)
                            .clickable { /* Acción al seleccionar un día */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day.toString(), fontSize = 16.sp)
                    }
                }else {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.LightGray)
                            .clickable { /* Acción al seleccionar un día */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day.toString(), fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

fun obtenerDiasDelMes(anio: Int, mes: Int): Int {
    val fecha = LocalDate(anio, mes, 1)

    return when (fecha.month) {
        kotlinx.datetime.Month.FEBRUARY -> if (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0)) 29 else 28
        kotlinx.datetime.Month.APRIL, kotlinx.datetime.Month.JUNE,
        kotlinx.datetime.Month.SEPTEMBER, kotlinx.datetime.Month.NOVEMBER -> 30

        else -> 31
    }
}

// Función para obtener el día de la semana del primer día del mes (0 = Lunes, 6 = Domingo)
fun obtenerPrimerDiaSemana(año: Int, mes: Int): Int {
    val primerDia = LocalDate(año, mes, 1).dayOfWeek
    return primerDia.ordinal  // Ajuste para empezar en lunes
}

fun diaActual(): LocalDate {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
}

//@Composable
//fun PantallaCalendario() {
//    MaterialTheme {
//        Scaffold(bottomBar = {
//            BottomNavigationBar()
//        }) {
//            Column {
//                Calendar()
//                XYSamplePlot()
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar() {
//    var selected by remember { mutableStateOf(0) }
//    val items = listOf("Home", "Calendar", "Notifications", "Profile")
//    val icons = listOf(
//        Icons.Filled.Home,
//        Icons.Filled.DateRange,
//        Icons.Filled.Notifications,
//        Icons.Filled.Person
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(80.dp)
//            .background(Color.White)
//            .graphicsLayer { shadowElevation = 10f }
//    ) {
//        FloatingActionButton(onClick = {},
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .offset(y = (-30).dp), // Posición del FAB sobre el hueco
//            shape = CircleShape,
//            contentColor = Color(0xFF7F57FF)){
//            Icon(Icons.Filled.Add, contentDescription = "Menu")
//        }
//        Canvas(modifier = Modifier.matchParentSize()) {
//            val width = size.width
//            val height = size.height
//            val curveSize = 100f
//            val curveHeight = 140f
//
//            val path = Path().apply {
//                moveTo(0f, 0f)
//                lineTo((width / 2) - curveSize, 0f)
//                quadraticBezierTo(width / 2, curveHeight, (width / 2) + curveSize, 0f)
//                lineTo(width, 0f)
//                lineTo(width, height)
//                lineTo(0f, height)
//                close()
//            }
//            drawPath(path, color = Color.White)
//        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp, vertical = 12.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            items.forEachIndexed { index, item ->
//                IconButton(onClick = { selected = index }) {
//                    Icon(
//                        imageVector = icons[index],
//                        contentDescription = item,
//                        tint = if (selected == index) Color(0xFF7F57FF) else Color.Gray,
//                        modifier = Modifier.size(28.dp)
//                    )
//                }
//            }
//        }
//    }
//}