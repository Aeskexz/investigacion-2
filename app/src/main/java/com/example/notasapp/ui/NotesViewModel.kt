package com.example.notasapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notasapp.data.Note
import com.example.notasapp.data.NoteDao
import kotlinx.coroutines.launch

class NotesViewModel(private val noteDao: NoteDao) : ViewModel() {
    val notes = noteDao.observeNotes()

    fun save(note: Note) {
        viewModelScope.launch { noteDao.save(note) }
    }

    fun delete(note: Note) {
        viewModelScope.launch { noteDao.delete(note) }
    }
}

class NotesViewModelFactory(private val noteDao: NoteDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
        return NotesViewModel(noteDao) as T
    }
}
