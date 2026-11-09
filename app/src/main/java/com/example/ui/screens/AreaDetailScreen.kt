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
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.model.LearningErrorRecord
import com.example.model.ReportPeriodFilter
import com.example.ui.ComparisonEntity
import com.example.ui.IcfesUiState
import com.example.ui.components.DualChartPoint
import com.example.ui.components.EasyExplanationBanner
import com.example.ui.components.HistoricalComparisonChart
import com.example.ui.components.PerformanceLevelsStackedBars
import com.example.ui.theme.IcfesLevel1Red
import com.example.ui.theme.IcfesLevel2Orange
import com.example.ui.theme.IcfesLevel3Amber
import com.example.ui.theme.IcfesLevel4Green

@Composable
fun AreaDetailScreen(
    uiState: IcfesUiState,
    onSelectArea: (AreaId) -> Unit,
    onSelectPeriodFilter: (ReportPeriodFilter) -> Unit,
    onSelectLearningCut: (String) -> Unit,
    onSelectComparisonEntity: (ComparisonEntity) -> Unit,
    onGoToImprovementPlan: () -> Unit,
    onShareReport: () -> Unit,
    modifier: Modifier = Modifier
) {
    val areaAnalytics = IcfesRepository.getAreaAnalytics(uiState.selectedArea)
    val filteredScores = areaAnalytics.scores.filter {
        uiState.periodFilter.periods.contains(it.period)
    }
    val filteredLevels = areaAnalytics.performanceLevels.filter {
        uiState.periodFilter.periods.contains(it.period)
    }
    val currentLearningErrors = areaAnalytics.learningErrorsByCut[uiState.selectedLearningCut]
        ?: areaAnalytics.learningErrorsByCut["2026-3"].orEmpty()

    val latestScore = areaAnalytics.scores.last()
    val diffCol = latestScore.eeScore - latestScore.colombiaScore

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("area_detail_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Selector Horizontal de Área Docente
        item {
            Column {
                Text(
                    text = "Selecciona tu Área Docente:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AreaId.entries.forEach { area ->
                        val isSelected = uiState.selectedArea == area
                        val areaColor = IcfesRepository.getAreaAnalytics(area).color
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onSelectArea(area) }
                                .testTag("area_selector_chip_${area.name.lowercase()}"),
                            color = if (isSelected) areaColor else MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(12.dp),
                            tonalElevation = 2.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(if (isSelected) Color.White else areaColor)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = area.title,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }

        // 2. Cabecera Ejecutiva del Área Seleccionada
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${areaAnalytics.areaId.sectionNumber} • Histórico del Área",
                                style = MaterialTheme.typography.labelMedium,
                                color = areaAnalytics.color
                            )
                            Text(
                                text = areaAnalytics.areaId.title,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = areaAnalytics.areaId.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Surface(
                            color = areaAnalytics.color.copy(alpha = 0.14f),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "2026-3",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = areaAnalytics.color
                                )
                                Text(
                                    text = "${latestScore.eeScore}",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = areaAnalytics.color,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "de 100 pts",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = areaAnalytics.color
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Diagnóstico Ejecutivo amigable
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "📌 Diagnóstico Ejecutivo para el Docente:",
                                style = MaterialTheme.typography.titleSmall,
                                color = areaAnalytics.color
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = areaAnalytics.executiveSummary,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Fortaleza y Oportunidad lado a lado
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            modifier = Modifier.weight(1f),
                            color = IcfesLevel4Green.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = IcfesLevel4Green,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Mayor Fortaleza",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = IcfesLevel4Green
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = areaAnalytics.keyStrength,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier.weight(1f),
                            color = IcfesLevel2Orange.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Warning,
                                        contentDescription = null,
                                        tint = IcfesLevel2Orange,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Principal Reto",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = IcfesLevel2Orange
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = areaAnalytics.mainOpportunity,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onGoToImprovementPlan,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("go_to_improvement_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = areaAnalytics.color)
                        ) {
                            Text("Ver Plan de Mejora")
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        OutlinedButton(
                            onClick = onShareReport,
                            modifier = Modifier.testTag("share_area_report_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Compartir",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Compartir")
                        }
                    }
                }
            }
        }

        // 3. Serie Histórica de Promedio y Desviación del Área (Tabla .1)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Evolución del Promedio en ${areaAnalytics.areaId.title} (Escala 0–100)",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    // Selector rápido de trienio y entidad de referencia
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ReportPeriodFilter.entries.forEach { filter ->
                            FilterChip(
                                selected = uiState.periodFilter == filter,
                                onClick = { onSelectPeriodFilter(filter) },
                                label = { Text(filter.label) }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ComparisonEntity.entries.forEach { entity ->
                            FilterChip(
                                selected = uiState.comparisonEntity == entity,
                                onClick = { onSelectComparisonEntity(entity) },
                                label = { Text("vs. ${entity.shortLabel}") }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val areaPoints = filteredScores.map { rec ->
                        val refScore = when (uiState.comparisonEntity) {
                            ComparisonEntity.COLOMBIA -> rec.colombiaScore
                            ComparisonEntity.OFICIALES_URBANOS -> rec.oficialesUrbanosScore
                            ComparisonEntity.ETC_BOGOTA -> rec.etcBogotaScore
                            ComparisonEntity.PRIVADOS -> rec.privadosScore
                        }
                        DualChartPoint(
                            period = rec.period,
                            primaryValue = rec.eeScore,
                            secondaryValue = refScore,
                            primaryStdDev = rec.eeStdDev
                        )
                    }

                    HistoricalComparisonChart(
                        points = areaPoints,
                        primaryLabel = "Colegio (EE)",
                        secondaryLabel = uiState.comparisonEntity.shortLabel,
                        primaryColor = areaAnalytics.color,
                        minScale = 44,
                        maxScale = 66
                    )

                    if (uiState.isEasyExplanationEnabled) {
                        Spacer(modifier = Modifier.height(12.dp))
                        val compExplain = if (diffCol >= 0) {
                            "En el último corte (2026-3), ${areaAnalytics.areaId.title} obtuvo ${latestScore.eeScore} puntos (+$diffCol frente al promedio de Colombia de ${latestScore.colombiaScore}). La desviación estándar es ±${latestScore.eeStdDev} puntos."
                        } else {
                            "En el último corte (2026-3), ${areaAnalytics.areaId.title} obtuvo ${latestScore.eeScore} puntos ($diffCol frente al promedio de Colombia de ${latestScore.colombiaScore}). Es prioritario trabajar en los aprendizajes con mayor porcentaje de error abajo."
                        }
                        EasyExplanationBanner(
                            title = "¿Qué significa esta curva para nuestra área?",
                            explanation = compExplain
                        )
                    }
                }
            }
        }

        // 4. Distribución por Niveles de Desempeño (Tabla .2 y Gráfica .3)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.BarChart,
                            contentDescription = "Niveles de Desempeño",
                            tint = areaAnalytics.color
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Porcentaje de Estudiantes por Nivel de Desempeño",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = if (areaAnalytics.areaId == AreaId.INGLES) {
                                    "Niveles Marco Común Europeo (A-, A1, A2, B1 y B+) - Tabla 7.2 y Gráfica 7.3"
                                } else {
                                    "Niveles 1 (Bajo), 2 (Básico), 3 (Satisfactorio) y 4 (Avanzado) - Tabla y Gráfica .3"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (uiState.isEasyExplanationEnabled) {
                        Spacer(modifier = Modifier.height(10.dp))
                        EasyExplanationBanner(
                            title = "¿Cuál es el escenario ideal en esta gráfica?",
                            explanation = "Como explica el ICFES en los reportes anexos: el escenario ideal es aquel donde el segmento Rojo (Nivel 1 o A-) disminuye al mínimo, y los segmentos Ámbar y Verde (Niveles 3 y 4, o A2/B1/B+) crecen a través del tiempo."
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    PerformanceLevelsStackedBars(
                        breakdowns = filteredLevels,
                        isEnglish = areaAnalytics.areaId == AreaId.INGLES
                    )
                }
            }
        }

        // 5. Semáforo de Aprendizajes Evaluados (Figura .4 - % de Respuestas Incorrectas)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Semáforo de Aprendizajes: ¿Qué debemos mejorar?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Porcentaje promedio de respuestas INCORRECTAS (Figura .4 de cada documento PDF). Entre más alto el porcentaje, mayor es la urgencia de refuerzo en clase:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Selector entre los 3 PDFs (2026-3, 2023-4, 2020-4)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "2026-3" to "Último PDF (2026-3)",
                            "2023-4" to "PDF 2 (2023-4)",
                            "2020-4" to "PDF 1 (2020-4)"
                        ).forEach { (cutKey, cutLabel) ->
                            val selected = uiState.selectedLearningCut == cutKey
                            FilterChip(
                                selected = selected,
                                onClick = { onSelectLearningCut(cutKey) },
                                label = { Text(cutLabel) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = areaAnalytics.color,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }

        items(currentLearningErrors) { err ->
            LearningErrorCard(
                errorRecord = err,
                showFriendlyExplanation = uiState.isEasyExplanationEnabled
            )
        }
    }
}

@Composable
private fun LearningErrorCard(
    errorRecord: LearningErrorRecord,
    showFriendlyExplanation: Boolean
) {
    val semaphoreColor = when {
        errorRecord.eeIncorrectPct >= 55 -> IcfesLevel1Red
        errorRecord.eeIncorrectPct >= 40 -> IcfesLevel2Orange
        else -> IcfesLevel4Green
    }
    val semaphoreLabel = when {
        errorRecord.eeIncorrectPct >= 55 -> "Alerta Crítica (Prioridad #1)"
        errorRecord.eeIncorrectPct >= 40 -> "Atención Media (Reforzar)"
        else -> "Fortaleza del Colegio"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
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
                Surface(
                    color = semaphoreColor.copy(alpha = 0.14f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "${errorRecord.categoryTag} • $semaphoreLabel",
                        style = MaterialTheme.typography.labelMedium,
                        color = semaphoreColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = "${errorRecord.eeIncorrectPct}% error",
                    style = MaterialTheme.typography.titleMedium,
                    color = semaphoreColor,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = errorRecord.statement,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Barra de progreso del porcentaje de error EE vs Colombia vs Bogotá ETC
            LinearProgressIndicator(
                progress = { (errorRecord.eeIncorrectPct / 100f).coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                color = semaphoreColor,
                trackColor = semaphoreColor.copy(alpha = 0.15f)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Colegio (EE): ${errorRecord.eeIncorrectPct}%",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = semaphoreColor
                )
                Text(
                    text = "Colombia: ${errorRecord.colombiaIncorrectPct}%",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Bogotá (ETC): ${errorRecord.etcBogotaIncorrectPct}%",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (showFriendlyExplanation) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    text = "🧐 ¿Qué nos dice este dato?: ${errorRecord.plainExplanation}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "✅ Qué hacer en el aula: ${errorRecord.classroomAction}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
