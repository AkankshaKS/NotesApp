package com.e.notesapp.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(

    var title: String,
    var description: String,
    val date : String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}