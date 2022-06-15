/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

import app.db.conexionSQL;
import app.vista.vistaAlumnos;
import app.vista.vistaRegistrar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mmoli
 */
public class consultaAlumnos extends conexionSQL {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();
    /*
    private vistaAlumnos view;

    public consultaAlumnos(vistaAlumnos view) {
        this.view = view;

    }
    */

    public boolean AgregarAlumno(mdAlumnos model) {
        PreparedStatement ps = null;
        conexionSQL cc = new conexionSQL();
        Connection con = cc.conexion();

        String SQL = "insert into alumnos (alm_nombre,alm_apellido,alm_materia,alm_calificacion,alm_estado) values(?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            

            ps.setString(1, model.getAlm_nombre());
            ps.setString(2, model.getAlm_apellido());
            ps.setString(3, model.getAlm_materia());
            ps.setDouble(4, model.getAlm_calificacion());
            ps.setString(5, model.getAlm_estado());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Registro exitoso");
            return true;
        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, "Error de registro" + f.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
