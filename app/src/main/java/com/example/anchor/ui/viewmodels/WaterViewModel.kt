package com.example.anchor.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anchor.data.local.entities.WaterIntakeEntity
import com.example.anchor.data.repository.AnchorRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

class WaterViewModel(private val repository: AnchorRepository) : ViewModel() {

    private val today = LocalDate.now()
    private val todayStartMillis = today.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val waterUiState: StateFlow<WaterUiState> = repository.getWaterIntakeStream(today)
        .map { entity ->
            if (entity == null) {
                WaterUiState(currentGlasses = 0)
            } else {
                WaterUiState(
                    currentGlasses = entity.amountMl / 250,
                    totalGoalGlasses = entity.goalMl / 250
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WaterUiState()
        )

    fun incrementWater() {
        viewModelScope.launch {
            val currentState = waterUiState.value
            val newAmount = (currentState.currentGlasses + 1) * 250
            repository.upsertWaterIntake(
                WaterIntakeEntity(
                    date = todayStartMillis,
                    amountMl = newAmount,
                    goalMl = currentState.totalGoalGlasses * 250
                )
            )
        }
    }
}

data class WaterUiState(
    val currentGlasses: Int = 0,
    val totalGoalGlasses: Int = 8
)
