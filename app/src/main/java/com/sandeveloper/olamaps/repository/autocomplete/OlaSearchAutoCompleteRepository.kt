package com.sandeveloper.olamaps.repository.autocomplete

import com.mapbox.mapboxsdk.geometry.LatLng
import com.ola.maps.navigation.v5.model.route.RouteInfoData
import com.sandeveloper.olamaps.model.response.autocompletesearch.OlaSearchAutoCompleteResponse
import com.sandeveloper.olamaps.util.NetworkResult

interface OlaSearchAutoCompleteRepository {
    suspend fun getSearchAutoComplete(
        location: String,
        radius: Int,
        strictBounds: Boolean,
        apiKey: String,
        input: String
    ): NetworkResult<OlaSearchAutoCompleteResponse>

    suspend fun getRouteInfo(
        originLatLng: LatLng,
        destinationLatLng: LatLng
    ): NetworkResult<RouteInfoData>
}