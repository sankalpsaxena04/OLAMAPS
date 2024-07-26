package com.sandeveloper.olamaps.repository

import com.sandeveloper.olamaps.model.response.OlaMapAccessTokenResponse
import com.sandeveloper.olamaps.remote.ApiServiceWithoutToken
import com.sandeveloper.olamaps.util.NetworkResult

class OlaMapOlaMapRepositoryImpl(
    private val apiService: ApiServiceWithoutToken
) : OlaMapRepository {

    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String
    ): NetworkResult<OlaMapAccessTokenResponse> = try {
        NetworkResult.Success(apiService.getAccessToken(clientId=clientId, clientSecret = clientSecret))
    } catch (e: Exception) {
        NetworkResult.Error(errorMsg = e.message.toString())
    }
}