package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PerformanceLevelBreakdown
import com.example.ui.theme.CgrYellowContainer
import com.example.ui.theme.IcfesLevel1Red
import com.example.ui.theme.IcfesLevel2Orange
import com.example.ui.theme.IcfesLevel3Amber
import com.example.ui.theme.IcfesLevel4Green

data class DualChartPoint(
    val period: String,
    val primaryValue: Int,
    val secondaryValue: Int,
    val primaryStdDev: Int
)

@Composable
fun EasyExplanationBanner(
    title: String,
    explanation: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = CgrYellowContainer.copy(alpha = 0.75f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background( Color(0xFFFACC15) ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = "Explicación sencilla",
                    tint = Color(0xFF422006),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "💡 $title",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFF422006),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = explanation,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF523008)
                )
            }
        }
    }
}

@Composable
fun HistoricalComparisonChart(
    points: List<DualChartPoint>,
    primaryLabel: String,
    secondaryLabel: String,
    primaryColor: Color,
    secondaryColor: Color = Color(0xFF64748B),
    minScale: Int,
    maxScale: Int,
    modifier: Modifier = Modifier
) {
    if (points.isEmpty()) return

    var selectedIndex by remember(points) { mutableIntStateOf(points.lastIndex) }
    val safeIndex = selectedIndex.coerceIn(0, points.lastIndex)
    val selectedPoint = points[safeIndex]
    val diff = selectedPoint.primaryValue - selectedPoint.secondaryValue

    Column(modifier = modifier.fillMaxWidth()) {
        // Leyenda superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(primaryColor)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = primaryLabel,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(14.dp))
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(secondaryColor)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = secondaryLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Tarjeta interactiva del periodo seleccionado
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Aplicación ${selectedPoint.period}",
                        style = MaterialTheme.typography.labelLarge,
                        color = primaryColor
                    )
                    Text(
                        text = "Desviación (brecha): ±${selectedPoint.primaryStdDev} pts",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Colegio: ${selectedPoint.primaryValue} pts",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$secondaryLabel: ${selectedPoint.secondaryValue} pts",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    val badgeColor = when {
                        diff > 0 -> IcfesLevel4Green
                        diff < 0 -> IcfesLevel1Red
                        else -> IcfesLevel3Amber
                    }
                    val diffText = when {
                        diff > 0 -> "+$diff"
                        diff < 0 -> "$diff"
                        else -> "Igual"
                    }
                    Surface(
                        color = badgeColor.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = diffText,
                            style = MaterialTheme.typography.labelLarge,
                            color = badgeColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Lienzo de la gráfica de líneas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .padding(horizontal = 8.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                val width = size.width
                val height = size.height
                val topPad = 18.dp.toPx()
                val bottomPad = 24.dp.toPx()
                val usableHeight = (height - topPad - bottomPad).coerceAtLeast(1f)
                val range = (maxScale - minScale).coerceAtLeast(1).toFloat()

                // Líneas guía horizontales
                val steps = 4
                for (i in 0..steps) {
                    val y = topPad + (usableHeight * i / steps)
                    drawLine(
                        color = Color.Gray.copy(alpha = 0.2f),
                        start = Offset(0f, y),
                        end = Offset(width, y),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                val stepX = if (points.size > 1) width / (points.size - 1) else width / 2f

                fun valueToY(v: Int): Float {
                    val norm = ((v - minScale).toFloat() / range).coerceIn(0f, 1f)
                    return topPad + usableHeight * (1f - norm)
                }

                // Línea de referencia secundaria (punteada)
                if (points.size > 1) {
                    val secPath = Path()
                    points.forEachIndexed { idx, pt ->
                        val x = idx * stepX
                        val y = valueToY(pt.secondaryValue)
                        if (idx == 0) secPath.moveTo(x, y) else secPath.lineTo(x, y)
                    }
                    drawPath(
                        path = secPath,
                        color = secondaryColor.copy(alpha = 0.75f),
                        style = Stroke(
                            width = 2.5.dp.toPx(),
                            cap = StrokeCap.Round,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
                        )
                    )
                }

                // Línea principal del Colegio (sólida y destacada)
                if (points.size > 1) {
                    val priPath = Path()
                    points.forEachIndexed { idx, pt ->
                        val x = idx * stepX
                        val y = valueToY(pt.primaryValue)
                        if (idx == 0) priPath.moveTo(x, y) else priPath.lineTo(x, y)
                    }
                    drawPath(
                        path = priPath,
                        color = primaryColor,
                        style = Stroke(width = 3.5.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                // Puntos e indicador vertical seleccionado
                points.forEachIndexed { idx, pt ->
                    val x = if (points.size > 1) idx * stepX else width / 2f
                    val yPri = valueToY(pt.primaryValue)
                    val ySec = valueToY(pt.secondaryValue)

                    if (idx == safeIndex) {
                        drawLine(
                            color = primaryColor.copy(alpha = 0.35f),
                            start = Offset(x, topPad),
                            end = Offset(x, height - bottomPad),
                            strokeWidth = 2.dp.toPx()
                        )
                    }

                    drawCircle(
                        color = secondaryColor,
                        radius = 4.dp.toPx(),
                        center = Offset(x, ySec)
                    )

                    drawCircle(
                        color = Color.White,
                        radius = if (idx == safeIndex) 7.dp.toPx() else 5.dp.toPx(),
                        center = Offset(x, yPri)
                    )
                    drawCircle(
                        color = primaryColor,
                        radius = if (idx == safeIndex) 5.5.dp.toPx() else 3.8.dp.toPx(),
                        center = Offset(x, yPri)
                    )
                }
            }
        }

        // Botones de selección de año/aplicación debajo de la gráfica
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            points.forEachIndexed { idx, pt ->
                val isSelected = idx == safeIndex
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { selectedIndex = idx },
                    color = if (isSelected) primaryColor else MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = pt.period,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                        Text(
                            text = "${pt.primaryValue}",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PerformanceLevelsStackedBars(
    breakdowns: List<PerformanceLevelBreakdown>,
    isEnglish: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Leyenda de colores semánticos ICFES
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isEnglish) {
                LegendDot("A- (Alerta)", IcfesLevel1Red)
                LegendDot("A1", IcfesLevel2Orange)
                LegendDot("A2", IcfesLevel3Amber)
                LegendDot("B1", Color(0xFF22C55E))
                LegendDot("B+", IcfesLevel4Green)
            } else {
                LegendDot("Nivel 1 (Bajo)", IcfesLevel1Red)
                LegendDot("Nivel 2 (Básico)", IcfesLevel2Orange)
                LegendDot("Nivel 3 (Medio-Alto)", IcfesLevel3Amber)
                LegendDot("Nivel 4 (Avanzado)", IcfesLevel4Green)
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            breakdowns.forEach { item ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.period,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(58.dp)
                        )
                        // Barra apilada al 100%
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .height(24.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .border(0.5.dp, Color.Gray.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                        ) {
                            item.levels.forEachIndexed { idx, lvl ->
                                if (lvl.percentage > 0) {
                                    val barColor = levelColorForIndex(idx, isEnglish)
                                    Box(
                                        modifier = Modifier
                                            .weight(lvl.percentage.toFloat().coerceAtLeast(1f))
                                            .fillMaxHeight()
                                            .background(barColor),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (lvl.percentage >= 8) {
                                            Text(
                                                text = "${lvl.percentage}%",
                                                color = Color.White,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LegendDot(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 10.sp
        )
    }
}

fun levelColorForIndex(index: Int, isEnglish: Boolean): Color {
    return if (isEnglish) {
        when (index) {
            0 -> IcfesLevel1Red        // A-
            1 -> IcfesLevel2Orange     // A1
            2 -> IcfesLevel3Amber      // A2
            3 -> Color(0xFF22C55E)     // B1
            else -> IcfesLevel4Green   // B+
        }
    } else {
        when (index) {
            0 -> IcfesLevel1Red        // Nivel 1
            1 -> IcfesLevel2Orange     // Nivel 2
            2 -> IcfesLevel3Amber      // Nivel 3
            else -> IcfesLevel4Green   // Nivel 4
        }
    }
}
