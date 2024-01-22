/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea05_psp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

//librerías de java
import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.SocketException;
/**
 *
 * @author Joseluisct
 */
public class Cliente_FTP {
    
    //objeto de la clase FTPClient de Apache, con diversos métodos para interactuar y recuperar un archivo de un servidor FTP
    private static FTPClient clienteFTP;
    //flujo de salida para la escritura de datos en un fichero
    private static FileOutputStream ficheroObtenido;
    //URL del servidor ftp.rediris.es, podrías probar con un servidor que tengas instalado en tu máquina
    private static String servidorURL = "localhost";
    //ruta relativa (en Servidor FTP) de la carpeta que contiene el fichero que vamos a descargar
    private static String rutaFichero = "debian";
    //nombre del fichero (aunque carece de extensión, se trata de un fichero de
    //texto que puede abrise con el bloc de notas)
    private static String nombreFichero = "README";
    //usuario
    private static String usuario = "joseluisct";
    //contraseña
    private static String password = "1234";
    //array de carpetas disponibles
    private static String[] nombreCarpeta;
    // Metodo Listar Carpetas
    public static void ListarCarpetas(){
        if (clienteFTP.isConnected()) {
            System.out.println("Conectado al servidor FTP.");
            try {
                System.out.println("Carpetas disponibles en el Servidor:");
                nombreCarpeta = clienteFTP.listNames();
                for (int i = 0; i < nombreCarpeta.length; i++) {
                    System.out.println(nombreCarpeta[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se ha establecido conexión con el servidor FTP.");
        }
    }
    // Metodo Conectar
    public void conectar() {
        int reply;
        Properties propiedades = new Properties();
        try {
            
            propiedades.load(new FileInputStream(new File("src\\main\\java\\resources\\configuracion.properties").getAbsolutePath()));
            servidorURL = propiedades.getProperty("ftp.servidorURL");
            clienteFTP = new FTPClient();
            clienteFTP.connect(servidorURL);
            reply = clienteFTP.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                clienteFTP.login(usuario, password);
                clienteFTP.enterLocalPassiveMode();
                System.out.println("Conexión exitosa al servidor FTP.");
            } else {
                System.out.println("Conexión rechazada al servidor FTP.");
                clienteFTP.disconnect();
            }
            
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * **************************************************************************
     * recupera el contenido de un fichero desde un Servidor FTP, y lo deposita en
     * un nuevo fichero en el directorio de nuestro proyecto
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            int reply;
            //creación del objeto cliente FTP
            clienteFTP = new FTPClient();
            //conexión del cliente al servidor FTP
            clienteFTP.connect("ftp.rediris.es");
            //omprobación de la conexión
            reply = clienteFTP.getReplyCode(); //Todo lo que hacemos con el servidor FTP devuelve una respuesta
            //si la conexión  es satisfactoria
            if (FTPReply.isPositiveCompletion(reply)) {
                //abre una sesión con el usuario anónimo
                clienteFTP.login(usuario, password);
                //Activar el modo pasivo para no tener problemas con el Firewall de Windows
                clienteFTP.enterLocalPassiveMode();
                //lista las carpetas de primer nivel del servidor FTP
                System.out.println("Carpetas disponibles en el Servidor:");
                nombreCarpeta = clienteFTP.listNames();
                for (int i = 0; i < nombreCarpeta.length; i++) {
                    System.out.println(nombreCarpeta[i]);
                }
                //nombre que el que va a recuperarse
                ficheroObtenido = new FileOutputStream(nombreFichero);
                //mensaje
                System.out.println("\nDescargando el fichero " + nombreFichero + " de la carpeta " + rutaFichero);
                //recupera el contenido del fichero en el Servidor, y lo escribe en el nuevo fichero del directorio del proyecto
                clienteFTP.retrieveFile("/" + rutaFichero + "/" + nombreFichero, ficheroObtenido);
                //Cada vez que realizas una operación con el servidor te devuelve una respuesta
                System.out.println(clienteFTP.getReplyString());
                //cierra el nuevo fichero
                ficheroObtenido.close();
                //cierra la conexión con el Servidor
                clienteFTP.disconnect();
                System.out.println("Descarga finalizada correctamente");
            } else {
                //desconecta
                clienteFTP.disconnect();
                System.err.println("FTP ha rechazado la conexión esblecida");
                System.exit(1);
            }
        } catch (SocketException ex) {
            //error de Socket
            System.out.println(ex.toString());
        } catch (IOException ex) {
            //error de fichero
            System.out.println(ex.toString());
        }
    }
}

