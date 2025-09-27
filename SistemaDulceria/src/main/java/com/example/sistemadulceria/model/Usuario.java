package com.example.sistemadulceria.model;

public class Usuario
{
    private int idUsuario;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String email;
    private String contrasena;

    public Usuario()
    {

    }
    public Usuario(String nombre, String apellidoPat, String apellidoMat, String email, String contrasena)
    {
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.email = email;
        this.contrasena = contrasena;
    }

    //Get y Set para IdUsuario
    public int getIdUsuario(){return idUsuario;}
    public void setIdUsuario(int idUsuario){this.idUsuario=idUsuario;}

    //Get y Set para Nombre
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre=nombre;}

    //Get y Set para Apellido Paterno
    public String getApellidoPat(){return apellidoPat;}
    public void setApellidoPat(String apellidoPat){this.apellidoPat=apellidoPat;}

    //Get y Set para Apellido Materno
    public String getApellidoMat(){return apellidoMat;}
    public void setApellidoMat(String apellidoMat){this.apellidoMat=apellidoMat;}

    //Get y Set para email
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    //Get y Set para contrasena
    public String getContrasena(){return contrasena;}
    public void setContrasena(String contrasena){this.contrasena=contrasena;}

}