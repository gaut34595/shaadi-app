package com.project.shaadi_assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.shaadi_assignment.data.model.UserProfile
import com.project.shaadi_assignment.data.model.MatchStatus
import com.project.shaadi_assignment.data.repository.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val repository: UserProfileRepository
) : ViewModel() {

    private val _profiles = MutableStateFlow<List<UserProfile>>(emptyList())
    val profiles: StateFlow<List<UserProfile>> = _profiles

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadProfiles()
    }

    private fun loadProfiles() {
        viewModelScope.launch {
            repository.getAllProfiles()
                .catch { e ->
                    _error.value = e.message
                }
                .collect { profiles ->
                    _profiles.value = profiles
                }
        }
    }

    fun fetchNewProfiles() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                repository.fetchAndSaveProfiles()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateProfileStatus(profileId: String, status: MatchStatus) {
        viewModelScope.launch {
            try {
                repository.updateProfileStatus(profileId, status)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
} 