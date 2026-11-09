package com.example.model

import androidx.compose.ui.graphics.Color

enum class AreaId(
    val title: String,
    val shortTitle: String,
    val sectionNumber: String,
    val description: String
) {
    LECTURA(
        title = "Lectura Crítica",
        shortTitle = "Lectura",
        sectionNumber = "Sección 3",
        description = "Comprensión literal, inferencial y evaluación crítica de textos continuos y discontinuos."
    ),
    MATEMATICAS(
        title = "Matemáticas",
        shortTitle = "Matemáticas",
        sectionNumber = "Sección 4",
        description = "Interpretación y representación, formulación y ejecución, y argumentación cuantitativa."
    ),
    SOCIALES(
        title = "Sociales y Ciudadanas",
        shortTitle = "Sociales",
        sectionNumber = "Sección 5",
        description = "Pensamiento social, interpretación y análisis de perspectivas, y pensamiento reflexivo y sistémico."
    ),
    CIENCIAS(
        title = "Ciencias Naturales",
        shortTitle = "Ciencias",
        sectionNumber = "Sección 6",
        description = "Uso comprensivo del conocimiento científico, explicación de fenómenos e indagación (Vivos, Físicos, Químicos y CTS)."
    ),
    INGLES(
        title = "Inglés",
        shortTitle = "Inglés",
        sectionNumber = "Sección 7",
        description = "Competencia comunicativa en lengua inglesa alineada con el Marco Común Europeo (MCER: A-, A1, A2, B1, B+)."
    )
}

enum class ReportPeriodFilter(val label: String, val subtitle: String, val periods: List<String>) {
    ALL(
        label = "Histórico Completo (2018–2026)",
        subtitle = "9 aplicaciones en los 3 documentos anexos",
        periods = listOf("2018-2", "2019-4", "2020-4", "2021-4", "2022-4", "2023-4", "2024-3", "2025-3", "2026-3")
    ),
    DOC1(
        label = "Reporte 1 (2018–2020)",
        subtitle = "Aplicaciones 2018-2, 2019-4 y 2020-4",
        periods = listOf("2018-2", "2019-4", "2020-4")
    ),
    DOC2(
        label = "Reporte 2 (2021–2023)",
        subtitle = "Aplicaciones 2021-4, 2022-4 y 2023-4",
        periods = listOf("2021-4", "2022-4", "2023-4")
    ),
    DOC3(
        label = "Reporte 3 (2024–2026)",
        subtitle = "Aplicaciones 2024-3, 2025-3 y 2026-3",
        periods = listOf("2024-3", "2025-3", "2026-3")
    )
}

data class ParticipationRecord(
    val period: String,
    val examDate: String,
    val reportGroup: String,
    val matriculados: Int,
    val inscritos: Int,
    val presentes: Int,
    val publicados: Int
)

data class GlobalScoreRecord(
    val period: String,
    val eeScore: Int,
    val eeStdDev: Int,
    val colombiaScore: Int,
    val colombiaStdDev: Int,
    val etcBogotaScore: Int,
    val etcBogotaStdDev: Int,
    val oficialesUrbanosScore: Int,
    val oficialesUrbanosStdDev: Int,
    val privadosScore: Int,
    val privadosStdDev: Int
)

data class AreaScoreRecord(
    val period: String,
    val eeScore: Int,
    val eeStdDev: Int,
    val colombiaScore: Int,
    val colombiaStdDev: Int,
    val etcBogotaScore: Int,
    val oficialesUrbanosScore: Int,
    val privadosScore: Int
)

data class PerformanceLevelBreakdown(
    val period: String,
    // Para áreas regulares son Niveles 1, 2, 3 y 4. Para Inglés: A-, A1, A2, B1 y B+
    val levels: List<LevelPercentage>
)

data class LevelPercentage(
    val code: String,
    val label: String,
    val percentage: Int,
    val isCriticalLow: Boolean = false,
    val isAdvancedHigh: Boolean = false
)

data class LearningErrorRecord(
    val reportCut: String, // "2020-4", "2023-4", "2026-3"
    val statement: String,
    val categoryTag: String, // Ej. "Sentido Global", "Procesos Físicos", "Formulación", etc.
    val eeIncorrectPct: Int,
    val colombiaIncorrectPct: Int,
    val etcBogotaIncorrectPct: Int,
    val plainExplanation: String,
    val classroomAction: String
)

data class ImprovementRecommendation(
    val id: String,
    val areaId: AreaId,
    val priorityLevel: String, // "Prioridad Alta", "Prioridad Media", "Fortaleza a Mantener"
    val title: String,
    val evidenceFromPdf: String,
    val friendlyExplanation: String,
    val stepByStepActions: List<String>,
    val targetIndicator: String
)

data class AreaFullAnalytics(
    val areaId: AreaId,
    val color: Color,
    val scores: List<AreaScoreRecord>,
    val performanceLevels: List<PerformanceLevelBreakdown>,
    val learningErrorsByCut: Map<String, List<LearningErrorRecord>>,
    val executiveSummary: String,
    val keyStrength: String,
    val mainOpportunity: String,
    val recommendations: List<ImprovementRecommendation>
)
