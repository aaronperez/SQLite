package com.izv.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "futbol.sqlite";
    public static final int DATABASE_VERSION = 2;

    public Ayudante (Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "create table " + Contrato.TablaPartido.TABLA +
                " (" + Contrato.TablaPartido._ID +
                " integer primary key autoincrement , " +
                Contrato.TablaPartido.IDJUGADOR + " integer, " +
                Contrato.TablaPartido.CONTRINCANTE + " text, " +
                Contrato.TablaPartido.VALORACION + " integer," +
                " unique ("+Contrato.TablaPartido.IDJUGADOR+","+Contrato.TablaPartido.CONTRINCANTE+"))";
        Log.v("sql",sql);
        db.execSQL(sql);

        sql = "create table "+ Contrato.TablaJugadorNew.TABLA +
                " (" + Contrato.TablaJugadorNew._ID +
                " integer primary key autoincrement, " +
                Contrato.TablaJugadorNew.NOMBRE + " text unique, " +
                Contrato.TablaJugadorNew.TELEFONO + " text, " +
                Contrato.TablaJugadorNew.FNAC + " text)";
        Log.v("sql",sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Pasamos a la versión 2
        // 1º Tablas de respaldo
        String sql = "create table respaldo"+ Contrato.TablaJugador.TABLA +
                " (" + Contrato.TablaJugador._ID +
                " integer primary key, " +
                Contrato.TablaJugador.NOMBRE + " text, " +
                Contrato.TablaJugador.TELEFONO + " text unique, " +
                Contrato.TablaJugador.VALORACION+ " integer, "+
                Contrato.TablaJugador.FNAC + " text)";
        Log.v("sql",sql);
        db.execSQL(sql);
        // 2º Volcamos los datos
        sql= "insert into respaldo"+Contrato.TablaJugador.TABLA+" select * from "+Contrato.TablaJugador.TABLA;
        Log.v("sql",sql);
        db.execSQL(sql);
        // 3º Borramos las tablas originales
        sql = "drop table "+Contrato.TablaJugador.TABLA;
        Log.v("sql",sql);
        db.execSQL(sql);
        // 4º Creamos las tablas nuevas
        onCreate(db);
        // 5º Copiamos los datos de las tablas de respaldo en las tablas nuevas
        sql= "insert into "+Contrato.TablaJugadorNew.TABLA+" select "
                +Contrato.TablaJugador._ID+","+Contrato.TablaJugador.NOMBRE+","+Contrato.TablaJugador.TELEFONO+","+
                Contrato.TablaJugador.FNAC +" from respaldo"+Contrato.TablaJugador.TABLA;
        Log.v("sql",sql);
        db.execSQL(sql);

        sql = "insert into "+Contrato.TablaPartido.TABLA+"("+ Contrato.TablaPartido.IDJUGADOR +","+Contrato.TablaPartido.VALORACION+") select "+Contrato.TablaJugador._ID+","
                +Contrato.TablaJugador.VALORACION+" from respaldo"+Contrato.TablaJugador.TABLA;
        Log.v("sql",sql);
        db.execSQL(sql);
        sql = "update "+Contrato.TablaPartido.TABLA+" set "+Contrato.TablaPartido.CONTRINCANTE+"='inicial'";
        Log.v("sql",sql);
        db.execSQL(sql);
        // 6º Borramos las tablas de respaldo.
        sql="drop table respaldo"+Contrato.TablaJugador.TABLA;
        Log.v("sql",sql);
        db.execSQL(sql);
    }

}