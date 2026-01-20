package com.info.otoparksistemi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.info.otoparksistemi.objects.NormalAlan;

import java.util.ArrayList;

public class NormalAlandao {

    public void personelAlanEkle(AlanVeritabaniYardimcisi vt, String alan_id, String alan_durum) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();

        degerler.put("alan_id", alan_id);
        degerler.put("alan_durum", alan_durum);

        dbx.insertOrThrow("personel_alan", null, degerler);
        dbx.close();
    }

    public ArrayList<NormalAlan> tumNormalAlan(AlanVeritabaniYardimcisi vt) {
        ArrayList<NormalAlan> normalAlanArrayList = new ArrayList<>();

        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM normal_alan", null);
        while (c.moveToNext()) {
            @SuppressLint("Range") NormalAlan normalAlan = new NormalAlan(c.getString(c.getColumnIndex("alan_id")), c.getString(c.getColumnIndex("alan_durum")));
            normalAlanArrayList.add(normalAlan);
        }
        return normalAlanArrayList;
    }
    public void normalAlanGuncelle(AlanVeritabaniYardimcisi vt, String alan_id, String alan_durum) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("alan_durum", alan_durum);

        dbx.update("normal_alan", degerler, "alan_id=?", new String[]{String.valueOf(alan_id)});
        dbx.close();
    }
}