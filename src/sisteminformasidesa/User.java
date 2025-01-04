/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisteminformasidesa;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/**
 *
 * @author Acer
 */
public class User {
    public String email, password, nama_lengkap, nik, no_kk, tempat_lahir, tanggal_lahir, warga_negara, agama, jenis_kelamin, pekerjaan, alamat_desa, alamat_kec, alamat_kab, alamat_prov, gol_darah;
    public int id_user, id_jabatan;
    public Connection koneksi;
    
    public User(String id_user) {
        try {
            String url = "jdbc:mysql://localhost/kantor_desa";
            String username = "root";
            String password = "";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection koneksi = DriverManager.getConnection(url, username, password);
            Statement s = koneksi.createStatement();
            String sql = "SELECT user.*, warga.* FROM user JOIN warga ON user.nik = warga.nik WHERE user.id_user = '"+ id_user +"'";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                this.id_user = r.getInt("id_user");
                this.id_jabatan = r.getInt("id_jabatan");
                this.email = r.getString("email");
                this.password = r.getString("password");
                this.nik = r.getString("nik");
                this.no_kk = r.getString("no_kk");
                this.tempat_lahir = r.getString("tempat_lahir");
                this.tanggal_lahir = r.getString("tanggal_lahir");
                this.warga_negara = r.getString("warga_negara");
                this.agama = r.getString("agama");
                this.jenis_kelamin = r.getString("jenis_kelamin");
                this.pekerjaan = r.getString("pekerjaan");
                this.alamat_desa = r.getString("alamat_desa");
                this.alamat_kec = r.getString("alamat_kec");
                this.alamat_kab = r.getString("alamat_kab");
                this.alamat_prov = r.getString("alamat_prov");
                this.gol_darah = r.getString("gol_darah");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
