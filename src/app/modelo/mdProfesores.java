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
public class mdProfesores {

    private int prf_id;
    private String prf_nombre;
    private String prf_apellido;
    private String prf_materia;
    private int mat_id;
    private String mat_nombre;

    public int getMat_id() {
        return mat_id;
    }

    public void setMat_id(int mat_id) {
        this.mat_id = mat_id;
    }

    public String getMat_nombre() {
        return mat_nombre;
    }

    public void setMat_nombre(String mat_nombre) {
        this.mat_nombre = mat_nombre;
    }

    public int getPrf_id() {
        return prf_id;
    }

    public void setPrf_id(int prf_id) {
        this.prf_id = prf_id;
    }

    public String getPrf_nombre() {
        return prf_nombre;
    }

    public void setPrf_nombre(String prf_nombre) {
        this.prf_nombre = prf_nombre;
    }

    public String getPrf_apellido() {
        return prf_apellido;
    }

    public void setPrf_apellido(String prf_apellido) {
        this.prf_apellido = prf_apellido;
    }

    public String getPrf_materia() {
        return prf_materia;
    }

    public void setPrf_materia(String prf_materia) {
        this.prf_materia = prf_materia;
    }

}
