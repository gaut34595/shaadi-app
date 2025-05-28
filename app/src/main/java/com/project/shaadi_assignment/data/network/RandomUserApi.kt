package com.project.shaadi_assignment.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/")
    suspend fun getUsers(
        @Query("results") results: Int = 10
    ): RandomUserResponse
}

data class RandomUserResponse(
    val results: List<RandomUserResult>,
    val info: RandomUserInfo
)

data class RandomUserResult(
    val gender: String,
    val name: RandomUserName,
    val location: RandomUserLocation,
    val email: String,
    val phone: String,
    val picture: RandomUserPicture,
    val login: RandomUserLogin
)

data class RandomUserName(
    val title: String,
    val first: String,
    val last: String
)

data class RandomUserLocation(
    val city: String,
    val state: String,
    val country: String
)

data class RandomUserPicture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class RandomUserLogin(
    val uuid: String
)

data class RandomUserInfo(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
) 