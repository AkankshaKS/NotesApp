package com.e.notesapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.notesapp.R
import com.e.notesapp.repository.db.Note
import com.e.notesapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Observer

class MainActivity : AppCompatActivity() {

    private var noteViewModel: NoteViewModel? = null
    private var adapter: NoteAdapter? = null
    private var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        recyclerView = findViewById(R.id.recycler_view)

        adapter = NoteAdapter()
        setupButtonAddNote()
        setupRecyclerView()

        noteViewModel?.getAllNotes()?.observe(this, androidx.lifecycle.Observer {
            notes -> applicationContext?.apply {
            notes.let {
                adapter?.setNotes(it)
            }
        }
      })


    }

    private fun setupButtonAddNote() {
        addnewform.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                ADD_NOTE_REQUEST
            )
        }
    }

    private fun setupRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newNote = Note(
                data.getStringExtra(AddNoteActivity.EXTRA_TITLE),
                data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            )
            noteViewModel?.insert(newNote)

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        private const val ADD_NOTE_REQUEST = 1
    }

}
