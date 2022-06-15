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
public class mdLogin {

    private int sis_id;
    private String sis_usuario;
    private int id_uni;
    private String sis_contraseña;
    

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

    public int getId_uni() {
        return id_uni;
    }

    public void setId_uni(int id_uni) {
        this.id_uni = id_uni;
    }

    public String getSis_contraseña() {
        return sis_contraseña;
    }

    public void setSis_contraseña(String sis_contraseña) {
        this.sis_contraseña = sis_contraseña;
    }

}
