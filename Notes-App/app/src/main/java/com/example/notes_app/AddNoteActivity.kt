package com.example.notes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes_app.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        binding.SaveButton.setOnClickListener {
            val title = binding.NoteTitle.text.toString()
            val description = binding.Content.text.toString()

           val note = Note(0, title, description)
            db.insertData(note)

            finish()
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        }


    }
}