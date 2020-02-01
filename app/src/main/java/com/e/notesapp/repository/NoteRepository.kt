package com.e.notesapp.repository

import android.app.Application
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import com.e.notesapp.repository.db.Note
import com.e.notesapp.repository.db.NoteDao
import com.e.notesapp.repository.db.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val application: Application) {


    private var noteDao: NoteDao? = null
    private var allNotes: LiveData<List<Note>>? = null

    init {
        noteDao = NoteDatabase.getDatabase(application.applicationContext).noteDao()
        allNotes = noteDao?.getAllNotes()
    }

    suspend fun insert(note: Note) {
        withContext(Dispatchers.IO){
            if (Looper.getMainLooper().getThread() == Thread.currentThread()){
                Log.d("Print", "Main Thread")
            } else {
                Log.d("Print", "Background Thread")
            }
            noteDao?.insert(note)
        }
    }

    fun getAllNotes(): LiveData<List<Note>>?{
        return allNotes
    }

}