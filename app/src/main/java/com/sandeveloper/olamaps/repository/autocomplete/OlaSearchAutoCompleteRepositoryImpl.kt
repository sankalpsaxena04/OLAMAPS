package com.sandeveloper.olamaps.repository.autocomplete


import com.mapbox.mapboxsdk.geometry.LatLng
import com.ola.maps.navigation.v5.model.route.RouteInfoData
import com.sandeveloper.olamaps.model.response.autocompletesearch.OlaSearchAutoCompleteResponse
import com.sandeveloper.olamaps.remote.AutoCompleteApi
import com.sandeveloper.olamaps.remote.OlaRouteApiService
import com.sandeveloper.olamaps.util.NetworkResult
import org.json.JSONObject

class OlaSearchAutoCompleteRepositoryImpl(
    private val api: AutoCompleteApi,
    private var olaRouteApi: OlaRouteApiService
) :
    OlaSearchAutoCompleteRepository {
    override suspend fun getSearchAutoComplete(
        location: String,
        radius: Int,
        strictBounds: Boolean,
        apiKey: String,
        input: String
    ): NetworkResult<OlaSearchAutoCompleteResponse> {
        return try {
            NetworkResult.Success(
                api.getAutoCompleteSearchResult(
                    location = location,
                    radius = radius,
                    strictBounds = strictBounds,
                    search = input,
                    apiKey = apiKey
                )
            )
        } catch (e: Exception) {
            NetworkResult.Error(errorMsg = e.message.toString())
        }
    }

    override suspend fun getRouteInfo(
        originLatLng: LatLng,
        destinationLatLng: LatLng
    ): NetworkResult<RouteInfoData> = try {
        val queryMap = mapOf(
            "origin" to "${originLatLng.latitude},${originLatLng.longitude}",
            "destination" to "${destinationLatLng.latitude},${destinationLatLng.longitude}",
            "alternatives" to "false",
            "steps" to "true",
            "overview" to "full",
            "language" to "en",
            "traffic_metadata" to "false",
        )

        val response = olaRouteApi.getRouteInfo(queryMap)

        if (response.isSuccessful) {
            NetworkResult.Success(response.body()!!)
        } else {
            response.errorBody()!!.charStream().use { reader ->
                val jsonObj = JSONObject(reader.readText())
                throw Exception(jsonObj.toString())
            }

        }
    } catch (e: Exception) {
        NetworkResult.Error(errorMsg = e.message.toString())
    }

}
