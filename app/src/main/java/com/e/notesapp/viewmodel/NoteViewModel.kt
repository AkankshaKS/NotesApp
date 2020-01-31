package com.e.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.notesapp.repository.NoteRepository
import com.e.notesapp.repository.db.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepository? = null
    private var allNotes: LiveData<List<Note>>? = null

    init {
        repository = NoteRepository(application)
        allNotes = repository?.getAllNotes()
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            repository?.insert(note)
        }
    }




    fun getAllNotes(): LiveData<List<Note>>? {
        return allNotes
    }
}