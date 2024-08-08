/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cliente;
import Model.Pedido;
import Model.Producto;
import Model.Restaurante;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FuncionesClienteDelivery {

    private JFrame ventana;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;
    private Socket conexionAlServidor;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public String agregarCliente(Cliente cliente) {
        try {
            listaClientes.add(cliente);
            return "Registro con éxito. Te damos la bienvenida " + cliente.getNombre();
        } catch (Exception error) {
            return "Hubo un problema en el registro: " + error.getMessage();
        }
    }

    public ArrayList<Cliente> listarClientes() {
        return listaClientes;
    }
public boolean conectarCliente() {
    try {
        conexionAlServidor = new Socket(HOST, PUERTO);
        salida = new DataOutputStream(conexionAlServidor.getOutputStream());
        entrada = new DataInputStream(conexionAlServidor.getInputStream());
        return true; // Retornar true si la conexión es exitosa
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor: " + e.getMessage());
        return false; // Retornar false si la conexión falla
    }
}


    public void ingresarClienteFormulario(JTextField txtNombreCliente, JTextField txtNumeroCliente, JTextField txtDireccionCliente) {
        try {
            Cliente nuevo = new Cliente(txtNombreCliente.getText(), Integer.parseInt(txtNumeroCliente.getText()), txtDireccionCliente.getText());
            String mensajeRespuesta = agregarCliente(nuevo);
            JOptionPane.showMessageDialog(null, mensajeRespuesta, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un dato válido: " + error.getMessage());
        }
    }

    public void agregarRestaurante(Restaurante restaurante) {
        listaRestaurantes.add(restaurante);
    }

    public ArrayList<Restaurante> listarRestaurantes() {
        return listaRestaurantes;
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public ArrayList<Producto> listarProductos() {
        return listaProductos;
    }

public void enviarPedido(Pedido pedido) {
    if (conectarCliente()) { // Asegurarse de que la conexión esté establecida
        try {
            String mensajeEnviado = "Pedido: " + pedido.getNumeroPedido() + ", Cliente: " + pedido.getCliente().getNombre();
            salida.writeUTF(mensajeEnviado);
            JOptionPane.showMessageDialog(null, "Pedido enviado al servidor: " + mensajeEnviado);
            JOptionPane.showMessageDialog(null, "Pedido enviado con éxito", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al enviar pedido: " + e.getMessage());
        } finally {
            cerrarConexion(); // Asegúrate de cerrar la conexión después de enviar
        }
    }
}

public void cerrarConexion() {
    try {
        if (conexionAlServidor != null) {
            salida.close();
            entrada.close();
            conexionAlServidor.close();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
    }
}


}
