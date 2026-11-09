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
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import com.example.model.ImprovementRecommendation
import com.example.ui.IcfesUiState
import com.example.ui.components.EasyExplanationBanner
import com.example.ui.theme.CgrGreenPrimary
import com.example.ui.theme.IcfesLevel1Red
import com.example.ui.theme.IcfesLevel2Orange
import com.example.ui.theme.IcfesLevel4Green
import kotlin.math.roundToInt

@Composable
fun ImprovementPlanScreen(
    uiState: IcfesUiState,
    onSelectArea: (AreaId) -> Unit,
    onToggleActionCheck: (String) -> Unit,
    onUpdateTeacherNote: (AreaId, String) -> Unit,
    onUpdateSimulatedGain: (AreaId, Int) -> Unit,
    onShareReport: () -> Unit,
    modifier: Modifier = Modifier
) {
    val areaAnalytics = IcfesRepository.getAreaAnalytics(uiState.selectedArea)
    val noteText = uiState.teacherAreaNotes[uiState.selectedArea].orEmpty()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("improvement_plan_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Selector de Área Docente
        item {
            Column {
                Text(
                    text = "Plan de Mejoramiento por Área:",
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
                                .clickable { onSelectArea(area) },
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

        if (uiState.isEasyExplanationEnabled) {
            item {
                EasyExplanationBanner(
                    title = "Plan de Acción Basado en Evidencia (Paso a Paso)",
                    explanation = "Aquí traducimos las tablas de desacierto de los 3 PDFs en acciones prácticas de aula para ${areaAnalytics.areaId.title}. Marca las casillas a medida que el departamento las implemente y escribe los acuerdos de reunión abajo."
                )
            }
        }

        // 2. Tarjetas de Recomendaciones Pedagógicas con Lista de Chequeo
        items(areaAnalytics.recommendations) { rec ->
            RecommendationActionCard(
                recommendation = rec,
                areaColor = areaAnalytics.color,
                checkedActionIds = uiState.checkedActionIds,
                onToggleActionCheck = onToggleActionCheck
            )
        }

        // 3. Bitácora de Acuerdos del Área Docente + Botón Compartir
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.EditNote,
                            contentDescription = "Bitácora del Área",
                            tint = areaAnalytics.color
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Acuerdos Pedagógicos de ${areaAnalytics.areaId.title}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Se guarda automáticamente en tu celular y se incluye al compartir el reporte:",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = noteText,
                        onValueChange = { onUpdateTeacherNote(uiState.selectedArea, it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("teacher_area_note_input"),
                        placeholder = {
                            Text("Escribe aquí las metas, talleres o compromisos acordados por los docentes de ${areaAnalytics.areaId.shortTitle}...")
                        },
                        minLines = 3,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onShareReport,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("share_plan_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = areaAnalytics.color)
                    ) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Compartir Diagnóstico y Acuerdos por WhatsApp / Correo")
                    }
                }
            }
        }

        // 4. Simulador Interactivo de Meta Saber 11° (Ponderación Oficial ICFES)
        item {
            GoalSimulatorCard(
                uiState = uiState,
                onUpdateSimulatedGain = onUpdateSimulatedGain
            )
        }
    }
}

@Composable
private fun RecommendationActionCard(
    recommendation: ImprovementRecommendation,
    areaColor: Color,
    checkedActionIds: Set<String>,
    onToggleActionCheck: (String) -> Unit
) {
    val badgeColor = if (recommendation.priorityLevel.contains("Alta")) IcfesLevel1Red else IcfesLevel2Orange

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = badgeColor.copy(alpha = 0.14f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = recommendation.priorityLevel,
                        style = MaterialTheme.typography.labelMedium,
                        color = badgeColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Default.Checklist,
                    contentDescription = null,
                    tint = areaColor
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = recommendation.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "📄 Evidencia en los PDFs anexos:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = areaColor
                    )
                    Text(
                        text = recommendation.evidenceFromPdf,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "💬 En palabras sencillas: ${recommendation.friendlyExplanation}",
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

            Text(
                text = "Paso a paso sugerido para el aula (toca para marcar avance):",
                style = MaterialTheme.typography.labelLarge,
                color = areaColor
            )

            Spacer(modifier = Modifier.height(6.dp))

            recommendation.stepByStepActions.forEachIndexed { idx, actionText ->
                val actionKey = "${recommendation.id}_$idx"
                val isChecked = checkedActionIds.contains(actionKey)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onToggleActionCheck(actionKey) }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { onToggleActionCheck(actionKey) },
                        colors = CheckboxDefaults.colors(checkedColor = areaColor)
                    )
                    Text(
                        text = actionText,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                color = IcfesLevel4Green.copy(alpha = 0.12f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "🎯 Meta verificable: ${recommendation.targetIndicator}",
                    style = MaterialTheme.typography.labelMedium,
                    color = IcfesLevel4Green,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
private fun GoalSimulatorCard(
    uiState: IcfesUiState,
    onUpdateSimulatedGain: (AreaId, Int) -> Unit
) {
    val latestGlobal = IcfesRepository.globalScoreHistory.last()
    val baseScores = AreaId.entries.associateWith { area ->
        IcfesRepository.getAreaAnalytics(area).scores.last().eeScore
    }
    val projectedScores = AreaId.entries.associateWith { area ->
        (baseScores[area] ?: 50) + (uiState.simulatedPointGainByArea[area] ?: 0)
    }

    // Fórmula oficial de ponderación ICFES Saber 11°:
    // Lectura (3), Matemáticas (3), Sociales (3), Ciencias (3), Inglés (1) -> suma de pesos = 13, multiplicado por 5
    val weightedSumBase = 3 * (baseScores[AreaId.LECTURA] ?: 54) +
        3 * (baseScores[AreaId.MATEMATICAS] ?: 49) +
        3 * (baseScores[AreaId.SOCIALES] ?: 52) +
        3 * (baseScores[AreaId.CIENCIAS] ?: 52) +
        1 * (baseScores[AreaId.INGLES] ?: 54)

    val weightedSumProjected = 3 * (projectedScores[AreaId.LECTURA] ?: 54) +
        3 * (projectedScores[AreaId.MATEMATICAS] ?: 49) +
        3 * (projectedScores[AreaId.SOCIALES] ?: 52) +
        3 * (projectedScores[AreaId.CIENCIAS] ?: 52) +
        1 * (projectedScores[AreaId.INGLES] ?: 54)

    val deltaGlobal = (((weightedSumProjected - weightedSumBase).toFloat() / 13f) * 5f).roundToInt()
    val projectedGlobal = latestGlobal.eeScore + deltaGlobal

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = "Simulador de Meta Saber 11",
                    tint = CgrGreenPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Simulador Institucional de Meta Saber 11°",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Mueve los controles para ver cómo el avance de cada área impulsa el Puntaje Global del Colegio:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Actual 2026-3: ${latestGlobal.eeScore} pts",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Meta Proyectada: $projectedGlobal / 500 pts",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = if (projectedGlobal >= latestGlobal.etcBogotaScore) {
                                "🏆 ¡Alcanza o supera el promedio total de Bogotá (${latestGlobal.etcBogotaScore} pts)!"
                            } else {
                                "A ${latestGlobal.etcBogotaScore - projectedGlobal} pts de alcanzar el promedio total de Bogotá (${latestGlobal.etcBogotaScore})"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Surface(
                        color = CgrGreenPrimary,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "+$deltaGlobal pts",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            AreaId.entries.forEach { area ->
                val areaObj = IcfesRepository.getAreaAnalytics(area)
                val currentPts = baseScores[area] ?: 50
                val gain = uiState.simulatedPointGainByArea[area] ?: 0
                val weightLabel = if (area == AreaId.INGLES) "Peso ICFES: 1x" else "Peso ICFES: 3x"

                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${area.shortTitle} ($weightLabel)",
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text = "$currentPts → ${currentPts + gain} pts (+$gain)",
                            style = MaterialTheme.typography.labelLarge,
                            color = areaObj.color,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Slider(
                        value = gain.toFloat(),
                        onValueChange = { onUpdateSimulatedGain(area, it.roundToInt()) },
                        valueRange = 0f..10f,
                        steps = 9,
                        colors = SliderDefaults.colors(
                            thumbColor = areaObj.color,
                            activeTrackColor = areaObj.color
                        )
                    )
                }
            }
        }
    }
}
