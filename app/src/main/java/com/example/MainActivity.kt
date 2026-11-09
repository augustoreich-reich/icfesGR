package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.IcfesRepository
import com.example.ui.AppTab
import com.example.ui.IcfesUiState
import com.example.ui.IcfesViewModel
import com.example.ui.screens.AreaDetailScreen
import com.example.ui.screens.BeginnerGuideScreen
import com.example.ui.screens.GeneralDashboardScreen
import com.example.ui.screens.ImprovementPlanScreen
import com.example.ui.theme.CgrGreenDark
import com.example.ui.theme.CgrGreenPrimary
import com.example.ui.theme.CgrRedSecondary
import com.example.ui.theme.CgrYellowAccent
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Saber11CgrApp()
            }
        }
    }
}

@Composable
fun Saber11CgrApp(
    viewModel: IcfesViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadPreferencesIfNeeded(context)
    }

    // Si el usuario está en una pestaña secundaria, el botón Atrás vuelve al Panorama General
    if (uiState.currentTab != AppTab.GENERAL) {
        BackHandler {
            viewModel.selectTab(AppTab.GENERAL)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            InstitutionalHeader(
                uiState = uiState,
                onToggleEasyMode = { viewModel.toggleEasyExplanation(context) },
                onShareClick = { viewModel.shareAreaOrGeneralReport(context) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                val tabs = listOf(
                    Triple(AppTab.GENERAL, Icons.Default.Dashboard, "tab_general"),
                    Triple(AppTab.MI_AREA, Icons.Default.Analytics, "tab_mi_area"),
                    Triple(AppTab.PLAN_MEJORA, Icons.Default.Checklist, "tab_mejora"),
                    Triple(AppTab.GUIA_FACIL, Icons.Default.HelpCenter, "tab_guia")
                )
                tabs.forEach { (tab, icon, tag) ->
                    val selected = uiState.currentTab == tab
                    NavigationBarItem(
                        selected = selected,
                        onClick = { viewModel.selectTab(tab) },
                        modifier = Modifier.testTag(tag),
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = tab.title
                            )
                        },
                        label = {
                            Text(
                                text = if (tab == AppTab.MI_AREA) {
                                    "Área: ${uiState.selectedArea.shortTitle}"
                                } else {
                                    tab.title
                                },
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.currentTab) {
                AppTab.GENERAL -> {
                    GeneralDashboardScreen(
                        uiState = uiState,
                        onSelectAreaAndNavigate = { area ->
                            viewModel.selectArea(area, navigateToAreaTab = true)
                        },
                        onSelectPeriodFilter = { viewModel.selectPeriodFilter(it) },
                        onSelectComparisonEntity = { viewModel.selectComparisonEntity(it) }
                    )
                }

                AppTab.MI_AREA -> {
                    AreaDetailScreen(
                        uiState = uiState,
                        onSelectArea = { viewModel.selectArea(it, navigateToAreaTab = false) },
                        onSelectPeriodFilter = { viewModel.selectPeriodFilter(it) },
                        onSelectLearningCut = { viewModel.selectLearningCut(it) },
                        onSelectComparisonEntity = { viewModel.selectComparisonEntity(it) },
                        onGoToImprovementPlan = { viewModel.selectTab(AppTab.PLAN_MEJORA) },
                        onShareReport = { viewModel.shareAreaOrGeneralReport(context) }
                    )
                }

                AppTab.PLAN_MEJORA -> {
                    ImprovementPlanScreen(
                        uiState = uiState,
                        onSelectArea = { viewModel.selectArea(it, navigateToAreaTab = false) },
                        onToggleActionCheck = { key -> viewModel.toggleActionCheck(context, key) },
                        onUpdateTeacherNote = { area, note ->
                            viewModel.updateTeacherNote(context, area, note)
                        },
                        onUpdateSimulatedGain = { area, gain ->
                            viewModel.updateSimulatedGain(area, gain)
                        },
                        onShareReport = { viewModel.shareAreaOrGeneralReport(context) }
                    )
                }

                AppTab.GUIA_FACIL -> {
                    BeginnerGuideScreen(
                        uiState = uiState,
                        onToggleEasyMode = { viewModel.toggleEasyExplanation(context) },
                        onShareReport = { viewModel.shareAreaOrGeneralReport(context) }
                    )
                }
            }
        }
    }
}

@Composable
private fun InstitutionalHeader(
    uiState: IcfesUiState,
    onToggleEasyMode: () -> Unit,
    onShareClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(CgrGreenDark, CgrGreenPrimary)
                )
            )
            .statusBarsPadding()
    ) {
        // Franja tricolor superior inspirada en el anillo amarillo y rojo del escudo CGR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .background(CgrYellowAccent)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .background(CgrRedSecondary)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Escudo oficial del Colegio Gustavo Restrepo (IED)
                Image(
                    painter = painterResource(id = R.drawable.escudo_cgr),
                    contentDescription = "Escudo Colegio Gustavo Restrepo IED",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(2.dp, CgrYellowAccent, CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = IcfesRepository.SCHOOL_NAME,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Histórico Saber 11° (2018–2026) • DANE ${IcfesRepository.DANE_CODE}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.85f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Botón rápido para activar/desactivar explicaciones sencillas
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onToggleEasyMode() }
                        .testTag("header_easy_mode_toggle"),
                    color = if (uiState.isEasyExplanationEnabled) {
                        CgrYellowAccent
                    } else {
                        Color.White.copy(alpha = 0.18f)
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "Modo Explicación Sencilla",
                            tint = if (uiState.isEasyExplanationEnabled) Color(0xFF422006) else Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (uiState.isEasyExplanationEnabled) "Guía ON" else "Guía OFF",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (uiState.isEasyExplanationEnabled) Color(0xFF422006) else Color.White
                        )
                    }
                }

                IconButton(
                    onClick = onShareClick,
                    modifier = Modifier.testTag("header_share_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartir Reporte",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
