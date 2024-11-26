/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sisteminformasidesa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Acer
 */
public class SistemInformasiDesa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseCRUD db = new DatabaseCRUD();
        new ListSurat().setVisible(true);
    }
    
}
