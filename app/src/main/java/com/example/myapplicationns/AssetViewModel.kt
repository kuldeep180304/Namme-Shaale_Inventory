package com.example.myapplicationns
 
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
 
class AssetViewModel : ViewModel() {
 
    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets 
 
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
 
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
 
    init {
        loadAssets()
    }
 
    fun loadAssets() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.d("AssetViewModel", "Attempting to load assets...")
                val result = AssetRepository.getAllAssets()
                if (result.isSuccess) {
                    val data = result.getOrDefault(emptyList())
                    Log.d("AssetViewModel", "Loaded ${data.size} assets")
                    _assets.value = data
                } else {
                    val error = result.exceptionOrNull()?.message ?: "Unknown error"
                    Log.e("AssetViewModel", "Load failed: $error")
                    _errorMessage.value = "Database Error: $error"
                }
            } catch (e: Exception) {
                Log.e("AssetViewModel", "Exception: ${e.message}")
                _errorMessage.value = "Unexpected Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
 
    fun addAsset(asset: Asset, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = AssetRepository.addAsset(asset)
                if (result.isSuccess) {
                    loadAssets()
                    onSuccess()
                } else {
                    _errorMessage.value = "Failed to add: ${result.exceptionOrNull()?.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error adding asset: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
 
    fun updateCondition(assetId: String, newCondition: String) {
        viewModelScope.launch {
            val result = AssetRepository.updateAssetCondition(assetId, newCondition)
            if (result.isSuccess) {
                loadAssets()
            } else {
                _errorMessage.value = "Update failed: ${result.exceptionOrNull()?.message}"
            }
        }
    }
 
    fun deleteAsset(assetId: String) {
        viewModelScope.launch {
            val result = AssetRepository.deleteAsset(assetId)
            if (result.isSuccess) {
                loadAssets()
            } else {
                _errorMessage.value = "Delete failed: ${result.exceptionOrNull()?.message}"
            }
        }
    }
 
    fun countByCondition(condition: String): Int {
        return _assets.value.count { it.condition == condition }
    }
 
    fun clearError() {
        _errorMessage.value = null
    }
}
