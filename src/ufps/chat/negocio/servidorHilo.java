/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.chat.negocio;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import ufps.chat.DTO.usuario;
import ufps.chat.Servidor.Servidor;

/**
 *
 * @author Jesus
 */
public class servidorHilo extends Thread{
 
   private ArrayList<usuarioHilo> usuarios;
   private Servidor server;
   
   /**
    * constructor
    */
   public servidorHilo() throws IOException{
     this.server=new Servidor();
     this.usuarios=new ArrayList<usuarioHilo>();
   }
   
   
   @Override
   public void run(){
        try {
            this.server.iniciarServidor();
            
            while (true) {
                Socket so;
                so = this.server.getSoc().accept();
                usuarioHilo usuarioH=new usuarioHilo();
                usuarioH.crearNuevoUsuario(so,this);
                System.out.println("aqui si");
                usuarioH.start();
                this.getUsuarios().add(usuarioH);
            }
        } catch (IOException ex) {
            System.out.print(ex.toString());
        }
    
}

    /**
     * @return the usuarios
     */
    public ArrayList<usuarioHilo> getUsuarios() {
        return usuarios;
    }
  
    
}
