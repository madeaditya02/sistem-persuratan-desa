/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sisteminformasidesa;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import sisteminformasidesa.pagePimpinan;
import java.sql.Timestamp;
import java.time.Instant;


/**
 *
 * @author LENOVO
 */
public class pagePimpinan extends javax.swing.JFrame {
    private File selectedFile;            // File reference
    private byte[] fileData;
    private DefaultTableModel model;
    public pagePimpinan() {
        initComponents();
        model = new DefaultTableModel();
        TabelSuratLuar.setModel(model);
        
        // Add columns to the table
        model.addColumn("Sifat Surat");
        model.addColumn("Jenis Surat");
        model.addColumn("Nomor Surat");
        model.addColumn("Tanggal Surat");
        model.addColumn("Pengirim");
        model.addColumn("Jabatan Pengirim");
        model.addColumn("Perihal");
        model.addColumn("Catatan");
        model.addColumn("Lampiran");
        
        DefaultTableModel modelDisposisi = new DefaultTableModel();
        tabelDisposisi.setModel(modelDisposisi);

        modelDisposisi.addColumn("ID Disposisi");
        modelDisposisi.addColumn("Nomor Surat");
        modelDisposisi.addColumn("Tanggal Surat");
        modelDisposisi.addColumn("Perihal");
        modelDisposisi.addColumn("Penerima");
        modelDisposisi.addColumn("Instruksi");
        modelDisposisi.addColumn("Sifat");
        modelDisposisi.addColumn("Catatan");
        modelDisposisi.addColumn("Deadline");
        modelDisposisi.addColumn("Respon");
        modelDisposisi.addColumn("Lampiran");
        
        DefaultTableModel modelPengajuan = new DefaultTableModel();
        jTable4.setModel(modelPengajuan);
        
        modelPengajuan.addColumn("nomor_surat");
        modelPengajuan.addColumn("judul_surat");
        modelPengajuan.addColumn("nama_lengkap");
        modelPengajuan.addColumn("sekdes");
        modelPengajuan.addColumn("status_sekdes");
        modelPengajuan.addColumn("kepdes");
        modelPengajuan.addColumn("status_kepdes");
        modelPengajuan.addColumn("tahun");
        modelPengajuan.addColumn("tempat_lahir");
        modelPengajuan.addColumn("tanggal_lahir");
        modelPengajuan.addColumn("usia");
        modelPengajuan.addColumn("warga_negara");
        modelPengajuan.addColumn("agama");
        modelPengajuan.addColumn("jenis_kelamin");
        modelPengajuan.addColumn("Pekerjaan");
        modelPengajuan.addColumn("alamat_lengkap");
        modelPengajuan.addColumn("nik");
        modelPengajuan.addColumn("no_kk");
        modelPengajuan.addColumn("keperluan");
        modelPengajuan.addColumn("gol_darah");


// Daftar indeks kolom yang ingin disembunyikan
        int[] kolomYangDisembunyikan = {7,8,9,10,11,12,13,14,15,16,17,18,19}; // Indeks untuk "status_sekdes" dan "tahun"

// Loop untuk menyembunyikan kolom
        for (int index : kolomYangDisembunyikan) {
            jTable4.getColumnModel().getColumn(index).setMinWidth(0);
            jTable4.getColumnModel().getColumn(index).setMaxWidth(0);
            jTable4.getColumnModel().getColumn(index).setPreferredWidth(0);
        }

        
        
        kirimSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kirimSurat.setBackground(new java.awt.Color(0, 128, 0)); // Green background
                kirimSurat.setForeground(java.awt.Color.WHITE);          // White text
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                kirimSurat.setBackground(java.awt.Color.WHITE);          // Restore background
                kirimSurat.setForeground(new java.awt.Color(0, 128, 0)); // Restore text color
            }
        });
        suratMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                suratMasuk.setBackground(new java.awt.Color(0, 128, 0)); // Green background
                suratMasuk.setForeground(java.awt.Color.WHITE);          // White text
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                suratMasuk.setBackground(java.awt.Color.WHITE);          // Restore background
                suratMasuk.setForeground(new java.awt.Color(0, 128, 0)); // Restore text color
            }
        });
        disposisiMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                disposisiMasuk.setBackground(new java.awt.Color(0, 128, 0)); // Green background
                disposisiMasuk.setForeground(java.awt.Color.WHITE);          // White text
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                disposisiMasuk.setBackground(java.awt.Color.WHITE);          // Restore background
                disposisiMasuk.setForeground(new java.awt.Color(0, 128, 0)); // Restore text color
            }
        });
        kirimDisposisi.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kirimDisposisi.setBackground(new java.awt.Color(0, 128, 0)); // Green background
                kirimDisposisi.setForeground(java.awt.Color.WHITE);          // White text
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                kirimDisposisi.setBackground(java.awt.Color.WHITE);          // Restore background
                kirimDisposisi.setForeground(new java.awt.Color(0, 128, 0)); // Restore text color
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable4.getSelectedRow();
                if (row != -1){
                    String nomorSurat = (String) jTable4.getValueAt(row, 0); // Misalnya kolom 0 adalah nomor surat
                    String judulSurat = (String) jTable4.getValueAt(row, 1); // Misalnya kolom 1 adalah judul surat
                    String namaLengkap = (String) jTable4.getValueAt(row, 2); // Misalnya kolom 2 adalah nama lengkap
                    String tahun = (String) jTable4.getValueAt(row, 7);
                    String tempat_lahir = (String) jTable4.getValueAt(row, 8);
                    String tanggal_lahir = (String) jTable4.getValueAt(row, 9);
                    String usia = (String) jTable4.getValueAt(row, 10);
                    String wargaNegara = (String) jTable4.getValueAt(row, 11);
                    String agama = (String) jTable4.getValueAt(row, 12);
                    String jenisKelamin = (String) jTable4.getValueAt(row, 13);
                    String pekerjaan = (String) jTable4.getValueAt(row, 14);
                    String alamatLengkap = (String) jTable4.getValueAt(row, 15);
                    String nik = (String) jTable4.getValueAt(row, 16);
                    String no_kk = (String) jTable4.getValueAt(row, 17);
                    String keperluan = (String) jTable4.getValueAt(row, 18);
                    String golDarah = (String) jTable4.getValueAt(row, 19);

                    // Buat instance DetailSurat
                    DetailSurat detailSurat = new DetailSurat();

                    // Set data ke DetailSurat
                    detailSurat.setNomorSurat(nomorSurat);
                    detailSurat.setJudulSurat(judulSurat);
                    detailSurat.setNamaLengkap(namaLengkap);
                    detailSurat.setTahun(tahun);
                    detailSurat.setTempatLahir(tempat_lahir);
                    detailSurat.setTanggalLahir(tanggal_lahir);
                    detailSurat.setUsia(usia);
                    detailSurat.setWargaNegara(wargaNegara);
                    detailSurat.setAgama(agama);
                    detailSurat.setJenisKelamin(jenisKelamin);
                    detailSurat.setPekerjaan(pekerjaan);
                    detailSurat.setAlamatLengkap(alamatLengkap);
                    detailSurat.setNik(nik);
                    detailSurat.setNokk(no_kk);
                    detailSurat.setKeperluan(keperluan);
                    detailSurat.setGolDarah(golDarah);

                    // Tampilkan DetailSurat
                    detailSurat.setVisible(true);
                    
                    try {
                        DatabaseCRUD db = new DatabaseCRUD();
                        Connection conn = db.koneksi;
                     // SQL untuk menambahkan baris baru
                        String sql = "INSERT INTO status_validasi (waktu_dibuka_kepdes) VALUES (?)"; // Sesuaikan kolom_lain dengan nama kolom sebenarnya
                        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                            Timestamp timestamp = Timestamp.from(Instant.now()); // Mendapatkan waktu sekarang
                            stmt.setTimestamp(1, timestamp); // Ganti dengan data yang sesuai untuk kolom_lain
        
                            stmt.executeUpdate();

                            // Ambil ID yang baru saja dibuat (opsional)
                            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    int idBaru = generatedKeys.getInt(1);
                                    detailSurat.setIdValidasi(idBaru);
                                }
                            }
                        }
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
                    pagePimpinan.this.dispose();
                    
                }// White text
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                kirimDisposisi.setBackground(java.awt.Color.WHITE);          // Restore background
                kirimDisposisi.setForeground(new java.awt.Color(0, 128, 0)); // Restore text color
            }
        });
    }
    private void loadTablePengajuan() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            String query = "SELECT surat.nomor_surat, surat.judul_surat, surat.tahun, warga.nama_lengkap, "
                    + "status_validasi.sekdes, status_validasi.status_sekdes, status_validasi.waktu_dibuka_sekdes, "
                    + "status_validasi.waktu_divalidasi_sekdes, status_validasi.kepdes, status_validasi.status_kepdes, "
                    + "status_validasi.status_sekdes, status_validasi.waktu_dibuka_kepdes, status_validasi.waktu_divalidasi_kepdes, "
                    + "warga.tempat_lahir, warga.tanggal_lahir, TIMESTAMPDIFF(YEAR, warga.tanggal_lahir, CURRENT_DATE()) AS usia, "
                    + "warga.warga_negara, warga.agama, warga.jenis_kelamin, warga.Pekerjaan, warga.alamat_lengkap, warga.nik, "
                    + "warga.no_kk, surat.keperluan, warga.gol_darah\n"
                    + "FROM surat LEFT JOIN warga ON surat.nik=warga.nik LEFT JOIN status_validasi ON surat.nomor_surat=status_validasi.nomor_surat\n" 
                    + "WHERE surat.nomor_surat IS NOT NULL";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel modelPengajuan= (DefaultTableModel) jTable4.getModel();
            //model.setRowCount(0); 

            while (rs.next()) {
                Object[] row = {
                    rs.getString("nomor_surat"),
                    rs.getString("judul_surat"),
                    rs.getString("nama_lengkap"),
                    rs.getString("sekdes"),
                    rs.getString("status_sekdes"),
                    rs.getString("kepdes"),
                    rs.getString("status_kepdes"),
                    rs.getString("tahun"),
                    rs.getString("tempat_lahir"),
                    rs.getString("tanggal_lahir"),
                    rs.getString("usia"),
                    rs.getString("warga_negara"),
                    rs.getString("agama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("Pekerjaan"),
                    rs.getString("alamat_lengkap"),
                    rs.getString("nik"),
                    rs.getString("no_kk"),
                    rs.getString("keperluan"),
                    rs.getString("gol_darah"),

                };
                modelPengajuan.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());}
}
    private void loadSuratLuarTable() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            String query = "SELECT * FROM surat_luar";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel modelPengajuan = (DefaultTableModel) TabelSuratLuar.getModel();
            //model.setRowCount(0); 

            while (rs.next()) {
                Object[] row = {
                    rs.getString("sifat_surat"),
                    rs.getString("jenis_surat"),
                    rs.getString("no_surat"),
                    rs.getString("tanggal_surat"),
                    rs.getString("NamaPengirim"),
                    rs.getString("jabatan"),
                    rs.getString("perihal"),
                    rs.getString("catatan"),
                    rs.getBytes("lampiran"),
                };
                modelPengajuan.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());
        }
    }
     private void loadHistoryTable() {
    // Membersihkan data yang ada di tabel
    DefaultTableModel model = (DefaultTableModel) tabelhistory.getModel();
    model.getDataVector().removeAllElements();
    model.fireTableDataChanged();
    
    try {
        // Membuat koneksi ke database
        DatabaseCRUD db = new DatabaseCRUD(); // Pastikan class ini memiliki properti koneksi
        Connection conn = db.koneksi;
        
        // Query untuk mengambil data dari tabel disposisi dan surat_luar
        
         String query = "SELECT surat_luar.id_mail, surat_luar.sifat_surat, "
                + "surat_luar.no_surat, surat_luar.tanggal_surat, surat_luar.NamaPengirim, "
                + "surat_luar.perihal, surat_luar.lampiran, "
                + "status_surat_luar.status, status_surat_luar.timestamp "
                + "FROM surat_luar INNER JOIN status_surat_luar "
                 + "ON surat_luar.id_mail = status_surat_luar.id_mail; ";
        
        // Menyiapkan statement dan eksekusi query
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        // Iterasi hasil query untuk mengisi data ke dalam tabel
        while (rs.next()) {
            Object[] row = {
                rs.getInt("id_mail"),           // ID surat
                rs.getString("sifat_surat"),    // Sifat surat
                rs.getString("no_surat"),       // Nomor surat
                rs.getString("tanggal_surat"),  // Tanggal surat
                rs.getString("nama_pengirim"),  // Nama pengirim
                rs.getString("perihal"),        // Perihal
                rs.getBytes("lampiran"),        // Lampiran
                rs.getString("status"),         // Status surat
                rs.getString("timestamp")       // Timestamp
            };
            model.addRow(row); // Menambahkan data ke model tabel
        }
        // Tutup koneksi setelah selesai
        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());
    }
     }
    
    private void loadDisposisiTable() {
    model.getDataVector().removeAllElements();
    model.fireTableDataChanged();
    
    try {
        DatabaseCRUD db = new DatabaseCRUD();
        Connection conn = db.koneksi;
        
        // Updated query to exclude created_at and updated_at but include additional columns from surat_luar
        String query = "SELECT disposisi.id_disposisi, disposisi.id_mail, "
                + "surat_luar.no_surat, surat_luar.tanggal_surat, surat_luar.perihal, "
                + "disposisi.penerima, disposisi.instruksi, disposisi.sifat, disposisi.catatan, "
                + "disposisi.deadline, disposisi.respon, surat_luar.lampiran "
                + "FROM disposisi "
                + "INNER JOIN surat_luar ON disposisi.id_mail = surat_luar.id_mail;";
        
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        
        // Get the table model
        DefaultTableModel model = (DefaultTableModel) tabelDisposisi.getModel();

        while (rs.next()) {
            Object[] row = {
                rs.getInt("id_disposisi"),
                rs.getString("no_surat"),      // From surat_luar
                rs.getString("tanggal_surat"), // From surat_luar
                rs.getString("perihal"),       // From surat_luar
                rs.getString("penerima"),
                rs.getString("instruksi"),
                rs.getString("sifat"),
                rs.getString("catatan"),
                rs.getString("deadline"),
                rs.getString("respon"),
                rs.getBytes("lampiran"), // If you need to handle binary data like images/files
            };
            model.addRow(row);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());
    }
    }
    
    
    private void populatesifatBox() {
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            // Get enum values from database metadata
            String query = "SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS " +
                           "WHERE TABLE_SCHEMA = 'kantor_desa' " +
                           "AND TABLE_NAME = 'surat_luar' " +
                           "AND COLUMN_NAME = 'sifat_surat'"; // Replace with your column and table name

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String columnType = rs.getString("COLUMN_TYPE");
                String enumValuesString = columnType.substring(columnType.indexOf("(") + 1, columnType.indexOf(")"));
                String[] enumValues = enumValuesString.replaceAll("'", "").split(",");

                // Correct way to add items to JComboBox
                for (String value : enumValues) {
                    sifatBox.addItem(value);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error populating combobox: " + e.getMessage());
        }
    }
      private void populatesifatBoxDisposisi() {
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            // Get enum values from database metadata
            String query = "SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS " +
                           "WHERE TABLE_SCHEMA = 'kantor_desa' " +
                           "AND TABLE_NAME = 'surat_luar' " +
                           "AND COLUMN_NAME = 'sifat_surat'"; // Replace with your column and table name

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String columnType = rs.getString("COLUMN_TYPE");
                String enumValuesString = columnType.substring(columnType.indexOf("(") + 1, columnType.indexOf(")"));
                String[] enumValues = enumValuesString.replaceAll("'", "").split(",");

                // Correct way to add items to JComboBox
                for (String value : enumValues) {
                    sifatsuratdisposisibox.addItem(value);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error populating combobox: " + e.getMessage());
        }
    }
    
    private void populatepnerimaBoxDisposisi() {
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            String query = "SELECT * FROM jabatan WHERE Id_Jabatan > 4"; 
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Nama_Jabatan");
                penerimaDisposisiBox.addItem(username); 
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error populating combobox: " + e.getMessage());
        }
    }
    private void populateintruksiDisposisi() {
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            String query = "SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS " +
                           "WHERE TABLE_SCHEMA = 'kantor_desa' " +
                           "AND TABLE_NAME = 'disposisi' " +
                           "AND COLUMN_NAME = 'instruksi'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String columnType = rs.getString("COLUMN_TYPE");
                String enumValuesString = columnType.substring(columnType.indexOf("(") + 1, columnType.indexOf(")"));
                String[] enumValues = enumValuesString.replaceAll("'", "").split(",");

                // Correct way to add items to JComboBox
                for (String value : enumValues) {
                    instruksiDisposisiBox.addItem(value);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error populating combobox: " + e.getMessage());
        }
        
    }
    public void setActiveTab(int index) {
        kirimDisposisiTab.setSelectedIndex(index);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        kirimDisposisiTab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kirimdatasurat = new javax.swing.JButton();
        sifatSurat = new javax.swing.JTextField();
        jenisSurat = new javax.swing.JTextField();
        nomorSurat = new javax.swing.JTextField();
        tanggalSurat = new javax.swing.JTextField();
        pengirim = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        catatan = new javax.swing.JTextArea();
        jabatanPengirimField = new javax.swing.JTextField();
        perihal = new javax.swing.JTextField();
        lampiran = new javax.swing.JTextField();
        attachFile = new javax.swing.JButton();
        sifatBox = new javax.swing.JComboBox<>();
        jabatanPengirim = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jComboBox18 = new javax.swing.JComboBox<>();
        jComboBox19 = new javax.swing.JComboBox<>();
        jComboBox20 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelSuratLuar = new javax.swing.JTable();
        SifatField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jenisField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        NomorField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tanggalField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        PengirimField = new javax.swing.JTextField();
        perihalField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jabatanField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        FileNameField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        catatanField = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        dispossiBtn = new javax.swing.JButton();
        ViewfileButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        sifatSuratDisposisi = new javax.swing.JTextField();
        nomorSuratDisposisi = new javax.swing.JTextField();
        tanggalSuratDisposisi = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        catatanIntruksiDisposisi = new javax.swing.JTextArea();
        disposisiIntruksi = new javax.swing.JTextField();
        penerimaDisposisi = new javax.swing.JTextField();
        perihalDisposisi = new javax.swing.JTextField();
        deadlineDisposisi = new javax.swing.JTextField();
        kirimDisposisiSurat = new javax.swing.JButton();
        penerimaDisposisiBox = new javax.swing.JComboBox<>();
        instruksiDisposisiBox = new javax.swing.JComboBox<>();
        sifatsuratdisposisibox = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        sifatDisposisiMasuk = new javax.swing.JTextField();
        noDisposisiMasuk = new javax.swing.JTextField();
        tanggalDisposisiMasuk = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        catatanDisposisiMasuk = new javax.swing.JTextArea();
        instruksiDisposisiMasuk = new javax.swing.JTextField();
        perihalDisposisiMasuk = new javax.swing.JTextField();
        deadlineDisposisiMasuk = new javax.swing.JTextField();
        terimaBTN = new javax.swing.JButton();
        tolakBTN = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelDisposisi = new javax.swing.JTable();
        jLabel74 = new javax.swing.JLabel();
        lampiranDisposisiMasuk = new javax.swing.JTextField();
        View = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelhistory = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        kirimSurat = new javax.swing.JButton();
        suratMasuk = new javax.swing.JButton();
        kirimDisposisi = new javax.swing.JButton();
        disposisiMasuk = new javax.swing.JButton();
        pengajuanSurat = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        pengajuanSurat1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Sifat Surat");

        jLabel3.setText("Jenis Surat");

        jLabel4.setText("Nomor Surat");

        jLabel5.setText("Tanggal Surat");

        jLabel6.setText("Pengirim");

        jLabel8.setText("Jabatan Pengirim");

        jLabel9.setText("perihal");

        jLabel10.setText("Catatan");

        jLabel11.setText("Lampiran");

        kirimdatasurat.setText("Kirim");
        kirimdatasurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kirimdatasuratActionPerformed(evt);
            }
        });

        sifatSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifatSuratActionPerformed(evt);
            }
        });

        tanggalSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalSuratActionPerformed(evt);
            }
        });

        pengirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengirimActionPerformed(evt);
            }
        });

        catatan.setColumns(20);
        catatan.setRows(5);
        jScrollPane1.setViewportView(catatan);

        jabatanPengirimField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jabatanPengirimFieldActionPerformed(evt);
            }
        });

        attachFile.setText("Attach");
        attachFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachFileActionPerformed(evt);
            }
        });

        sifatBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifatBoxActionPerformed(evt);
            }
        });

        jabatanPengirim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gubernur", "Bupati", "Camat", "Other" }));
        jabatanPengirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jabatanPengirimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kirimdatasurat)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tanggalSurat)
                            .addComponent(sifatSurat)
                            .addComponent(jenisSurat)
                            .addComponent(nomorSurat)
                            .addComponent(pengirim)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                            .addComponent(jabatanPengirimField)
                            .addComponent(perihal)
                            .addComponent(lampiran))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attachFile)
                            .addComponent(sifatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jabatanPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(308, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sifatSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sifatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jenisSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nomorSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tanggalSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(pengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jabatanPengirimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jabatanPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(perihal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lampiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(attachFile)))
                .addGap(18, 18, 18)
                .addComponent(kirimdatasurat)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kirimDisposisiTab.addTab("Kirim Surat", jPanel2);

        jButton11.setText("Cari");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox18ActionPerformed(evt);
            }
        });

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TabelSuratLuar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelSuratLuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelSuratLuarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelSuratLuar);

        SifatField.setText("jTextField1");
        SifatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SifatFieldActionPerformed(evt);
            }
        });

        jLabel12.setText("Sifat Surat");

        jenisField.setText("jTextField2");
        jenisField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisFieldActionPerformed(evt);
            }
        });

        jLabel13.setText("Jenis Surat");

        jLabel14.setText("Nomor Surat");

        NomorField.setText("jTextField3");

        jLabel15.setText("Tanggal Surat");

        tanggalField.setText("jTextField4");

        jLabel16.setText("Pengirim");

        PengirimField.setText("jTextField5");

        perihalField.setText("jTextField8");
        perihalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perihalFieldActionPerformed(evt);
            }
        });

        jLabel17.setText("perihal");

        jabatanField.setText("jTextField6");
        jabatanField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jabatanFieldActionPerformed(evt);
            }
        });

        jLabel19.setText("Jabatan Pengirim");

        jLabel20.setText("Lampiran");

        FileNameField.setText("jTextField9");

        catatanField.setColumns(20);
        catatanField.setRows(5);
        jScrollPane4.setViewportView(catatanField);

        jLabel21.setText("Catatan");

        dispossiBtn.setText("Disposisi");
        dispossiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispossiBtnActionPerformed(evt);
            }
        });

        ViewfileButton.setText("View");
        ViewfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewfileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(PengirimField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(perihalField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jabatanField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NomorField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tanggalField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jenisField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SifatField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(839, 839, 839)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel16)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
                            .addComponent(jButton11)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(383, 383, 383)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20)
                                    .addComponent(dispossiBtn))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(FileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ViewfileButton)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(SifatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jenisField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(NomorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(tanggalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(PengirimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jabatanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(perihalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewfileButton)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addComponent(dispossiBtn)))
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel13);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
        );

        kirimDisposisiTab.addTab("Surat masuk", jPanel5);

        jLabel42.setText("Sifat Surat");

        jLabel44.setText("Nomor Surat");

        jLabel45.setText("Tanggal Surat");

        jLabel46.setText("Instruksi");

        jLabel48.setText("Penerima");

        jLabel49.setText("perihal");

        jLabel50.setText("Catatan instruksi");

        jLabel51.setText("Deadline");

        sifatSuratDisposisi.setText("jTextField1");
        sifatSuratDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifatSuratDisposisiActionPerformed(evt);
            }
        });

        nomorSuratDisposisi.setText("jTextField3");
        nomorSuratDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomorSuratDisposisiActionPerformed(evt);
            }
        });

        tanggalSuratDisposisi.setText("jTextField4");

        catatanIntruksiDisposisi.setColumns(20);
        catatanIntruksiDisposisi.setRows(5);
        jScrollPane10.setViewportView(catatanIntruksiDisposisi);

        disposisiIntruksi.setText("jTextField6");
        disposisiIntruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disposisiIntruksiActionPerformed(evt);
            }
        });

        penerimaDisposisi.setText("jTextField7");

        perihalDisposisi.setText("jTextField8");

        deadlineDisposisi.setText("jTextField9");

        kirimDisposisiSurat.setText("Kirim");
        kirimDisposisiSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kirimDisposisiSuratActionPerformed(evt);
            }
        });

        penerimaDisposisiBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penerimaDisposisiBoxActionPerformed(evt);
            }
        });

        instruksiDisposisiBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instruksiDisposisiBoxActionPerformed(evt);
            }
        });

        sifatsuratdisposisibox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifatsuratdisposisiboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kirimDisposisiSurat)
                    .addComponent(jLabel45)
                    .addComponent(jLabel49)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(deadlineDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(penerimaDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(perihalDisposisi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tanggalSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(nomorSuratDisposisi, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel50))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                    .addComponent(disposisiIntruksi)
                                    .addComponent(sifatSuratDisposisi))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instruksiDisposisiBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sifatsuratdisposisibox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(penerimaDisposisiBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(nomorSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(tanggalSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(perihalDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(penerimaDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(penerimaDisposisiBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(disposisiIntruksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instruksiDisposisiBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sifatSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(sifatsuratdisposisibox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deadlineDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kirimDisposisiSurat)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(jPanel12);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kirimDisposisiTab.addTab("Kirim Disposisi", jPanel6);

        jLabel43.setText("Sifat Surat");

        jLabel47.setText("Nomor Surat");

        jLabel52.setText("Tanggal Surat");

        jLabel53.setText("Instruksi");

        jLabel55.setText("perihal");

        jLabel56.setText("Catatan instruksi");

        jLabel57.setText("Deadline");

        sifatDisposisiMasuk.setText("jTextField1");

        noDisposisiMasuk.setText("jTextField3");

        tanggalDisposisiMasuk.setText("jTextField4");

        catatanDisposisiMasuk.setColumns(20);
        catatanDisposisiMasuk.setRows(5);
        jScrollPane12.setViewportView(catatanDisposisiMasuk);

        instruksiDisposisiMasuk.setText("jTextField6");

        perihalDisposisiMasuk.setText("jTextField8");

        deadlineDisposisiMasuk.setText("jTextField9");

        terimaBTN.setText("Terima");
        terimaBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terimaBTNActionPerformed(evt);
            }
        });

        tolakBTN.setText("Tolak");
        tolakBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tolakBTNActionPerformed(evt);
            }
        });

        tabelDisposisi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelDisposisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDisposisiMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabelDisposisi);

        jLabel74.setText("Lampiran");

        lampiranDisposisiMasuk.setText("jTextField9");

        View.setText("View");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53)
                                .addComponent(jLabel56))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane12)
                                .addComponent(instruksiDisposisiMasuk)
                                .addComponent(sifatDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel57)
                                .addComponent(terimaBTN))
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addGap(6, 6, Short.MAX_VALUE)
                                    .addComponent(tolakBTN)
                                    .addGap(470, 470, 470))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(deadlineDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addComponent(jLabel52)
                    .addComponent(jLabel55)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addGap(106, 106, 106)
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(perihalDisposisiMasuk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tanggalDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(noDisposisiMasuk, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lampiranDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(View)
                            .addGap(6, 6, 6))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(noDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lampiranDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74)
                            .addComponent(View))))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(tanggalDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(perihalDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(instruksiDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sifatDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deadlineDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(terimaBTN)
                    .addComponent(tolakBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel14);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        kirimDisposisiTab.addTab("Disposisi Masuk", jPanel7);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(370, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        kirimDisposisiTab.addTab("Pengajuan Surat", jPanel8);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel1.setText("Histori Surat");

        tabelhistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id mail", "Sifat surat", "No surat", "Tanggal surat", "Nama pengirim", "Perihal", "Lampiran", "Status", "Timestamp"
            }
        ));
        jScrollPane7.setViewportView(tabelhistory);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        kirimDisposisiTab.addTab("Histori Surat", jPanel1);

        getContentPane().add(kirimDisposisiTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 780, 470));

        jPanel10.setBackground(new java.awt.Color(0, 146, 89));

        kirimSurat.setText("Kirim Surat");

        suratMasuk.setText("Surat Masuk");

        kirimDisposisi.setText("Kirim Disposisi");

        disposisiMasuk.setText("Disposisi Masuk");

        pengajuanSurat.setText("Pengajuan surat");

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User_fill@3x (2).png"))); // NOI18N
        usernameLabel.setText("Username");
        usernameLabel.setMaximumSize(new java.awt.Dimension(501, 444));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Privilege user");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pengajuanSurat1.setText("Histori surat");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kirimSurat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suratMasuk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kirimDisposisi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(disposisiMasuk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pengajuanSurat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pengajuanSurat1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(kirimSurat)
                .addGap(7, 7, 7)
                .addComponent(suratMasuk)
                .addGap(7, 7, 7)
                .addComponent(kirimDisposisi)
                .addGap(7, 7, 7)
                .addComponent(disposisiMasuk)
                .addGap(7, 7, 7)
                .addComponent(pengajuanSurat)
                .addGap(7, 7, 7)
                .addComponent(pengajuanSurat1))
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        populatesifatBox();
        populatesifatBoxDisposisi();
        populatepnerimaBoxDisposisi();
        populateintruksiDisposisi();
        loadSuratLuarTable();
        loadDisposisiTable();
        loadHistoryTable();
        loadTablePengajuan();
    }//GEN-LAST:event_formWindowOpened

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked

    }//GEN-LAST:event_jTable4MouseClicked

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        int selectedRow = tabelDisposisi.getSelectedRow();

        if (selectedRow != -1) { // Check if a row is selected
            try {
                // 1. Fetch the byte array from the table model
                byte[] fileData = (byte[]) tabelDisposisi.getValueAt(selectedRow, 10);

                // 2. Determine the file type (you'll need to know this beforehand)
                String fileType = "pdf"; // Example: Assuming it's a PDF

                // 3. Create a temporary file
                File tempFile = File.createTempFile("tempFile", "." + fileType);
                tempFile.deleteOnExit(); // Delete the file when the JVM exits

                // 4. Write the byte array to the temporary file
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(fileData);
                fos.close();

                // 5. Open the file with the default application associated with the file type
                Desktop.getDesktop().open(tempFile);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row.");
        }
    }//GEN-LAST:event_ViewActionPerformed

    private void tabelDisposisiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDisposisiMouseClicked
        try {
            int x = tabelDisposisi.getSelectedRow();

            String noSurat = tabelDisposisi.getValueAt(x, 1).toString();
            String tanggalSurat = tabelDisposisi.getValueAt(x, 2).toString();
            String perihal = tabelDisposisi.getValueAt(x, 3).toString();
            String instruksi = tabelDisposisi.getValueAt(x, 5).toString();
            String sifat = tabelDisposisi.getValueAt(x, 6).toString();
            String catatan = tabelDisposisi.getValueAt(x, 7).toString();
            String deadline = tabelDisposisi.getValueAt(x, 8).toString();
            String lampiranDisposisi = tabelDisposisi.getValueAt(x, 10).toString();

            // Set values to UI components
            noDisposisiMasuk.setText(noSurat);
            tanggalDisposisiMasuk.setText(tanggalSurat);
            perihalDisposisiMasuk.setText(perihal);
            instruksiDisposisiMasuk.setText(instruksi);
            sifatDisposisiMasuk.setText(sifat);
            catatanDisposisiMasuk.setText(catatan);
            deadlineDisposisiMasuk.setText(deadline);
            lampiranDisposisiMasuk.setText(lampiranDisposisi);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tabelDisposisiMouseClicked

    private void tolakBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tolakBTNActionPerformed
        try {
            int x = tabelDisposisi.getSelectedRow();

            if (x == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row first!", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idDisposisi = Integer.parseInt(tabelDisposisi.getValueAt(x, 0).toString()); // Assuming column 0 is id_disposisi

            // Database update query
            String query = "UPDATE disposisi SET respon = 'Ditolak' WHERE id_disposisi = ?";

            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idDisposisi);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Response updated to 'Ditolak' successfully!");
                loadDisposisiTable(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update response!", "Update Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tolakBTNActionPerformed

    private void terimaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terimaBTNActionPerformed
        try {
            int x = tabelDisposisi.getSelectedRow();

            if (x == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row first!", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idDisposisi = Integer.parseInt(tabelDisposisi.getValueAt(x, 0).toString()); // Assuming column 0 is id_disposisi

            // Database update query
            String query = "UPDATE disposisi SET respon = 'Diterima' WHERE id_disposisi = ?";

            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idDisposisi);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Response updated to 'Diterima' successfully!");
                loadDisposisiTable(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update response!", "Update Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_terimaBTNActionPerformed

    private void sifatsuratdisposisiboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatsuratdisposisiboxActionPerformed
        String selectedValue = (String) sifatsuratdisposisibox.getSelectedItem();
        sifatSuratDisposisi.setText(selectedValue);            // TODO add your handling code here:
    }//GEN-LAST:event_sifatsuratdisposisiboxActionPerformed

    private void instruksiDisposisiBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instruksiDisposisiBoxActionPerformed
        String selectedValue = (String) instruksiDisposisiBox.getSelectedItem();
        disposisiIntruksi.setText(selectedValue); // TODO add your handling code here:
    }//GEN-LAST:event_instruksiDisposisiBoxActionPerformed

    private void penerimaDisposisiBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penerimaDisposisiBoxActionPerformed
        String selectedValue = (String) penerimaDisposisiBox.getSelectedItem();
        penerimaDisposisi.setText(selectedValue);
    }//GEN-LAST:event_penerimaDisposisiBoxActionPerformed

    private void kirimDisposisiSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kirimDisposisiSuratActionPerformed
        String PenerimaDisposisi = penerimaDisposisi.getText();
        String IntruksiDisposisi = disposisiIntruksi.getText();
        String SifatSuratDisposisi = sifatSuratDisposisi.getText();
        String CatatanDisposisi = catatanIntruksiDisposisi.getText();
        String DeadlineDisposisi = deadlineDisposisi.getText();
        String nomorSurat = nomorSuratDisposisi.getText();
        try{
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            // 3. Fetch Penerima ID
            String getIdJabatanQuery = "SELECT Id_Jabatan FROM jabatan WHERE Nama_Jabatan = ?";
            PreparedStatement getidJabatanStmt = conn.prepareStatement(getIdJabatanQuery);
            getidJabatanStmt.setString(1, PenerimaDisposisi);
            ResultSet JabatanIdResult = getidJabatanStmt.executeQuery();
            int JabatanId = -1;
            if (JabatanIdResult.next()) {
                JabatanId = JabatanIdResult.getInt("Id_Jabatan");
            }
            // 3. Fetch Penerima ID
            String getIdMailQuery = "SELECT id_mail FROM surat_luar WHERE no_surat = ?";
            PreparedStatement getIdMailStmt = conn.prepareStatement(getIdMailQuery);
            getIdMailStmt.setString(1, nomorSurat); // Replace 'noSurat' with the actual variable containing the no_surat value
            ResultSet mailIdResult = getIdMailStmt.executeQuery();
            int mailId = -1;
            if (mailIdResult.next()) {
                mailId = mailIdResult.getInt("id_mail");
            }

            String query = "INSERT INTO disposisi (id_mail, penerima, instruksi, sifat, catatan, deadline) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, mailId);
            stmt.setInt(2, JabatanId);
            stmt.setString(3, IntruksiDisposisi);
            stmt.setString(4, SifatSuratDisposisi);
            stmt.setString(5, CatatanDisposisi);
            stmt.setString(6, DeadlineDisposisi);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data successfully added!");
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_kirimDisposisiSuratActionPerformed

    private void disposisiIntruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disposisiIntruksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disposisiIntruksiActionPerformed

    private void nomorSuratDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomorSuratDisposisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomorSuratDisposisiActionPerformed

    private void sifatSuratDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatSuratDisposisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sifatSuratDisposisiActionPerformed

    private void ViewfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewfileButtonActionPerformed
        int selectedRow = TabelSuratLuar.getSelectedRow();

        if (selectedRow != -1) { // Check if a row is selected
            try {
                // 1. Fetch the byte array from the table model
                byte[] fileData = (byte[]) TabelSuratLuar.getValueAt(selectedRow, 8);

                // 2. Determine the file type (you'll need to know this beforehand)
                String fileType = "pdf"; // Example: Assuming it's a PDF

                // 3. Create a temporary file
                File tempFile = File.createTempFile("tempFile", "." + fileType);
                tempFile.deleteOnExit(); // Delete the file when the JVM exits

                // 4. Write the byte array to the temporary file
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(fileData);
                fos.close();

                // 5. Open the file with the default application associated with the file type
                Desktop.getDesktop().open(tempFile);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row.");
        }
    }//GEN-LAST:event_ViewfileButtonActionPerformed

    private void dispossiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispossiBtnActionPerformed
        String SifatSurat = SifatField.getText();
        String NomorSurat = NomorField.getText();
        String TanggalSurat = tanggalField.getText();
        String Perihal = perihalField.getText();

        sifatSuratDisposisi.setText(SifatSurat);
        sifatSuratDisposisi.setEditable(false);
        nomorSuratDisposisi.setText(NomorSurat);
        nomorSuratDisposisi.setEditable(false);
        tanggalSuratDisposisi.setText(TanggalSurat);
        tanggalSuratDisposisi.setEditable(false);
        perihalDisposisi.setText(Perihal);
        perihalDisposisi.setEditable(false);
        kirimDisposisiTab.setSelectedIndex(2);

    }//GEN-LAST:event_dispossiBtnActionPerformed

    private void jabatanFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jabatanFieldActionPerformed

    private void perihalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perihalFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perihalFieldActionPerformed

    private void jenisFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenisFieldActionPerformed

    private void SifatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SifatFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SifatFieldActionPerformed

    private void TabelSuratLuarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelSuratLuarMouseClicked
        int x = TabelSuratLuar.getSelectedRow();
        String SifatSurat = TabelSuratLuar.getValueAt(x, 0).toString();
        String JenisSurat = TabelSuratLuar.getValueAt(x, 1).toString();
        String NomorSurat = TabelSuratLuar.getValueAt(x, 2).toString();
        String TanggalSurat = TabelSuratLuar.getValueAt(x, 3).toString();
        String Pengirim = TabelSuratLuar.getValueAt(x, 4).toString();
        String jabatan = TabelSuratLuar.getValueAt(x, 5).toString();
        String Perihal = TabelSuratLuar.getValueAt(x, 6).toString();
        String Catatan = TabelSuratLuar.getValueAt(x, 7).toString();
        String lampiran = TabelSuratLuar.getValueAt(x, 8).toString();

        SifatField.setText(SifatSurat);
        jenisField.setText(JenisSurat);
        NomorField.setText(NomorSurat);
        tanggalField.setText(TanggalSurat);
        PengirimField.setText(Pengirim);
        jabatanField.setText(jabatan);
        perihalField.setText(Perihal);
        catatanField.setText(Catatan);
        FileNameField.setText(lampiran);
    }//GEN-LAST:event_TabelSuratLuarMouseClicked

    private void jComboBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox18ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jabatanPengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanPengirimActionPerformed
        String selectedValue = (String) jabatanPengirim.getSelectedItem();
        if (selectedValue == "Other"){
            jabatanPengirimField.setEditable(true);
        } else {
            jabatanPengirimField.setEditable(false);
            jabatanPengirimField.setText(selectedValue);
        }
    }//GEN-LAST:event_jabatanPengirimActionPerformed

    private void sifatBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatBoxActionPerformed
        String selectedValue = (String) sifatBox.getSelectedItem();
        sifatSurat.setText(selectedValue);
    }//GEN-LAST:event_sifatBoxActionPerformed

    private void attachFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file to upload");

        int userChoice = fileChooser.showOpenDialog(this); // Open the file chooser

        if (userChoice == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile(); // Get the selected file
            String fileName = selectedFile.getName();     // Extract the file name

            // Display the file name in the JTextField (replace fileNameTextField with your JTextField name)
            lampiran.setText(fileName);

            try {
                // Read the file into a byte array
                fileData = Files.readAllBytes(selectedFile.toPath());
                System.out.println("File selected: " + fileName);
                System.out.println("File size: " + fileData.length + " bytes");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(),
                    "File Read Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            System.out.println("File selection cancelled.");
        }
    }//GEN-LAST:event_attachFileActionPerformed

    private void jabatanPengirimFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanPengirimFieldActionPerformed

    }//GEN-LAST:event_jabatanPengirimFieldActionPerformed

    private void pengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengirimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pengirimActionPerformed

    private void tanggalSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalSuratActionPerformed

    private void sifatSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sifatSuratActionPerformed

    private void kirimdatasuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kirimdatasuratActionPerformed
        String SifatSurat = sifatSurat.getText();
        String JenisSurat = jenisSurat.getText();
        String NomorSurat = nomorSurat.getText();
        String TanggalSurat = tanggalSurat.getText();
        String Pengirim = pengirim.getText();
        String jabatan = jabatanPengirimField.getText();
        String Penerima = jabatanPengirimField.getText();
        String Perihal = perihal.getText();
        String Catatan = catatan.getText();

        try{
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            String getPengirimIdQuery = "SELECT id_user FROM user WHERE nama_lengkap = ?";
            PreparedStatement getPengirimIdStmt = conn.prepareStatement(getPengirimIdQuery);
            getPengirimIdStmt.setString(1, Pengirim);
            ResultSet pengirimIdResult = getPengirimIdStmt.executeQuery();
            int pengirimId = -1;
            if (pengirimIdResult.next()) {
                pengirimId = pengirimIdResult.getInt("id_user");
            }

            String query = "INSERT INTO surat_luar (sifat_surat, jenis_surat, no_surat, tanggal_surat, NamaPengirim, jabatan, perihal, catatan, lampiran) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, SifatSurat);
            stmt.setString(2, JenisSurat);
            stmt.setString(3, NomorSurat);
            stmt.setString(4, TanggalSurat);
            stmt.setInt(5, pengirimId);
            stmt.setString(6, jabatan);
            stmt.setString(7, Perihal);
            stmt.setString(8, Catatan);
            stmt.setBytes(9, fileData);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data successfully added!");
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_kirimdatasuratActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pagePimpinan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pagePimpinan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pagePimpinan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pagePimpinan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pagePimpinan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FileNameField;
    private javax.swing.JTextField NomorField;
    private javax.swing.JTextField PengirimField;
    private javax.swing.JTextField SifatField;
    private javax.swing.JTable TabelSuratLuar;
    private javax.swing.JButton View;
    private javax.swing.JButton ViewfileButton;
    private javax.swing.JButton attachFile;
    private javax.swing.JTextArea catatan;
    private javax.swing.JTextArea catatanDisposisiMasuk;
    private javax.swing.JTextArea catatanField;
    private javax.swing.JTextArea catatanIntruksiDisposisi;
    private javax.swing.JTextField deadlineDisposisi;
    private javax.swing.JTextField deadlineDisposisiMasuk;
    private javax.swing.JTextField disposisiIntruksi;
    private javax.swing.JButton disposisiMasuk;
    private javax.swing.JButton dispossiBtn;
    private javax.swing.JComboBox<String> instruksiDisposisiBox;
    private javax.swing.JTextField instruksiDisposisiMasuk;
    private javax.swing.JButton jButton11;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jabatanField;
    private javax.swing.JComboBox<String> jabatanPengirim;
    private javax.swing.JTextField jabatanPengirimField;
    private javax.swing.JTextField jenisField;
    private javax.swing.JTextField jenisSurat;
    private javax.swing.JButton kirimDisposisi;
    private javax.swing.JButton kirimDisposisiSurat;
    private javax.swing.JTabbedPane kirimDisposisiTab;
    private javax.swing.JButton kirimSurat;
    private javax.swing.JButton kirimdatasurat;
    private javax.swing.JTextField lampiran;
    private javax.swing.JTextField lampiranDisposisiMasuk;
    private javax.swing.JTextField noDisposisiMasuk;
    private javax.swing.JTextField nomorSurat;
    private javax.swing.JTextField nomorSuratDisposisi;
    private javax.swing.JTextField penerimaDisposisi;
    private javax.swing.JComboBox<String> penerimaDisposisiBox;
    private javax.swing.JButton pengajuanSurat;
    private javax.swing.JButton pengajuanSurat1;
    private javax.swing.JTextField pengirim;
    private javax.swing.JTextField perihal;
    private javax.swing.JTextField perihalDisposisi;
    private javax.swing.JTextField perihalDisposisiMasuk;
    private javax.swing.JTextField perihalField;
    private javax.swing.JComboBox<String> sifatBox;
    private javax.swing.JTextField sifatDisposisiMasuk;
    private javax.swing.JTextField sifatSurat;
    private javax.swing.JTextField sifatSuratDisposisi;
    private javax.swing.JComboBox<String> sifatsuratdisposisibox;
    private javax.swing.JButton suratMasuk;
    private javax.swing.JTable tabelDisposisi;
    private javax.swing.JTable tabelhistory;
    private javax.swing.JTextField tanggalDisposisiMasuk;
    private javax.swing.JTextField tanggalField;
    private javax.swing.JTextField tanggalSurat;
    private javax.swing.JTextField tanggalSuratDisposisi;
    private javax.swing.JButton terimaBTN;
    private javax.swing.JButton tolakBTN;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
