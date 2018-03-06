/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.chat.DTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Jesus
 */
public class usuario {

 
    private String nombre;
    private int puerto;
    private Socket socket;
    private ObjectOutputStream enviar;
    private ObjectInputStream recibir;

    
    /**
     * constructor
     * @param nombre
     * @param puerto
     * @param socket
     * @param enviar
     * @param recibir 
     */
    
       public usuario(String nombre, int puerto, Socket socket, ObjectOutputStream enviar, ObjectInputStream recibir) {
        this.nombre = nombre;
        this.puerto = puerto;
        this.socket = socket;
        this.enviar = enviar;
        this.recibir = recibir;
    }
       
       public usuario(Socket s){
           this.socket=s;
       }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the puerto
     */
    public int getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * @return the enviar
     */
    public ObjectOutputStream getEnviar() {
        return enviar;
    }

    /**
     * @param enviar the enviar to set
     */
    public void setEnviar(ObjectOutputStream enviar) {
        this.enviar = enviar;
    }

    /**
     * @return the recibir
     */
    public ObjectInputStream getRecibir() {
        return recibir;
    }

    /**
     * @param recibir the recibir to set
     */
    public void setRecibir(ObjectInputStream recibir) {
        this.recibir = recibir;
    }
   
    
    
    
}
