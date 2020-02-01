package com.e.notesapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.notesapp.R
import kotlinx.android.synthetic.main.activity_add_note.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TITLE = "com.e.notesapp.view.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.e.notesapp.view.EXTRA_DESCRIPTION"
        const val EXTRA_TIME = "com.e.notesapp.view.EXTRA_TIME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        save_note.setOnClickListener {
            saveNote()
        }
    }



    private fun saveNote() {
        if (edit_text_title.text.toString().trim().isBlank() || edit_text_description.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
            return
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)

        val data = Intent().apply {
            putExtra(EXTRA_TITLE, edit_text_title.text.toString())
            putExtra(EXTRA_DESCRIPTION, edit_text_description.text.toString())
            putExtra(EXTRA_TIME, formatted)
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
