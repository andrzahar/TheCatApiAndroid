package com.example.thecatapi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val api = CatApi()

    private val _catsData = mutableStateOf<List<CatData>>(listOf())
    val catsData: State<List<CatData>> = _catsData

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    private val _isLoad = mutableStateOf(false)
    val isLoad: State<Boolean> = _isLoad

    fun load(num: String) {
        _isLoad.value = true
        viewModelScope.launch {
            val response = api.load(num)
            _isLoad.value = false
            when (response) {
                is Response.Success -> _catsData.value = response.data!!
                is Response.Error -> _error.value = response.message!!
            }
        }
    }
}