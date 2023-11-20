package com.example.madr_4_lab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {

    private ListView listViewDeleteNote;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> noteList = new ArrayList<>();
    private static final int ADD_NOTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        listViewDeleteNote = findViewById(R.id.listViewDeleteNote);

        noteList.add("Note 1: Content 1");
        noteList.add("Note 2: Content 2");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteList);
        listViewDeleteNote.setAdapter(adapter);

        listViewDeleteNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeSelectedNote(position);
            }
        });
    }


    private void removeSelectedNote(final int position) {
        showDeleteConfirmationDialog(position);
    }

    private void saveNotesToExternalStorage() {
        try {
            File externalFile = new File(getExternalFilesDir(null), "NotesFile");
            FileWriter writer = new FileWriter(externalFile);

            for (String note : noteList) {
                writer.append(note).append("\n");
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteList.remove(position);
                        adapter.notifyDataSetChanged();
                        saveNotesToExternalStorage();
                        Toast.makeText(DeleteNoteActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DeleteNoteActivity.this, "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }
}
