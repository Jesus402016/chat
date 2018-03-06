
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesus
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Socket so = new Socket("127.0.0.1", 10101);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(so.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(so.getInputStream());
            String[] c = new String[4];
            c[0] = "CONEXION";
            c[1] = "Jesus";
            objectOutputStream.writeObject(c);
            c[0] = "MENSAJE";
            c[1]="Jesus";//De:
            c[2]="Jesus";///Para>
            c[3]="como va todo";
             objectOutputStream.writeObject(c);
            objectOutputStream.close();
            so.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    
}
