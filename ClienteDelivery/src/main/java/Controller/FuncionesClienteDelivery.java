/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;
import Model.Cliente;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author Franco Coward
 */
public class FuncionesClienteDelivery {

    private JFrame ventana;
    ArrayList<Cliente> listaClientes = new ArrayList();

    public String AgregarCliente(Cliente cliente) {
        try {
            listaClientes.add(cliente);
            return "Registro con exito. Te damos la bienvenida" + cliente.getNombre();
        } catch (Exception error) {
            return "Hubo un problema en el registro: " + error.getMessage();
        }
    }

    public ArrayList<Cliente> ListaClientes() {
        return listaClientes;
    }
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    private Socket conexionAlServidor;

    private DataInputStream entrada;
    private DataOutputStream salida;

    private String mensajeRecibido = "";

    public void conectarCliente() {
        try {
            conexionAlServidor = new Socket(HOST, PUERTO);

            salida = new DataOutputStream(conexionAlServidor.getOutputStream());
            entrada = new DataInputStream(conexionAlServidor.getInputStream());
            String mensajeEnviado = "";

            Scanner lector = new Scanner(System.in); // Create a Scanner object

            while (!mensajeEnviado.equalsIgnoreCase("SALIR")) {
                System.out.println("Digite su mensaje para el servidor:");
                System.out.println();
                //Aqui enviamos mensajes
                mensajeEnviado = lector.nextLine();
                salida.writeUTF(mensajeEnviado);
                //Aqui leemos los mensajes del servidor
                mensajeRecibido = entrada.readUTF();
                System.out.println("Mensaje del servidor: " + mensajeRecibido);

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Se presentó un error: " + e.getMessage());
        }
    }

    public String mostrarCliente(Model.Cliente cliente) {

        return "Cliente{"
                + "nombre='" + cliente.getNombre() + '\''
                + ", numero='" + cliente.getNumeroTelefono() + '\''
                + ", direccion='" + cliente.getDireccion() + '\''
                + '}';
    }

    public void ingresarClienteFormulario(JTextField txtNombreCliente, JTextField txtNumeroCliente,
            JTextField txtDireccionCliente) {
        Cliente nuevo = new Cliente(txtNombreCliente.getText(), Integer.parseInt(txtNumeroCliente.getText()), txtDireccionCliente.getText());

        try {
            nuevo.setNombre(txtNombreCliente.getText());
            nuevo.setNumeroTelefono(Integer.parseInt(txtNumeroCliente.getText()));
            nuevo.setDireccion(txtDireccionCliente.getText());
            String mensajeRespuesta = AgregarCliente(nuevo);
            JOptionPane.showMessageDialog(null, mensajeRespuesta, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un dato: " + error.getMessage());
        }

    }

}
