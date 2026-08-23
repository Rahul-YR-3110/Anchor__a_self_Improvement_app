package com.example.anchor.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anchor.data.local.entities.JournalEntity
import com.example.anchor.data.local.entities.Mood
import com.example.anchor.data.repository.AnchorRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class JournalViewModel(private val repository: AnchorRepository) : ViewModel() {

    /**
     * Holds the list of journal entries. 
     * We convert the Flow from the repository into a StateFlow so Compose can observe it.
     */
    val journalUiState: StateFlow<JournalUiState> =
        repository.getAllJournalsStream().map { JournalUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = JournalUiState()
            )

    fun saveJournalEntry(notes: String, mood: Mood) {
        viewModelScope.launch {
            repository.insertJournal(
                JournalEntity(
                    notes = notes,
                    mood = mood,
                    timestamp = LocalDateTime.now()
                )
            )
        }
    }

    fun deleteJournalEntry(journal: JournalEntity) {
        viewModelScope.launch {
            repository.deleteJournal(journal)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class JournalUiState(val journalList: List<JournalEntity> = listOf())
