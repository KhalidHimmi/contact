package com.example.gestiondecontact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button btndis;
    ImageView imgdis;
    Uri display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndis = findViewById(R.id.takepic);
        imgdis = findViewById(R.id.dispic);

       btndis.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                   if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                           || checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
                   {
                       String[] tablepermession = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
                       requestPermissions(tablepermession,100);
                   }
                   else
                       openCamera();
               }
           }
       });


    }

    private void openCamera() {
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE,"img");
        cv.put(MediaStore.Images.Media.DESCRIPTION,"Des img");
        display = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cv);

        Intent ci = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ci.putExtra(MediaStore.EXTRA_OUTPUT,display);
        startActivityForResult(ci,101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
            else
                Toast.makeText(this, "Permession", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101)
        {
            imgdis.setImageURI(display);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.aj){
            Intent i =  new Intent(this, add_contact.class);
            startActivity(i);
        }
        if (id == R.id.listofitem){
            Toast.makeText(this, "List", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}