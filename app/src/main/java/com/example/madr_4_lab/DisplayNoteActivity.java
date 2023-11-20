package com.example.madr_4_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class DisplayNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note);

        Intent intent = getIntent();
        String noteName = intent.getStringExtra("noteName");
        String noteContent = intent.getStringExtra("noteContent");

        TextView NameView = findViewById(R.id.nameView);
        TextView ContentView = findViewById(R.id.contentView);

        NameView.setText(noteName);
        ContentView.setText(noteContent);
    }
}