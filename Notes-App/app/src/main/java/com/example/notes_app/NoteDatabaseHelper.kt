package com.example.notes_app

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "notes_database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "notes"
        private const val COL_ID = "id"
        private const val COL_TITLE = "title"
        private const val COL_DESCRIPTION = "description"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_TITLE TEXT, $COL_DESCRIPTION TEXT)"
        p0?.execSQL(createTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(dropTable)
        onCreate(p0)
    }

    // Insert data into the database

    fun insertData(note: Note){
        val db = this.writableDatabase
        val query = "INSERT INTO $TABLE_NAME ($COL_TITLE, $COL_DESCRIPTION) VALUES ('${note.title}', '${note.description}')"
        db.execSQL(query)
        db.close()
    }

    // Get all notes from the database

    fun getAllNotes(): List<Note>{
        val notes = mutableListOf<Note>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

       while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITLE))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPTION))
            val note = Note(id, title, description)
            notes.add(note)
        }
        cursor.close()
        db.close()
        return notes
    }

    // Update a note in the database

    fun updateData(note: Note){
        val db = this.writableDatabase
        val query = "UPDATE $TABLE_NAME SET $COL_TITLE = '${note.title}', $COL_DESCRIPTION = '${note.description}' WHERE $COL_ID = ${note.id}"
        db.execSQL(query)
        db.close()
    }

    // Get a note by ID

    fun getNoteById(id: Int): Note?{
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_ID = $id"
        val cursor = db.rawQuery(query, null)
        var note: Note? = null
        if (cursor.moveToFirst()){
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITLE))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRIPTION))
            note = Note (id, title, description)
        }
        cursor.close()
        db.close()
        return note

    }

}