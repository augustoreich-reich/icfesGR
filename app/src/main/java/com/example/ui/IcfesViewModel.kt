package com.example.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.data.IcfesRepository
import com.example.model.AreaFullAnalytics
import com.example.model.AreaId
import com.example.model.ReportPeriodFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppTab(val title: String) {
    GENERAL("General"),
    MI_AREA("Mi Área"),
    PLAN_MEJORA("Mejora"),
    GUIA_FACIL("Guía Fácil")
}

enum class ComparisonEntity(val label: String, val shortLabel: String) {
    COLOMBIA("Promedio Nacional (Colombia)", "Colombia"),
    OFICIALES_URBANOS("Colegios Oficiales Urbanos Bogotá", "Ofic. Urbanos"),
    ETC_BOGOTA("Total Bogotá D.C. (ETC)", "Bogotá ETC"),
    PRIVADOS("Colegios Privados Bogotá", "Privados ETC")
}

data class IcfesUiState(
    val currentTab: AppTab = AppTab.GENERAL,
    val selectedArea: AreaId = AreaId.LECTURA,
    val periodFilter: ReportPeriodFilter = ReportPeriodFilter.ALL,
    val selectedLearningCut: String = "2026-3",
    val comparisonEntity: ComparisonEntity = ComparisonEntity.COLOMBIA,
    val isEasyExplanationEnabled: Boolean = true,
    val checkedActionIds: Set<String> = emptySet(),
    val teacherAreaNotes: Map<AreaId, String> = emptyMap(),
    val simulatedPointGainByArea: Map<AreaId, Int> = mapOf(
        AreaId.LECTURA to 2,
        AreaId.MATEMATICAS to 4,
        AreaId.SOCIALES to 2,
        AreaId.CIENCIAS to 3,
        AreaId.INGLES to 2
    ),
    val showWelcomeTourSheet: Boolean = false
)

class IcfesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(IcfesUiState())
    val uiState: StateFlow<IcfesUiState> = _uiState.asStateFlow()

    private var prefsLoaded = false

    fun loadPreferencesIfNeeded(context: Context) {
        if (prefsLoaded) return
        prefsLoaded = true
        val prefs = context.getSharedPreferences("saber11_cgr_prefs", Context.MODE_PRIVATE)
        val savedChecked = prefs.getStringSet("checked_actions", emptySet()) ?: emptySet()
        val easyMode = prefs.getBoolean("easy_mode", true)
        val notesMap = mutableMapOf<AreaId, String>()
        AreaId.entries.forEach { area ->
            val note = prefs.getString("note_${area.name}", "") ?: ""
            if (note.isNotBlank()) {
                notesMap[area] = note
            }
        }
        _uiState.update {
            it.copy(
                checkedActionIds = savedChecked,
                isEasyExplanationEnabled = easyMode,
                teacherAreaNotes = notesMap
            )
        }
    }

    fun selectTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun selectArea(areaId: AreaId, navigateToAreaTab: Boolean = false) {
        _uiState.update {
            it.copy(
                selectedArea = areaId,
                currentTab = if (navigateToAreaTab) AppTab.MI_AREA else it.currentTab
            )
        }
    }

    fun selectPeriodFilter(filter: ReportPeriodFilter) {
        _uiState.update { it.copy(periodFilter = filter) }
    }

    fun selectLearningCut(cut: String) {
        _uiState.update { it.copy(selectedLearningCut = cut) }
    }

    fun selectComparisonEntity(entity: ComparisonEntity) {
        _uiState.update { it.copy(comparisonEntity = entity) }
    }

    fun toggleEasyExplanation(context: Context) {
        val nextValue = !_uiState.value.isEasyExplanationEnabled
        _uiState.update { it.copy(isEasyExplanationEnabled = nextValue) }
        context.getSharedPreferences("saber11_cgr_prefs", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("easy_mode", nextValue)
            .apply()
    }

    fun toggleActionCheck(context: Context, actionKey: String) {
        val current = _uiState.value.checkedActionIds.toMutableSet()
        if (current.contains(actionKey)) {
            current.remove(actionKey)
        } else {
            current.add(actionKey)
        }
        _uiState.update { it.copy(checkedActionIds = current) }
        context.getSharedPreferences("saber11_cgr_prefs", Context.MODE_PRIVATE)
            .edit()
            .putStringSet("checked_actions", current)
            .apply()
    }

    fun updateTeacherNote(context: Context, areaId: AreaId, note: String) {
        val updated = _uiState.value.teacherAreaNotes.toMutableMap()
        updated[areaId] = note
        _uiState.update { it.copy(teacherAreaNotes = updated) }
        context.getSharedPreferences("saber11_cgr_prefs", Context.MODE_PRIVATE)
            .edit()
            .putString("note_${areaId.name}", note)
            .apply()
    }

    fun updateSimulatedGain(areaId: AreaId, gain: Int) {
        val updated = _uiState.value.simulatedPointGainByArea.toMutableMap()
        updated[areaId] = gain.coerceIn(0, 12)
        _uiState.update { it.copy(simulatedPointGainByArea = updated) }
    }

    fun setWelcomeTourVisible(visible: Boolean) {
        _uiState.update { it.copy(showWelcomeTourSheet = visible) }
    }

    fun getSelectedAreaAnalytics(): AreaFullAnalytics {
        return IcfesRepository.getAreaAnalytics(_uiState.value.selectedArea)
    }

    fun shareAreaOrGeneralReport(context: Context) {
        val state = _uiState.value
        val areaAnalytics = IcfesRepository.getAreaAnalytics(state.selectedArea)
        val latestGlobal = IcfesRepository.globalScoreHistory.last()
        val latestAreaScore = areaAnalytics.scores.last()
        val latestLevels = areaAnalytics.performanceLevels.last()
        val latestErrors = areaAnalytics.learningErrorsByCut["2026-3"].orEmpty()
        val teacherNote = state.teacherAreaNotes[state.selectedArea].orEmpty()

        val reportText = buildString {
            appendLine("📊 REPORTE DE ANÁLISIS SABER 11° (2018–2026)")
            appendLine("🏫 ${IcfesRepository.SCHOOL_NAME}")
            appendLine("📍 Código DANE: ${IcfesRepository.DANE_CODE}")
            appendLine("--------------------------------------------")
            appendLine("🌐 PANORAMA GENERAL DEL COLEGIO (Último Corte 2026-3):")
            appendLine("• Puntaje Global EE: ${latestGlobal.eeScore} / 500 pts (Desviación: ${latestGlobal.eeStdDev})")
            appendLine("• Comparativa Nacional (Colombia): ${latestGlobal.colombiaScore} pts")
            appendLine("• Oficiales Urbanos Bogotá: ${latestGlobal.oficialesUrbanosScore} pts")
            appendLine()
            appendLine("📘 ANÁLISIS DEL ÁREA: ${state.selectedArea.title.uppercase()}")
            appendLine("• Serie Histórica EE (2018-2 a 2026-3):")
            appendLine("  " + areaAnalytics.scores.joinToString(" → ") { "${it.period}: ${it.eeScore}" })
            appendLine("• Último Promedio (2026-3): ${latestAreaScore.eeScore} / 100 pts (Colombia: ${latestAreaScore.colombiaScore} | Bogotá ETC: ${latestAreaScore.etcBogotaScore})")
            appendLine("• Distribución por Niveles de Desempeño (2026-3):")
            latestLevels.levels.forEach { lvl ->
                appendLine("  - ${lvl.label}: ${lvl.percentage}%")
            }
            appendLine()
            appendLine("⚠️ APRENDIZAJES PRIORITARIOS A MEJORAR (% Desacierto 2026-3):")
            latestErrors.forEachIndexed { idx, err ->
                appendLine("${idx + 1}. [${err.eeIncorrectPct}% error EE vs ${err.etcBogotaIncorrectPct}% Bogotá] ${err.statement}")
                appendLine("   💡 Acción sugerida: ${err.classroomAction}")
            }
            appendLine()
            appendLine("✅ DIAGNÓSTICO EJECUTIVO:")
            appendLine(areaAnalytics.executiveSummary)
            if (teacherNote.isNotBlank()) {
                appendLine()
                appendLine("📝 ACUERDOS DEL EQUIPO DOCENTE (${state.selectedArea.shortTitle}):")
                appendLine(teacherNote)
            }
        }

        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Análisis Saber 11° CGR - ${state.selectedArea.title}")
            putExtra(Intent.EXTRA_TEXT, reportText)
        }
        val chooser = Intent.createChooser(sendIntent, "Compartir análisis con docentes")
        context.startActivity(chooser)
    }
}
