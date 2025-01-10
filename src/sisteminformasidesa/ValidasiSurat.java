/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sisteminformasidesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
/**
 *
 * @author Satria Dharma
 */
public class ValidasiSurat extends javax.swing.JFrame {
    private DefaultTableModel model;
    User user = Session.loggedUser;
    int idJabatan = user.id_jabatan;
    String NIK = user.nik;
    int idBaru;

    /**
     * Creates new form PengajuanSurat
     */        

    public ValidasiSurat() {
        initComponents();
        model = new DefaultTableModel();
        DefaultTableModel validasisurat = new DefaultTableModel();
        validasiTable.setModel(validasisurat);
        
        validasisurat.addColumn("nomor_surat");
        validasisurat.addColumn("judul_surat");
        validasisurat.addColumn("nama_lengkap");
        validasisurat.addColumn("sekdes");
        validasisurat.addColumn("status_sekdes");
        validasisurat.addColumn("kepdes");
        validasisurat.addColumn("status_kepdes");
        validasisurat.addColumn("tahun");
        validasisurat.addColumn("tempat_lahir");
        validasisurat.addColumn("tanggal_lahir");
        validasisurat.addColumn("usia");
        validasisurat.addColumn("warga_negara");
        validasisurat.addColumn("agama");
        validasisurat.addColumn("jenis_kelamin");
        validasisurat.addColumn("Pekerjaan");
        validasisurat.addColumn("alamat_lengkap");
        validasisurat.addColumn("nik");
        validasisurat.addColumn("no_kk");
        validasisurat.addColumn("keperluan");
        validasisurat.addColumn("gol_darah");
        validasisurat.addColumn("id_validasi");
        
        int[] kolomYangDisembunyikan = {7,8,9,10,11,12,13,14,15,16,17,18,19,20};

        for (int index : kolomYangDisembunyikan) {
            validasiTable.getColumnModel().getColumn(index).setMinWidth(0);
            validasiTable.getColumnModel().getColumn(index).setMaxWidth(0);
            validasiTable.getColumnModel().getColumn(index).setPreferredWidth(0);
        }

        validasiTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = validasiTable.getSelectedRow();
                
                if (row != -1){
                    String nomorSurat = (String) validasiTable.getValueAt(row, 0);
                    String judulSurat = (String) validasiTable.getValueAt(row, 1);
                    String namaLengkap = (String) validasiTable.getValueAt(row, 2);
                    String sekdes = (String) validasiTable.getValueAt(row, 3);
                    String statusSekdes = (String) validasiTable.getValueAt(row, 4);
                    String kepdes = (String) validasiTable.getValueAt(row, 5);
                    String statusKepdes = (String) validasiTable.getValueAt(row, 6);
                    String tahun = (String) validasiTable.getValueAt(row, 7);
                    String tempat_lahir = (String) validasiTable.getValueAt(row, 8);
                    String tanggal_lahir = (String) validasiTable.getValueAt(row, 9);
                    String usia = (String) validasiTable.getValueAt(row, 10);
                    String wargaNegara = (String) validasiTable.getValueAt(row, 11);
                    String agama = (String) validasiTable.getValueAt(row, 12);
                    String jenisKelamin = (String) validasiTable.getValueAt(row, 13);
                    String pekerjaan = (String) validasiTable.getValueAt(row, 14);
                    String alamatLengkap = (String) validasiTable.getValueAt(row, 15);
                    String nik = (String) validasiTable.getValueAt(row, 16);
                    String no_kk = (String) validasiTable.getValueAt(row, 17);
                    String keperluan = (String) validasiTable.getValueAt(row, 18);
                    String golDarah = (String) validasiTable.getValueAt(row, 19);

                    DetailSurat detailSurat = new DetailSurat();

                    detailSurat.setNomorSurat(nomorSurat);
                    detailSurat.setJudulSurat(judulSurat);
                    detailSurat.setNamaLengkap(namaLengkap);
                    detailSurat.setSekdes(sekdes);
                    detailSurat.setStatusSekdes(statusSekdes);
                    detailSurat.setKepdes(kepdes);
                    detailSurat.setStatusKepdes(statusKepdes);
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
                    
                    if (idJabatan == 1) {
                        String idvalidasi = (String) validasiTable.getValueAt(row, 20);
                        int idBaru = Integer.parseInt(idvalidasi);
                        loadTableValidasiKepdes();
                        updateWaktuDibukaKepdes(nomorSurat, idBaru);
                        detailSurat.setVisible(true);
                    } else if (idJabatan == 2) {
                        loadTableValidasiSekdes();
                        int idBaru = updateWaktuDibukaSekdes(nomorSurat);
                        if (idBaru != -1) {
                            detailSurat.IdValidasi(idBaru);
                            detailSurat.setVisible(true);
                            detailSurat.getNomorSurat(nomorSurat);
                        } else {
                            JOptionPane.showMessageDialog(ValidasiSurat.this, "Gagal menyimpan waktu dibuka.", "Error", JOptionPane.ERROR_MESSAGE);
                        }  
                    } else {
                        JOptionPane.showMessageDialog(ValidasiSurat.this, "Gagal menyimpan waktu dibuka.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    ValidasiSurat.this.dispose();
  
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                validasiTable.setBackground(java.awt.Color.WHITE);
                validasiTable.setForeground(new java.awt.Color(0, 128, 0));
            }
        });        
    }
    
    public int getIdBaru() {
        return idBaru;
    }
    
    private void loadTableValidasiSekdes() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            String query = "SELECT surat.nomor_surat, surat.judul_surat, surat.tahun, warga.nama_lengkap, status_validasi.sekdes, status_validasi.status_sekdes, status_validasi.waktu_dibuka_sekdes, status_validasi.waktu_divalidasi_sekdes, status_validasi.kepdes, status_validasi.status_kepdes, status_validasi.status_sekdes, status_validasi.waktu_dibuka_kepdes, status_validasi.waktu_divalidasi_kepdes, status_validasi.id_validasi, warga.tempat_lahir, warga.tanggal_lahir, TIMESTAMPDIFF(YEAR, warga.tanggal_lahir, CURRENT_DATE()) AS usia, warga.warga_negara, warga.agama, warga.jenis_kelamin, warga.Pekerjaan, warga.alamat_lengkap, warga.nik, warga.no_kk, surat.keperluan, warga.gol_darah\n" +
"                           FROM surat LEFT JOIN warga ON surat.nik=warga.nik LEFT JOIN status_validasi ON surat.nomor_surat=status_validasi.nomor_surat\n" +
"                           WHERE surat.nomor_surat IS NOT NULL AND status_validasi.status_sekdes IS NULL";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel modelValidasi = (DefaultTableModel) validasiTable.getModel(); 

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
                    rs.getString("id_validasi")

                };
                modelValidasi.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());}
    }
    
    private void loadTableValidasiKepdes() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            String query = "SELECT surat.nomor_surat, surat.judul_surat, surat.tahun, warga.nama_lengkap, status_validasi.sekdes, status_validasi.status_sekdes, status_validasi.waktu_dibuka_sekdes, status_validasi.waktu_divalidasi_sekdes, status_validasi.kepdes, status_validasi.status_kepdes, status_validasi.status_sekdes, status_validasi.waktu_dibuka_kepdes, status_validasi.waktu_divalidasi_kepdes, status_validasi.id_validasi, warga.tempat_lahir, warga.tanggal_lahir, TIMESTAMPDIFF(YEAR, warga.tanggal_lahir, CURRENT_DATE()) AS usia, warga.warga_negara, warga.agama, warga.jenis_kelamin, warga.Pekerjaan, warga.alamat_lengkap, warga.nik, warga.no_kk, surat.keperluan, warga.gol_darah\n" +
"                           FROM surat LEFT JOIN warga ON surat.nik=warga.nik LEFT JOIN status_validasi ON surat.nomor_surat=status_validasi.nomor_surat\n" +
"                           WHERE surat.nomor_surat IS NOT NULL AND status_validasi.status_sekdes = 'Valid'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel modelPengajuan = (DefaultTableModel) validasiTable.getModel(); 

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
                    rs.getString("id_validasi")

                };
                modelPengajuan.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading table: " + e.getMessage());}
    }

    public int updateWaktuDibukaSekdes(String nomorSurat) {
        int idbaru = -1;
        
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            
            String sql = "INSERT INTO status_validasi (waktu_dibuka_sekdes, nomor_surat, sekdes) VALUES (?, ?, ?)";            
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                Timestamp timestamp = Timestamp.from(Instant.now());
                stmt.setTimestamp(1, timestamp);
                stmt.setString(2, nomorSurat);
                stmt.setString(3, this.NIK);
                
                stmt.executeUpdate();
              
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            idbaru = generatedKeys.getInt(1);
                    }
                                
                }                
            }
        } catch (SQLException e) {
        }
        return idbaru;
    }

    public void updateWaktuDibukaKepdes(String nomorSurat, int idBaru) {
        
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            
            String sql = "UPDATE status_validasi SET waktu_dibuka_kepdes = ?, kepdes = ? WHERE id_validasi = ? AND nomor_surat = ?";            
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                Timestamp timestamp = Timestamp.from(Instant.now());
                stmt.setTimestamp(1, timestamp);
                stmt.setString(2, this.NIK);
                stmt.setInt(3, idBaru);
                stmt.setString(4, nomorSurat);
                
                stmt.executeUpdate();
                             
            }
        } catch (SQLException e) {
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

        jPanel1 = new javax.swing.JPanel();
        validasiSurat = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        laporanSurat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        validasiTable = new javax.swing.JTable();
        validasiSuratLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 146, 89));

        validasiSurat.setText("Validasi Surat");
        validasiSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validasiSuratActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User_fill@3x (2).png"))); // NOI18N
        usernameLabel.setText("Username");
        usernameLabel.setMaximumSize(new java.awt.Dimension(501, 444));

        userLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userLabel.setForeground(new java.awt.Color(255, 255, 255));
        userLabel.setText("Validasi Surat");

        laporanSurat.setText("Laporan Surat");
        laporanSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanSuratMouseClicked(evt);
            }
        });
        laporanSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanSuratActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(validasiSurat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userLabel)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(laporanSurat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userLabel)
                .addGap(54, 54, 54)
                .addComponent(validasiSurat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laporanSurat)
                .addContainerGap(307, Short.MAX_VALUE))
        );

        validasiTable.setModel(new javax.swing.table.DefaultTableModel(
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
        validasiTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                validasiTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(validasiTable);

        validasiSuratLabel.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        validasiSuratLabel.setText("Validasi Surat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(validasiSuratLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(280, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(validasiSuratLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void validasiSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validasiSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_validasiSuratActionPerformed

    private void laporanSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_laporanSuratActionPerformed

    private void validasiTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_validasiTableMouseClicked

    }//GEN-LAST:event_validasiTableMouseClicked

    private void laporanSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanSuratMouseClicked
        LaporanSurat laporansurat = new LaporanSurat();
        laporansurat.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_laporanSuratMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
 
    }//GEN-LAST:event_formWindowOpened

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ValidasiSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ValidasiSurat().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton laporanSurat;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JButton validasiSurat;
    private javax.swing.JLabel validasiSuratLabel;
    private javax.swing.JTable validasiTable;
    // End of variables declaration//GEN-END:variables
}