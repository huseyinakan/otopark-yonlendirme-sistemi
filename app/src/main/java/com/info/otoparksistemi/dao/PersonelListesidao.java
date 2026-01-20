package com.info.otoparksistemi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.info.otoparksistemi.objects.PersonelListesi;

import java.util.ArrayList;

public class PersonelListesidao {

    public ArrayList<PersonelListesi> tumPersonelleriGetir(AlanVeritabaniYardimcisi vt) {
        ArrayList<PersonelListesi> personelArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM personeller", null);

        while (cursor.moveToNext()) {

            @SuppressLint("Range") PersonelListesi personelListesi = new PersonelListesi(cursor.getInt(cursor.getColumnIndex("personel_id")), cursor.getInt(cursor.getColumnIndex("personel_sifre")));
            personelArrayList.add(personelListesi);

        }
        return personelArrayList;
    }

    public void personelEkle(AlanVeritabaniYardimcisi vt, int personel_id, int personel_sifre) {
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("personel_id", personel_id);
        values.put("personel_sifre", personel_sifre);

        db.insertOrThrow("personeller", null, values);
        db.close();
    }

    /*public void personelAlanGuncelle(AlanVeritabaniYardimcisi vt, String alan_id, String alan_durum){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("alan_durum", alan_durum);

        dbx.update("personel_alan",degerler,"alan_id=?",new String[]{String.valueOf(alan_id)});
        dbx.close();
    }
    */
}
