/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador;

import app.db.conexionSQL;
import app.modelo.consultaAlumnos;
import app.modelo.mdAlumnos;
import app.modelo.mdProfesores;
import app.vista.vistaAlumnos;
import app.vista.vistaProfesores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mmoli
 */
public class ctlProfesores implements ActionListener {

    conexionSQL cc = new conexionSQL();
    Connection con = cc.conexion();

    private vistaProfesores view;
    private mdProfesores model;

    public ctlProfesores(vistaProfesores view, mdProfesores model) {
        this.view = view;
        this.model = model;
        this.view.btnNuevo.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        this.view.btnModificar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnSalir.addActionListener(this);
        this.view.btnAgregarMateria.addActionListener(this);

    }

    public void iniciar() {
        view.setTitle("Profesores");
        mostrarDatos();
        actualizarCombo();
        //view.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnEliminar) {
            int filaSeleccionada = view.tablaProfesores.getSelectedRow();

            try {
                String SQL = "delete from profesores where prf_id=" + view.tablaProfesores.getValueAt(filaSeleccionada, 0);

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

        if (e.getSource() == view.btnModificar) {
            try {
                String SQL = "update profesores set prf_nombre=?,prf_apellido=?,prf_materia=? where prf_id=?";

                int filaSeleccionada = view.tablaProfesores.getSelectedRow();
                String dao = (String) view.tablaProfesores.getValueAt(filaSeleccionada, 0);

                PreparedStatement pst = con.prepareStatement(SQL);

                pst.setString(1, view.txtNombre.getText());

                pst.setString(2, view.txtApellido.getText());

                int seleccionado = view.cbMateria.getSelectedIndex();
                pst.setString(3, view.cbMateria.getItemAt(seleccionado));

                pst.setString(4, dao);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Registro editado exitosamente");

            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error de edición" + f.getMessage());
            }
            limpiarCajas();
            mostrarDatos();
        }

        if (e.getSource() == view.btnNuevo) {
            limpiarCajas();
            mostrarDatos();
        }

        if (e.getSource() == view.btnGuardar) {
            try {
                String SQL = "insert into profesores (prf_nombre,prf_apellido,prf_materia) values(?,?,?)";
                PreparedStatement pst = con.prepareStatement(SQL);

                pst.setString(1, view.txtNombre.getText());

                pst.setString(2, view.txtApellido.getText());

                int seleccionado = view.cbMateria.getSelectedIndex();
                pst.setString(3, view.cbMateria.getItemAt(seleccionado));

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

            mdAlumnos model = new mdAlumnos();
            consultaAlumnos modelC = new consultaAlumnos();
            vistaAlumnos view = new vistaAlumnos();
            ctlAlumnos ctrl = new ctlAlumnos(view, model, modelC);

            ctrl.iniciar();
            view.setVisible(true);
        }

        if (e.getSource() == view.btnAgregarMateria) {
            try {
                String SQL = "insert into materias (mat_nombre) values(?)";
                PreparedStatement pst = con.prepareStatement(SQL);

                pst.setString(1, view.txtMateria.getText());

                pst.execute();

                JOptionPane.showMessageDialog(null, "Registro exitoso");

            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Error de registro" + f.getMessage());
            }
            limpiarCajas();
            mostrarDatos();
            view.cbMateria.removeAllItems();
            actualizarCombo();
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
        view.txtMateria.setText("");
        view.cbMateria.setSelectedItem(null);
    }

    public void mostrarDatos() {
        String[] titulos = {"ID", "Nombre", "Apellido", "Materia"};
        String[] registros = new String[5];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String SQL = "select * from profesores";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {

                registros[0] = rs.getString("prf_id");
                registros[1] = rs.getString("prf_nombre");
                registros[2] = rs.getString("prf_apellido");
                registros[3] = rs.getString("prf_materia");

                modelo.addRow(registros);
            }

            view.tablaProfesores.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos " + e.getMessage());
        }
    }
}
