package com.example.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.model.Empleado;
import com.example.repositorio.EmpleadoRepositorio;
import com.example.repositorio.Repositorio;

public class SwingApp extends JFrame {

    private final Repositorio<Empleado> empleadoRepositorio;
    private final JTable tablaEmpleado;

    public SwingApp() {
        setTitle("Administrador de empleados");
        setSize(600, 230);
        tablaEmpleado = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEmpleado);
        add(scrollPane, BorderLayout.CENTER);
        // Crea botones de la aplicacion
        JButton agregarButton = new JButton("Agregar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");

        JPanel panelButton = new JPanel();
        panelButton.add(agregarButton);
        panelButton.add(actualizarButton);
        panelButton.add(eliminarButton);
        add(panelButton, BorderLayout.SOUTH);
        // Estilos
        agregarButton.setBackground(new Color(8, 54, 6));
        agregarButton.setForeground(Color.WHITE);
        actualizarButton.setBackground(new Color(8, 54, 6));
        actualizarButton.setForeground(Color.WHITE);
        eliminarButton.setBackground(new Color(8, 54, 6));
        eliminarButton.setForeground(Color.WHITE);

        empleadoRepositorio = new EmpleadoRepositorio(); // Objeto creado para acceder a la base de datos

        // agregar la accion de escuchar a los botones
        agregarButton.addActionListener(e -> {
            try {
                agregarEmpleado();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        });
        actualizarButton.addActionListener(e -> actualizarEmpleado());
        eliminarButton.addActionListener(e -> eliminarEmpleado());
    }

    private Object eliminarEmpleado() {
        String empleadoString = JOptionPanel.showInputDialog(this, "Ingrese el Id del empleado a eliminar","Elimina empleado", JOptionPane.QUESTION_MESSAGE);
        if(empleadoString != null) {
            try {
                int empleadoId = Integer.parseInt(empleadoString);
                int confirmarEliminacion = JOptionPane.showConfirmDialog(this,"Seguro que desea eliminar el empleado","Confirma eliminación", JOptionPanel.YES_NO_OPTION){
                    empleadoRepositorio.eliminar(empleadoId);
                    refrescarEmpleado();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un numero valido", "Error",JOptionPane.);
    
        }
        }
    }

    private Object actualizarEmpleado() {

        String empleadoIdString = JOptionPane.showInputDialog(this, "Ingrese el Id del empleado al actualizar", "Actualizar empleado", JOptionPane.QUESTION_MESSAGE);
        if (empleadoIdString != null) {
            try {
                int empleadoId = Integer.parseInt(empleadoIdString);
                Empleado empleado = empleadoRepositorio.obternerPorId(empleadoId);
                if (empleado != null) {
                    JTextField nombre = new JTextField();
                    JTextField primerApellido = new JTextField();
                    JTextField segundoApellido = new JTextField();
                    JTextField email = new JTextField();
                    JTextField salario = new JTextField();
                    Object[] campos = {
                        "Nombre: ", nombre,
                        "Primer Apellido: ", primerApellido,
                        "Segundo Apellido: ", segundoApellido,
                        "Email: ", email,
                        "Salario: ", salario
                    };
                    int confirmarResultado = JOptionPane.showConfirmDialog(this, campos, "Actualizar empleado", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmarResultado == JOptionPane.OK_OPTION) {
                        empleado.setNombre(nombre.getText());
                        empleado.setPrimerApellido(primerApellido.getText());
                        empleado.setSegundoApellido(segundoApellido.getText());
                        empleado.setEmail(email.getText());
                        empleado.setSalario(Float.parseFloat(salario.getText()));
                        empleadoRepositorio.guardar(empleado);
                        refrescarEmpleado();
                    }

                }
            }else{
        JOptionPane.showMessageDialog(this, "No se encontro el empleado", "Error", JOptionPane.ERROR_MESSAGE);
    }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(this, "Ingrese un valor númerico válido", "Error", JOptionPane.ERROR_MESSAGE);
    }catch(SQLException e){
        JOptionPane.showMessageDialog(this, "Error al actualizar el empleado", "Error al cargar los datos", JOptionPane.ERROR_MESSAGE);
    }
        }

    private void agregarEmpleado() throws SQLException {
        JTextField nombre = new JTextField();
        JTextField primerApellido = new JTextField();
        JTextField segundoApellido = new JTextField();
        JTextField email = new JTextField();
        JTextField salario = new JTextField();
        Object[] campos = {
                "Nombre", nombre,
                "Primer Apellido", primerApellido,
                "Segundo Apellido", segundoApellido,
                "Email", email,
                "Salario", salario
        };
        int resultado = JOptionPane.showConfirmDialog(this, campos, "Agregar empleado", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            Empleado empleado = new Empleado();
            empleado.setNombre(nombre.getText());
            empleado.setPrimerApellido(primerApellido.getText());
            empleado.setSegundoApellido(segundoApellido.getText());
            empleado.setEmail(email.getText());
            empleado.setSalario(Float.parseFloat(salario.getText()));
            empleadoRepositorio.guardar(empleado);

            // Metodo actualizar tabla
            refrescarEmpleado();
            JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente", "Operación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void refrescarEmpleado() {
        try {
            List<Empleado> empleados = empleadoRepositorio.encontrarTodos();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Id");
            model.addColumn("Nombre");
            model.addColumn("primerApellido");
            model.addColumn("segundoApellido");
            model.addColumn("email");
            model.addColumn("salario");
            for (Empleado em : empleados) {
                Object[] filaDatos = {
                        em.getId(),
                        em.getNombre(),
                        em.getPrimerApellido(),
                        em.getSegundoApellido(),
                        em.getEmail(),
                        em.getSalario()
                };
                model.addRow(filaDatos);
            }
            tablaEmpleado.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener la lista de empleados", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
