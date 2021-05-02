package com.example.smartgarden.ui.entities;

import android.content.ContentValues;

public class Sesion {
    private long usuarioID;
    private String usuario;

    public Sesion(long usuarioID, String usuario){ this.usuarioID = usuarioID; this.usuario = usuario; }

    public long getUsuarioID() { return usuarioID; }

    public String getUsuario() { return usuario; }

    /**
     * Este metodo auxiliar es utilizado para realizar
     * una interpretacion del objeto como un ContentValues
     * @return representacion del objeto en ContentValue
     */
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("UsuarioID", usuarioID);
        values.put("Usuario", usuario);
        return values;
    }
}
