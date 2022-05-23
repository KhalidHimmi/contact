package com.example.gestiondecontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class list_contact extends AppCompatActivity {

    ListView listv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        mydb db = new mydb(this,"contact",null,1);
        listv = findViewById(R.id.list);
        btn = findViewById(R.id.af_contat);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> a = db.affiche();
                ArrayAdapter<String> ad = new ArrayAdapter<String>(list_contact.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,a);
                listv.setAdapter(ad);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add){
            Intent i =  new Intent(this, add_contact.class);
            startActivity(i);
        }
        if (id == R.id.home){
            Intent i =  new Intent(this, MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}