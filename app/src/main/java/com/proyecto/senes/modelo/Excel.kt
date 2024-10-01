package com.proyecto.senes.modelo

data class ExcelParticipante(
    val nombres: String? = null,
    val apellidos: String? = null,
    val edadp: String? = null,
    val sexo: String? = null,
    val id: String? = null
)
data class ExcelPuntucacion(
    val id_participante: String? = null,
    val chair_Stand_Test: String? = null,
    val arm_Curl_Text: String? = null,
    val chair_Sit_and_Reach_Test: String? = null,
    val back_Scratch_Test: String? = null,
    val foot_Up_and_Go_Test: String? = null,
    val minute_step_test: String? = null
)
data class ExcelResultado(
    val resultado_Chair_Stand_Test: String? = null,
    val resultado_Arm_Curl_Text: String? = null,
    val resultado_Chair_Sit_and_Reach_Test: String? = null,
    val resultado_Back_Scratch_Test: String? = null,
    val resultado_Foot_Up_and_Go_Test: String? = null,
    val resultado_minute_step_test: String? = null
)