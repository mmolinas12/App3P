/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador;

import app.db.conexionSQL;
import app.modelo.consultaLogin;
import app.modelo.consultaRegistrar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import app.modelo.mdLogin;
import app.modelo.mdRegistrar;
import app.vista.vistaAlumnos;
import app.vista.vistaRegistrar;
import app.controlador.ctlRegistrar;
import app.modelo.consultaAlumnos;
import app.modelo.mdAlumnos;
import app.vista.vistaLogin;
import java.sql.Connection;

/**
 *
 * @author mmoli
 */
public class ctlLogin implements ActionListener {

    private vistaLogin view;
    private mdLogin model;
    private consultaLogin modelC;

    public ctlLogin(vistaLogin view, mdLogin model, consultaLogin modelC) {
        this.view = view;
        this.model = model;
        this.modelC = modelC;
        this.view.btnIngresar.addActionListener(this);
        this.view.btnRegistrar.addActionListener(this);
    }

    public void iniciar() {
        view.setTitle("Sistema Universidad");
        view.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.btnIngresar) {
            String pass = String.valueOf(view.txtPass.getPassword());
            String usuario = view.txtUsuario.getText();

            modelC.setPassConsulta(pass);
            modelC.setUsuarioConsulta(usuario);

            if (modelC.ingresar(model)) {
                JOptionPane.showMessageDialog(null, "Ingreso correcto");
                limpiar();

                view.dispose();

                mdAlumnos model = new mdAlumnos();
                consultaAlumnos modelC = new consultaAlumnos();
                vistaAlumnos view = new vistaAlumnos();
                ctlAlumnos ctrl = new ctlAlumnos(view, model, modelC);

                ctrl.iniciar();
                view.setVisible(true);
            } else {
                limpiar();
            }
        }
        if (e.getSource() == view.btnRegistrar) {
            view.dispose();

            mdRegistrar model = new mdRegistrar();
            consultaRegistrar modelC = new consultaRegistrar();
            vistaRegistrar view = new vistaRegistrar();
            ctlRegistrar ctrl = new ctlRegistrar(view, model, modelC);

            ctrl.iniciar();
            view.setVisible(true);

            //this.vRegistrar();
        }
    }

    /*
    public static void vRegistrar(String[] args) {
        mdRegistrar model = new mdRegistrar();
        consultaRegistrar modelC = new consultaRegistrar();
        vistaRegistrar view = new vistaRegistrar();
        ctlRegistrar ctrl = new ctlRegistrar(view, model, modelC);

        ctrl.iniciar();
        view.setVisible(true);
    }
     */
    public void limpiar() {
        view.txtUsuario.setText(null);
        view.txtPass.setText(null);
    }

}
/*    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.btnIngresar) {
            int resultado = 0;
            String pass = String.valueOf(view.txtPass.getPassword());
            String usuario = view.txtUsuario.getText();
            String SQL = "select * from cuentas where sis_usuario='" + usuario + "' and sis_contraseña='" + pass + "' ";

            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs.next()) {
                    resultado = 1;
                    if (resultado == 1) {
                        vistaAlumnos form = new vistaAlumnos();
                        form.setVisible(true);
                        this.view.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error de acceso: Usuario no registrado");
                }
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error al ingresar" + f.getMessage());
            }
        }
        if (e.getSource() == view.btnRegistrar) {
            mdRegistrar model = new mdRegistrar();
            vistaRegistrar view = new vistaRegistrar();
            ctlRegistrar ctrl = new ctlRegistrar(view, model);

            ctrl.iniciar();
            view.setVisible(true);
            ctrl.mostrarDatos();
        }
    }
 */
