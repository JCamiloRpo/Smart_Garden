package com.example.smartgarden.ui.entities;

import android.content.ContentValues;

public class Sesion {
    private long usuarioID;

    public Sesion(long usuarioID){ this.usuarioID = usuarioID; }

    public long getUsuarioID() { return usuarioID; }

    /**
     * Este metodo auxiliar es utilizado para realizar
     * una interpretacion del objeto como un ContentValues
     * @return representacion del objeto en ContentValue
     */
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("UsuarioID", usuarioID);
        return values;
    }
}
