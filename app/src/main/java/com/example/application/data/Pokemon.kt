package com.example.application.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val height: Int,
    val weight: Int,
)
