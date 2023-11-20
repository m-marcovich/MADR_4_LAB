package com.example.madr_4_lab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    Button switchToSecondActivity;
    Button switchToDeleteActivity;
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int DELETE_NOTE_REQUEST = 1;

    private void openDeleteNoteActivity(String selectedNoteName) {
        Intent intent = new Intent(MainActivity.this, DeleteNoteActivity.class);
        intent.putExtra("selectedNoteName", selectedNoteName);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToSecondActivity = findViewById(R.id.btnAddNotes);
        switchToDeleteActivity = findViewById(R.id.btnDeleteNotes);

        switchToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddNoteActivity.class), ADD_NOTE_REQUEST);
            }
        });
        switchToDeleteActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            startActivityForResult(new Intent(MainActivity.this, DeleteNoteActivity.class), DELETE_NOTE_REQUEST);

            }
        });

        setupListView();
    }
    private List<String> noteNamesList = new ArrayList<>();
    private List<String> noteContentsList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK && requestCode == DELETE_NOTE_REQUEST) {
            if (data != null) {
                String noteName = data.getStringExtra("noteName");
                String noteContent = data.getStringExtra("noteContent");

                noteNamesList.add(noteName);
                noteContentsList.add(noteContent);

                adapter.notifyDataSetChanged();
            }
        }
    }

    private void setupListView() {
        ListView listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteNamesList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNoteName = noteNamesList.get(position);
                String selectedNoteContent = noteContentsList.get(position);
                Intent displayNoteIntent = new Intent(MainActivity.this, DisplayNoteActivity.class);
                displayNoteIntent.putExtra("noteName", selectedNoteName);
                displayNoteIntent.putExtra("noteContent", selectedNoteContent);
                startActivity(displayNoteIntent);
            }
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, AddNoteActivity.class);
        startActivity(switchActivityIntent);
    }
    private void setupDeleteListView() {
        ListView listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteNamesList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNoteName = noteNamesList.get(position);

                openDeleteNoteActivity(selectedNoteName);
            }
        });
    }
}