package com.izv.sqlite;

import java.io.Serializable;

public class Partido implements Serializable, Comparable <Partido>{

    private long id;
    private int idjugador;
    private String contrincante;
    private int valoracion;

    public Partido() {
    }

    public Partido(long id, int idjugador, String contrincante, int valoracion) {
        this.id = id;
        this.idjugador = idjugador;
        this.contrincante = contrincante;
        this.valoracion = valoracion;
    }

    public Partido(int idjugador, String contrincante, int valoracion) {
        this.id=0;
        this.idjugador = idjugador;
        this.contrincante = contrincante;
        this.valoracion = valoracion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(int idjugador) {
        this.idjugador = idjugador;
    }

    public String getContrincante() {
        return contrincante;
    }

    public void setContrincante(String contrincante) {
        this.contrincante = contrincante;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }


    @Override
    public int compareTo(Partido partido) {
        if (this.idjugador < partido.getIdjugador()){
            return this.idjugador;
        }else{
            return partido.getIdjugador();
        }
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", idjugador=" + idjugador +
                ", contrincante='" + contrincante + '\'' +
                ", valoracion='" + valoracion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Partido.class != o.getClass()) return false;

        Partido partido = (Partido) o;

        if (id != partido.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
