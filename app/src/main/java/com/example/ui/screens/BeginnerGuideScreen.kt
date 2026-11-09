package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.IcfesUiState
import com.example.ui.theme.CgrGreenPrimary
import com.example.ui.theme.CgrRedSecondary
import com.example.ui.theme.IcfesLevel1Red
import com.example.ui.theme.IcfesLevel2Orange
import com.example.ui.theme.IcfesLevel3Amber
import com.example.ui.theme.IcfesLevel4Green

@Composable
fun BeginnerGuideScreen(
    uiState: IcfesUiState,
    onToggleEasyMode: () -> Unit,
    onShareReport: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("beginner_guide_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Interruptor de Modo Explicación Sencilla
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Modo Explicación Sencilla (Activo)",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Muestra recuadros amarillos con consejos claros debajo de cada gráfica para docentes que no son expertos en estadística.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Switch(
                        checked = uiState.isEasyExplanationEnabled,
                        onCheckedChange = { onToggleEasyMode() },
                        modifier = Modifier.testTag("easy_mode_switch")
                    )
                }
            }
        }

        // 2. Paso a Paso: Cómo Publicar y Compartir esta App con los Docentes
        item {
            GuideSectionCard(
                icon = Icons.Default.IosShare,
                badgeText = "PASO 1 • COMPARTIR CON DOCENTES",
                title = "¿Cómo publicar y compartir esta App con tus compañeros?",
                accentColor = CgrGreenPrimary
            ) {
                StepNumberRow(
                    number = "1",
                    title = "Desde dentro de esta misma App (Botón Compartir)",
                    body = "Toca el botón «Compartir» arriba a la derecha o el botón verde de abajo. La app genera automáticamente un resumen ejecutivo listo para enviar por WhatsApp, Correo Institucional o grupos de área."
                )
                Spacer(modifier = Modifier.height(10.dp))
                StepNumberRow(
                    number = "2",
                    title = "Compartir el Enlace en Google AI Studio",
                    body = "En la barra superior derecha de tu pantalla de Google AI Studio verás el icono de Compartir (Share) o Publicar. Al activarlo, obtienes un enlace público para que cualquier docente del Colegio Gustavo Restrepo abra la app desde su navegador o celular."
                )
                Spacer(modifier = Modifier.height(10.dp))
                StepNumberRow(
                    number = "3",
                    title = "Descargar como App Instalable de Celular (APK)",
                    body = "En el menú de configuración (icono de engranaje arriba a la derecha en AI Studio), selecciona la opción «Export / Generate APK». Así podrás enviar el archivo instalable (.apk) por WhatsApp o Google Drive a todos los profesores con celular Android."
                )
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onShareReport,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = CgrGreenPrimary)
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Probar ahora: Enviar Resumen por WhatsApp / Correo")
                }
            }
        }

        // 3. Diccionario Fácil para entender el Reporte ICFES sin saber de estadística
        item {
            GuideSectionCard(
                icon = Icons.Default.HelpOutline,
                badgeText = "PASO 2 • DICCIONARIO SIN TECNICISMOS",
                title = "¿Qué significan los números y símbolos del ICFES?",
                accentColor = CgrRedSecondary
            ) {
                GlossaryItem(
                    term = "1. Puntaje Global (0 a 500 puntos)",
                    meaning = "Es la nota general del colegio sumando todas las pruebas. La media nacional cuando se creó la escala fue 250 puntos. Nuestro colegio obtuvo 259 en 2026-3 (su récord fue 265 en 2022-4)."
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                GlossaryItem(
                    term = "2. Promedio por Área (0 a 100 puntos)",
                    meaning = "Cada una de las 5 áreas (Lectura, Matemáticas, Sociales, Ciencias e Inglés) se califica de 0 a 100. Superar los 52–54 puntos ubica al colegio por encima del promedio nacional."
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                GlossaryItem(
                    term = "3. Desviación Estándar («La brecha del salón»)",
                    meaning = "Imagina dos cursos con promedio de 50: en el Curso A todos sacaron entre 48 y 52 (desviación baja = grupo parejo, fácil de enseñar). En el Curso B unos sacaron 80 y otros 20 (desviación alta = brecha enorme). ¡Entre más BAJA sea la desviación, más parejo y equitativo es el aprendizaje en el colegio!"
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                GlossaryItem(
                    term = "4. Símbolos de los PDFs: ● (Igual), ▲ (Subió), ▼ (Bajó)",
                    meaning = "El ICFES compara el último año con los anteriores: ● significa que el resultado es estadísticamente similar; ▲ que el último año fue mayor; ▼ que el último año fue menor. Si el símbolo es verde favorece al colegio; si es rojo es una alerta."
                )
            }
        }

        // 4. Guía visual de los 4 Niveles de Desempeño (Semáforo ICFES)
        item {
            GuideSectionCard(
                icon = Icons.Default.MenuBook,
                badgeText = "PASO 3 • EL SEMÁFORO DE NIVELES",
                title = "¿Qué significan los 4 colores de Nivel de Desempeño?",
                accentColor = Color(0xFFB47B00)
            ) {
                LevelExplainRow(
                    badge = "Nivel 1 / A-",
                    color = IcfesLevel1Red,
                    description = "Rojo (Alerta): El estudiante aún no alcanza las competencias mínimas. La meta del colegio es llevar este porcentaje lo más cerca posible a 0%."
                )
                Spacer(modifier = Modifier.height(8.dp))
                LevelExplainRow(
                    badge = "Nivel 2 / A1",
                    color = IcfesLevel2Orange,
                    description = "Naranja (Básico): Supera preguntas sencillas y literales, pero falla cuando debe relacionar variables o inferir. Aquí suele estar el grupo que podemos impulsar rápidamente."
                )
                Spacer(modifier = Modifier.height(8.dp))
                LevelExplainRow(
                    badge = "Nivel 3 / A2-B1",
                    color = IcfesLevel3Amber,
                    description = "Ámbar (Satisfactorio): ¡Buen desempeño! Comprende textos completos, resuelve problemas e interpreta gráficas. En Lectura Crítica 2026-3 logramos tener aquí al 72%."
                )
                Spacer(modifier = Modifier.height(8.dp))
                LevelExplainRow(
                    badge = "Nivel 4 / B+",
                    color = IcfesLevel4Green,
                    description = "Verde (Avanzado): Excelencia académica. Evalúa críticamente, valida procedimientos complejos y propone soluciones."
                )
            }
        }

        // 5. Resumen de los 3 Documentos PDF Anexos
        item {
            GuideSectionCard(
                icon = Icons.Default.Description,
                badgeText = "PASO 4 • DOCUMENTOS INTEGRADOS",
                title = "Resumen de los 3 Reportes Oficiales Anexos",
                accentColor = CgrGreenPrimary
            ) {
                GlossaryItem(
                    term = "📄 Documento 1 (icfes1): Trienio 2018-2, 2019-4 y 2020-4",
                    meaning = "Muestra una etapa de alta estabilidad con Puntaje Global de 264 pts (2018 y 2020) y un pico sobresaliente en Matemáticas en 2020-4 (55 pts y 73% en Nivel 3)."
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                GlossaryItem(
                    term = "📄 Documento 2 (icfes2): Trienio 2021-4, 2022-4 y 2023-4",
                    meaning = "Registra el récord histórico del Colegio Gustavo Restrepo en 2022-4 con 265 puntos globales y Lectura Crítica en 56 puntos, manteniendo ventaja sobre Colombia."
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                GlossaryItem(
                    term = "📄 Documento 3 (icfes3): Trienio 2024-3, 2025-3 y 2026-3",
                    meaning = "Evidencia una recuperación en 2026-3 (subiendo de 255 a 259 puntos globales y reduciendo la desviación de 43 a 36). Destacan los récords históricos de Sociales (52 pts) e Inglés (54 pts), y señala la urgencia de apoyar a Matemáticas (49 pts)."
                )
            }
        }
    }
}

@Composable
private fun GuideSectionCard(
    icon: ImageVector,
    badgeText: String,
    title: String,
    accentColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = badgeText,
                    style = MaterialTheme.typography.labelLarge,
                    color = accentColor,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun StepNumberRow(
    number: String,
    title: String,
    body: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(CgrGreenPrimary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun GlossaryItem(
    term: String,
    meaning: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = term,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = meaning,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LevelExplainRow(
    badge: String,
    color: Color,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            color = color,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(96.dp)
        ) {
            Text(
                text = badge,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}
