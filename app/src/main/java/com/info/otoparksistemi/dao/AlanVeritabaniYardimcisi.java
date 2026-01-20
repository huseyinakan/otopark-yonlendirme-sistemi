package com.info.otoparksistemi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AlanVeritabaniYardimcisi extends SQLiteOpenHelper {

    public AlanVeritabaniYardimcisi(@Nullable Context context) {

        super(context, "otoparksistemi.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS \"personel_alan\" (\n" +
                "\t\"alan_id\"\tTEXT,\n" +
                "\t\"alan_durum\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"alan_id\")\n" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"engelli_alan\" (\n" +
                "\t\"alan_id\"\tTEXT,\n" +
                "\t\"alan_durum\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"alan_id\")\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"normal_alan\" (\n" +
                "\t\"alan_id\"\tTEXT,\n" +
                "\t\"alan_durum\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"alan_id\")\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"personeller\" (\n" +
                "\t\"personel_id\"\tINTEGER,\n" +
                "\t\"personel_sifre\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"personel_id\")\n" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personel_alan");
        db.execSQL("DROP TABLE IF EXISTS normal_alan");
        db.execSQL("DROP TABLE IF EXISTS engelli_alan");
        db.execSQL("DROP TABLE IF EXISTS personeller");
        onCreate(db);
    }
}
