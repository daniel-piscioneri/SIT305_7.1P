package com.example.a71p;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {
    EditText Name, Phone, Desc, Date, Location;
    RadioGroup posttype;
    Button Save;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Name = findViewById(R.id.editTextText);
        Phone = findViewById(R.id.editTextText2);
        Desc = findViewById(R.id.editTextText3);
        Date = findViewById(R.id.editTextText4);
        Location = findViewById(R.id.editTextText5);
        posttype = findViewById(R.id.posttype);
        Save = findViewById(R.id.button3);

        myDB = new MyDatabaseHelper(this);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SelectedId = posttype.getCheckedRadioButtonId();
                RadioButton selectedbutton = findViewById(SelectedId);
                String postYpe = selectedbutton.getText().toString();

                boolean isInserted = myDB.insertItem(postYpe, Name.getText().toString(), Phone.getText().toString(), Desc.getText().toString(), Date.getText().toString(), Location.getText().toString());

                if (isInserted){
                    Toast.makeText(CreateActivity.this, "Data Created", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(CreateActivity.this, "Data unable to be Created", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
