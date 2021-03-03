package com.udacity.notepad.crud

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity

import com.udacity.notepad.R
import com.udacity.notepad.data.DataStore
import com.udacity.notepad.data.DataStore.execute
import com.udacity.notepad.data.DataStore.notes
import com.udacity.notepad.data.Note
import com.udacity.notepad.databinding.ActivityCreateBinding

import java.util.*

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_accept, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_accept) {
            save()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        execute {
            val note = updateNote()
            notes.insert(note)
        }
    }

    private fun updateNote(): Note {
        val note = Note()
        note.text = binding.editText.text.toString()
        note.updatedAt = Date()
        return note
    }

    companion object {
        operator fun get(context: Context?): Intent {
            return Intent(context, CreateActivity::class.java)
        }
    }
}