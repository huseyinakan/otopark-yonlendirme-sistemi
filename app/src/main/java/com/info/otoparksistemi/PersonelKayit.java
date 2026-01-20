package com.info.otoparksistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.info.otoparksistemi.dao.AlanVeritabaniYardimcisi;
import com.info.otoparksistemi.dao.PersonelListesidao;
import com.info.otoparksistemi.objects.PersonelListesi;

public class PersonelKayit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_kayit);
        EditText kayitIdText = findViewById(R.id.kayitIdText);
        EditText kayitIdSifre = findViewById(R.id.kayitSifreText);
        Button butonKayit = findViewById(R.id.butonKayit);

        butonKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = kayitIdText.getText().toString();
                String sifreText = kayitIdSifre.getText().toString();

                try {
                    int id = Integer.parseInt(idText);
                    int sifre = Integer.parseInt(sifreText);
                    if (idText.length() != 8 || sifreText.length() != 4) {
                        if (idText.length() != 8) {
                            // Uyarı ver - ID en az 8 karakter olmalı
                            View rootView = findViewById(android.R.id.content);
                            Snackbar.make(rootView, "ID 8 karakter olmalı!", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(), "ID 8 karakter olmalı", Toast.LENGTH_SHORT).show();
                        }
                        if (sifreText.length() != 4) {
                            // Uyarı ver - Şifre 4 karakter olmalı
                            View rootView = findViewById(android.R.id.content);
                            Snackbar.make(rootView, "Şifre 4 karakter olmalı!", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(), "Şifre 4 karakter olmalı", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Veritabanına ekleme işlemi
                        AlanVeritabaniYardimcisi vt = new AlanVeritabaniYardimcisi(getApplicationContext());
                        PersonelListesi yeniPersonel = new PersonelListesi(id, sifre);
                        PersonelListesidao personelListesiDao = new PersonelListesidao();
                        personelListesiDao.personelEkle(vt, yeniPersonel.personel_id(), yeniPersonel.personel_sifre());
                        vt.close();

                        // Başarılı ekleme uyarısı
                        Intent intent = new Intent(PersonelKayit.this, PersonelGiris.class); // He-def aktivitesinin adını buraya yazın

                        int kayitKod = 3; // requestCode olarak 3'i kullanıyoruz
                        intent.putExtra("kayitKod", kayitKod);
                        startActivityForResult(intent, kayitKod);
                        //Toast.makeText(getApplicationContext(), "KAYIT BAŞARILI. LÜTFEN GİRİŞ YAPINIZ..", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                } catch (NumberFormatException e) {
                    if(idText.equals("") || sifreText.equals("")){
                        View rootView = findViewById(android.R.id.content);
                        Snackbar.make(rootView, "Tüm alanları doldurunuz!", Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), "Tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Uyarı ver - ID sayısal bir değer olmalı
                        View rootView = findViewById(android.R.id.content);
                        Snackbar.make(rootView, "ID ve Şifre sayısal bir değer olmalı", Snackbar.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(), "ID ve Şifre sayısal bir değer olmalı", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button anasayfa = findViewById(R.id.donAnaSayfa); // Butonun ID'sini buraya yazın
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonelKayit.this, BaslangicSayfa.class); // Hedef aktivitesinin adını buraya yazın
                startActivity(intent);
            }
        });
    }
}