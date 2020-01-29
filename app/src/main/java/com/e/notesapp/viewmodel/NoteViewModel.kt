package com.e.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.notesapp.repository.NoteRepository
import com.e.notesapp.repository.db.Note

class NoteViewModel(private var repository: NoteRepository) : ViewModel() {

    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun insert(note: Note) {
        repository.insert(note)
    }


    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}