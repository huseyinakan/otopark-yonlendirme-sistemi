package com.info.otoparksistemi.objects;

public class PersonelListesi {

    private int personel_id;
    private int personel_sifre;

    public PersonelListesi() {
    }

    public PersonelListesi(int personel_id, int personel_sifre) {
        this.personel_id = personel_id;
        this.personel_sifre = personel_sifre;
    }

    public int personel_id() {
        return personel_id;
    }

    public void personel_Id(int personel_id) {
        this.personel_id = personel_id;
    }

    public int personel_sifre() {
        return personel_sifre;
    }

    public void personel_sifre(int personel_sifre) {
        this.personel_sifre = personel_sifre;
    }
}
