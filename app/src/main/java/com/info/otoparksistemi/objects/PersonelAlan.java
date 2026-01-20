package com.info.otoparksistemi.objects;

public class PersonelAlan {
    private String alan_id;
    private String alan_durum;

    public PersonelAlan() {
    }

    public PersonelAlan(String alan_id, String alan_durum) {
        this.alan_id = alan_id;
        this.alan_durum = alan_durum;
    }

    public String alan_id() {
        return alan_id;
    }

    public void alan_id(String alan_id) {
        this.alan_id = alan_id;
    }

    public String alan_durum() {
        return alan_durum;
    }

    public void alan_durum(String alan_durum) {
        this.alan_durum = alan_durum;
    }
}
