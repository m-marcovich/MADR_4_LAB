package com.example.madr_4_lab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class AddNoteActivity extends AppCompatActivity {

    private EditText edtNoteName;
    private EditText edtNoteContent;
    private Button btnSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtNoteName = findViewById(R.id.NameNote);
        edtNoteContent = findViewById(R.id.ContentNote);
        btnSaveNote = findViewById(R.id.btnAddNote);

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String noteName = edtNoteName.getText().toString();
        String noteContent = edtNoteContent.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("noteName", noteName);
        resultIntent.putExtra("noteContent", noteContent);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}