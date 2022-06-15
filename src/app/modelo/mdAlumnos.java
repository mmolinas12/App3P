/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

/**
 *
 * @author mmoli
 */
public class mdAlumnos {
    private int alm_id;
    private String alm_nombre;
    private String alm_apellido;
    private String alm_materia;
    private Double alm_calificacion;
    private String alm_estado;

    public String getAlm_estado() {
        return alm_estado;
    }

    public void setAlm_estado(String alm_estado) {
        this.alm_estado = alm_estado;
    }

    public int getAlm_id() {
        return alm_id;
    }

    public void setAlm_id(int alm_id) {
        this.alm_id = alm_id;
    }

    public String getAlm_nombre() {
        return alm_nombre;
    }

    public void setAlm_nombre(String alm_nombre) {
        this.alm_nombre = alm_nombre;
    }

    public String getAlm_apellido() {
        return alm_apellido;
    }

    public void setAlm_apellido(String alm_apellido) {
        this.alm_apellido = alm_apellido;
    }

    public String getAlm_materia() {
        return alm_materia;
    }

    public void setAlm_materia(String alm_materia) {
        this.alm_materia = alm_materia;
    }

    public Double getAlm_calificacion() {
        return alm_calificacion;
    }

    public void setAlm_calificacion(Double alm_calificacion) {
        this.alm_calificacion = alm_calificacion;
    }


    
}
