/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sisteminformasidesa;

import java.awt.Desktop;
import java.util.List; 
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
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class pagePimpinan extends javax.swing.JFrame {
    private File selectedFile;            // File reference
    private byte[] fileData;
    public pagePimpinan() {
        initComponents();
        DefaultTableModel modelSuratLuar = new DefaultTableModel();
        TabelSuratLuar.setModel(modelSuratLuar);
        
        // Add columns to the table
        modelSuratLuar.addColumn("Sifat Surat");
        modelSuratLuar.addColumn("Jenis Surat");
        modelSuratLuar.addColumn("Nomor Surat");
        modelSuratLuar.addColumn("Tanggal Surat");
        modelSuratLuar.addColumn("Pengirim");
        modelSuratLuar.addColumn("Jabatan Pengirim");
        modelSuratLuar.addColumn("Perihal");
        modelSuratLuar.addColumn("Catatan");
        modelSuratLuar.addColumn("Lampiran");
        
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
    }
    public void fetchUserDetails(){
        try{
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            String userid = Session.id_user;
            int idjabatan = Session.id_jabatan;
            String query = "SELECT user.id_jabatan, user.nik, warga.nama_lengkap,jabatan.Nama_Jabatan "
                    + "FROM user "
                    + "INNER JOIN warga ON user.nik = warga.nik "
                    + "INNER JOIN jabatan ON user.id_jabatan = jabatan.Id_Jabatan "
                    + "WHERE user.id_user = ?;";
            
            PreparedStatement stmt = conn.prepareStatement (query);
            stmt.setString(1, Session.id_user);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) { 
                String username = rs.getString("nama_lengkap");
                String namaJabatan = rs.getString("Nama_Jabatan");
                usernameLabel.setText(username);
                jabatanBox.addItem(namaJabatan);
                jabatanBox.setSelectedItem(namaJabatan);
            } else {
                // Handle the case where no results are found
                JOptionPane.showMessageDialog(this, "User details not found."); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching1 data: " + e.getMessage());
        }
    }
    public void setTabVisibility() {
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            
            tabUtama.setEnabledAt(0, false);
            tabUtama.setEnabledAt(1, false);
            tabUtama.setEnabledAt(2, false);
            tabUtama.setEnabledAt(3, false);
            tabUtama.setEnabledAt(4, false);
            tabUtama.setEnabledAt(5, false);
            int idJabatan = Session.id_jabatan;
            
            String query = "SELECT idakses FROM jabatanakses WHERE Id_Jabatan = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Session.id_jabatan); // Use the id_jabatan from the Session
            ResultSet result = stmt.executeQuery();
            List<Integer> idAksesList = new ArrayList<>();
            while (result.next()) {
                idAksesList.add(result.getInt("idakses"));
            }
            
            for (Integer idAkses : idAksesList) {
                if (idAkses == 1) {
                    tabUtama.setEnabledAt(0, true);
                } else if (idAkses == 2) {
                    tabUtama.setEnabledAt(1, true);
                } else if (idAkses == 3) {
                    tabUtama.setEnabledAt(2, true);
                } else if (idAkses == 4) {
                    tabUtama.setEnabledAt(3, true);
                } else if (idAkses == 5) {
                    tabUtama.setEnabledAt(4, true);
                } else if (idAkses == 6) {
                    tabUtama.setEnabledAt(5, true);
                } 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
    }
    private void loadSuratLuarTable() {
        DefaultTableModel model = (DefaultTableModel) TabelSuratLuar.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            String query = "SELECT * FROM surat_luar ORDER BY created_at";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
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
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());
        }
    }
    private void loadHistoryTable() {
        // Membersihkan data yang ada di tabel
        DefaultTableModel modelhistoryTable = (DefaultTableModel) tabelhistory.getModel();
        modelhistoryTable.getDataVector().removeAllElements();

        try {
            // Membuat koneksi ke database
            DatabaseCRUD db = new DatabaseCRUD(); // Pastikan class ini memiliki properti koneksi
            Connection conn = db.koneksi;

            // Query untuk mengambil data dari tabel disposisi dan surat_luar

             String query = "SELECT surat_luar.id_mail, surat_luar.sifat_surat, surat_luar.no_surat, "
                     + "surat_luar.tanggal_surat, surat_luar.NamaPengirim, surat_luar.catatan, "
                     + "disposisi.respon, status_surat_luar.status, status_surat_luar.timestamp "
                     + "FROM surat_luar "
                     + "LEFT JOIN status_surat_luar ON surat_luar.id_mail = status_surat_luar.id_mail "
                     + "LEFT JOIN disposisi ON surat_luar.id_mail = disposisi.id_mail ORDER BY `status_surat_luar`.`timestamp` DESC;";

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
                    rs.getString("NamaPengirim"),  // Nama pengirim
                    rs.getString("catatan"),  
                    rs.getString("respon"), // Perihal
                    rs.getString("status"),         // Status surat
                    rs.getString("timestamp")       // Timestamp
                };
                modelhistoryTable.addRow(row); // Menambahkan data ke model tabel
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
    DefaultTableModel model = (DefaultTableModel) tabelDisposisi.getModel();
    int IdJabatan = Session.id_jabatan;
    try {
        DatabaseCRUD db = new DatabaseCRUD();
        Connection conn = db.koneksi;
        
        // Updated query to exclude created_at and updated_at but include additional columns from surat_luar
        String query = "SELECT disposisi.id_disposisi, disposisi.id_mail, "
                + "surat_luar.no_surat, surat_luar.tanggal_surat, surat_luar.perihal, "
                + "disposisi.penerima, disposisi.instruksi, disposisi.sifat, disposisi.catatan, "
                + "disposisi.deadline, disposisi.respon, surat_luar.lampiran "
                + "FROM disposisi "
                + "INNER JOIN surat_luar ON disposisi.id_mail = surat_luar.id_mail WHERE disposisi.penerima = ? ORDER BY disposisi.id_disposisi DESC;";
        
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, IdJabatan);
        ResultSet rs = stmt.executeQuery();

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
                           "AND COLUMN_NAME = 'sifat_surat'";

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

            String query = "SELECT * FROM jabatan WHERE Id_Jabatan > 2"; 
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
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        tabUtama = new javax.swing.JTabbedPane();
        kirimSuratPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab0 = new javax.swing.JPanel();
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
        suratMasukPanel = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tab1 = new javax.swing.JPanel();
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
        kirimDisposisiPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tab2 = new javax.swing.JPanel();
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
        disposisiMasukPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tab3 = new javax.swing.JPanel();
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
        pengajuanSuratPanel = new javax.swing.JPanel();
        tab4 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel64 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jTextField58 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jTextField59 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jTextField60 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jTextField63 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jTextField66 = new javax.swing.JTextField();
        historiSuratPanel = new javax.swing.JPanel();
        tab5 = new javax.swing.JPanel();
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
        jabatanBox = new javax.swing.JComboBox<>();
        pengajuanSurat1 = new javax.swing.JButton();
        logout = new javax.swing.JButton();

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

        javax.swing.GroupLayout tab0Layout = new javax.swing.GroupLayout(tab0);
        tab0.setLayout(tab0Layout);
        tab0Layout.setHorizontalGroup(
            tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab0Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kirimdatasurat)
                    .addGroup(tab0Layout.createSequentialGroup()
                        .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attachFile)
                            .addComponent(sifatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jabatanPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(308, Short.MAX_VALUE))
        );
        tab0Layout.setVerticalGroup(
            tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab0Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sifatSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sifatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jenisSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nomorSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(tanggalSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(pengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jabatanPengirimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jabatanPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(perihal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(tab0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lampiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(attachFile)))
                .addGap(18, 18, 18)
                .addComponent(kirimdatasurat)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(tab0);

        javax.swing.GroupLayout kirimSuratPanelLayout = new javax.swing.GroupLayout(kirimSuratPanel);
        kirimSuratPanel.setLayout(kirimSuratPanelLayout);
        kirimSuratPanelLayout.setHorizontalGroup(
            kirimSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kirimSuratPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        kirimSuratPanelLayout.setVerticalGroup(
            kirimSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kirimSuratPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabUtama.addTab("Kirim Surat", kirimSuratPanel);

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

        SifatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SifatFieldActionPerformed(evt);
            }
        });

        jLabel12.setText("Sifat Surat");

        jenisField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisFieldActionPerformed(evt);
            }
        });

        jLabel13.setText("Jenis Surat");

        jLabel14.setText("Nomor Surat");

        jLabel15.setText("Tanggal Surat");

        jLabel16.setText("Pengirim");

        perihalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perihalFieldActionPerformed(evt);
            }
        });

        jLabel17.setText("perihal");

        jabatanField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jabatanFieldActionPerformed(evt);
            }
        });

        jLabel19.setText("Jabatan Pengirim");

        jLabel20.setText("Lampiran");

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

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PengirimField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(perihalField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(tab1Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jabatanField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(tab1Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(NomorField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tanggalField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jenisField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SifatField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel16)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addGap(383, 383, 383)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20)
                                    .addComponent(dispossiBtn))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addComponent(FileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ViewfileButton))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(483, 483, 483)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(SifatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel21)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jenisField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(NomorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(tanggalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(PengirimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jabatanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(perihalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(FileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ViewfileButton)
                                    .addComponent(jLabel20))
                                .addGap(18, 18, 18)
                                .addComponent(dispossiBtn)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(tab1);

        javax.swing.GroupLayout suratMasukPanelLayout = new javax.swing.GroupLayout(suratMasukPanel);
        suratMasukPanel.setLayout(suratMasukPanelLayout);
        suratMasukPanelLayout.setHorizontalGroup(
            suratMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suratMasukPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
        );
        suratMasukPanelLayout.setVerticalGroup(
            suratMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suratMasukPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
        );

        tabUtama.addTab("Surat masuk", suratMasukPanel);

        jLabel42.setText("Sifat Surat");

        jLabel44.setText("Nomor Surat");

        jLabel45.setText("Tanggal Surat");

        jLabel46.setText("Instruksi");

        jLabel48.setText("Penerima");

        jLabel49.setText("perihal");

        jLabel50.setText("Catatan instruksi");

        jLabel51.setText("Deadline");

        sifatSuratDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifatSuratDisposisiActionPerformed(evt);
            }
        });

        nomorSuratDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomorSuratDisposisiActionPerformed(evt);
            }
        });

        catatanIntruksiDisposisi.setColumns(20);
        catatanIntruksiDisposisi.setRows(5);
        jScrollPane10.setViewportView(catatanIntruksiDisposisi);

        disposisiIntruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disposisiIntruksiActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kirimDisposisiSurat)
                    .addComponent(jLabel45)
                    .addComponent(jLabel49)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(deadlineDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab2Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(penerimaDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(perihalDisposisi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tanggalSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(nomorSuratDisposisi, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab2Layout.createSequentialGroup()
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel50))
                                .addGap(18, 18, 18)
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                    .addComponent(disposisiIntruksi)
                                    .addComponent(sifatSuratDisposisi))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instruksiDisposisiBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sifatsuratdisposisibox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(penerimaDisposisiBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(nomorSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(tanggalSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(perihalDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(penerimaDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(penerimaDisposisiBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(disposisiIntruksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instruksiDisposisiBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sifatSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(sifatsuratdisposisibox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deadlineDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kirimDisposisiSurat)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(tab2);

        javax.swing.GroupLayout kirimDisposisiPanelLayout = new javax.swing.GroupLayout(kirimDisposisiPanel);
        kirimDisposisiPanel.setLayout(kirimDisposisiPanelLayout);
        kirimDisposisiPanelLayout.setHorizontalGroup(
            kirimDisposisiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kirimDisposisiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addContainerGap())
        );
        kirimDisposisiPanelLayout.setVerticalGroup(
            kirimDisposisiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kirimDisposisiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabUtama.addTab("Kirim Disposisi", kirimDisposisiPanel);

        jLabel43.setText("Sifat Surat");

        jLabel47.setText("Nomor Surat");

        jLabel52.setText("Tanggal Surat");

        jLabel53.setText("Instruksi");

        jLabel55.setText("perihal");

        jLabel56.setText("Catatan instruksi");

        jLabel57.setText("Deadline");

        sifatDisposisiMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifatDisposisiMasukActionPerformed(evt);
            }
        });

        tanggalDisposisiMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalDisposisiMasukActionPerformed(evt);
            }
        });

        catatanDisposisiMasuk.setColumns(20);
        catatanDisposisiMasuk.setRows(5);
        jScrollPane12.setViewportView(catatanDisposisiMasuk);

        perihalDisposisiMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perihalDisposisiMasukActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab3Layout.createSequentialGroup()
                            .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53)
                                .addComponent(jLabel56))
                            .addGap(18, 18, 18)
                            .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane12)
                                .addComponent(instruksiDisposisiMasuk)
                                .addComponent(sifatDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(tab3Layout.createSequentialGroup()
                            .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel57)
                                .addComponent(terimaBTN))
                            .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(tab3Layout.createSequentialGroup()
                                    .addGap(6, 6, Short.MAX_VALUE)
                                    .addComponent(tolakBTN)
                                    .addGap(470, 470, 470))
                                .addGroup(tab3Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(deadlineDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addComponent(jLabel52)
                    .addComponent(jLabel55)
                    .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(tab3Layout.createSequentialGroup()
                            .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(tab3Layout.createSequentialGroup()
                                    .addGap(106, 106, 106)
                                    .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(noDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lampiranDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74)
                            .addComponent(View))))
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(tanggalDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(perihalDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(instruksiDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sifatDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deadlineDisposisiMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(terimaBTN)
                    .addComponent(tolakBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(tab3);

        javax.swing.GroupLayout disposisiMasukPanelLayout = new javax.swing.GroupLayout(disposisiMasukPanel);
        disposisiMasukPanel.setLayout(disposisiMasukPanelLayout);
        disposisiMasukPanelLayout.setHorizontalGroup(
            disposisiMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(disposisiMasukPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        disposisiMasukPanelLayout.setVerticalGroup(
            disposisiMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, disposisiMasukPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabUtama.addTab("Disposisi Masuk", disposisiMasukPanel);

        jLabel54.setText("Agama");

        jLabel58.setText("Nama");

        jLabel59.setText("TTL");

        jLabel60.setText("Warga Negara");

        jLabel61.setText("Umur");

        jLabel63.setText("Jenis Kelamin");

        jTextField51.setText("jTextField1");

        jTextField52.setText("jTextField3");

        jTextField53.setText("jTextField4");

        jTextField54.setText("jTextField6");

        jTextField55.setText("jTextField8");

        jTextField56.setText("jTextField9");

        jButton15.setText("Validasi");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Front Office");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

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
        jScrollPane14.setViewportView(jTable4);

        jLabel64.setText("Pekerjaan");

        jTextField57.setText("jTextField9");

        jLabel65.setText("Tempat Tinggal");

        jTextField58.setText("jTextField9");

        jLabel66.setText("Surat Bukti Diri :");

        jTextField59.setText("jTextField9");
        jTextField59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField59ActionPerformed(evt);
            }
        });

        jLabel67.setText("Keperluan");

        jTextField60.setText("jTextField9");

        jLabel68.setText("Berlaku");

        jTextField61.setText("jTextField9");

        jLabel69.setText("KTP :");

        jLabel70.setText("KK :");

        jTextField62.setText("jTextField9");

        jLabel71.setText("Golongan Darah");

        jTextField63.setText("jTextField9");

        jLabel62.setText("Judul");

        jTextField64.setText("jTextField3");

        jTextField65.setText("jTextField3");

        jLabel72.setText("No Surat");

        jLabel73.setText("Tanggal");

        jTextField66.setText("jTextField3");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(tab4Layout.createSequentialGroup()
                                    .addComponent(jLabel63)
                                    .addGap(34, 34, 34)
                                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab4Layout.createSequentialGroup()
                                    .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel60)
                                        .addComponent(jLabel61)
                                        .addComponent(jLabel59))
                                    .addGap(31, 31, 31)
                                    .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField54, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextField51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab4Layout.createSequentialGroup()
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                                        .addComponent(jLabel68)
                                        .addGap(49, 49, 49)
                                        .addComponent(jTextField61))
                                    .addGroup(tab4Layout.createSequentialGroup()
                                        .addComponent(jLabel67)
                                        .addGap(35, 35, 35)
                                        .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab4Layout.createSequentialGroup()
                                        .addComponent(jLabel66)
                                        .addGap(11, 11, 11)
                                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel70)
                                            .addComponent(jLabel69))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tab4Layout.createSequentialGroup()
                                                .addComponent(jTextField62)
                                                .addGap(130, 130, 130))
                                            .addGroup(tab4Layout.createSequentialGroup()
                                                .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(tab4Layout.createSequentialGroup()
                                        .addComponent(jLabel65)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel64)
                                .addGap(37, 37, 37)
                                .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(tab4Layout.createSequentialGroup()
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(tab4Layout.createSequentialGroup()
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(tab4Layout.createSequentialGroup()
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addGap(6, 6, 6)
                        .addComponent(jButton16)))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(27, 27, 27)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel64)
                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addGap(9, 9, 9)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63)))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pengajuanSuratPanelLayout = new javax.swing.GroupLayout(pengajuanSuratPanel);
        pengajuanSuratPanel.setLayout(pengajuanSuratPanelLayout);
        pengajuanSuratPanelLayout.setHorizontalGroup(
            pengajuanSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
            .addGroup(pengajuanSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pengajuanSuratPanelLayout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(tab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );
        pengajuanSuratPanelLayout.setVerticalGroup(
            pengajuanSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
            .addGroup(pengajuanSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pengajuanSuratPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabUtama.addTab("Pengajuan Surat", pengajuanSuratPanel);

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
                "Id mail", "Sifat surat", "No surat", "Tanggal surat", "Nama pengirim", "Catatan", "Respon", "Status", "Timestamp"
            }
        ));
        jScrollPane7.setViewportView(tabelhistory);

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout historiSuratPanelLayout = new javax.swing.GroupLayout(historiSuratPanel);
        historiSuratPanel.setLayout(historiSuratPanelLayout);
        historiSuratPanelLayout.setHorizontalGroup(
            historiSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        historiSuratPanelLayout.setVerticalGroup(
            historiSuratPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historiSuratPanelLayout.createSequentialGroup()
                .addComponent(tab5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabUtama.addTab("Histori Surat", historiSuratPanel);

        getContentPane().add(tabUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 780, 470));

        jPanel10.setBackground(new java.awt.Color(0, 146, 89));

        kirimSurat.setText("Kirim Surat");
        kirimSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kirimSuratActionPerformed(evt);
            }
        });

        suratMasuk.setText("Surat Masuk");
        suratMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suratMasukActionPerformed(evt);
            }
        });

        kirimDisposisi.setText("Kirim Disposisi");
        kirimDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kirimDisposisiActionPerformed(evt);
            }
        });

        disposisiMasuk.setText("Disposisi Masuk");
        disposisiMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disposisiMasukActionPerformed(evt);
            }
        });

        pengajuanSurat.setText("Pengajuan surat");
        pengajuanSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengajuanSuratActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User_fill@3x (3).png"))); // NOI18N
        usernameLabel.setText("Username");
        usernameLabel.setMaximumSize(new java.awt.Dimension(501, 444));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Privilege user");

        jabatanBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pengajuanSurat1.setText("Histori surat");
        pengajuanSurat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengajuanSurat1ActionPerformed(evt);
            }
        });

        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

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
                    .addComponent(jabatanBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pengajuanSurat1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jabatanBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(pengajuanSurat1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logout)
                .addContainerGap())
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void kirimdatasuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kirimdatasuratActionPerformed
        String SifatSurat = sifatSurat.getText();
        String JenisSurat = jenisSurat.getText();
        String NomorSurat = nomorSurat.getText();
        String TanggalSurat = tanggalSurat.getText();
        String Pengirim = pengirim.getText();
        String Jabatan = jabatanPengirimField.getText();
        String Perihal = perihal.getText();
        String Catatan = catatan.getText();

        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            // Insert into surat_luar
            String insertSuratLuarQuery = 
                "INSERT INTO surat_luar (sifat_surat, jenis_surat, no_surat, tanggal_surat, NamaPengirim, jabatan, perihal, catatan, lampiran) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt1 = conn.prepareStatement(insertSuratLuarQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt1.setString(1, SifatSurat);
            stmt1.setString(2, JenisSurat);
            stmt1.setString(3, NomorSurat);
            stmt1.setString(4, TanggalSurat);
            stmt1.setString(5, Pengirim);
            stmt1.setString(6, Jabatan);
            stmt1.setString(7, Perihal);
            stmt1.setString(8, Catatan);
            stmt1.setBytes(9, fileData); // Assuming fileData contains the file as a byte array

            int rowsInserted = stmt1.executeUpdate();

            if (rowsInserted > 0) {
                // Retrieve the generated id_mail
                ResultSet generatedKeys = stmt1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idMail = generatedKeys.getInt(1);

                    // Insert into status_surat_luar using the generated id_mail
                    String insertStatusSuratLuarQuery = 
                        "INSERT INTO status_surat_luar (id_mail, status) VALUES (?, ?)";
                    PreparedStatement stmt2 = conn.prepareStatement(insertStatusSuratLuarQuery);

                    stmt2.setInt(1, idMail);
                    stmt2.setString(2, "Terkirim"); // Example status

                    stmt2.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Data successfully added!");
                } else {
                    throw new Exception("Failed to retrieve generated id_mail.");
                }
            } else {
                throw new Exception("Failed to insert data into surat_luar.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_kirimdatasuratActionPerformed

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
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(), "File Read Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            System.out.println("File selection cancelled.");
        }
    }//GEN-LAST:event_attachFileActionPerformed

    private void kirimDisposisiSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kirimDisposisiSuratActionPerformed
        String PenerimaDisposisi = penerimaDisposisi.getText();
        String IntruksiDisposisi = disposisiIntruksi.getText();
        String SifatSuratDisposisi = sifatSuratDisposisi.getText();
        String CatatanDisposisi = catatanIntruksiDisposisi.getText();
        String DeadlineDisposisi = deadlineDisposisi.getText();
        String nomorSurat = nomorSuratDisposisi.getText();
        String Status = "Didisposisi";
        try{
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            String getIdJabatanQuery = "SELECT Id_Jabatan FROM jabatan WHERE Nama_Jabatan = ?";
            PreparedStatement getidJabatanStmt = conn.prepareStatement(getIdJabatanQuery);
            getidJabatanStmt.setString(1, PenerimaDisposisi);
            ResultSet JabatanIdResult = getidJabatanStmt.executeQuery();
            int JabatanId = -1; 
            if (JabatanIdResult.next()) {
                JabatanId = JabatanIdResult.getInt("Id_Jabatan");
            }
            
            String getIdMailQuery = "SELECT id_mail FROM surat_luar WHERE no_surat = ?"; 
            PreparedStatement getIdMailStmt = conn.prepareStatement(getIdMailQuery); 
            getIdMailStmt.setString(1, nomorSurat);  
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
            
            String queryStatus = "INSERT INTO status_surat_luar (id_mail, status) VALUES (?,?); ";
            PreparedStatement queryStatuStmt = conn.prepareStatement(queryStatus);
            queryStatuStmt.setInt(1, mailId);
            queryStatuStmt.setString(2, Status);
            queryStatuStmt.executeUpdate();
            loadHistoryTable();
            
            JOptionPane.showMessageDialog(this, "Data successfully added!");
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }          
    }//GEN-LAST:event_kirimDisposisiSuratActionPerformed

    private void penerimaDisposisiBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penerimaDisposisiBoxActionPerformed
        String selectedValue = (String) penerimaDisposisiBox.getSelectedItem();
        penerimaDisposisi.setText(selectedValue);
    }//GEN-LAST:event_penerimaDisposisiBoxActionPerformed

    private void jComboBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox18ActionPerformed

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
        tabUtama.setSelectedIndex(2);
        
    }//GEN-LAST:event_dispossiBtnActionPerformed

    private void terimaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terimaBTNActionPerformed
        try {
            int x = tabelDisposisi.getSelectedRow();

            if (x == -1) { 
                JOptionPane.showMessageDialog(this, "Please select a row first!", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idDisposisi = Integer.parseInt(tabelDisposisi.getValueAt(x, 0).toString()); // Assuming column 0 is id_disposisi

            // Create input dialog
            JTextField responseField = new JTextField();
            Object[] message = {
                "Enter Response:", responseField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Input Response", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String responseText = responseField.getText().trim();

                if (responseText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Response cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Database update query
                String query = "UPDATE disposisi SET respon = ? WHERE id_disposisi = ?";

                DatabaseCRUD db = new DatabaseCRUD();
                Connection conn = db.koneksi;
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, responseText);
                stmt.setInt(2, idDisposisi);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Response updated successfully!");
                    loadDisposisiTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update response!", "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadDisposisiTable();
        loadHistoryTable();
    }//GEN-LAST:event_terimaBTNActionPerformed

    private void tolakBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tolakBTNActionPerformed
        try {
            int x = tabelDisposisi.getSelectedRow();

            if (x == -1) { 
                JOptionPane.showMessageDialog(this, "Please select a row first!", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idDisposisi = Integer.parseInt(tabelDisposisi.getValueAt(x, 0).toString()); // Assuming column 0 is id_disposisi

            // Create input dialog
            JTextField responseField = new JTextField();
            Object[] message = {
                "Enter Response:", responseField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Input Response", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String responseText = responseField.getText().trim();

                if (responseText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Response cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Database update query
                String query = "UPDATE disposisi SET respon = ? WHERE id_disposisi = ?";

                DatabaseCRUD db = new DatabaseCRUD();
                Connection conn = db.koneksi;
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, responseText);
                stmt.setInt(2, idDisposisi);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Response updated successfully!");
                    loadDisposisiTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update response!", "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadDisposisiTable();
        loadHistoryTable();
    }//GEN-LAST:event_tolakBTNActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField59ActionPerformed

    private void tanggalSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalSuratActionPerformed

    private void sifatBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatBoxActionPerformed
        String selectedValue = (String) sifatBox.getSelectedItem();
        sifatSurat.setText(selectedValue);
    }//GEN-LAST:event_sifatBoxActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setTabVisibility();    
        populatesifatBox();
        fetchUserDetails();
            populatesifatBoxDisposisi();
            populatepnerimaBoxDisposisi();
            populateintruksiDisposisi();
            loadSuratLuarTable();
            loadDisposisiTable();
            loadHistoryTable();
        
    }//GEN-LAST:event_formWindowOpened

    private void pengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengirimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pengirimActionPerformed

    private void sifatSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sifatSuratActionPerformed

    private void sifatsuratdisposisiboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatsuratdisposisiboxActionPerformed
        String selectedValue = (String) sifatsuratdisposisibox.getSelectedItem();
        sifatSuratDisposisi.setText(selectedValue);            // TODO add your handling code here:
    }//GEN-LAST:event_sifatsuratdisposisiboxActionPerformed

    private void instruksiDisposisiBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instruksiDisposisiBoxActionPerformed
        String selectedValue = (String) instruksiDisposisiBox.getSelectedItem();
        disposisiIntruksi.setText(selectedValue); // TODO add your handling code here:
    }//GEN-LAST:event_instruksiDisposisiBoxActionPerformed

    private void sifatSuratDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatSuratDisposisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sifatSuratDisposisiActionPerformed

    private void disposisiIntruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disposisiIntruksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disposisiIntruksiActionPerformed

    private void jabatanPengirimFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanPengirimFieldActionPerformed

    }//GEN-LAST:event_jabatanPengirimFieldActionPerformed

    private void jabatanPengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanPengirimActionPerformed
        String selectedValue = (String) jabatanPengirim.getSelectedItem();
        if (selectedValue == "Other"){
            jabatanPengirimField.setEditable(true);
        } else {
            jabatanPengirimField.setEditable(false);
            jabatanPengirimField.setText(selectedValue);
        }
    }//GEN-LAST:event_jabatanPengirimActionPerformed

    private void ViewfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewfileButtonActionPerformed
        int selectedRow = TabelSuratLuar.getSelectedRow();

        if (selectedRow != -1) { 
            try {
                // 1. Fetch the byte array from the table model
                byte[] fileData = (byte[]) TabelSuratLuar.getValueAt(selectedRow, 8); 

                // 2. Determine the file type (you'll need to know this beforehand)
                String fileType = "pdf"; // Example: Assuming it's a PDF

                // 3. Create a temporary file
                File tempFile = File.createTempFile("tempFile", "." + fileType);
                tempFile.deleteOnExit(); 

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

    private void SifatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SifatFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SifatFieldActionPerformed

    private void jenisFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenisFieldActionPerformed

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
        
        int idMail = -1; 

        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;

            // Fetch id_mail
            String query = "SELECT id_mail FROM `surat_luar` WHERE no_surat = ? ";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, NomorSurat);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idMail = rs.getInt("id_mail");
            }

            rs.close();
            stmt.close(); // Close the select statement

            if (idMail == -1) {
                System.err.println("Error: Could not find id_mail for the selected mail.");
                return; 
            }
            
            if (Session.id_jabatan == 1) {
                // Update status_surat_luar
                String updateQuery = "UPDATE status_surat_luar SET status='Dibaca Oleh Kepala Desa' WHERE id_mail = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, idMail);
                updateStmt.executeUpdate(); 
                updateStmt.close(); 
                loadHistoryTable();
            } else if (Session.id_jabatan == 2) {
                String updateQuery = "UPDATE status_surat_luar SET status = 'Dibaca Oleh Sekretaris Desa' WHERE id_mail = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, idMail);
                updateStmt.executeUpdate(); 
                updateStmt.close(); 
                loadHistoryTable();
            }
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }//GEN-LAST:event_TabelSuratLuarMouseClicked

    private void perihalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perihalFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perihalFieldActionPerformed

    private void nomorSuratDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomorSuratDisposisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomorSuratDisposisiActionPerformed

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

    private void jabatanFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jabatanFieldActionPerformed

    private void tanggalDisposisiMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalDisposisiMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalDisposisiMasukActionPerformed

    private void perihalDisposisiMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perihalDisposisiMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perihalDisposisiMasukActionPerformed

    private void sifatDisposisiMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifatDisposisiMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sifatDisposisiMasukActionPerformed

    private void kirimSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kirimSuratActionPerformed
        tabUtama.setSelectedIndex(0);
    }//GEN-LAST:event_kirimSuratActionPerformed

    private void suratMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suratMasukActionPerformed
        tabUtama.setSelectedIndex(1);
    }//GEN-LAST:event_suratMasukActionPerformed

    private void kirimDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kirimDisposisiActionPerformed
        tabUtama.setSelectedIndex(3);
    }//GEN-LAST:event_kirimDisposisiActionPerformed

    private void disposisiMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disposisiMasukActionPerformed
        tabUtama.setSelectedIndex(4);
    }//GEN-LAST:event_disposisiMasukActionPerformed

    private void pengajuanSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengajuanSuratActionPerformed
        tabUtama.setSelectedIndex(5);
    }//GEN-LAST:event_pengajuanSuratActionPerformed

    private void pengajuanSurat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengajuanSurat1ActionPerformed
        tabUtama.setSelectedIndex(6);
    }//GEN-LAST:event_pengajuanSurat1ActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        Session.logout();
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

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
                if(Session.loggedUser == null){
                    new pagePimpinan().setVisible(false);
                } else {
                    new pagePimpinan().setVisible(true); 
                }
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
    private javax.swing.JPanel disposisiMasukPanel;
    private javax.swing.JButton dispossiBtn;
    private javax.swing.JPanel historiSuratPanel;
    private javax.swing.JComboBox<String> instruksiDisposisiBox;
    private javax.swing.JTextField instruksiDisposisiMasuk;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox20;
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
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JComboBox<String> jabatanBox;
    private javax.swing.JTextField jabatanField;
    private javax.swing.JComboBox<String> jabatanPengirim;
    private javax.swing.JTextField jabatanPengirimField;
    private javax.swing.JTextField jenisField;
    private javax.swing.JTextField jenisSurat;
    private javax.swing.JButton kirimDisposisi;
    private javax.swing.JPanel kirimDisposisiPanel;
    private javax.swing.JButton kirimDisposisiSurat;
    private javax.swing.JButton kirimSurat;
    private javax.swing.JPanel kirimSuratPanel;
    private javax.swing.JButton kirimdatasurat;
    private javax.swing.JTextField lampiran;
    private javax.swing.JTextField lampiranDisposisiMasuk;
    private javax.swing.JButton logout;
    private javax.swing.JTextField noDisposisiMasuk;
    private javax.swing.JTextField nomorSurat;
    private javax.swing.JTextField nomorSuratDisposisi;
    private javax.swing.JTextField penerimaDisposisi;
    private javax.swing.JComboBox<String> penerimaDisposisiBox;
    private javax.swing.JButton pengajuanSurat;
    private javax.swing.JButton pengajuanSurat1;
    private javax.swing.JPanel pengajuanSuratPanel;
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
    private javax.swing.JPanel suratMasukPanel;
    private javax.swing.JPanel tab0;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JTabbedPane tabUtama;
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
