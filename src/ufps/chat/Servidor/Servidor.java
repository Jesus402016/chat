/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.chat.Servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Jesus
 */
public class Servidor {
    
    private int puerto=3000;
    private ServerSocket soc;
     
    
    public Servidor() throws IOException{
     
        
    }
    
    //metodo que inicia el servidor
   public void iniciarServidor() throws IOException{
        this.soc=new ServerSocket(puerto);
       System.out.println("nueva conexion");      
       
   }

    /**
     * @return the soc
     */
    public ServerSocket getSoc() {
        return soc;
    }

    /**
     * @param soc the soc to set
     */
    public void setSoc(ServerSocket soc) {
        this.soc = soc;
    }
}
