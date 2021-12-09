package com.example.ejemplofirebase;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;

    public Usuario(){

    }

    public Usuario(String nombre,String apellido,String correo,String clave){
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setClave(clave);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
