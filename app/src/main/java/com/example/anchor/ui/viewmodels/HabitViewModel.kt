package com.example.anchor.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anchor.data.local.entities.HabitEntity
import com.example.anchor.data.local.entities.HabitLogEntity
import com.example.anchor.data.repository.AnchorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalCoroutinesApi::class)
class HabitViewModel(private val repository: AnchorRepository) : ViewModel() {

    /**
     * Combine habits with their logs to calculate streaks
     */
    val habitUiState: StateFlow<HabitUiState> = repository.getAllHabitsStream()
        .flatMapLatest { habits ->
            if (habits.isEmpty()) return@flatMapLatest flowOf(HabitUiState())
            
            // For each habit, get its logs and create a HabitItem
            val flows = habits.map { habit ->
                repository.getHabitLogsStream(habit.id).map { logs ->
                    HabitItem(
                        habit = habit,
                        streak = calculateStreak(logs),
                        isCompletedToday = isCompletedToday(logs)
                    )
                }
            }
            
            combine(flows) { items ->
                HabitUiState(habitItems = items.toList())
            }
        }
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

    fun toggleHabitCompletion(habitId: String, completed: Boolean) {
        viewModelScope.launch {
            if (completed) {
                repository.completeHabit(habitId, LocalDate.now())
            } else {
                repository.uncompleteHabit(habitId, LocalDate.now())
            }
        }
    }

    private fun calculateStreak(logs: List<HabitLogEntity>): Int {
        if (logs.isEmpty()) return 0
        
        val sortedDates = logs.map { 
            Instant.ofEpochMilli(it.date).atZone(ZoneId.systemDefault()).toLocalDate()
        }.distinct().sortedDescending()

        var streak = 0
        var currentDate = LocalDate.now()
        
        // If not completed today, check if it was completed yesterday to continue streak
        if (sortedDates.firstOrNull() != currentDate) {
            currentDate = currentDate.minusDays(1)
        }

        for (date in sortedDates) {
            if (date == currentDate) {
                streak++
                currentDate = currentDate.minusDays(1)
            } else if (date.isBefore(currentDate)) {
                break
            }
        }
        return streak
    }

    private fun isCompletedToday(logs: List<HabitLogEntity>): Boolean {
        val today = LocalDate.now()
        return logs.any { 
            Instant.ofEpochMilli(it.date).atZone(ZoneId.systemDefault()).toLocalDate() == today
        }
    }
}

data class HabitItem(
    val habit: HabitEntity,
    val streak: Int,
    val isCompletedToday: Boolean
)

data class HabitUiState(
    val habitItems: List<HabitItem> = listOf()
)
