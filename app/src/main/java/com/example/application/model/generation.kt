package com.example.application.model

data class GenerationResponse(
    val results: List<GenerationResults>
)

data class GenerationResults(
    val name: String,
    val url: String
)