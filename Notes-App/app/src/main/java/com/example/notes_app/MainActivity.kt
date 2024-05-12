package com.example.notes_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var db: NoteDatabaseHelper
private lateinit var adapter: NotesAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        adapter = NotesAdapter(db.getAllNotes(), this)

        binding.NotesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.NotesRecyclerView.adapter = adapter



        binding.AddButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.refreshData(db.getAllNotes())
    }
}