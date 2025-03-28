package org.example.project.widgetsScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import io.github.koalaplot.core.Symbol
import io.github.koalaplot.core.bar.GroupedVerticalBarPlot
import io.github.koalaplot.core.bar.solidBar
import io.github.koalaplot.core.legend.FlowLegend
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.util.generateHueColorPalette
import io.github.koalaplot.core.xygraph.CategoryAxisModel
import io.github.koalaplot.core.xygraph.XYGraph
import io.github.koalaplot.core.xygraph.rememberFloatLinearAxisModel

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun XYSamplePlot() {
    val boroughs = listOf("Bronx", "Brooklyn", "Manhattan", "Queens", "Island")
    val years = listOf(1980, 1990, 2000, 2010, 2020)
    val colors =
        listOf(
            Color.hsv(0f, 1f, 0.7f),
            Color.hsv(72f, 1f, 0.7f),
            Color.hsv(144f, 1f, 0.7f),
            Color.hsv(216f, 1f, 0.7f),
            Color.hsv(288f, 1f, 0.7f)
        ) // 1 color per year

    // Population data is in order of borough, then year
    val population = listOf(
        listOf(200, 80, 4, 54, 167), // Bronx
        listOf(165, 76, 36, 6, 36), // Brooklyn
        listOf(23, 59, 12, 78, 190), // Manhattan
        listOf(67, 98, 120, 10, 17), // Queens
        listOf(175, 120, 15, 6, 185), // Staten Island
    )

    val itemCount = 5
    val palette = generateHueColorPalette(itemCount)

    XYGraph(
        xAxisModel = remember { CategoryAxisModel(boroughs) },
        yAxisModel = rememberFloatLinearAxisModel(0f..200f, minorTickCount = 0),
        yAxisTitle = "Population (Millions)"
    ) {
        GroupedVerticalBarPlot {
            years.indices.forEach {
                series(solidBar(colors[it])) {
                    boroughs.forEachIndexed { index, borough ->
                        item(borough, 0f, population[index][it].toFloat())
                    }
                }
            }
        }
    }
    FlowLegend(
        modifier = Modifier.padding(16.dp).border(1.dp, Color.Black).padding(16.dp),
        itemCount = itemCount,
        symbol = { Symbol(shape = RectangleShape, fillBrush = SolidColor(palette[it])) },
        label = { Text("Item $it") },
    )
}