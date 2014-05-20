/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scoringsystem.fabio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class RegisterClient {

    /**
     *
     * @param ipA
     * @param p
     * @param name
     */
    public boolean registered = true;

    public RegisterClient(String ipA, int p, String name, String password) {
        try {
            String local;

            try {
                local = InetAddress.getLocalHost().getHostAddress() + ":" + port;
            } catch (UnknownHostException ex) {
                local = "Network Error";
            }

            ip = ipA;
            port = p;

            socket = new Socket(ip, port);

            username = name;

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("RegisterClient");

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(username);

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(password);


            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String response = (String) ois.readObject();


            if (response.equals("Registered unsuccesful")) {
                registered = false;

            } else if (response.equals("Registered succesful")) {
                registered = true;
            }


        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public boolean getRegistered() {
        return registered;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(RegisterClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *
     */
    public static Socket socket;
    /**
     *
     */
    public static int port = 2406;
    /**
     *
     */
    public static String ip = "";
    /**
     *
     */
    public String username = "";
    /**
     *
     */
}
