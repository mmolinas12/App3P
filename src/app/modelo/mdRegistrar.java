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
public class mdRegistrar {

    private int sis_id;
    private String sis_usuario;
    private String sis_contraseña;
    
    private int id_uni;
    
    private int uni_id;
    private String uni_nombre;

    
    public int getSis_id() {
        return sis_id;
    }

    public void setSis_id(int sis_id) {
        this.sis_id = sis_id;
    }

    public String getSis_usuario() {
        return sis_usuario;
    }

    public void setSis_usuario(String sis_usuario) {
        this.sis_usuario = sis_usuario;
    }

    public String getSis_contraseña() {
        return sis_contraseña;
    }

    public void setSis_contraseña(String sis_contraseña) {
        this.sis_contraseña = sis_contraseña;
    }

    public int getId_uni() {
        return id_uni;
    }

    public void setId_uni(int id_uni) {
        this.id_uni = id_uni;
    }

    public int getUni_id() {
        return uni_id;
    }

    public void setUni_id(int uni_id) {
        this.uni_id = uni_id;
    }

    public String getUni_nombre() {
        return uni_nombre;
    }

    public void setUni_nombre(String uni_nombre) {
        this.uni_nombre = uni_nombre;
    }
    
    
}
