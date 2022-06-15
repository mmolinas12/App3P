/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador;

import app.db.conexionSQL;
import app.modelo.consultaAlumnos;
import app.modelo.consultaLogin;
import app.modelo.mdAlumnos;
import app.modelo.mdLogin;
import app.modelo.mdProfesores;
import app.vista.vistaAlumnos;
import app.vista.vistaLogin;
import app.vista.vistaProfesores;
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
public class ctlAlumnos implements ActionListener {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    private vistaAlumnos view;
    private mdAlumnos model;
    private consultaAlumnos modelC;

    public ctlAlumnos(vistaAlumnos view, mdAlumnos model, consultaAlumnos modelC) {
        this.view = view;
        this.model = model;
        this.modelC = modelC;
        this.view.btnNuevo.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        this.view.btnModificar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnProfesores.addActionListener(this);

    }

    public void iniciar() {
        view.setTitle("Alumnos");
        mostrarDatos();
        actualizarCombo();
        //view.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnGuardar) {
            try {
                String SQL = "insert into alumnos (alm_nombre,alm_apellido,alm_materia,alm_calificacion,alm_estado) values(?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(SQL);

                pst.setString(1, view.txtNombre.getText());

                pst.setString(2, view.txtApellido.getText());

                int seleccionado = view.cbMateria.getSelectedIndex();
                pst.setString(3, view.cbMateria.getItemAt(seleccionado));

                pst.setDouble(4, Double.parseDouble(view.txtCalificacion.getText()));

                int seleccionado1 = view.cbEstado.getSelectedIndex();
                pst.setString(5, view.cbEstado.getItemAt(seleccionado1));

                pst.execute();

                JOptionPane.showMessageDialog(null, "Registro exitoso");

            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error de registro" + f.getMessage());
            }
            limpiarCajas();
            mostrarDatos();
        }

        if (e.getSource() == view.btnNuevo) {
            limpiarCajas();
            mostrarDatos();
        }

        if (e.getSource() == view.btnModificar) {
            try {
                String SQL = "update alumnos set alm_nombre=?,alm_apellido=?,alm_materia=?,alm_calificacion=?,alm_estado=? where alm_id=?";

                int filaSeleccionada = view.tablaAlumnos.getSelectedRow();
                String dao = (String) view.tablaAlumnos.getValueAt(filaSeleccionada, 0);

                PreparedStatement pst = con.prepareStatement(SQL);

                pst.setString(1, view.txtNombre.getText());

                pst.setString(2, view.txtApellido.getText());

                int seleccionado = view.cbMateria.getSelectedIndex();
                pst.setString(3, view.cbMateria.getItemAt(seleccionado));

                pst.setDouble(4, Double.parseDouble(view.txtCalificacion.getText()));

                int seleccionado1 = view.cbEstado.getSelectedIndex();
                pst.setString(5, view.cbEstado.getItemAt(seleccionado1));

                pst.setString(6, dao);

                pst.execute();

                JOptionPane.showMessageDialog(null, "Registro editado exitosamente");

            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error de edición" + f.getMessage());
            }
            limpiarCajas();
            mostrarDatos();
        }
        if (e.getSource() == view.btnEliminar) {
            int filaSeleccionada = view.tablaAlumnos.getSelectedRow();

            try {
                String SQL = "delete from alumnos where alm_id=" + view.tablaAlumnos.getValueAt(filaSeleccionada, 0);

                Statement st = con.createStatement();

                int n = st.executeUpdate(SQL);

                if (n >= 0) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado");
                }
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error al eliminar registro" + f.getMessage());
            }
            limpiarCajas();
            mostrarDatos();
        }
        if (e.getSource() == view.btnProfesores) {
            this.view.dispose();
            mdProfesores model = new mdProfesores();
            vistaProfesores view = new vistaProfesores();
            ctlProfesores ctrl = new ctlProfesores(view, model);

            ctrl.iniciar();
            view.setVisible(true);
        }
    }

    public void actualizarCombo() {
        String SQL = "Select * from materias";
        try {
            PreparedStatement pst = con.prepareStatement(SQL);
            ResultSet rs = pst.executeQuery(SQL);
            while (rs.next()) {
                view.cbMateria.addItem(rs.getString("mat_nombre"));
            }

        } catch (Exception e) {
        }
    }

    public void limpiarCajas() {
        view.txtNombre.setText("");
        view.txtApellido.setText("");
        view.txtBuscar.setText("");
        view.txtCalificacion.setText("");
        view.cbMateria.setSelectedItem(null);
        view.cbEstado.setSelectedItem(null);
    }

    public void mostrarDatos() {
        String[] titulos = {"ID", "Nombre", "Apellido", "Materia", "Calificación", "Estado"};
        String[] registros = new String[6];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String SQL = "select * from alumnos";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                registros[0] = rs.getString("alm_id");
                registros[1] = rs.getString("alm_nombre");
                registros[2] = rs.getString("alm_apellido");
                registros[3] = rs.getString("alm_materia");
                registros[4] = rs.getString("alm_calificacion");
                registros[5] = rs.getString("alm_estado");

                modelo.addRow(registros);
            }

            view.tablaAlumnos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos " + e.getMessage());
        }
    }
}
