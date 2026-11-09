package com.example.data

import com.example.model.AreaFullAnalytics
import com.example.model.AreaId
import com.example.model.AreaScoreRecord
import com.example.model.GlobalScoreRecord
import com.example.model.ImprovementRecommendation
import com.example.model.LearningErrorRecord
import com.example.model.LevelPercentage
import com.example.model.ParticipationRecord
import com.example.model.PerformanceLevelBreakdown
import com.example.ui.theme.AreaCienciasColor
import com.example.ui.theme.AreaInglesColor
import com.example.ui.theme.AreaLecturaColor
import com.example.ui.theme.AreaMatematicasColor
import com.example.ui.theme.AreaSocialesColor

object IcfesRepository {

    const val SCHOOL_NAME = "COLEGIO GUSTAVO RESTREPO (IED)"
    const val SEDE_NAME = "COL DIST GUSTAVO RESTREPO (Sede 1)"
    const val DANE_CODE = "111001027332"
    const val ADDRESS = "CL 28 SUR 14 51 • Bogotá D.C."
    const val SECTOR_ZONA = "Oficial • Urbano • Grupo de Comparación (GC) 3"

    // Tabla 1.1: Ficha Técnica (2018-2 a 2026-3) de los 3 PDFs anexos
    val participationHistory: List<ParticipationRecord> = listOf(
        ParticipationRecord("2018-2", "12/08/2018", "Reporte 1", 78, 78, 77, 75),
        ParticipationRecord("2019-4", "11/08/2019", "Reporte 1", 67, 66, 66, 64),
        ParticipationRecord("2020-4", "07/11/2020", "Reporte 1", 87, 87, 84, 84),
        ParticipationRecord("2021-4", "04/09/2021", "Reporte 2", 94, 90, 83, 83),
        ParticipationRecord("2022-4", "04/09/2022", "Reporte 2", 87, 83, 83, 83),
        ParticipationRecord("2023-4", "13/08/2023", "Reporte 2", 91, 90, 88, 88),
        ParticipationRecord("2024-3", "18/08/2024", "Reporte 3", 98, 96, 94, 94),
        ParticipationRecord("2025-3", "10/08/2025", "Reporte 3", 97, 96, 94, 94),
        ParticipationRecord("2026-3", "26/07/2026", "Reporte 3", 81, 79, 77, 77)
    )

    // Tabla 2.1: Promedio del Puntaje Global (0 a 500 puntos) y Desviación Estándar
    val globalScoreHistory: List<GlobalScoreRecord> = listOf(
        GlobalScoreRecord("2018-2", 264, 36, 258, 49, 276, 46, 262, 41, 296, 45),
        GlobalScoreRecord("2019-4", 256, 36, 253, 50, 272, 47, 258, 42, 292, 46),
        GlobalScoreRecord("2020-4", 264, 36, 252, 48, 270, 45, 257, 40, 290, 44),
        GlobalScoreRecord("2021-4", 260, 43, 250, 50, 270, 48, 255, 42, 293, 47),
        GlobalScoreRecord("2022-4", 265, 38, 254, 51, 274, 48, 259, 43, 295, 47),
        GlobalScoreRecord("2023-4", 262, 44, 257, 51, 274, 48, 260, 43, 295, 47),
        GlobalScoreRecord("2024-3", 255, 43, 260, 52, 277, 47, 264, 43, 299, 46),
        GlobalScoreRecord("2025-3", 256, 36, 261, 53, 278, 49, 263, 44, 301, 47),
        GlobalScoreRecord("2026-3", 259, 36, 258, 48, 273, 44, 260, 40, 293, 44)
    )

    private val lecturaAnalytics = AreaFullAnalytics(
        areaId = AreaId.LECTURA,
        color = AreaLecturaColor,
        scores = listOf(
            AreaScoreRecord("2018-2", 55, 9, 54, 10, 57, 55, 60),
            AreaScoreRecord("2019-4", 54, 8, 53, 10, 57, 54, 60),
            AreaScoreRecord("2020-4", 56, 9, 53, 10, 56, 54, 59),
            AreaScoreRecord("2021-4", 56, 9, 53, 11, 57, 54, 61),
            AreaScoreRecord("2022-4", 56, 8, 54, 11, 57, 55, 61),
            AreaScoreRecord("2023-4", 55, 9, 54, 10, 57, 55, 61),
            AreaScoreRecord("2024-3", 53, 9, 54, 10, 58, 55, 61),
            AreaScoreRecord("2025-3", 53, 7, 55, 10, 58, 55, 62),
            AreaScoreRecord("2026-3", 54, 7, 53, 10, 56, 54, 60)
        ),
        performanceLevels = listOf(
            PerformanceLevelBreakdown("2018-2", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 1, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 35),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 56),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 8, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2019-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 0, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 27),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 66),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 8, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2020-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 1, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 23),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 65),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 11, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2021-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 0, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 32),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 51),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 17, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2022-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 1, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 25),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 61),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 13, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2023-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 1, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 30),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 57),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 12, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2024-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 3, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 33),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 59),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 5, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2025-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 0, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 41),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 57),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 2, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2026-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 1, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 24),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 72),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 3, isAdvancedHigh = true)
            ))
        ),
        learningErrorsByCut = mapOf(
            "2026-3" to listOf(
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Comprende cómo se articulan las partes de un texto para darle un sentido global.",
                    categoryTag = "Sentido Global (Inferencial)",
                    eeIncorrectPct = 47,
                    colombiaIncorrectPct = 47,
                    etcBogotaIncorrectPct = 42,
                    plainExplanation = "Casi la mitad de las preguntas donde el estudiante debe conectar párrafos, títulos y gráficos para entender la idea central se responden de forma incorrecta (47%).",
                    classroomAction = "Realizar ejercicios de esquemas mentales y resúmenes por párrafo antes de responder preguntas globales."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Reflexiona a partir de un texto y evalúa su contenido.",
                    categoryTag = "Lectura Crítica / Evaluación",
                    eeIncorrectPct = 47,
                    colombiaIncorrectPct = 49,
                    etcBogotaIncorrectPct = 44,
                    plainExplanation = "Presenta 47% de desacierto al identificar la intención del autor, detectar prejuicios o evaluar la validez de argumentos.",
                    classroomAction = "Comparar dos columnas de opinión opuestas sobre un mismo tema e identificar tesis, tono e intención."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Identifica y entiende los contenidos locales que conforman un texto.",
                    categoryTag = "Comprensión Literal",
                    eeIncorrectPct = 28,
                    colombiaIncorrectPct = 29,
                    etcBogotaIncorrectPct = 25,
                    plainExplanation = "¡Gran fortaleza del colegio! El error bajó del 40% en 2020-4 a solo 28% en 2026-3. Los estudiantes ubican muy bien datos explícitos.",
                    classroomAction = "Mantener el vocabulario en contexto como puente hacia la inferencia global."
                )
            ),
            "2023-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Reflexiona a partir de un texto y evalúa su contenido.",
                    categoryTag = "Lectura Crítica / Evaluación",
                    eeIncorrectPct = 49,
                    colombiaIncorrectPct = 50,
                    etcBogotaIncorrectPct = 44,
                    plainExplanation = "En 2023-4 fue el aprendizaje con mayor dificultad (49% de error).",
                    classroomAction = "Debates guiados sobre validez de fuentes y propósito comunicativo."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Comprende cómo se articulan las partes de un texto para darle un sentido global.",
                    categoryTag = "Sentido Global (Inferencial)",
                    eeIncorrectPct = 47,
                    colombiaIncorrectPct = 48,
                    etcBogotaIncorrectPct = 42,
                    plainExplanation = "47% de respuestas incorrectas al relacionar distintas partes del texto.",
                    classroomAction = "Mapas conceptuales de conectores lógicos (causa, contraste, consecuencia)."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Identifica y entiende los contenidos locales que conforman un texto.",
                    categoryTag = "Comprensión Literal",
                    eeIncorrectPct = 36,
                    colombiaIncorrectPct = 37,
                    etcBogotaIncorrectPct = 33,
                    plainExplanation = "36% de error en comprensión literal de palabras y frases locales.",
                    classroomAction = "Glosarios contextuales en lecturas científicas y filosóficas."
                )
            ),
            "2020-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Comprende cómo se articulan las partes de un texto para darle un sentido global.",
                    categoryTag = "Sentido Global (Inferencial)",
                    eeIncorrectPct = 43,
                    colombiaIncorrectPct = 48,
                    etcBogotaIncorrectPct = 42,
                    plainExplanation = "En 2020-4 fue el aprendizaje con mayor error en el colegio (43%).",
                    classroomAction = "Talleres de estructura textual e infografías."
                ),
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Identifica y entiende los contenidos locales que conforman un texto.",
                    categoryTag = "Comprensión Literal",
                    eeIncorrectPct = 40,
                    colombiaIncorrectPct = 43,
                    etcBogotaIncorrectPct = 39,
                    plainExplanation = "40% de error en preguntas literales durante 2020-4.",
                    classroomAction = "Lectura guiada subrayando referentes pronominales."
                ),
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Reflexiona a partir de un texto y evalúa su contenido.",
                    categoryTag = "Lectura Crítica / Evaluación",
                    eeIncorrectPct = 33,
                    colombiaIncorrectPct = 39,
                    etcBogotaIncorrectPct = 33,
                    plainExplanation = "Solo 33% de desacierto en 2020-4, igualando el promedio de Bogotá.",
                    classroomAction = "Foros de argumentación crítica."
                )
            )
        ),
        executiveSummary = "Lectura Crítica es el área más sólida y estable del Colegio Gustavo Restrepo (promedio entre 53 y 56 puntos, superando a Colombia en 7 de las 9 aplicaciones). En 2026-3 logró un hito: el 72% de los estudiantes se ubicó en Nivel 3 (Satisfactorio) y la desviación estándar bajó a 7 (grupo muy parejo). El reto principal es recuperar la proporción de estudiantes en Nivel 4 (que pasó de 17% en 2021-4 a 3% en 2026-3).",
        keyStrength = "Comprensión literal de contenidos locales (solo 28% de error en 2026-3) y 75% de estudiantes en Niveles 3 y 4.",
        mainOpportunity = "Impulsar a los estudiantes del Nivel 3 (72%) hacia el Nivel 4 (Avanzado) fortaleciendo la evaluación crítica e intertextualidad (47% de error).",
        recommendations = listOf(
            ImprovementRecommendation(
                id = "lec_1",
                areaId = AreaId.LECTURA,
                priorityLevel = "Prioridad Alta",
                title = "Plan «De Nivel 3 a Nivel 4»: Lectura Crítica e Intertextual",
                evidenceFromPdf = "En 2026-3, el 72% está en Nivel 3 pero solo el 3% alcanzó Nivel 4 (en 2021-4 era 17%). Además, 'Reflexionar y evaluar el contenido' aumentó su error de 33% (2020) a 47% (2026).",
                friendlyExplanation = "Nuestros estudiantes ya entienden qué dice el texto literalmente, pero les cuesta dar el paso final: descubrir qué hay 'detrás' del autor (ironía, postura ideológica, validez de fuentes) y comparar dos textos distintos.",
                stepByStepActions = listOf(
                    "Incluir en todas las áreas lecturas par (Texto A vs. Texto B) con posturas contrarias para preguntar: ¿En qué coinciden y en qué discrepan?",
                    "Aplicar la rutina de 3 preguntas en clase: 1) ¿Qué dice el autor? 2) ¿Para qué lo dice (intención)? 3) ¿Qué fuente o evidencia usa?",
                    "Trabajar textos discontinuos complejos (infografías, caricaturas políticas, tablas estadísticas) articulados con textos argumentativos."
                ),
                targetIndicator = "Subir Nivel 4 del 3% al 12% y bajar error crítico del 47% al 35%."
            ),
            ImprovementRecommendation(
                id = "lec_2",
                areaId = AreaId.LECTURA,
                priorityLevel = "Prioridad Media",
                title = "Taller de Cohesión y Sentido Global del Texto",
                evidenceFromPdf = "El aprendizaje 'Comprende cómo se articulan las partes de un texto para darle un sentido global' presenta un 47% de respuestas incorrectas en 2026-3 (5 puntos más de error que Bogotá ETC con 42%).",
                friendlyExplanation = "Muchos estudiantes leen párrafos sueltos pero pierden el hilo conductor cuando el texto usa conectores de contraargumentación ('sin embargo', 'no obstante', 'a pesar de').",
                stepByStepActions = listOf(
                    "Entrenar el rastreo de conectores lógicos encerrándolos en círculos durante los simulacros de lectura.",
                    "Pedir a los estudiantes titular cada párrafo en 5 palabras antes de contestar preguntas sobre el tema central."
                ),
                targetIndicator = "Reducir el porcentaje de respuestas incorrectas en sentido global por debajo del 40%."
            )
        )
    )

    private val matematicasAnalytics = AreaFullAnalytics(
        areaId = AreaId.MATEMATICAS,
        color = AreaMatematicasColor,
        scores = listOf(
            AreaScoreRecord("2018-2", 53, 8, 52, 12, 56, 53, 60),
            AreaScoreRecord("2019-4", 52, 8, 52, 12, 56, 53, 60),
            AreaScoreRecord("2020-4", 55, 8, 52, 11, 56, 53, 59),
            AreaScoreRecord("2021-4", 52, 9, 51, 11, 55, 51, 59),
            AreaScoreRecord("2022-4", 53, 9, 52, 12, 55, 53, 60),
            AreaScoreRecord("2023-4", 53, 11, 52, 12, 55, 53, 60),
            AreaScoreRecord("2024-3", 53, 11, 53, 13, 57, 54, 61),
            AreaScoreRecord("2025-3", 51, 9, 53, 13, 57, 54, 61),
            AreaScoreRecord("2026-3", 49, 9, 52, 12, 54, 52, 59)
        ),
        performanceLevels = listOf(
            PerformanceLevelBreakdown("2018-2", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 0, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 32),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 68),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2019-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 3, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 36),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 59),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 2, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2020-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 0, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 26),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 73),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2021-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 0, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 42),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 56),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2022-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 1, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 38),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 58),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 3, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2023-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 7, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 31),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 57),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 5, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2024-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 7, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 34),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 53),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 6, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2025-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 3, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 42),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 53),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2026-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 7, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 47),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 46),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            ))
        ),
        learningErrorsByCut = mapOf(
            "2026-3" to listOf(
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Frente a un problema que involucre información cuantitativa, plantea e implementa estrategias que lleven a soluciones adecuadas.",
                    categoryTag = "Formulación y Ejecución",
                    eeIncorrectPct = 68,
                    colombiaIncorrectPct = 65,
                    etcBogotaIncorrectPct = 62,
                    plainExplanation = "Es la mayor alerta del área en 2026-3: 68% de desacierto al traducir un problema cotidiano en operaciones matemáticas y resolverlo paso a paso.",
                    classroomAction = "Implementar el método Polya en el aula: 1) Entender datos, 2) Trazar plan, 3) Ejecutar sin calculadora, 4) Verificar sentido del resultado."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Comprende y transforma la información cuantitativa y esquemática presentada en distintos formatos.",
                    categoryTag = "Interpretación de Gráficos y Tablas",
                    eeIncorrectPct = 63,
                    colombiaIncorrectPct = 57,
                    etcBogotaIncorrectPct = 53,
                    plainExplanation = "Subió de 23% de error en 2020-4 a 63% en 2026-3 (10 puntos por encima del error de Bogotá ETC). Cuesta pasar de una tabla a una gráfica o porcentaje.",
                    classroomAction = "Dedicar 15 minutos semanales a transformar tablas reales en diagramas de barras, circulares y líneas."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Valida procedimientos y estrategias matemáticas utilizadas para dar solución a problemas.",
                    categoryTag = "Argumentación y Validación",
                    eeIncorrectPct = 62,
                    colombiaIncorrectPct = 56,
                    etcBogotaIncorrectPct = 53,
                    plainExplanation = "62% de desacierto cuando la pregunta pide detectar dónde está el error en el procedimiento de otra persona o justificar por qué una afirmación es falsa.",
                    classroomAction = "Actividad «Caza el Error»: mostrar en el tablero un problema mal resuelto para que los estudiantes expliquen en qué paso falló."
                )
            ),
            "2023-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Valida procedimientos y estrategias matemáticas utilizadas para dar solución a problemas.",
                    categoryTag = "Argumentación y Validación",
                    eeIncorrectPct = 65,
                    colombiaIncorrectPct = 60,
                    etcBogotaIncorrectPct = 57,
                    plainExplanation = "65% de error en validación de argumentos y procedimientos matemáticos.",
                    classroomAction = "Preguntas abiertas donde el estudiante justifique con contraejemplos."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Frente a un problema que involucre información cuantitativa, plantea e implementa estrategias que lleven a soluciones adecuadas.",
                    categoryTag = "Formulación y Ejecución",
                    eeIncorrectPct = 59,
                    colombiaIncorrectPct = 57,
                    etcBogotaIncorrectPct = 53,
                    plainExplanation = "59% de respuestas incorrectas al resolver problemas aplicados.",
                    classroomAction = "Talleres de proporcionalidad, geometría aplicada y probabilidad."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Comprende y transforma la información cuantitativa y esquemática presentada en distintos formatos.",
                    categoryTag = "Interpretación de Gráficos y Tablas",
                    eeIncorrectPct = 35,
                    colombiaIncorrectPct = 39,
                    etcBogotaIncorrectPct = 34,
                    plainExplanation = "En 2023-4 era la competencia más fuerte con 35% de error.",
                    classroomAction = "Lectura cruzada de gráficos estadísticos."
                )
            ),
            "2020-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Valida procedimientos y estrategias matemáticas utilizadas para dar solución a problemas.",
                    categoryTag = "Argumentación y Validación",
                    eeIncorrectPct = 53,
                    colombiaIncorrectPct = 51,
                    etcBogotaIncorrectPct = 45,
                    plainExplanation = "53% de error en validación de procedimientos en 2020-4.",
                    classroomAction = "Justificación verbal y escrita de pasos algebraicos."
                ),
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Frente a un problema que involucre información cuantitativa, plantea e implementa estrategias que lleven a soluciones adecuadas.",
                    categoryTag = "Formulación y Ejecución",
                    eeIncorrectPct = 46,
                    colombiaIncorrectPct = 50,
                    etcBogotaIncorrectPct = 45,
                    plainExplanation = "46% de error en resolución de problemas (4 puntos mejor que Colombia).",
                    classroomAction = "Simulacros por componentes numérico-variacional y geométrico."
                ),
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Comprende y transforma la información cuantitativa y esquemática presentada en distintos formatos.",
                    categoryTag = "Interpretación de Gráficos y Tablas",
                    eeIncorrectPct = 23,
                    colombiaIncorrectPct = 30,
                    etcBogotaIncorrectPct = 24,
                    plainExplanation = "Excelente desempeño en 2020-4 con solo 23% de respuestas incorrectas.",
                    classroomAction = "Mantener ejercicios de conversión entre tablas y gráficos."
                )
            )
        ),
        executiveSummary = "Matemáticas requiere atención prioritaria del equipo docente en 2026: tras mantenerse entre 52 y 55 puntos desde 2018 hasta 2024 (con su pico histórico de 55 puntos y 73% en Nivel 3 en 2020-4), descendió a 51 en 2025-3 y a 49 en 2026-3, quedando 3 puntos por debajo del promedio nacional (52). El Nivel 3 bajó de 73% (2020) a 46% (2026) y los tres aprendizajes superaron el 62% de desacierto.",
        keyStrength = "Baja dispersión histórica (desviación de 8 a 9 puntos) y tradición de alto desempeño en 2020–2024 (hasta 73% en Nivel 3) que demuestra el potencial del área.",
        mainOpportunity = "Revertir la caída a 49 puntos recuperando la lectura de tablas/gráficos (cuyo error subió de 23% a 63%) y la resolución de problemas cotidianos (68% de error).",
        recommendations = listOf(
            ImprovementRecommendation(
                id = "mat_1",
                areaId = AreaId.MATEMATICAS,
                priorityLevel = "Prioridad Alta",
                title = "Rescate de Interpretación de Gráficos, Tablas y Porcentajes",
                evidenceFromPdf = "El aprendizaje 'Comprende y transforma la información cuantitativa y esquemática' pasó de 23% de error (2020-4) y 35% (2023-4) a 63% en 2026-3.",
                friendlyExplanation = "Antes nuestros estudiantes dominaban la lectura de gráficas y tablas, pero en la última cohorte 6 de cada 10 respuestas en este tema fallaron. Recuperar esta habilidad es la vía más rápida para volver a 54+ puntos.",
                stepByStepActions = listOf(
                    "Iniciar cada unidad temática con el análisis de una gráfica o tabla real (recibos públicos, datos DANE, infografías financieras).",
                    "Practicar conversiones rápidas sin calculadora: fracción ↔ decimal ↔ porcentaje (ej. 1/4 = 0,25 = 25%).",
                    "Hacer que los estudiantes pasen del Nivel 2 (47%) al Nivel 3 entrenando lectura de ejes, escalas engañosas y tendencias."
                ),
                targetIndicator = "Reducir el error de interpretación gráfica del 63% a menos del 38%."
            ),
            ImprovementRecommendation(
                id = "mat_2",
                areaId = AreaId.MATEMATICAS,
                priorityLevel = "Prioridad Alta",
                title = "Laboratorio de Formulación y Validación («Caza el Error»)",
                evidenceFromPdf = "El 68% falla al plantear estrategias frente a problemas cuantitativos y el 62% al validar procedimientos (Figura 4.4 de 2026-3).",
                friendlyExplanation = "El ICFES no evalúa memorizar fórmulas, sino elegir qué operación sirve para un caso práctico (presupuestos, áreas, probabilidades) y detectar errores en razonamientos ajenos.",
                stepByStepActions = listOf(
                    "Resolver en clase problemas tipo ICFES donde la pregunta no es '¿cuánto da?' sino '¿cuál procedimiento permite hallar la respuesta?'.",
                    "Reforzar geometría espacial (perímetros, áreas compuestas, Pitágoras) y estadística (promedio ponderado, combinaciones y probabilidad simple)."
                ),
                targetIndicator = "Subir el promedio del área de 49 a 53+ puntos y recuperar al menos 60% en Nivel 3."
            )
        )
    )

    private val socialesAnalytics = AreaFullAnalytics(
        areaId = AreaId.SOCIALES,
        color = AreaSocialesColor,
        scores = listOf(
            AreaScoreRecord("2018-2", 50, 10, 49, 12, 53, 50, 58),
            AreaScoreRecord("2019-4", 48, 11, 48, 12, 51, 49, 56),
            AreaScoreRecord("2020-4", 51, 10, 49, 12, 53, 50, 57),
            AreaScoreRecord("2021-4", 49, 11, 48, 12, 52, 49, 56),
            AreaScoreRecord("2022-4", 51, 10, 48, 12, 53, 50, 57),
            AreaScoreRecord("2023-4", 50, 11, 49, 12, 53, 50, 57),
            AreaScoreRecord("2024-3", 48, 11, 49, 12, 53, 50, 57),
            AreaScoreRecord("2025-3", 49, 9, 49, 12, 52, 49, 57),
            AreaScoreRecord("2026-3", 52, 7, 50, 11, 53, 50, 57)
        ),
        performanceLevels = listOf(
            PerformanceLevelBreakdown("2018-2", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 19, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 51),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 29),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2019-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 28, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 50),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 22),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2020-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 17, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 52),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 26),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 5, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2021-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 21, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 47),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 31),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2022-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 13, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 51),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 35),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2023-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 22, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 49),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 25),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 4, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2024-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 29, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 47),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 22),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 2, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2025-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 17, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 59),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 24),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2026-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 9, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 60),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 31),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            ))
        ),
        learningErrorsByCut = mapOf(
            "2026-3" to listOf(
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Comprende dimensiones espaciales y temporales de eventos, problemáticas y prácticas sociales.",
                    categoryTag = "Espacio y Tiempo Histórico",
                    eeIncorrectPct = 54,
                    colombiaIncorrectPct = 55,
                    etcBogotaIncorrectPct = 49,
                    plainExplanation = "54% de respuestas incorrectas al ubicar procesos históricos en su contexto temporal o geográfico.",
                    classroomAction = "Usar líneas de tiempo comparadas (Colombia vs. Mundo) y mapas temáticos en cada periodo."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Comprende modelos conceptuales, sus características y contextos de aplicación.",
                    categoryTag = "Conceptos Constitucionales y Políticos",
                    eeIncorrectPct = 52,
                    colombiaIncorrectPct = 49,
                    etcBogotaIncorrectPct = 43,
                    plainExplanation = "52% de desacierto al aplicar conceptos como Estado Social de Derecho, división de poderes, mecanismos de participación o modelos económicos.",
                    classroomAction = "Estudio de casos reales con mecanismos constitucionales (Tutela, Acción Popular, Plebiscito, Referendo, Consulta Popular)."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Contextualiza y evalúa usos de fuentes y argumentos.",
                    categoryTag = "Evaluación de Fuentes",
                    eeIncorrectPct = 51,
                    colombiaIncorrectPct = 53,
                    etcBogotaIncorrectPct = 47,
                    plainExplanation = "51% de error al evaluar si una fuente histórica o política es confiable o pertinente para sustentar una tesis.",
                    classroomAction = "Analizar discursos históricos identificando quién habla, en qué época y con qué interés."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Comprende que los problemas y sus soluciones involucran distintas dimensiones y reconoce relaciones entre estas.",
                    categoryTag = "Pensamiento Sistémico / Multidimensional",
                    eeIncorrectPct = 48,
                    colombiaIncorrectPct = 43,
                    etcBogotaIncorrectPct = 37,
                    plainExplanation = "Mejoró del 60% de error en 2023-4 al 48% en 2026-3, pero sigue 11 puntos por encima del error de Bogotá (37%).",
                    classroomAction = "Matrices de impacto: ante un proyecto (ej. represa o peaje), clasificar efectos económicos, ambientales, políticos y culturales."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Comprende perspectivas de distintos actores y grupos sociales.",
                    categoryTag = "Análisis de Perspectivas",
                    eeIncorrectPct = 38,
                    colombiaIncorrectPct = 43,
                    etcBogotaIncorrectPct = 35,
                    plainExplanation = "¡Buen desempeño! Bajó de 50% (2023) a 38% de error (2026), superando en 5 puntos el promedio nacional.",
                    classroomAction = "Continuar con juegos de rol de actores sociales en conflictos."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Evalúa usos sociales de las ciencias sociales.",
                    categoryTag = "Quehacer de las Ciencias Sociales",
                    eeIncorrectPct = 36,
                    colombiaIncorrectPct = 45,
                    etcBogotaIncorrectPct = 39,
                    plainExplanation = "¡La mayor fortaleza del área en 2026-3! Solo 36% de error (9 puntos mejor que Colombia y 3 puntos mejor que Bogotá).",
                    classroomAction = "Socializar esta buena práctica con el resto del departamento."
                )
            ),
            "2023-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Comprende que los problemas y sus soluciones involucran distintas dimensiones y reconoce relaciones entre estas.",
                    categoryTag = "Pensamiento Sistémico",
                    eeIncorrectPct = 60,
                    colombiaIncorrectPct = 61,
                    etcBogotaIncorrectPct = 61,
                    plainExplanation = "60% de error en 2023-4 al cruzar dimensiones políticas, económicas y ambientales.",
                    classroomAction = "Análisis multidimensional de conflictos socioambientales."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Comprende dimensiones espaciales y temporales de eventos, problemáticas y prácticas sociales.",
                    categoryTag = "Espacio y Tiempo Histórico",
                    eeIncorrectPct = 59,
                    colombiaIncorrectPct = 56,
                    etcBogotaIncorrectPct = 51,
                    plainExplanation = "59% de respuestas incorrectas en ubicación espacio-temporal.",
                    classroomAction = "Líneas de tiempo del siglo XX en Colombia."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Comprende modelos conceptuales, sus características y contextos de aplicación.",
                    categoryTag = "Conceptos Constitucionales",
                    eeIncorrectPct = 36,
                    colombiaIncorrectPct = 39,
                    etcBogotaIncorrectPct = 33,
                    plainExplanation = "En 2023-4 fue la fortaleza del área con solo 36% de desacierto.",
                    classroomAction = "Talleres de Constitución Política de 1991."
                )
            ),
            "2020-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Evalúa usos sociales de las ciencias sociales.",
                    categoryTag = "Quehacer Social",
                    eeIncorrectPct = 52,
                    colombiaIncorrectPct = 56,
                    etcBogotaIncorrectPct = 49,
                    plainExplanation = "En 2020-4 tuvo 52% de error (hoy en 2026-3 bajó a 36%).",
                    classroomAction = "Articulación de disciplinas sociales."
                ),
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Comprende que los problemas y sus soluciones involucran distintas dimensiones y reconoce relaciones entre estas.",
                    categoryTag = "Pensamiento Sistémico",
                    eeIncorrectPct = 47,
                    colombiaIncorrectPct = 49,
                    etcBogotaIncorrectPct = 42,
                    plainExplanation = "47% de desacierto en relaciones multidimensionales.",
                    classroomAction = "Debates sobre políticas públicas."
                )
            )
        ),
        executiveSummary = "¡Sociales y Ciudadanas protagonizó el mayor avance institucional en 2026-3! Alcanzó su máximo histórico de 52 puntos (superando a Colombia con 50 y a Oficiales Urbanos con 50), redujo su desviación estándar a un récord de 7 puntos y disminuyó drásticamente el Nivel 1 (Bajo) del 29% en 2024-3 a solo 9% en 2026-3.",
        keyStrength = "Reducción histórica del Nivel 1 al 9% y dominio destacado en 'Evalúa usos sociales de las ciencias sociales' (36% de error vs. 45% en Colombia).",
        mainOpportunity = "El 60% de los estudiantes está concentrado en Nivel 2. Fortalecer modelos conceptuales y dimensiones espacio-temporales (52%–54% de error) permitirá moverlos masivamente al Nivel 3 y 4.",
        recommendations = listOf(
            ImprovementRecommendation(
                id = "soc_1",
                areaId = AreaId.SOCIALES,
                priorityLevel = "Prioridad Alta",
                title = "Movilización del Nivel 2 (60%) hacia el Nivel 3: Modelos y Constitución",
                evidenceFromPdf = "El 60% de los estudiantes se ubica en Nivel 2 en 2026-3. 'Comprende modelos conceptuales' subió su error de 36% (2023) a 52% (2026), y 'Dimensiones espaciales y temporales' registra 54% de error.",
                friendlyExplanation = "Logramos sacar a los estudiantes del Nivel 1 (solo queda 9%), y ahora tenemos una gran base en Nivel 2 (60%). Si afianzamos conceptos de Constitución de 1991 y modelos económicos/políticos, darán el salto inmediato a Nivel 3.",
                stepByStepActions = listOf(
                    "Trabajar cuadros comparativos de modelos económicos (Apertura económica, Proteccionismo, Neoliberalismo, Estado de Bienestar) aplicados a noticias reales.",
                    "Repasar con casos prácticos cuándo procede cada mecanismo constitucional (Tutela vs. Acción de Cumplimiento vs. Acción Popular) y funciones de los organismos de control (Procuraduría, Contraloría, Defensoría)."
                ),
                targetIndicator = "Bajar el Nivel 2 del 60% al 45% y subir el Nivel 3+4 del 31% al 45%."
            ),
            ImprovementRecommendation(
                id = "soc_2",
                areaId = AreaId.SOCIALES,
                priorityLevel = "Prioridad Media",
                title = "Matrices Multidimensionales de Conflictos (Pensamiento Sistémico)",
                evidenceFromPdf = "En 'Comprende que los problemas y sus soluciones involucran distintas dimensiones' el colegio tiene 48% de error frente a 37% de Bogotá (ETC).",
                friendlyExplanation = "En muchas preguntas el estudiante debe identificar qué efecto negativo genera una solución (por ejemplo, construir una carretera mejora la economía pero afecta el ambiente). Ahí tenemos 11 puntos de brecha frente a Bogotá.",
                stepByStepActions = listOf(
                    "En cada estudio de caso, pedir a los estudiantes llenar una cruz de 4 cuadrantes: Dimensión Política, Económica, Social y Ambiental."
                ),
                targetIndicator = "Reducir el desacierto en pensamiento sistémico del 48% al 38%."
            )
        )
    )

    private val cienciasAnalytics = AreaFullAnalytics(
        areaId = AreaId.CIENCIAS,
        color = AreaCienciasColor,
        scores = listOf(
            AreaScoreRecord("2018-2", 53, 7, 51, 10, 54, 52, 58),
            AreaScoreRecord("2019-4", 51, 7, 50, 11, 53, 50, 57),
            AreaScoreRecord("2020-4", 52, 9, 49, 10, 52, 50, 56),
            AreaScoreRecord("2021-4", 51, 9, 49, 10, 52, 50, 57),
            AreaScoreRecord("2022-4", 53, 8, 50, 10, 53, 51, 57),
            AreaScoreRecord("2023-4", 51, 10, 50, 10, 53, 51, 57),
            AreaScoreRecord("2024-3", 50, 9, 51, 11, 54, 52, 58),
            AreaScoreRecord("2025-3", 51, 8, 52, 11, 54, 52, 59),
            AreaScoreRecord("2026-3", 52, 9, 51, 10, 54, 52, 57)
        ),
        performanceLevels = listOf(
            PerformanceLevelBreakdown("2018-2", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 7, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 53),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 40),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2019-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 9, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 63),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 28),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2020-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 7, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 59),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 33),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2021-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 17, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 54),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 28),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2022-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 6, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 60),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 32),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2023-4", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 17, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 51),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 29),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 4, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2024-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 14, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 56),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 29),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2025-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 14, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 55),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 32),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2026-3", listOf(
                LevelPercentage("N1", "Nivel 1 (Bajo)", 16, isCriticalLow = true),
                LevelPercentage("N2", "Nivel 2 (Básico)", 47),
                LevelPercentage("N3", "Nivel 3 (Satisfactorio)", 37),
                LevelPercentage("N4", "Nivel 4 (Avanzado)", 0, isAdvancedHigh = true)
            ))
        ),
        learningErrorsByCut = mapOf(
            "2026-3" to listOf(
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Explicar cómo ocurren algunos fenómenos de la naturaleza basado en observaciones, en patrones y en conceptos propios del conocimiento científico - Procesos físicos.",
                    categoryTag = "Procesos Físicos (Explicación)",
                    eeIncorrectPct = 65,
                    colombiaIncorrectPct = 59,
                    etcBogotaIncorrectPct = 55,
                    plainExplanation = "Mayor dificultad del área en 2026-3: 65% de error al explicar fenómenos físicos (fuerzas, energía, ondas, circuitos) a partir de patrones.",
                    classroomAction = "Usar simulaciones interactivas (ej. PhET) y diagramas de cuerpo libre para explicar qué pasa cuando cambia una variable física."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Identificar las características de algunos fenómenos de la naturaleza basado en el análisis de información y conceptos propios - Procesos físicos.",
                    categoryTag = "Procesos Físicos (Uso de Conceptos)",
                    eeIncorrectPct = 64,
                    colombiaIncorrectPct = 65,
                    etcBogotaIncorrectPct = 62,
                    plainExplanation = "64% de respuestas incorrectas al analizar datos sobre movimiento, termodinámica o electromagnetismo.",
                    classroomAction = "Relacionar gráficas de posición/velocidad/tiempo y temperatura/calor con situaciones cotidianas."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Explicar cómo ocurren algunos fenómenos de la naturaleza basado en observaciones, en patrones y en conceptos propios - Procesos vivos.",
                    categoryTag = "Procesos Vivos (Biología)",
                    eeIncorrectPct = 63,
                    colombiaIncorrectPct = 62,
                    etcBogotaIncorrectPct = 60,
                    plainExplanation = "63% de desacierto al explicar relaciones causales en genética, ecosistemas, homeostasis y evolución.",
                    classroomAction = "Trabajar cadenas tróficas, flujos de energía y cruces genéticos mediante problemas de predicción ('¿qué pasaría si desaparece la especie X?')."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Comprender que a partir de la investigación científica se construyen explicaciones sobre el mundo natural - Procesos químicos.",
                    categoryTag = "Procesos Químicos (Indagación)",
                    eeIncorrectPct = 62,
                    colombiaIncorrectPct = 53,
                    etcBogotaIncorrectPct = 48,
                    plainExplanation = "62% de error (14 puntos más de error que Bogotá ETC con 48%) al interpretar diseños experimentales en química.",
                    classroomAction = "En cada práctica de química, pedir identificar explícitamente: Hipótesis, Variable Independiente, Variable Dependiente y Control."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Asociar fenómenos naturales con conceptos propios del conocimiento científico - Procesos químicos.",
                    categoryTag = "Procesos Químicos (Conceptos)",
                    eeIncorrectPct = 55,
                    colombiaIncorrectPct = 54,
                    etcBogotaIncorrectPct = 50,
                    plainExplanation = "55% de desacierto en estequiometría, soluciones, cambios de estado y tabla periódica.",
                    classroomAction = "Reforzar lectura de curvas de solubilidad, puntos de ebullición y balanceo visual de ecuaciones."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Utilizar algunas habilidades de pensamiento y de procedimiento para evaluar predicciones - Procesos físicos.",
                    categoryTag = "Indagación en Física (Fortaleza)",
                    eeIncorrectPct = 33,
                    colombiaIncorrectPct = 38,
                    etcBogotaIncorrectPct = 32,
                    plainExplanation = "¡Gran fortaleza en 2026-3! Solo 33% de error al evaluar predicciones a partir de procedimientos experimentales en física.",
                    classroomAction = "Replicar este enfoque de evaluación de predicciones en Química y Biología."
                )
            ),
            "2023-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Identificar las características de algunos fenómenos de la naturaleza basado en el análisis de información - Procesos físicos.",
                    categoryTag = "Procesos Físicos",
                    eeIncorrectPct = 65,
                    colombiaIncorrectPct = 63,
                    etcBogotaIncorrectPct = 59,
                    plainExplanation = "65% de error en identificación de fenómenos físicos en 2023-4.",
                    classroomAction = "Talleres de mecánica y energía."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Modelar fenómenos de la naturaleza basado en el análisis de variables - Procesos físicos.",
                    categoryTag = "Procesos Físicos (Modelación)",
                    eeIncorrectPct = 64,
                    colombiaIncorrectPct = 59,
                    etcBogotaIncorrectPct = 54,
                    plainExplanation = "64% de desacierto al relacionar variables físicas.",
                    classroomAction = "Análisis de tablas de laboratorio de física."
                ),
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Explicar cómo ocurren algunos fenómenos de la naturaleza basado en observaciones y patrones - Procesos químicos.",
                    categoryTag = "Procesos Químicos",
                    eeIncorrectPct = 62,
                    colombiaIncorrectPct = 64,
                    etcBogotaIncorrectPct = 61,
                    plainExplanation = "62% de respuestas incorrectas en explicación de procesos químicos.",
                    classroomAction = "Modelado molecular y reacciones químicas."
                )
            ),
            "2020-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Explicar cómo ocurren algunos fenómenos de la naturaleza basado en observaciones y patrones - Procesos químicos.",
                    categoryTag = "Procesos Químicos",
                    eeIncorrectPct = 61,
                    colombiaIncorrectPct = 59,
                    etcBogotaIncorrectPct = 53,
                    plainExplanation = "61% de error en explicación de fenómenos químicos en 2020-4.",
                    classroomAction = "Prácticas guiadas de reacciones y mezclas."
                ),
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Identificar las características de algunos fenómenos de la naturaleza basado en el análisis de información - Procesos vivos.",
                    categoryTag = "Procesos Vivos",
                    eeIncorrectPct = 61,
                    colombiaIncorrectPct = 59,
                    etcBogotaIncorrectPct = 54,
                    plainExplanation = "61% de error en análisis de información biológica.",
                    classroomAction = "Lectura de gráficas poblacionales y celulares."
                )
            )
        ),
        executiveSummary = "Ciencias Naturales muestra una recuperación sostenida en los últimos 3 años: pasó de 50 puntos (2024-3) a 51 (2025-3) y 52 puntos en 2026-3, superando nuevamente el promedio nacional (51) e igualando a los colegios Oficiales Urbanos de Bogotá (52). Además, el Nivel 3 subió de 29% a 37% en 2026-3.",
        keyStrength = "Crecimiento continuo del Nivel 3 (de 28% en 2021-4 a 37% en 2026-3) y excelente desempeño en evaluación de predicciones y componente CTS (solo 33%–38% de error).",
        mainOpportunity = "Reducir el 16% de estudiantes en Nivel 1 y bajar el porcentaje de error en Explicación de Fenómenos Físicos (65%) e Indagación en Química (62%).",
        recommendations = listOf(
            ImprovementRecommendation(
                id = "cie_1",
                areaId = AreaId.CIENCIAS,
                priorityLevel = "Prioridad Alta",
                title = "Fortalecimiento de Procesos Físicos y Diseño Experimental en Química",
                evidenceFromPdf = "En 2026-3, los mayores errores están en 'Explicar fenómenos - Procesos físicos' (65%) y 'Comprender que a partir de la investigación científica se construyen explicaciones - Procesos químicos' (62%, frente a 48% de Bogotá).",
                friendlyExplanation = "Cuando la pregunta muestra un experimento químico o un fenómeno físico (fuerzas, circuitos, calor), a los estudiantes les cuesta conectar la teoría con los datos de la tabla experimental.",
                stepByStepActions = listOf(
                    "Estandarizar en Biología, Física y Química la ficha 'Pregunta – Hipótesis – Variables – Conclusión' en todas las guías de laboratorio.",
                    "Entrenar en Física la lectura de esquemas visuales (poleas, planos inclinados, circuitos en serie/paralelo y refracción de ondas) sin cálculos extensos, priorizando el análisis conceptual."
                ),
                targetIndicator = "Reducir el error en Procesos Físicos y Químicos por debajo del 50% y bajar el Nivel 1 del 16% a menos del 8%."
            ),
            ImprovementRecommendation(
                id = "cie_2",
                areaId = AreaId.CIENCIAS,
                priorityLevel = "Prioridad Media",
                title = "Articulación de Procesos Vivos con Gráficas Ecológicas y Genéticas",
                evidenceFromPdf = "'Explicar cómo ocurren algunos fenómenos - Procesos vivos' presenta un 63% de desacierto en 2026-3.",
                friendlyExplanation = "Las preguntas de Biología del ICFES suelen presentar gráficas de crecimiento de poblaciones, actividad enzimática o árboles genealógicos que requieren explicar la causa biológica del cambio.",
                stepByStepActions = listOf(
                    "Trabajar estudios de caso con gráficas de fotosíntesis, respiración celular y dinámica de poblaciones preguntando siempre el 'porqué' del cambio en la curva."
                ),
                targetIndicator = "Aumentar el porcentaje de estudiantes en Niveles 3 y 4 del 37% al 48%."
            )
        )
    )

    private val inglesAnalytics = AreaFullAnalytics(
        areaId = AreaId.INGLES,
        color = AreaInglesColor,
        scores = listOf(
            AreaScoreRecord("2018-2", 51, 9, 52, 11, 57, 53, 63),
            AreaScoreRecord("2019-4", 50, 9, 50, 12, 56, 51, 62),
            AreaScoreRecord("2020-4", 48, 8, 48, 11, 52, 48, 57),
            AreaScoreRecord("2021-4", 52, 12, 50, 13, 56, 51, 63),
            AreaScoreRecord("2022-4", 52, 12, 51, 13, 57, 52, 63),
            AreaScoreRecord("2023-4", 52, 11, 52, 12, 57, 53, 63),
            AreaScoreRecord("2024-3", 53, 12, 52, 13, 58, 54, 65),
            AreaScoreRecord("2025-3", 52, 9, 53, 13, 58, 54, 65),
            AreaScoreRecord("2026-3", 54, 10, 53, 13, 58, 54, 64)
        ),
        performanceLevels = listOf(
            PerformanceLevelBreakdown("2018-2", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 39, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 41),
                LevelPercentage("A2", "Nivel A2 (Básico)", 16),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 3),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2019-4", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 39, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 41),
                LevelPercentage("A2", "Nivel A2 (Básico)", 19),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 2),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2020-4", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 61, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 26),
                LevelPercentage("A2", "Nivel A2 (Básico)", 12),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 1),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2021-4", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 38, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 28),
                LevelPercentage("A2", "Nivel A2 (Básico)", 19),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 14),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2022-4", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 35, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 35),
                LevelPercentage("A2", "Nivel A2 (Básico)", 18),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 10),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2023-4", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 37, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 34),
                LevelPercentage("A2", "Nivel A2 (Básico)", 18),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 10),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2024-3", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 34, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 30),
                LevelPercentage("A2", "Nivel A2 (Básico)", 27),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 8),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 1, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2025-3", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 26, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 47),
                LevelPercentage("A2", "Nivel A2 (Básico)", 24),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 3),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 0, isAdvancedHigh = true)
            )),
            PerformanceLevelBreakdown("2026-3", listOf(
                LevelPercentage("A-", "Nivel A- (Pre-básico)", 28, isCriticalLow = true),
                LevelPercentage("A1", "Nivel A1 (Principiante)", 38),
                LevelPercentage("A2", "Nivel A2 (Básico)", 24),
                LevelPercentage("B1", "Nivel B1 (Pre-intermedio)", 7),
                LevelPercentage("B+", "Nivel B+ (Supera B1)", 3, isAdvancedHigh = true)
            ))
        ),
        learningErrorsByCut = mapOf(
            "2026-3" to listOf(
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Nivel MCER A- y A1 (Partes 1 a 3: Avisos, Vocabulario y Conversaciones cortas)",
                    categoryTag = "Transición de A-/A1 hacia A2",
                    eeIncorrectPct = 66,
                    colombiaIncorrectPct = 68,
                    etcBogotaIncorrectPct = 53,
                    plainExplanation = "Nota ICFES (pág. 61): Inglés se evalúa por niveles del Marco Común Europeo (MCER). En 2026-3, el 66% de los estudiantes se ubicó en A- (28%) y A1 (38%), frente al 53% en Bogotá.",
                    classroomAction = "Asegurar el 100% de acierto en las Partes 1, 2 y 3 del examen (avisos públicos, definiciones por campos semánticos y diálogos cortos) para reducir A- por debajo del 15%."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Nivel MCER A2 (Partes 4 y 5: Gramática en contexto y Comprensión literal de textos)",
                    categoryTag = "Consolidación de Nivel A2 (24% en EE)",
                    eeIncorrectPct = 34,
                    colombiaIncorrectPct = 29,
                    etcBogotaIncorrectPct = 47,
                    plainExplanation = "¡El 34% de los estudiantes del colegio ya alcanza A2, B1 o B+ en 2026-3! (en 2020-4 era apenas el 13%). El Nivel A2 creció de 12% a 24%.",
                    classroomAction = "Entrenar lectura tipo 'Skimming & Scanning' y uso de preposiciones, conectores y tiempos verbales en textos cortos (Partes 4 y 5)."
                ),
                LearningErrorRecord(
                    reportCut = "2026-3",
                    statement = "Niveles MCER B1 y B+ (Partes 6 y 7: Lectura inferencial y léxico-gramática avanzada)",
                    categoryTag = "Meta Bilingüismo B1/B+ (10% en EE)",
                    eeIncorrectPct = 10,
                    colombiaIncorrectPct = 15,
                    etcBogotaIncorrectPct = 24,
                    plainExplanation = "En 2026-3 el colegio alcanzó su récord histórico en B+ (3%) y subió B1+B+ al 10% (7% en B1 y 3% en B+).",
                    classroomAction = "Talleres de lectura inferencial en inglés (identificar intención del autor y preguntas 'What can the reader find out?') para las Partes 6 y 7."
                )
            ),
            "2023-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2023-4",
                    statement = "Concentración en Niveles A- (37%) y A1 (34%) según Marco Común Europeo",
                    categoryTag = "Niveles Básicos MCER",
                    eeIncorrectPct = 71,
                    colombiaIncorrectPct = 73,
                    etcBogotaIncorrectPct = 55,
                    plainExplanation = "En 2023-4 el 71% de los estudiantes estuvo en A- o A1, y el 29% alcanzó A2, B1 o B+.",
                    classroomAction = "Refuerzo de vocabulario funcional y lectura literal."
                )
            ),
            "2020-4" to listOf(
                LearningErrorRecord(
                    reportCut = "2020-4",
                    statement = "Concentración en Niveles A- (61%) y A1 (26%) según Marco Común Europeo",
                    categoryTag = "Niveles Básicos MCER",
                    eeIncorrectPct = 87,
                    colombiaIncorrectPct = 84,
                    etcBogotaIncorrectPct = 74,
                    plainExplanation = "En 2020-4 el 87% estuvo en A- o A1 (con 61% en A-). Desde entonces el colegio ha reducido A- en 33 puntos porcentuales.",
                    classroomAction = "Plan choque de vocabulario por categorías."
                )
            )
        ),
        executiveSummary = "¡Inglés muestra la tendencia de crecimiento más constante de todo el colegio entre 2020 y 2026! Pasó de 48 puntos en 2020-4 a su récord histórico de 54 puntos en 2026-3, superando a Colombia (53) e igualando a los Oficiales Urbanos de Bogotá (54). El nivel crítico A- cayó del 61% (2020-4) al 28% (2026-3), mientras que los estudiantes en A2+B1+B+ subieron del 13% al 34%.",
        keyStrength = "Crecimiento sostenido de +6 puntos en el promedio (de 48 a 54), récord de 3% en Nivel B+ en 2026-3 y reducción a más de la mitad del Nivel A-.",
        mainOpportunity = "Mover al 38% de estudiantes que ya están en A1 hacia A2 y B1 mediante el dominio de textos cortos (Partes 4 y 5) y lectura inferencial (Partes 6 y 7).",
        recommendations = listOf(
            ImprovementRecommendation(
                id = "ing_1",
                areaId = AreaId.INGLES,
                priorityLevel = "Prioridad Alta",
                title = "Puente A1 → A2: Dominio de Gramática Funcional y Lectura Literal (Partes 4 y 5)",
                evidenceFromPdf = "En 2026-3 tenemos un 38% de los estudiantes en Nivel A1 y 28% en A-. El Nivel A2 ya llegó al 24%.",
                friendlyExplanation = "Como explica el reporte ICFES (pág. 61), Inglés evalúa el Marco Común Europeo. Nuestros estudiantes ya salieron masivamente de A- hacia A1; para llevarlos a A2 necesitan dominar conectores, tiempos verbales y paráfrasis en textos cortos.",
                stepByStepActions = listOf(
                    "Entrenar semanalmente las Partes 1, 2 y 3 como 'puntos seguros' (asociación de avisos, descripciones de objetos/profesiones y respuestas comunicativas).",
                    "Aplicar talleres de 'Cloze text' (completar espacios en textos cortos - Parte 4) enfocándose en preposiciones (in/on/at), pronombres relativos (who/which/that) y modales."
                ),
                targetIndicator = "Reducir el Nivel A- del 28% a menos del 18% y subir A2+B1+B+ del 34% al 48%."
            ),
            ImprovementRecommendation(
                id = "ing_2",
                areaId = AreaId.INGLES,
                priorityLevel = "Prioridad Media",
                title = "Semillero B1 / B+: Comprensión Inferencial en Inglés (Partes 6 y 7)",
                evidenceFromPdf = "En 2026-3 el colegio logró 7% en B1 y 3% en B+ (en 2021-4 había alcanzado 14% en B1).",
                friendlyExplanation = "Las Partes 6 y 7 definen quién alcanza B1 y B+. Evalúan intención del autor, inferencias y distinción entre palabras de significado cercano.",
                stepByStepActions = listOf(
                    "Articular con el área de Lectura Crítica estrategias de identificación de idea principal e intención del autor en artículos cortos en inglés."
                ),
                targetIndicator = "Superar el 15% de estudiantes en niveles B1 y B+ combinados."
            )
        )
    )

    val allAreasAnalytics: List<AreaFullAnalytics> = listOf(
        lecturaAnalytics,
        matematicasAnalytics,
        socialesAnalytics,
        cienciasAnalytics,
        inglesAnalytics
    )

    fun getAreaAnalytics(areaId: AreaId): AreaFullAnalytics {
        return allAreasAnalytics.first { it.areaId == areaId }
    }
}
