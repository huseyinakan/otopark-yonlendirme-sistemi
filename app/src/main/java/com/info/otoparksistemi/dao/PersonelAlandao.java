package com.info.otoparksistemi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.info.otoparksistemi.objects.PersonelAlan;

import java.util.ArrayList;

public class PersonelAlandao {

    public ArrayList<PersonelAlan> tumPersonelAlan(AlanVeritabaniYardimcisi vt) {
        ArrayList<PersonelAlan> personelAlanArrayList = new ArrayList<>();

        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM personel_alan", null);
        while (c.moveToNext()) {
            @SuppressLint("Range") PersonelAlan personelalan = new PersonelAlan(c.getString(c.getColumnIndex("alan_id")), c.getString(c.getColumnIndex("alan_durum")));
            personelAlanArrayList.add(personelalan);

        }
        return personelAlanArrayList;
    }

    public void personelAlanGuncelle(AlanVeritabaniYardimcisi vt, String alan_id, String alan_durum) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("alan_durum", alan_durum);

        dbx.update("personel_alan", degerler, "alan_id=?", new String[]{String.valueOf(alan_id)});
        dbx.close();
    }
}