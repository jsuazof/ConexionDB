package com.example.model;

public class Empleado {
    private int id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private float salario;

    public Empleado() {
    }

    public Empleado(String email, int id, String nombre, String primerApellido, float salario, String segundoApellido) {
        this.email = email;
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.salario = salario;
        this.segundoApellido = segundoApellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido="
                + segundoApellido + ", email=" + email + ", salario=" + salario + "]";
    }




}
