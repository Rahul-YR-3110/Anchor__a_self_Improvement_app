package com.example.anchor.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anchor.data.local.entities.HabitEntity
import com.example.anchor.data.repository.AnchorRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HabitViewModel(private val repository: AnchorRepository) : ViewModel() {

    val habitUiState: StateFlow<HabitUiState> = repository.getAllHabitsStream()
        .map { habits -> HabitUiState(habits = habits) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HabitUiState()
        )

    fun addHabit(title: String) {
        viewModelScope.launch {
            repository.insertHabit(HabitEntity(title = title))
        }
    }

    fun deleteHabit(habit: HabitEntity) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }
    fun incrementStreak(habitId: String) {
        viewModelScope.launch {
            repository.incrementStreak(habitId)
        }
    }
}

data class HabitUiState(
    val habits: List<HabitEntity> = listOf()
)
