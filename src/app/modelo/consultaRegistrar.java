/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

import app.db.conexionSQL;
import app.vista.vistaRegistrar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mmoli
 */
public class consultaRegistrar extends conexionSQL {

    //public vistaRegistrar view;

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    public boolean AgregarUniversidad(mdRegistrar model) {
        PreparedStatement ps = null;
        conexionSQL cc = new conexionSQL();
        Connection con = cc.conexion();

        String SQL = "insert into universidad (uni_nombre) values(?)";
        try {
            PreparedStatement pst = con.prepareStatement(SQL);

            ps.setString(1, model.getUni_nombre());

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
