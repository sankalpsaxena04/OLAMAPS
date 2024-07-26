package com.appscrip.olamapdemo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeveloper.olamaps.remote.RetrofitClient
import com.sandeveloper.olamaps.repository.OlaMapOlaMapRepositoryImpl
import com.sandeveloper.olamaps.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsViewModel : ViewModel() {

    private val repository = OlaMapOlaMapRepositoryImpl(RetrofitClient.apiService)

    // creating bool variable since viewModel.getAccessToken() can be called multiple times
    private var isApiCalled = false

    var accessToken = ""
    fun getAccessToken(clientId: String, clientSecret: String, onSuccess: () -> Unit) {
        if (isApiCalled) return

        isApiCalled = true

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAccessToken(clientId, clientSecret)

            if (result is NetworkResult.Success) {
                Log.d("ola", "Access Token: ${result.data.accessToken}")
                accessToken = result.data.accessToken

                withContext(Dispatchers.Main) {
                    onSuccess()
                }

            } else if (result is NetworkResult.Error) {
                Log.d("ola", "ERROR: ${result.errorMsg}")
            }
        }
    }
}