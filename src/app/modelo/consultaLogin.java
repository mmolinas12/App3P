/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

import app.controlador.ctlLogin;
import app.db.conexionSQL;
import app.vista.vistaAlumnos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author mmoli
 */
public class consultaLogin extends conexionSQL {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    public String passConsulta;
    public String usuarioConsulta;

    public String getPassConsulta() {
        return passConsulta;
    }

    public void setPassConsulta(String passConsulta) {
        this.passConsulta = passConsulta;
    }

    public String getUsuarioConsulta() {
        return usuarioConsulta;
    }

    public void setUsuarioConsulta(String usuarioConsulta) {
        this.usuarioConsulta = usuarioConsulta;
    }

    public boolean ingresar(mdLogin lg) {

        int resultado = 0;
        String SQL = "select * from cuentas where sis_usuario='" + usuarioConsulta + "' and sis_contraseña='" + passConsulta + "' ";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                resultado = 1;
                if (resultado == 1) {
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error de acceso: Usuario no registrado");
                return false;
            }

        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, "Error al ingresar" + f.getMessage());
        }
        return false;
    }

    /*
    public boolean buscar(mdLogin model){
        PreparedStatement ps = null;
        ResultSet rs = null;
        conexionSQL cc = new conexionSQL();
        Connection con = cc.conexion();
        
        String sql = "SELECT * FROM cuentas WHERE sis_id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getSis_id());
            rs = ps.executeQuery();
            
            if(rs.next()){
                model.setSis_usuario(rs.getString("Sis_usuario"));
                model.setSis_contraseña(rs.getString("Sis_contraseña"));
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        } 
    }
     */
}
