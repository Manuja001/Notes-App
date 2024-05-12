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

    // Add the following functions to the NoteDatabaseHelper class

    fun insertData(note: Note){
        val db = this.writableDatabase
        val query = "INSERT INTO $TABLE_NAME ($COL_TITLE, $COL_DESCRIPTION) VALUES ('${note.title}', '${note.description}')"
        db.execSQL(query)
        db.close()
    }
//
//    fun readData() : MutableList<Note>{
//        val list : MutableList<Note> = ArrayList()
//        val db = this.readableDatabase
//        val query = "SELECT * FROM $TABLE_NAME"
//        val result = db.rawQuery(query, null)
//        if(result.moveToFirst()){
//            do{
//                val note = Note()
//                note.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
//                note.title = result.getString(result.getColumnIndex(COL_TITLE))
//                note.description = result.getString(result.getColumnIndex(COL_DESCRIPTION))
//                list.add(note)
//            }while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//        return list
//    }
//
//    fun updateData(note: Note){
//        val db = this.writableDatabase
//        val query = "UPDATE $TABLE_NAME SET $COL_TITLE = '${note.title}', $COL_DESCRIPTION = '${note.description}' WHERE $COL_ID = ${note.id}"
//        db.execSQL(query)
//    }
//
//    fun deleteData(note: Note){
//        val db = this.writableDatabase
//        val query = "DELETE FROM $TABLE_NAME WHERE $COL_ID = ${note.id}"
//        db.execSQL(query)
//    }

}