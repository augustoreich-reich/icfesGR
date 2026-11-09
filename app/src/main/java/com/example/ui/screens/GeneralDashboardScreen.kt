package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.IcfesRepository
import com.example.model.AreaId
import com.example.model.ReportPeriodFilter
import com.example.ui.ComparisonEntity
import com.example.ui.IcfesUiState
import com.example.ui.components.DualChartPoint
import com.example.ui.components.EasyExplanationBanner
import com.example.ui.components.HistoricalComparisonChart
import com.example.ui.theme.CgrGreenPrimary
import com.example.ui.theme.CgrRedSecondary
import com.example.ui.theme.IcfesLevel1Red
import com.example.ui.theme.IcfesLevel3Amber
import com.example.ui.theme.IcfesLevel4Green

@Composable
fun GeneralDashboardScreen(
    uiState: IcfesUiState,
    onSelectAreaAndNavigate: (AreaId) -> Unit,
    onSelectPeriodFilter: (ReportPeriodFilter) -> Unit,
    onSelectComparisonEntity: (ComparisonEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredGlobal = IcfesRepository.globalScoreHistory.filter {
        uiState.periodFilter.periods.contains(it.period)
    }
    val filteredParticipation = IcfesRepository.participationHistory.filter {
        uiState.periodFilter.periods.contains(it.period)
    }
    val latestGlobal = IcfesRepository.globalScoreHistory.last()
    val peakGlobal = IcfesRepository.globalScoreHistory.maxBy { it.eeScore }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("general_dashboard_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Selector Rápido para el Docente según su Área
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Acceso por Área Docente",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Acceso Directo por Área Docente",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Toca tu área para abrir el diagnóstico profundo y qué mejorar, o desliza hacia abajo para ver el histórico general de todo el colegio:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IcfesRepository.allAreasAnalytics.forEach { areaAnalytics ->
                            val isSelected = uiState.selectedArea == areaAnalytics.areaId
                            Surface(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { onSelectAreaAndNavigate(areaAnalytics.areaId) }
                                    .testTag("quick_area_${areaAnalytics.areaId.name.lowercase()}"),
                                color = if (isSelected) areaAnalytics.color else MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(12.dp),
                                tonalElevation = 2.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .clip(CircleShape)
                                            .background(if (isSelected) Color.White else areaAnalytics.color)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = areaAnalytics.areaId.title,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "2026-3: ${areaAnalytics.scores.last().eeScore} pts",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (isSelected) Color.White.copy(alpha = 0.9f) else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Ir a ${areaAnalytics.areaId.title}",
                                        tint = if (isSelected) Color.White else areaAnalytics.color,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 2. Explicación sencilla para quienes no conocen de estadística
        if (uiState.isEasyExplanationEnabled) {
            item {
                EasyExplanationBanner(
                    title = "¿Cómo leer este Panorama General en 30 segundos?",
                    explanation = "1) El Puntaje Global va de 0 a 500 puntos (la media nacional histórica se fijó en 250). " +
                        "2) Nuestro colegio (CGR) obtuvo 259 puntos en 2026-3, superando el promedio de Colombia (258). " +
                        "3) La 'Desviación Estándar' (36 pts) mide qué tan parejos están los estudiantes: como bajó de 44 a 36, significa que hoy tenemos menos brecha entre los estudiantes y cursos más homogéneos."
                )
            }
        }

        // 3. Tarjetas KPI de Resumen General (2018-2 a 2026-3)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GlobalKpiCard(
                    title = "Último Global (2026-3)",
                    value = "${latestGlobal.eeScore} / 500",
                    subtitle = "+1 pt sobre Colombia (${latestGlobal.colombiaScore})",
                    accentColor = CgrGreenPrimary,
                    modifier = Modifier.weight(1f)
                )
                GlobalKpiCard(
                    title = "Mejor Histórico",
                    value = "${peakGlobal.eeScore} pts",
                    subtitle = "Logrado en ${peakGlobal.period}",
                    accentColor = Color(0xFFB47B00),
                    modifier = Modifier.weight(1f)
                )
                GlobalKpiCard(
                    title = "Desviación 2026-3",
                    value = "±${latestGlobal.eeStdDev} pts",
                    subtitle = "Menor brecha (vs ±48 Col)",
                    accentColor = CgrRedSecondary,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // 4. Filtros de Documento PDF / Periodo y Entidad de Comparación
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "1. Filtra por Documento PDF Anexo (Trienio):",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ReportPeriodFilter.entries.forEach { filter ->
                            val selected = uiState.periodFilter == filter
                            FilterChip(
                                selected = selected,
                                onClick = { onSelectPeriodFilter(filter) },
                                label = { Text(filter.label) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "2. Comparar resultados del Colegio contra:",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ComparisonEntity.entries.forEach { entity ->
                            val selected = uiState.comparisonEntity == entity
                            FilterChip(
                                selected = selected,
                                onClick = { onSelectComparisonEntity(entity) },
                                label = { Text(entity.shortLabel) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.secondary,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 14.dp))

                    // Gráfica Interactiva del Puntaje Global (Tabla 2.1)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                            contentDescription = "Evolución Puntaje Global",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Evolución del Puntaje Global (Tabla 2.1)",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = uiState.periodFilter.subtitle,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    val chartPoints = filteredGlobal.map { rec ->
                        val refVal = when (uiState.comparisonEntity) {
                            ComparisonEntity.COLOMBIA -> rec.colombiaScore
                            ComparisonEntity.OFICIALES_URBANOS -> rec.oficialesUrbanosScore
                            ComparisonEntity.ETC_BOGOTA -> rec.etcBogotaScore
                            ComparisonEntity.PRIVADOS -> rec.privadosScore
                        }
                        DualChartPoint(
                            period = rec.period,
                            primaryValue = rec.eeScore,
                            secondaryValue = refVal,
                            primaryStdDev = rec.eeStdDev
                        )
                    }

                    HistoricalComparisonChart(
                        points = chartPoints,
                        primaryLabel = "Colegio Gustavo Restrepo (EE)",
                        secondaryLabel = uiState.comparisonEntity.shortLabel,
                        primaryColor = CgrGreenPrimary,
                        minScale = 235,
                        maxScale = 305
                    )
                }
            }
        }

        // 5. Comparativo Histórico de las 5 Áreas Evaluadas (Escala 0 a 100)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = "Comparativo de las 5 Áreas",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Radiografía General de las 5 Áreas (0–100 pts)",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Comparativa del Colegio en los 3 cortes (2020-4, 2023-4 y 2026-3) frente a Colombia",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (uiState.isEasyExplanationEnabled) {
                        Spacer(modifier = Modifier.height(10.dp))
                        EasyExplanationBanner(
                            title = "¿Qué áreas subieron y cuál necesita apoyo urgente?",
                            explanation = "• Inglés (+6 pts desde 2020) y Sociales (52 pts en 2026-3) lograron sus puntajes más altos de la historia.\n" +
                                "• Lectura Crítica (54 pts) y Ciencias Naturales (52 pts) superan el promedio nacional.\n" +
                                "• Matemáticas (49 pts en 2026-3) es la única área por debajo de Colombia (52 pts) y requiere plan de choque."
                        )
                    }
                }
            }
        }

        items(IcfesRepository.allAreasAnalytics) { areaAnalytics ->
            val score2018 = areaAnalytics.scores.first { it.period == "2018-2" }.eeScore
            val score2020 = areaAnalytics.scores.first { it.period == "2020-4" }.eeScore
            val score2023 = areaAnalytics.scores.first { it.period == "2023-4" }.eeScore
            val latestScoreObj = areaAnalytics.scores.last()
            val diffVsCol = latestScoreObj.eeScore - latestScoreObj.colombiaScore

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectAreaAndNavigate(areaAnalytics.areaId) }
                    .testTag("area_summary_card_${areaAnalytics.areaId.name.lowercase()}"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(14.dp)
                                    .clip(CircleShape)
                                    .background(areaAnalytics.color)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = areaAnalytics.areaId.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${areaAnalytics.areaId.sectionNumber} del Reporte ICFES",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        val statusColor = when {
                            diffVsCol > 0 -> IcfesLevel4Green
                            diffVsCol < 0 -> IcfesLevel1Red
                            else -> IcfesLevel3Amber
                        }
                        val statusText = when {
                            diffVsCol > 0 -> "+$diffVsCol vs Colombia"
                            diffVsCol < 0 -> "$diffVsCol vs Colombia"
                            else -> "Igual a Colombia"
                        }
                        Surface(
                            color = statusColor.copy(alpha = 0.14f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = statusText,
                                style = MaterialTheme.typography.labelMedium,
                                color = statusColor,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Evolución en 4 hitos clave
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        MilestoneColumn("2018-2", score2018, false)
                        MilestoneColumn("2020-4", score2020, false)
                        MilestoneColumn("2023-4", score2023, false)
                        MilestoneColumn("2026-3", latestScoreObj.eeScore, true, areaAnalytics.color)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "💡 Oportunidad clave: ${areaAnalytics.mainOpportunity}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ver análisis detallado de ${areaAnalytics.areaId.shortTitle}",
                            style = MaterialTheme.typography.labelLarge,
                            color = areaAnalytics.color
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = areaAnalytics.color,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        // 6. Tabla 1.1: Ficha Técnica de Participación Estudiantil
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Groups,
                            contentDescription = "Ficha Técnica de Estudiantes",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Ficha Técnica de Estudiantes (Tabla 1.1)",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Matriculados en SIMAT, Inscritos, Presentes y con Resultados Publicados",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(vertical = 8.dp, horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Periodo", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                        Text("Matric.", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Inscr.", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Pres.", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Text("Public.", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    }

                    filteredParticipation.forEach { row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1.2f)) {
                                Text(row.period, style = MaterialTheme.typography.labelLarge)
                                Text(row.examDate, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Text("${row.matriculados}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                            Text("${row.inscritos}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                            Text("${row.presentes}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${row.publicados}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                        }
                        HorizontalDivider(color = Color.Gray.copy(alpha = 0.15f))
                    }
                }
            }
        }
    }
}

@Composable
private fun GlobalKpiCard(
    title: String,
    value: String,
    subtitle: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun MilestoneColumn(
    period: String,
    score: Int,
    isHighlighted: Boolean,
    highlightColor: Color = CgrGreenPrimary
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = period,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "$score pts",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = if (isHighlighted) highlightColor else MaterialTheme.colorScheme.onSurface
        )
    }
}
