package com.prmto.entities

@kotlinx.serialization.Serializable
data class ToDoDraft(
    val title: String,
    val done: Boolean
)