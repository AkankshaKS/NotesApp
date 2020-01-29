package com.e.notesapp.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.e.notesapp.repository.db.Note
import com.e.notesapp.repository.db.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    private val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun insert(note: Note) {
        InsertNoteAsyncTask(
            noteDao
        ).execute(note)
    }

    private class InsertNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {

        override fun doInBackground(vararg note: Note) {
            noteDao.insert(note[0])
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

}