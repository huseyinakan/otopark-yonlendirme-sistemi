package com.info.otoparksistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.info.otoparksistemi.dao.AlanVeritabaniYardimcisi;
import com.info.otoparksistemi.dao.DatabaseCopyHelper;
import com.info.otoparksistemi.dao.NormalAlandao;
import com.info.otoparksistemi.dao.PersonelAlandao;
import com.info.otoparksistemi.dao.PersonelListesidao;
import com.info.otoparksistemi.objects.NormalAlan;
import com.info.otoparksistemi.objects.PersonelAlan;
import com.info.otoparksistemi.objects.PersonelListesi;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BaslangicSayfa extends AppCompatActivity {
    private PersonelAlandao pA = new PersonelAlandao();
private AlanVeritabaniYardimcisi vt = new AlanVeritabaniYardimcisi(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sayfa_baslangic);
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        boolean firstRun = preferences.getBoolean("firstRun", true);


        if (firstRun) {
            kopyala();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();
        }

        Button iletisimButon = findViewById(R.id.iletisimButon);
        iletisimButon.setOnClickListener(v -> {
            Intent intent = new Intent(BaslangicSayfa.this,Iletisim.class);
            startActivity(intent);
        });

        ArrayList<PersonelListesi> gelenPersonel = new PersonelListesidao().tumPersonelleriGetir(vt);

        for (PersonelListesi pl : gelenPersonel) {
            Log.e(String.valueOf(pl.personel_id()), pl.personel_id() + " " + pl.personel_sifre());
        }
        Button normal = findViewById(R.id.butonNormal); // Butonun ID'sini buraya yazın
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaslangicSayfa.this, OtoparkHarita.class); // Hedef aktivitesinin adını buraya yazın
                int girisKod = 1; // requestCode olarak 1'i kullanıyoruz
                intent.putExtra("girisKod", girisKod);
                startActivityForResult(intent, girisKod);
            }
        });
        Button engelli = findViewById(R.id.butonEngelli); // Butonun ID'sini buraya yazın
        engelli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaslangicSayfa.this, OtoparkHarita.class);
                int girisKod = 0; // requestCode olarak 1'i kullanıyoruz
                intent.putExtra("girisKod", girisKod);
                startActivityForResult(intent, girisKod);
            }
        });
        Button personel = findViewById(R.id.butonPersonel); // Butonun ID'sini buraya yazın
        personel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaslangicSayfa.this, PersonelGiris.class); // Hedef aktivitesinin adını buraya yazın
                startActivity(intent);
            }
        });
    }

    public void kopyala() {
        {
            DatabaseCopyHelper helper = new DatabaseCopyHelper(this);
            try {
                helper.createDataBase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            helper.openDataBase();
        }
    }

}
