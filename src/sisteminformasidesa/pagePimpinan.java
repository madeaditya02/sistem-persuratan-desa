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
    private void loadSuratLuarTable() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            String query = "SELECT * FROM surat_luar";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) TabelSuratLuar.getModel();
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
        jPanel10 = new javax.swing.JPanel();
        kirimSurat = new javax.swing.JButton();
        suratMasuk = new javax.swing.JButton();
        kirimDisposisi = new javax.swing.JButton();
        disposisiMasuk = new javax.swing.JButton();
        pengajuanSurat = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();

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
                .addContainerGap(247, Short.MAX_VALUE))
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
                .addContainerGap(231, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addComponent(jLabel63)
                                    .addGap(34, 34, 34)
                                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel60)
                                        .addComponent(jLabel61)
                                        .addComponent(jLabel59))
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField54, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextField51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel68)
                                        .addGap(49, 49, 49)
                                        .addComponent(jTextField61))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel67)
                                        .addGap(35, 35, 35)
                                        .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel66)
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel70)
                                            .addComponent(jLabel69))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(jTextField62)
                                                .addGap(130, 130, 130))
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel65)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel64)
                                .addGap(37, 37, 37)
                                .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addGap(6, 6, 6)
                        .addComponent(jButton16)))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel64)
                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGap(0, 793, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        kirimDisposisiTab.addTab("Pengajuan Surat", jPanel8);

        getContentPane().add(kirimDisposisiTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 780, 470));

        jPanel10.setBackground(new java.awt.Color(0, 146, 89));

        kirimSurat.setText("Kirim Surat");

        suratMasuk.setText("Surat Masuk");

        kirimDisposisi.setText("Kirim Disposisi");

        disposisiMasuk.setText("Disposisi Masuk");

        pengajuanSurat.setText("Pengajuan surat");

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/User_fill@3x (2).png"))); // NOI18N
        usernameLabel.setText("Username");
        usernameLabel.setMaximumSize(new java.awt.Dimension(501, 444));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Privilege user");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(pengajuanSurat))
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

    private void penerimaDisposisiBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penerimaDisposisiBoxActionPerformed
        String selectedValue = (String) penerimaDisposisiBox.getSelectedItem();
        penerimaDisposisi.setText(selectedValue);
    }//GEN-LAST:event_penerimaDisposisiBoxActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

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
        kirimDisposisiTab.setSelectedIndex(2);
        
    }//GEN-LAST:event_dispossiBtnActionPerformed

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
        populatesifatBox();
        populatesifatBoxDisposisi();
        populatepnerimaBoxDisposisi();
        populateintruksiDisposisi();
        loadSuratLuarTable();
        loadDisposisiTable();
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
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox5;
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
    private javax.swing.JTextField tanggalDisposisiMasuk;
    private javax.swing.JTextField tanggalField;
    private javax.swing.JTextField tanggalSurat;
    private javax.swing.JTextField tanggalSuratDisposisi;
    private javax.swing.JButton terimaBTN;
    private javax.swing.JButton tolakBTN;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
