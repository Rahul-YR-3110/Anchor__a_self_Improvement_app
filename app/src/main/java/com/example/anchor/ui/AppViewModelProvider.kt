package com.example.anchor.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.anchor.AnchorApplication
import com.example.anchor.ui.viewmodels.HabitViewModel
import com.example.anchor.ui.viewmodels.JournalViewModel
import com.example.anchor.ui.viewmodels.WaterViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Anchor app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for JournalViewModel
        initializer {
            JournalViewModel(anchorApplication().container.anchorRepository)
        }
        // Initializer for HabitViewModel
        initializer {
            HabitViewModel(anchorApplication().container.anchorRepository)
        }
        // Initializer for WaterViewModel
        initializer {
            WaterViewModel(anchorApplication().container.anchorRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [AnchorApplication].
 */
fun CreationExtras.anchorApplication(): AnchorApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as AnchorApplication)
