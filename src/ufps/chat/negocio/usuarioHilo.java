/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.chat.negocio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufps.chat.DTO.usuario;

/**
 *
 * @author Jesus
 */
public class usuarioHilo extends Thread{
    
  private usuario usuario;
  private boolean activo;
  private servidorHilo servidor;
  public usuarioHilo(){
      
  }
  
  @Override
  public void run(){
      
      
    this.recibir();
      
      
  }  
  //escucha cada una de las peticiones que envia el cliente
    public void recibir() {
        this.activo = true;
        while (activo) {
            try {
                Object mensaje = this.usuario.getRecibir().readObject();
                String[] list = (String[])mensaje;
                if (list instanceof String[]) {
                    if (list[0].equals("CONEXION")) 
                    {
                        conexionUsuario(list[1]);
                    } else if (list[0].equals("DESCONECTAR")) 
                    {
                        
                    }else if(list[0].equals("MENSAJE")){
                       enviarMensaje(list);
                    }else if(list[0].equals("MENSAJETODOS")){
                        enviarMensajeTodos(list);
                    }
                    else if(list[0].equals("CONECTADOS")){
                        conectados();
                    }
                }
            } catch (IOException e) {
                this.activo=false;
                try {
                    desconectar();
                } catch (IOException ex) {
                    Logger.getLogger(usuarioHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            } catch(Exception e){
                System.out.println(e);
            }

        }
    }
    //Metodo encargado de crear un nuevo socket y un nuevo usuario en el servidor
  public void crearNuevoUsuario(Socket s,servidorHilo servidor){
     try{
     this.usuario=new usuario(s);
     this.servidor=servidor;
     usuario.setEnviar(new ObjectOutputStream(s.getOutputStream()));
     usuario.setRecibir(new ObjectInputStream(s.getInputStream()));

  }catch (IOException E){
      System.out.print(E.toString());
  }
          
}
  //metodo que desconecta un cliente cuando sale de la conexion
  public void desconectar() throws IOException{
      
              this.usuario.getEnviar().close();
              this.usuario.getRecibir().close(); 
              this.servidor.getUsuarios().remove(this);
      String [] list=new String[2];
      list[0]="DESCONECTAR";
      list[1]=this.usuario.getNombre();
      System.out.print(list[1]+"Cerro seccion");
          for (usuarioHilo usuario :this.servidor.getUsuarios()) {
                 usuario.usuario.getEnviar().writeObject(list);
          }
      this.usuarios();
      
  }
  //Metodo que se encarga de ingresar el nombre de usuario que se conecto y avisar a los demas
  public void conexionUsuario(String nombre) throws IOException{
    this.usuario.setNombre(nombre);
    if(!buscarUsuario(nombre)){
      usuarios();
  }
  }
  
  private boolean buscarUsuario(String nombre){
      boolean c=false;
      for (usuarioHilo user: this.servidor.getUsuarios()) {
          if(user.equals(nombre)){
              c=true;
          }
          
      }
      return c;
  }
  
  
  
  //Metodo que imprime los usuarios que se conectaron y envia una lista de los conectados
  
  public void usuarios() throws IOException{
      String[] usuarios=new String[this.servidor.getUsuarios().size()+1];
      usuarios[0]="CONECTADOS";
      int aux=1;
      for (usuarioHilo usuario :this.servidor.getUsuarios()) {
             usuarios[aux]=usuario.usuario.getNombre();
             System.out.println("conectado>>>"+usuario.usuario.getNombre());
             aux++;
          }
      
      //Enviar a todos los usuarios cuantos conectados hay
        for (usuarioHilo usuario :this.servidor.getUsuarios()) {
          usuario.usuario.getEnviar().writeObject(usuarios);
        }
  }
  //metodo que pidio la peticion de cuantos conectados hay
   public void conectados() throws IOException{
      String[] usuarios=new String[this.servidor.getUsuarios().size()+1];
      usuarios[0]="CONECTADOS";
      usuarioHilo user=null;
      int aux=1;
      for (usuarioHilo usuario :this.servidor.getUsuarios()) {
          if(usuario.usuario.getNombre().equals(this.usuario.getNombre())){
              user=usuario;
          }
            usuarios[aux]=usuario.usuario.getNombre();
             aux++;
          }
     
          user.usuario.getEnviar().writeObject(usuarios);
        
  }
  //Metodo encargado de enviar Mensaje a un solo usuario
  public void enviarMensaje(String[] list) throws IOException{
      ArrayList<usuarioHilo> usuarios=this.servidor.getUsuarios();
      for (usuarioHilo user : usuarios) {
         if(list[2].equals(user.usuario.getNombre())){
              user.usuario.getEnviar().writeObject(list);
          }
      }
  }
      
    //Metodo encargado de enviar Mensaje a todos
  public void enviarMensajeTodos(String[] list) throws IOException{
      ArrayList<usuarioHilo> usuarios=this.servidor.getUsuarios();
      for (usuarioHilo user : usuarios) {
          if(!list[1].equals(user.usuario.getNombre())){
              user.usuario.getEnviar().writeObject(list);
          }
      }
  }
  
}
