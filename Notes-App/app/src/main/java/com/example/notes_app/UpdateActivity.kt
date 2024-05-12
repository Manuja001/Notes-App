package com.example.notes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes_app.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)


        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        if (note != null) {
            binding.UpdateNoteTitle.setText(note.title)
        }
        if (note != null) {
            binding.UpdateContent.setText(note.description)
        }

        binding.UpdateButton.setOnClickListener {
            val title = binding.UpdateNoteTitle.text.toString()
            val description = binding.UpdateContent.text.toString()

            val note = Note(noteId, title, description)
            db.updateData(note)

            finish()
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
        }
    }
}