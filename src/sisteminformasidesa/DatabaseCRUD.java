/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisteminformasidesa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Acer
 */
public class DatabaseCRUD {
//    static Connection koneksi;
    public Connection koneksi;
    public DatabaseCRUD() {
        try {
            String url = "jdbc:mysql://localhost/sistem_desa";
            String username = "root";
            String password = "";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.koneksi = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error Connection");
        }
    }
    public void getData() {
        try {
            Statement s = this.koneksi.createStatement();
            String sql = "SELECT * FROM surat";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                System.out.println("Judul Surat : "+r.getString("judul_surat"));
                System.out.println("Isi Surat : "+r.getString("isi_surat"));
            }
        } catch (SQLException e) {
            
        }
    }
//    public static Connection getConnection() {
//        try {
//            String url = "jdbc:mysql://localhost/sistem_desa";
//            String username = "root";
//            String password = "";
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            koneksi = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            System.out.println("Error Connection");
//        }
//        return koneksi;
//    }
}
