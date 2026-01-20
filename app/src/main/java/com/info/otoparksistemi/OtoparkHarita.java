package com.info.otoparksistemi;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.info.otoparksistemi.dao.AlanVeritabaniYardimcisi;
import com.info.otoparksistemi.dao.EngelliAlandao;
import com.info.otoparksistemi.dao.NormalAlandao;
import com.info.otoparksistemi.dao.PersonelAlandao;
import com.info.otoparksistemi.objects.EngelliAlan;
import com.info.otoparksistemi.objects.NormalAlan;
import com.info.otoparksistemi.objects.PersonelAlan;

import java.util.ArrayList;
import java.util.Random;

public class OtoparkHarita extends AppCompatActivity {

    private AlanVeritabaniYardimcisi vt;
    private PersonelAlandao pAdao;
    private NormalAlandao nAdao;
    private EngelliAlandao eAdao;
    private TextView textViewBosAlan, kapatNormalAlan1,kapatNormalAlan2,kapatNormalAlan3,kapatNormalAlan4,kapatNormalAlan5,kapatNormalAlan6, kapatPersonel, kapatEngelli, na1,nb,nc,nd,ne,nf;
    private ImageView p_a1_kirmizi,p_a2_kirmizi,p_a3_kirmizi,p_a4_kirmizi,p_a5_kirmizi, p_a1,p_a2,p_a3 , personelImage, engelliImage, ea,pa;
    private ImageView n_a1_kirmizi,n_a2_kirmizi,n_a3_kirmizi,n_a4_kirmizi,n_a5_kirmizi,n_a6_kirmizi,n_a7_kirmizi,
            n_a1,n_a2,n_a3,n_a4,n_a5,n_a6,n_a7,nb_1,nb_2,nb_3,nb_4,nb_5,nb_6,
            nb_1_kirmizi,nb_2_kirmizi,nb_3_kirmizi,nb_4_kirmizi,nb_5_kirmizi,nb_6_kirmizi
            ;
//    private String p_a1_alan,p_a2_alan,p_a3_alan,p_a4_alan,p_a5_alan = "bos";
String normalBosAlanlar = "";
    String personelBosAlanlar = "";
    String engelliBosAlanlar = "";

    int[] normalIds = {
            R.id.n_a1, R.id.n_a2, R.id.n_a3, R.id.n_a4, R.id.n_a5, R.id.n_a6, R.id.n_a7, R.id.nb_1, R.id.nb_2, R.id.nb_3, R.id.nb_4, R.id.nb_5, R.id.nb_6, R.id.nc_1, R.id.nc_2, R.id.nc_3, R.id.nc_4, R.id.nc_5, R.id.nc_6,
            R.id.nd_1, R.id.nd_2, R.id.nd_3, R.id.nd_4, R.id.nd_5, R.id.nd_6, R.id.ne_1, R.id.ne_2, R.id.ne_3, R.id.ne_4, R.id.ne_5, R.id.ne_6, R.id.nf_1, R.id.nf_2, R.id.nf_3, R.id.nf_4, R.id.nf_5, R.id.nf_6, R.id.nf_7, R.id.nf_8};
    int[] kirmiziIds = {R.id.n_a1_kirmizi, R.id.n_a2_kirmizi, R.id.n_a3_kirmizi, R.id.n_a4_kirmizi, R.id.n_a5_kirmizi, R.id.n_a6_kirmizi, R.id.n_a7_kirmizi, R.id.nb_1_kirmizi, R.id.nb_2_kirmizi, R.id.nb_3_kirmizi,R.id.nb_4_kirmizi, R.id.nb_5_kirmizi, R.id.nb_6_kirmizi,
            R.id.nc_1_kirmizi, R.id.nc_2_kirmizi, R.id.nc_3_kirmizi, R.id.nc_4_kirmizi, R.id.nc_5_kirmizi, R.id.nc_6_kirmizi, R.id.nd_1_kirmizi, R.id.nd_2_kirmizi, R.id.nd_3_kirmizi, R.id.nd_4_kirmizi, R.id.nd_5_kirmizi, R.id.nd_6_kirmizi,
            R.id.ne_1_kirmizi, R.id.ne_2_kirmizi, R.id.ne_3_kirmizi, R.id.ne_4_kirmizi, R.id.ne_5_kirmizi, R.id.ne_6_kirmizi, R.id.nf_1_kirmizi, R.id.nf_2_kirmizi, R.id.nf_3_kirmizi, R.id.nf_4_kirmizi, R.id.nf_5_kirmizi, R.id.nf_6_kirmizi, R.id.nf_7_kirmizi, R.id.nf_8_kirmizi};

    int[] personelIds = {R.id.p_a1, R.id.p_a2, R.id.p_a3, R.id.p_a4, R.id.p_a5, R.id.p_a6, R.id.p_a7, R.id.p_a8, R.id.p_a9};

    int[] personelKirmiziIds = {R.id.p_a1_kirmizi, R.id.p_a2_kirmizi, R.id.p_a3_kirmizi, R.id.p_a4_kirmizi, R.id.p_a5_kirmizi, R.id.p_a6_kirmizi, R.id.p_a7_kirmizi, R.id.p_a8_kirmizi, R.id.p_a9_kirmizi};
    int[] engelliIds = {R.id.ea_1, R.id.ea_2, R.id.ea_3, R.id.ea_4, R.id.ea_5, R.id.ea_6, R.id.ea_7};

    int[] engelliKirmiziIds = {R.id.ea_1_kirmizi, R.id.ea_2_kirmizi, R.id.ea_3_kirmizi, R.id.ea_4_kirmizi, R.id.ea_5_kirmizi, R.id.ea_6_kirmizi, R.id.ea_7_kirmizi};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otopark_harita);

        pAdao = new PersonelAlandao();
        nAdao = new NormalAlandao();
        eAdao = new EngelliAlandao();
        vt = new AlanVeritabaniYardimcisi(this);

        textViewBosAlan = findViewById(R.id.textBosAlanlar);
        kapatNormalAlan1 = findViewById(R.id.kapatNormalAlan1);
        kapatNormalAlan2 = findViewById(R.id.kapatNormalAlan2);
        kapatNormalAlan3 = findViewById(R.id.kapatNormalAlan3);
        kapatNormalAlan4 = findViewById(R.id.kapatNormalAlan4);
        kapatNormalAlan5 = findViewById(R.id.kapatNormalAlan5);
        kapatNormalAlan6 = findViewById(R.id.kapatNormalAlan6);
        kapatPersonel = findViewById(R.id.kapatPersonel);
        personelImage = findViewById(R.id.personelImage);
        kapatEngelli = findViewById(R.id.kapatEngelli);
        engelliImage = findViewById(R.id.engelliImage);
        na1 = findViewById(R.id.na1);
        nb = findViewById(R.id.nb);
        nc = findViewById(R.id.nc);
        nd = findViewById(R.id.nd);
        ne = findViewById(R.id.ne);
        nf = findViewById(R.id.nf);
        ea = findViewById(R.id.ea);
        pa = findViewById(R.id.pa);

        Button butonCikis = findViewById(R.id.butonCikis);
        butonCikis.setOnClickListener(v -> {
            Intent intent = new Intent(OtoparkHarita.this, PersonelGiris.class);
            startActivity(intent);
        });

        Button butonAnaSayfa = findViewById(R.id.otoparkToAna);
        butonAnaSayfa.setOnClickListener(v -> {
            Intent intent = new Intent(OtoparkHarita.this, BaslangicSayfa.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        if (intent != null) {
            int engelliGirisKod = intent.getIntExtra("girisKod", 0);
            if (engelliGirisKod == 0) {
                kapatNormalAlan1.setVisibility(View.VISIBLE);
                kapatNormalAlan2.setVisibility(View.VISIBLE);
                kapatNormalAlan3.setVisibility(View.VISIBLE);
                kapatNormalAlan4.setVisibility(View.VISIBLE);
                kapatNormalAlan5.setVisibility(View.VISIBLE);
                kapatNormalAlan6.setVisibility(View.VISIBLE);
                kapatPersonel.setVisibility(View.VISIBLE);
                personelImage.setVisibility(View.VISIBLE);
                kapatEngelli.setVisibility(View.INVISIBLE);
                engelliImage.setVisibility(View.INVISIBLE);
                ea.setVisibility(View.VISIBLE);
                pa.setVisibility(View.INVISIBLE);
                na1.setVisibility(View.INVISIBLE);
                nb.setVisibility(View.INVISIBLE);
                nc.setVisibility(View.INVISIBLE);
                nd.setVisibility(View.INVISIBLE);
                ne.setVisibility(View.INVISIBLE);
                nf.setVisibility(View.INVISIBLE);
                butonCikis.setVisibility(View.INVISIBLE);
                butonAnaSayfa.setVisibility(View.VISIBLE);
            }
        }
        if (intent != null) {
            int normalGirisKod = intent.getIntExtra("girisKod", 0);
            if (normalGirisKod == 1) {
                kapatNormalAlan1.setVisibility(View.INVISIBLE);
                kapatNormalAlan2.setVisibility(View.INVISIBLE);
                kapatNormalAlan3.setVisibility(View.INVISIBLE);
                kapatNormalAlan4.setVisibility(View.INVISIBLE);
                kapatNormalAlan5.setVisibility(View.INVISIBLE);
                kapatNormalAlan6.setVisibility(View.INVISIBLE);
                kapatPersonel.setVisibility(View.VISIBLE);
                personelImage.setVisibility(View.VISIBLE);
                kapatEngelli.setVisibility(View.VISIBLE);
                engelliImage.setVisibility(View.VISIBLE);
                ea.setVisibility(View.INVISIBLE);
                pa.setVisibility(View.INVISIBLE);
                na1.setVisibility(View.VISIBLE);
                nb.setVisibility(View.VISIBLE);
                nc.setVisibility(View.VISIBLE);
                nd.setVisibility(View.VISIBLE);
                ne.setVisibility(View.VISIBLE);
                nf.setVisibility(View.VISIBLE);
                butonCikis.setVisibility(View.INVISIBLE);
                butonAnaSayfa.setVisibility(View.VISIBLE);
            }
        }
        if (intent != null) {
            int personelGirisKod = intent.getIntExtra("girisKod", 0);
            if (personelGirisKod == 2) {
                View rootView = findViewById(android.R.id.content);
                Snackbar.make(rootView, "Giriş başarılı. Hoşgeldiniz..", Snackbar.LENGTH_LONG).show();

                kapatNormalAlan1.setVisibility(View.VISIBLE);
                kapatNormalAlan2.setVisibility(View.VISIBLE);
                kapatNormalAlan3.setVisibility(View.VISIBLE);
                kapatNormalAlan4.setVisibility(View.VISIBLE);
                kapatNormalAlan5.setVisibility(View.VISIBLE);
                kapatNormalAlan6.setVisibility(View.VISIBLE);
                kapatPersonel.setVisibility(View.INVISIBLE);
                personelImage.setVisibility(View.INVISIBLE);
                kapatEngelli.setVisibility(View.VISIBLE);
                engelliImage.setVisibility(View.VISIBLE);
                ea.setVisibility(View.INVISIBLE);
                pa.setVisibility(View.VISIBLE);
                na1.setVisibility(View.INVISIBLE);
                nb.setVisibility(View.INVISIBLE);
                nc.setVisibility(View.INVISIBLE);
                nd.setVisibility(View.INVISIBLE);
                ne.setVisibility(View.INVISIBLE);
                nf.setVisibility(View.INVISIBLE);
                butonAnaSayfa.setVisibility(View.INVISIBLE);
                butonCikis.setVisibility(View.VISIBLE);

            }
        }

        startUpdatingStrings(); // Stringleri güncellemek için fonksiyonu çağır
    }

    private void startUpdatingStrings() {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateStrings();
                handler.postDelayed(this, 120000); // Her 1 saniyede bir güncelleme yap
            }
        };
        handler.post(runnable);
    }

    private void updateStrings() {
        String[] possibilities = {"bos", "dolu"};
        ArrayList<NormalAlan> gelenNormalAlan = new NormalAlandao().tumNormalAlan(vt);
        ArrayList<PersonelAlan> gelenPersonelAlan = new PersonelAlandao().tumPersonelAlan(vt);
        ArrayList<EngelliAlan> gelenEngelliAlan = new EngelliAlandao().tumEngelliAlan(vt);

        Random random = new Random(); // Rastgele nesnesini döngü dışında oluşturuyoruz


        getIntent().getStringExtra("MESSAGE_KEY");
        for (NormalAlan n1 : gelenNormalAlan) {
            // Her döngüde yeni bir rastgele değer üretiyoruz
            String randomValueNormal = possibilities[random.nextInt(possibilities.length)];
            nAdao.normalAlanGuncelle(vt, n1.alan_id(), randomValueNormal);
        }

        for (PersonelAlan p1 : gelenPersonelAlan) {
            // Her döngüde yeni bir rastgele değer üretiyoruz
            String randomValuePersonel = possibilities[random.nextInt(possibilities.length)];
            pAdao.personelAlanGuncelle(vt, p1.alan_id(), randomValuePersonel);
        }

        for (EngelliAlan e1 : gelenEngelliAlan) {
            // Her döngüde yeni bir rastgele değer üretiyoruz
            String randomValueEngelli = possibilities[random.nextInt(possibilities.length)];
            eAdao.engelliAlanGuncelle(vt ,e1.alan_id() ,randomValueEngelli);
        }
        checkStrings(); // Her güncelleme sonrası kontrol et
    }


    private void checkStrings() {
        normalBosAlanlar = "";
        personelBosAlanlar = "";
        engelliBosAlanlar = "";


        ArrayList<PersonelAlan> gelenPersonelAlan = new PersonelAlandao().tumPersonelAlan(vt);
        ArrayList<NormalAlan> gelenNormalAlan = new NormalAlandao().tumNormalAlan(vt);
        ArrayList<EngelliAlan> gelenEngelliAlan = new EngelliAlandao().tumEngelliAlan(vt);


        String[] normalAlanlar = {"na1", "na2", "na3", "na4", "na5", "na6", "na7",
                "nb1", "nb2", "nb3", "nb4", "nb5", "nb6",
                "nc1", "nc2", "nc3", "nc4", "nc5", "nc6",
                "nd1", "nd2", "nd3", "nd4", "nd5", "nd6",
                "ne1", "ne2", "ne3", "ne4", "ne5", "ne6",
                "nf1", "nf2", "nf3", "nf4", "nf5", "nf6", "nf7", "nf8", "nf9"};
        boolean[] normalAlanlarBos = new boolean[normalAlanlar.length];

        for (NormalAlan n : gelenNormalAlan) {
            for (int i = 0; i < normalAlanlar.length; i++) {
                if (n.alan_id().equals(normalAlanlar[i]) && n.alan_durum().equals("bos")) {
                    normalAlanlarBos[i] = true;
                    findViewById(kirmiziIds[i]).setVisibility(View.INVISIBLE);
                    findViewById(normalIds[i]).setVisibility(View.VISIBLE);
                    normalBosAlanlar = normalBosAlanlar + normalAlanlar[i].toUpperCase() + " ";
                } else if (n.alan_id().equals(normalAlanlar[i]) && n.alan_durum().equals("dolu")) {
                    findViewById(kirmiziIds[i]).setVisibility(View.VISIBLE);
                    findViewById(normalIds[i]).setVisibility(View.INVISIBLE);
                }
            }
        }

        String[] personelAlanlar = {"pa1", "pa2", "pa3", "pa4", "pa5", "pa6", "pa7", "pa8", "pa9"};
        boolean[] personelAlanlarBos = new boolean[personelAlanlar.length];

        for (PersonelAlan p : gelenPersonelAlan) {
            for (int i = 0; i < personelAlanlar.length; i++) {
                if (p.alan_id().equals(personelAlanlar[i]) && p.alan_durum().equals("bos")) {
                    personelAlanlarBos[i] = true;
                    findViewById(personelIds[i]).setVisibility(View.VISIBLE);
                    findViewById(personelKirmiziIds[i]).setVisibility(View.INVISIBLE);
                    personelBosAlanlar = personelBosAlanlar + personelAlanlar[i].toUpperCase() + " ";
                } else if (p.alan_id().equals(personelAlanlar[i]) && p.alan_durum().equals("dolu")) {
                    findViewById(personelIds[i]).setVisibility(View.INVISIBLE);
                    findViewById(personelKirmiziIds[i]).setVisibility(View.VISIBLE);
                }
            }
        }

        String[] engelliAlanlar = {"ea1", "ea2", "ea3", "ea4", "ea5", "ea6", "ea7"};
        boolean[] engelliAlanlarBos = new boolean[engelliAlanlar.length];

        for (EngelliAlan e : gelenEngelliAlan) {
            for (int i = 0; i < engelliAlanlar.length; i++) {
                if (e.alan_id().equals(engelliAlanlar[i]) && e.alan_durum().equals("bos")) {
                    engelliAlanlarBos[i] = true;
                    findViewById(engelliIds[i]).setVisibility(View.VISIBLE);
                    findViewById(engelliKirmiziIds[i]).setVisibility(View.INVISIBLE);
                    engelliBosAlanlar = engelliBosAlanlar + engelliAlanlar[i].toUpperCase() + " ";
                } else if (e.alan_id().equals(engelliAlanlar[i]) && e.alan_durum().equals("dolu")) {
                    findViewById(engelliIds[i]).setVisibility(View.INVISIBLE);
                    findViewById(engelliKirmiziIds[i]).setVisibility(View.VISIBLE);
                }
            }
        }

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.getIntExtra("girisKod", 0) == 0) {
                if (engelliBosAlanlar != "") {
                    textViewBosAlan.setText(engelliBosAlanlar);
                    textViewBosAlan.setTextColor(Color.GREEN);
                } else {
                    textViewBosAlan.setText("Boş Alan Yok!");
                    textViewBosAlan.setTextColor(Color.RED);
                }
            }
        }

        if (intent != null) {
            if (intent.getIntExtra("girisKod", 0) == 1) {
                if (normalBosAlanlar != "") {
                    textViewBosAlan.setText(normalBosAlanlar);
                    textViewBosAlan.setTextColor(Color.GREEN);
                } else {
                    textViewBosAlan.setText("Boş Alan Yok!");
                    textViewBosAlan.setTextColor(Color.RED);
                }
            }
        }
        if (intent != null) {
            if (intent.getIntExtra("girisKod", 0) == 2) {
                if (personelBosAlanlar != "") {
                    textViewBosAlan.setText(personelBosAlanlar);
                    textViewBosAlan.setTextColor(Color.GREEN);
                } else {
                    textViewBosAlan.setText("Boş Alan Yok!");
                    textViewBosAlan.setTextColor(Color.RED);
                }
            }
        }


    }
}
