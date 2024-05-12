package com.example.notes_app

data class Note(val id : Int, val title : String, val description : String) {
    companion object {
        var noteId = 0
    }
}
