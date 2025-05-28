package com.project.shaadi_assignment.data.repository

import com.project.shaadi_assignment.data.db.UserProfileDao
import com.project.shaadi_assignment.data.model.UserProfile
import com.project.shaadi_assignment.data.model.MatchStatus
import com.project.shaadi_assignment.data.network.RandomUserApi
import kotlinx.coroutines.flow.Flow

class UserProfileRepository(
    private val userProfileDao: UserProfileDao,
    private val randomUserApi: RandomUserApi
) {
    fun getAllProfiles(): Flow<List<UserProfile>> = userProfileDao.getAllProfiles()

    fun getProfilesByStatus(status: MatchStatus): Flow<List<UserProfile>> =
        userProfileDao.getProfilesByStatus(status)

    suspend fun fetchAndSaveProfiles() {
        try {
            val response = randomUserApi.getUsers()
            val profiles = response.results.map { result ->
                UserProfile(
                    id = result.login.uuid,
                    name = "${result.name.first} ${result.name.last}",
                    age = (20..50).random(), // RandomUser API doesn't provide age
                    gender = result.gender,
                    location = "${result.location.city}, ${result.location.state}",
                    email = result.email,
                    phone = result.phone,
                    pictureUrl = result.picture.large
                )
            }
            userProfileDao.insertProfiles(profiles)
        } catch (e: Exception) {
            // Handle error
            throw e
        }
    }

    suspend fun updateProfileStatus(profileId: String, status: MatchStatus) {
        userProfileDao.updateProfileStatus(profileId, status)
    }
} 