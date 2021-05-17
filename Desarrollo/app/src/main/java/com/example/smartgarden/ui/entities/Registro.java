package com.example.smartgarden.ui.entities;

import android.content.ContentValues;

public class Registro {
    private long ID;
    private String fecha;
    private int tem_suelo, tem_aire, hum_suelo, hum_aire, lux;

    public Registro(long ID, String fecha, int tem_suelo, int tem_aire, int hum_suelo, int hum_aire, int lux) {
        this.ID = ID;
        this.fecha = fecha;
        this.tem_suelo = tem_suelo;
        this.tem_aire = tem_aire;
        this.hum_suelo = hum_suelo;
        this.hum_aire = hum_aire;
        this.lux = lux;
    }

    public long getID() { return ID; }

    public String getFecha() { return fecha; }

    public int getTemSuelo() { return tem_suelo; }

    public int getTemAire() { return tem_aire; }

    public int getHumSuelo() { return hum_suelo; }

    public int getHumAire() { return hum_aire; }

    public int getLux() { return lux; }

    /**
     * Este metodo auxiliar es utilizado para realizar
     * una interpretacion del objeto como un ContentValues
     * @return representacion del objeto en ContentValue
     */
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("ID", ID);
        values.put("Fecha", fecha);
        values.put("Temperatura_Suelo", tem_suelo);
        values.put("Temperatura_Aire", tem_aire);
        values.put("Humedad_Suelo", hum_suelo);
        values.put("Humedad_Aire", hum_aire);
        values.put("Luminosidad", lux);
        return values;
    }
}
