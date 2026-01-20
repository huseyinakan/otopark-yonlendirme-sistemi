package com.info.otoparksistemi.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.info.otoparksistemi.objects.EngelliAlan;
import com.info.otoparksistemi.objects.NormalAlan;
import com.info.otoparksistemi.objects.PersonelAlan;

import java.util.ArrayList;

public class EngelliAlandao {
    public ArrayList<EngelliAlan> tumEngelliAlan(AlanVeritabaniYardimcisi vt) {
        ArrayList<EngelliAlan> engelliAlanArrayListl = new ArrayList<>();

        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM engelli_alan", null);
        while (c.moveToNext()) {
            @SuppressLint("Range") EngelliAlan engelliAlan = new EngelliAlan(c.getString(c.getColumnIndex("alan_id")), c.getString(c.getColumnIndex("alan_durum")));
            engelliAlanArrayListl.add(engelliAlan);
        }
        return engelliAlanArrayListl;
    }
    public void engelliAlanGuncelle(AlanVeritabaniYardimcisi vt, String alan_id, String alan_durum) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("alan_durum", alan_durum);

        dbx.update("engelli_alan", degerler, "alan_id=?", new String[]{String.valueOf(alan_id)});
        dbx.close();
    }
}