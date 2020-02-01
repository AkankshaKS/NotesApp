package com.e.notesapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.e.notesapp.R
import kotlinx.android.synthetic.main.activity_view_note.*
import kotlinx.android.synthetic.main.list_item_note.text_view_description
import kotlinx.android.synthetic.main.list_item_note.text_view_title

class ViewNoteActivity : AppCompatActivity() {

    var title: String? = null
    var description: String? = null
    var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)

        val intent = intent
        if(intent!= null){
            title = intent.getStringExtra("NoteTitle")
            description = intent.getStringExtra("NoteDescription")
            date = intent.getStringExtra("NoteDate")
            setUpViews()
        }


    }

    private fun setUpViews() {
        text_view_title?.text = title
        text_view_description?.text = description
        text_view_date?.text = date

    }

}
