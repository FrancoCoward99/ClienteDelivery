/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Random;
/**
 *
 * @author Franco Coward
 */
public class Pedido {
    
    private int numeroPedido;
    private Cliente cliente;
    private Restaurante restaurante;

    public Pedido(int numeroPedido, Cliente cliente, Restaurante restaurante) {
       
        this.cliente = cliente;
        this.restaurante = restaurante;
        Random random = new Random();
        this.numeroPedido = random.nextInt(100000) + 1;
    }

    public Pedido(String generarNumeroOrden, Cliente currentUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    
    
}
