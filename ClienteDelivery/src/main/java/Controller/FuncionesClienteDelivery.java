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

/**
 *
 * @author Franco Coward
 */
public class FuncionesClienteDelivery {
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
                System.out.println("Mensaje del servidor: "+mensajeRecibido);
               
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Se presentó un error: " + e.getMessage());
        }
    }
}
