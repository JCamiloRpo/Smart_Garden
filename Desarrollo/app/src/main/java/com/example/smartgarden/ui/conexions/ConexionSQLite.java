package com.example.smartgarden.ui.conexions;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartgarden.ui.entities.Usuario;

public class ConexionSQLite {
    public static final String TABLE_USUARIO = "Usuario";

    private SQLiteHelper conn; //conexion a BD.
    private SQLiteDatabase db;

    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        conn = new SQLiteHelper(context,name,factory,version);
    }

    public long insert(Usuario usuario){
        db = conn.getWritableDatabase();
        long result = db.insert(TABLE_USUARIO, null, usuario.toContentValues());
        db.close();
        return result;
    }

    public String[][] read(String table, String where){
        String[][] result;
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM "+table+" WHERE "+where;
        Cursor c= db.rawQuery(query, null);
        if (c.moveToFirst()) {
            result = new String[c.getCount()][c.getColumnCount()];
            int i=0;
            do {
                for(int j=0; j<c.getColumnCount(); j++)
                    result[i][j] = c.getString(j);
                i++;
            } while (c.moveToNext());
        }
        else
            result=new String[0][0];
        db.close();
        return result;
    }

    private class SQLiteHelper extends SQLiteOpenHelper {

        /* Como no existen el tipo de dato boolean, se utilizará el Integer donde 0 es false y 1 es true
        Como tampoco existe el tipo de dato Datetime, se utilizará el TEXT con el formato DD/MM/AAAA
         */
        String crear_tbUsuario = "CREATE TABLE "+TABLE_USUARIO+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre TEXT NOT NULL, " +
                "Usuario TEXT NOT NULL, " +
                "Password TEXT NOT NULL, " +
                "Correo TEXT NOT NULL)";

        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*Se ejecuta automaticamente para crear la BD si no existe*/
            db.execSQL(crear_tbUsuario);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*Se ejecuta cuando la version de la BD cambia, por lo que se define la migracion de la estructura*/
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_USUARIO);

            db.execSQL(crear_tbUsuario);
        }
    }
}
