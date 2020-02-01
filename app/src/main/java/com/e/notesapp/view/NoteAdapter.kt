package com.e.notesapp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.notesapp.R
import com.e.notesapp.repository.db.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var notes: List<Note> = ArrayList()
    var context : Context?= null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_note, parent, false)
        return NoteHolder(itemView)
    }



    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]

        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ViewNoteActivity::class.java)
            intent.putExtra("NoteTitle", currentNote.title)
            intent.putExtra("NoteDescription",currentNote.description)
            intent.putExtra("NoteDate",currentNote.date)
            context?.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

     inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        var textViewDescription: TextView = itemView.findViewById(R.id.text_view_description)

    }

}