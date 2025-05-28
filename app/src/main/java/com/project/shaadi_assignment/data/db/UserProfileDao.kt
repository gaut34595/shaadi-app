package com.project.shaadi_assignment.data.db

import androidx.room.*
import com.project.shaadi_assignment.data.model.UserProfile
import com.project.shaadi_assignment.data.model.MatchStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profiles")
    fun getAllProfiles(): Flow<List<UserProfile>>

    @Query("SELECT * FROM user_profiles WHERE status = :status")
    fun getProfilesByStatus(status: MatchStatus): Flow<List<UserProfile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfiles(profiles: List<UserProfile>)

    @Update
    suspend fun updateProfile(profile: UserProfile)

    @Query("UPDATE user_profiles SET status = :status WHERE id = :profileId")
    suspend fun updateProfileStatus(profileId: String, status: MatchStatus)

    @Query("DELETE FROM user_profiles")
    suspend fun deleteAllProfiles()
} 