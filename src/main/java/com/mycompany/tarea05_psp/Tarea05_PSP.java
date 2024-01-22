/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea05_psp;

import java.util.Scanner;

/**
 *
 * @author Joseluisct
 */
public class Tarea05_PSP {

    public static void main(String[] args) {
        // crea un objeto de la clase Cliente_FTP
        Cliente_FTP Cliente_FTP = new Cliente_FTP();
        Cliente_FTP.conectar();
        // conecta con el servidor FTP
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("------------------------------------------");
            System.out.println("1. Listar contenido");
            System.out.println("2. Entrar directorio");
            System.out.println("3. Subir fichero");
            System.out.println("4. Crear un directorio");
            System.out.println("5. Volver al Home");
            System.out.println("6. Salir");
            System.out.println("------------------------------------------");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    // clienteFTP.ListarCarpetas();
                    Cliente_FTP.ListarCarpetas();
                    break;
                case 2:
                    // System.out.print("Nombre del directorio: ");
                    // entrarDirectorio(scanner.next());
                    break;
                case 3:
                    System.out.print("Nombre del fichero: ");
                    // subirFichero(scanner.next());
                    break;
                case 4:
                    System.out.print("Nombre del directorio: ");
                    // crearDirectorio(scanner.next());
                    break;
                case 5:
                    // volverHome();
                    break;
                case 6:
                    // salir();
                    break;
            }
        } while (opcion != 6);
    }
}
