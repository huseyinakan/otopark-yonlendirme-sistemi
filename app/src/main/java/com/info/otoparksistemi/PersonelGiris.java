package com.info.otoparksistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.info.otoparksistemi.dao.AlanVeritabaniYardimcisi;
import com.info.otoparksistemi.dao.NormalAlandao;
import com.info.otoparksistemi.dao.PersonelListesidao;
import com.info.otoparksistemi.objects.NormalAlan;
import com.info.otoparksistemi.objects.PersonelListesi;

import java.util.ArrayList;

public class PersonelGiris extends AppCompatActivity {
private TextView gitKayit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_giris);
        AlanVeritabaniYardimcisi vt = new AlanVeritabaniYardimcisi(this);
        EditText girisIdText = findViewById(R.id.girisIdText);
        EditText girisSifreText = findViewById(R.id.girisSifreText);

        Intent intent = getIntent();
        if (intent != null) {
            int engelliGirisKod = intent.getIntExtra("kayitKod", 0);
            if (engelliGirisKod == 3){
                View rootView = findViewById(android.R.id.content);
                Snackbar.make(rootView, "Kayıt Başarılı. Lütfen Giriş Yapınız..", Snackbar.LENGTH_LONG).show();
            }
        }
        Button butonGiris = findViewById(R.id.butonGiris);
        butonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = girisIdText.getText().toString();
                String sifreText = girisSifreText.getText().toString();

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
                            // Uyarı ver - Şifre en az 4 karakter olmalı
                            View rootView = findViewById(android.R.id.content);
                            Snackbar.make(rootView, "Şifre 4 karakter olmalı!", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(), "Şifre 4 karakter olmalı", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                            ArrayList<PersonelListesi> gelenPersonel = new PersonelListesidao().tumPersonelleriGetir(vt);
                            int q = 0;
                            for (PersonelListesi pl : gelenPersonel) {
                                if (pl.personel_id() == id && pl.personel_sifre() == sifre) {
                                    Intent intent = new Intent(PersonelGiris.this, OtoparkHarita.class);
                                    int girisKod = 2; // requestCode olarak 1'i kullanıyoruz
                                    intent.putExtra("girisKod", girisKod);
                                    startActivityForResult(intent, girisKod);
                                    startActivity(intent);
                                    q=1;
                                }
                            }
                            if (q==0){
                                View rootView = findViewById(android.R.id.content);
                                Snackbar.make(rootView, "ID veya Şifre Hatalı. Tekrar Deneyiniz!", Snackbar.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(),"ID veya Şifre Hatalı. Tekrar Deneyiniz!",Toast.LENGTH_LONG).show();
                            }
                    }
                }
                catch (NumberFormatException e){
                    if(idText.equals("") || sifreText.equals("")){
                        View rootView = findViewById(android.R.id.content);
                        Snackbar.make(rootView, "Tüm alanları doldurunuz!", Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), "Tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        View rootView = findViewById(android.R.id.content);
                        Snackbar.make(rootView, "ID ve Şifre sayısal bir değer olmalı", Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), "ID ve Şifre sayısal bir değer olmalı", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        gitKayit = findViewById(R.id.gitKaydol);


        gitKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonelGiris.this, PersonelKayit.class); // He-def aktivitesinin adını buraya yazın
                startActivity(intent);
            }
        });



        Button anasayfa = findViewById(R.id.butonAnasayfaDon); // Butonun ID'sini buraya yazın
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonelGiris.this, BaslangicSayfa.class); // Hedef aktivitesinin adını buraya yazın
                startActivity(intent);
            }
        });
    }
}