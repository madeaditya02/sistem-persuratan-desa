/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisteminformasidesa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Acer
 */
public class Session {
    public static String id_user;
    public static int id_jabatan;
    public static User loggedUser;

    public static boolean login(String email, String password) {
        try {
            String url = "jdbc:mysql://localhost/kantordesafix";
            String usernameDb = "root";
            String passDb = "";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection koneksi = DriverManager.getConnection(url, usernameDb, passDb);
            Statement stmt = koneksi.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'");
            if (result.next()) {
                id_user = result.getString("id_user");
                id_jabatan = result.getInt("Id_Jabatan"); // Fetch id_jabatan
                loggedUser = new User(id_user); 
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static void logout() {
        id_user = null;
        loggedUser = null;
    }
    
    public static User getLoggedUser() {
        return new User(id_user);
    }
}
