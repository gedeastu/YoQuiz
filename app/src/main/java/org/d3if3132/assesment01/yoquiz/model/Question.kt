package org.d3if3132.assesment01.yoquiz.model

data class Question(
    val id: Int,
    val text: String,
    val image: Int,
    val answers: List<AnswerItem>,
    val correctAnswer: String
)
