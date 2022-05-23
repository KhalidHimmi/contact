package com.example.gestiondecontact;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class mydb extends SQLiteOpenHelper {
    public mydb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE contact(nom TEXT,numTel TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table contact");
        onCreate(sqLiteDatabase);
    }
    public void ajout(String nom,String tele)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",nom);
        cv.put("numTele",tele);
        db.insert("contact",null,cv);
        db.close();

    }
    @SuppressLint("Range")
    public ArrayList<String> affiche()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM contat",null);
        ArrayList<String> a = new ArrayList<String>();
        cur.moveToFirst();
        while (!cur.isAfterLast())
        {
            String nom,tele;
            nom = cur.getString(cur.getColumnIndex("nom"));
            tele = cur.getString(cur.getColumnIndex("numTele"));
            a.add(nom + "|" + tele);
            cur.moveToNext();
        }
        db.close();
        return a;
    }
}
