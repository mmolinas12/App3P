/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador;

import app.db.conexionSQL;
import app.modelo.consultaLogin;
import app.modelo.consultaRegistrar;
import app.modelo.mdLogin;
import app.modelo.mdRegistrar;
import app.vista.vistaLogin;
import app.vista.vistaRegistrar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mmoli
 */
public class ctlRegistrar implements ActionListener {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    private vistaRegistrar view;
    private mdRegistrar model;
    private consultaRegistrar modelC;

    public ctlRegistrar(vistaRegistrar view, mdRegistrar model, consultaRegistrar modelC) {
        this.view = view;
        this.model = model;
        this.modelC = modelC;
        this.view.btnAgregar.addActionListener(this);
        this.view.btnAgregarUniversidad.addActionListener(this);
        this.view.btnSalir.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);

    }

    public void iniciar() {
        view.setTitle("Registrar");
        mostrarDatos();
        //view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //agregar cuenta
        if (e.getSource() == view.btnAgregar) {
            String pass = String.valueOf(view.txtPass.getPassword());
            String SQL = "insert into cuentas (sis_usuario, sis_contraseña, id_uni) values(?,?,?)";

            try {
                PreparedStatement pst = con.prepareStatement(SQL);
                pst.setString(1, view.txtUsuario.getText());
                pst.setString(2, pass);
                pst.setString(3, view.txtUniID.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro exitoso");

            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error al registrar" + f.getMessage());
            }

            limpiarCajas();
            mostrarDatos();

        }
        //agregar universidad

        if (e.getSource() == view.btnAgregarUniversidad) {
            try {
                String SQL = "insert into universidad (uni_nombre) values(?)";
                PreparedStatement pst = con.prepareStatement(SQL);

                pst.setString(1, view.txtAgregarUni.getText());

                pst.execute();

                JOptionPane.showMessageDialog(null, "Registro exitoso");

            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error de registro" + f.getMessage());
            }
            limpiarCajas();
            mostrarDatos();

        }
        if (e.getSource() == view.btnSalir) {
            this.view.dispose();
            mdLogin model = new mdLogin();
            consultaLogin modelC = new consultaLogin();
            vistaLogin view = new vistaLogin();
            ctlLogin ctrl = new ctlLogin(view, model, modelC);

            ctrl.iniciar();
            view.setVisible(true);

        }
    }

    public void mostrarDatos() {
        String[] titulos = {"ID", "Nombre"};
        String[] registros = new String[3];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String SQL = "select * from universidad";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                registros[0] = rs.getString("uni_id");
                registros[1] = rs.getString("uni_nombre");

                modelo.addRow(registros);
            }

            view.tablaUniversidad.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos " + e.getMessage());
        }
    }

    
    public void limpiarCajas() {
        view.txtUsuario.setText("");
        view.txtPass.setText("");
        view.txtUniID.setText("");
        view.txtUni.setText("");
        view.txtAgregarUni.setText("");
    }
}
