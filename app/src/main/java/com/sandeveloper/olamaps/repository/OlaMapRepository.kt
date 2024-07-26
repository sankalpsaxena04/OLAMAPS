package com.sandeveloper.olamaps.repository

import com.sandeveloper.olamaps.model.response.OlaMapAccessTokenResponse
import com.sandeveloper.olamaps.util.NetworkResult

interface OlaMapRepository {
    suspend fun getAccessToken(clientId: String, clientSecret: String): NetworkResult<OlaMapAccessTokenResponse>
}