package com.example.sistemadulceria.model;

public class Carrito {
    private int idCarrito;
    private int cantidad;
    private double total;
    private boolean activo;
    private int idProducto;
    private int idUsuario;

    public Carrito()
    {

    }

    public Carrito(int idCarrito, int cantidad, double total, boolean activo, int idProducto, int idUsuario)
    {
        this.idCarrito=idCarrito;
        this.cantidad=cantidad;
        this.total=total;
        this.activo=activo;
        this.idProducto=idProducto;
        this.idUsuario=idUsuario;
    }

    //Get y set para idCarrito
    public int getIdCarrito(){return idCarrito;}
    public void setIdCarrito(int idCarrito){this.idCarrito=idCarrito;}

    //Get y set para cantidad
    public int getCantidad(){return cantidad;}
    public void setCantidad(int cantidad){this.cantidad=cantidad;}

    //Get y set para total
    public double getTotal(){return total;}
    public void setTotal(double total){this.total=total;}

    //Get y set para activo
    public boolean getActivo(){return activo;}
    public void setActivo(boolean activo){this.activo=activo;}

    //Get y set para idProducto
    public int getIdProducto(){return idProducto;}
    public void setIdProducto(int idProducto){this.idProducto=idProducto;}

    //Get y set para idUsuario
    public int getIdUsuario(){return idUsuario;}
    public void setIdUsuario(int idUsuario){this.idUsuario=idUsuario;}


}
