package com.example.sistemadulceria.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Producto implements Comparable<Producto> {

    //Atributos que usaremos

    private int idProducto;
    private String nombre;
    private String descripcion;
    private String productos_url;
    private Double precio;
    private int stock;
    private TipoDulce tipoDulce; //Relacion con tabla tipo_dulce
    //Constructor vacio
    public Producto()
    {

    }

    //Constructor con los atributos
    public Producto(String nombre, String descripcion, String productos_url, int stock, Double precio, TipoDulce tipoDulce)
    {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.productos_url=productos_url;
        this.stock=stock;
        this.precio=precio;
        this.tipoDulce=tipoDulce;
    }

    //Get y set para idProducto
    public int getIdProducto() {return idProducto;}
    public void setIdProducto(int idProducto){this.idProducto=idProducto;}

    //Get y Set para nombre
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}

    //Get y Set para descripcion
    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion=descripcion;}

    //Get y set para productos_url
    public String getProductos_url(){return productos_url;}
    public void setProductos_url(String productos_url){this.productos_url=productos_url;}

    //Get y set para stock
    public int getStock(){return stock;}
    public void setStock(int stock){this.stock=stock;}

    //Get y set para precio
    public Double getPrecio(){return precio;}
    public void setPrecio(Double precio){this.precio=precio;}

    //Get y set para prioridad
    public TipoDulce getTipoDulce(){return tipoDulce;}
    public void setTipoDulce(TipoDulce tipoDulce){this.tipoDulce=tipoDulce;}

    //Implementacion de comparable
    @Override
    public int compareTo(Producto otro)
    {
        //Ordenar por prioridad
        return Integer.compare(this.tipoDulce.getPrioridad(), otro.tipoDulce.getPrioridad());
    }
}

