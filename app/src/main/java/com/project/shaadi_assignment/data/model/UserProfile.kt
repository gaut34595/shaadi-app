package com.project.shaadi_assignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey
    val id: String,
    val name: String,
    val age: Int,
    val gender: String,
    val location: String,
    val email: String,
    val phone: String,
    val pictureUrl: String,
    val status: MatchStatus = MatchStatus.PENDING
)

enum class MatchStatus {
    PENDING,
    ACCEPTED,
    DECLINED
} 