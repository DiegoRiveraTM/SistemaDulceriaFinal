package com.example.sistemadulceria.model;

public class TipoDulce {
    private int idDulce;
    private String nombre;
    private int prioridad;

    public TipoDulce()
    {

    }

    //Constructor con id y nombre ya que no se necesita la prioridad al momento de guardar el producto en la db ya que ya
    //Está definido
    public TipoDulce(int idDulce, String nombre)
    {
        this.idDulce= idDulce;
        this.nombre=nombre;
    }

    //Constructor con los 3 atributos
    public TipoDulce(int idDulce, String nombre, int prioridad)
    {
        this.idDulce= idDulce;
        this.nombre=nombre;
        this.prioridad=prioridad;
    }

    //Get y Set para idDulce
    public int getIdDulce(){return idDulce;}
    public void setIdDulce(int idDulce){this.idDulce=idDulce;}

    //Get y Set para nombre
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}

    //Get y Set para prioridad
    public int getPrioridad(){return prioridad;}
    public void setPrioridad(int prioridad){this.prioridad=prioridad;}


}
