
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sisteminformasidesa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author nanda
 */
public class DetailSurat extends javax.swing.JFrame {
    
    public void setIdValidasi(int idBaru) {
        this.idValidasi = idBaru;
    }
    

    /**
     * Creates new form DetailSurat
     */
    public DetailSurat() {
        initComponents();
        updateValidasiButton();
// Memastikan tombol Validasi diatur dengan benar saat inisialisasi

        // Menambahkan listener pada setiap checkbox
        jCheckBox1.addItemListener(e -> updateValidasiButton());
        jCheckBox2.addItemListener(e -> updateValidasiButton());
        jCheckBox3.addItemListener(e -> updateValidasiButton());
        jCheckBox4.addItemListener(e -> updateValidasiButton());
        jCheckBox5.addItemListener(e -> updateValidasiButton());
        jCheckBox6.addItemListener(e -> updateValidasiButton());
        jCheckBox7.addItemListener(e -> updateValidasiButton());
        jCheckBox8.addItemListener(e -> updateValidasiButton());
        jCheckBox9.addItemListener(e -> updateValidasiButton());
        jCheckBox10.addItemListener(e -> updateValidasiButton());
        jCheckBox11.addItemListener(e -> updateValidasiButton());
        jCheckBox12.addItemListener(e -> updateValidasiButton());
    }
    
    private void updateValidasiButton() {
        boolean allChecked = jCheckBox1.isSelected() && jCheckBox2.isSelected()
                && jCheckBox3.isSelected() && jCheckBox4.isSelected()
                && jCheckBox5.isSelected() && jCheckBox6.isSelected()
                && jCheckBox7.isSelected() && jCheckBox8.isSelected()
                && jCheckBox9.isSelected() && jCheckBox10.isSelected()
                && jCheckBox11.isSelected() && jCheckBox12.isSelected();

        jButton2.setEnabled(allChecked); // Mengaktifkan atau menonaktifkan tombol Validasi
    }
    
    // Setter untuk Nomor Surat
    public void setNomorSurat(String nomorSurat) {
        // Misalnya, jika Anda memiliki JTextField bernama nomorSuratField
        this.nomorSuratField.setText(nomorSurat); // Ganti dengan field yang sesuai
    }

    // Setter untuk Judul Surat
    public void setJudulSurat(String judulSurat) {
        // Misalnya, jika Anda memiliki JTextField bernama judulSuratField
        this.judulSuratField.setText(judulSurat); // Ganti dengan field yang sesuai
    }

    // Setter untuk Nama Lengkap
    public void setNamaLengkap(String namaLengkap) {
        // Misalnya, jika Anda memiliki JTextField bernama namaLengkapField
        this.namaLengkapField.setText(namaLengkap); // Ganti dengan field yang sesuai
    }

    public void setTahun(String tahun) {
        this.tahunField.setText(tahun); // Ganti dengan field yang sesuai
    }

    public void setTempatLahir(String tempat_lahir) {
        this.tempatLahirField.setText(tempat_lahir); // Ganti dengan field yang sesuai    
    }
    
    public void setTanggalLahir(String tanggal_lahir) {
        this.tanggalLahirField.setText(tanggal_lahir); // Ganti dengan field yang sesuai    
    }
    
    public void setUsia(String usia) {
        this.usiaField.setText(usia); // Ganti dengan field yang sesuai    
    }
    
    public void setWargaNegara(String warga_negara) {
        this.wargaNegaraField.setText(warga_negara); // Ganti dengan field yang sesuai    
    }
    
    public void setAgama(String agama) {
        this.agamaField.setText(agama); // Ganti dengan field yang sesuai    
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelaminField.setText(jenisKelamin); // Ganti dengan field yang sesuai    
    }
    
    public void setPekerjaan(String pekerjaan) {
        this.pekerjaanField.setText(pekerjaan); // Ganti dengan field yang sesuai    
    }
    
    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkapField.setText(alamatLengkap); // Ganti dengan field yang sesuai    
    }
    
    public void setNik(String nik) {
        this.nikField.setText(nik); // Ganti dengan field yang sesuai    
    }
    
    public void setNokk(String no_kk) {
        this.nokkField.setText(no_kk); // Ganti dengan field yang sesuai    
    }
    
    public void setKeperluan(String keperluan) {
        this.keperluanField.setText(keperluan); // Ganti dengan field yang sesuai    
    }
    
    public void setGolDarah(String golDarah) {
        this.golDarahField.setText(golDarah); // Ganti dengan field yang sesuai    
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
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        jCheckBox8 = new javax.swing.JCheckBox();
        tahunField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jCheckBox9 = new javax.swing.JCheckBox();
        judulSuratField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jCheckBox10 = new javax.swing.JCheckBox();
        tanggalLahirField = new javax.swing.JTextField();
        jCheckBox11 = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        jCheckBox12 = new javax.swing.JCheckBox();
        tempatLahirField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        pekerjaanField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jenisKelaminField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        agamaField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        golDarahField = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        keperluanField = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        nikField = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        alamatLengkapField = new javax.swing.JTextField();
        jCheckBox4 = new javax.swing.JCheckBox();
        nokkField = new javax.swing.JTextField();
        nomorSuratField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        namaLengkapField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        usiaField = new javax.swing.JTextField();
        wargaNegaraField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel22.setText("Nomor Surat");

        tahunField.setEditable(false);

        jLabel23.setText("Tahun");

        judulSuratField.setEditable(false);

        jLabel24.setText("Judul Surat");

        tanggalLahirField.setEditable(false);

        tempatLahirField.setEditable(false);

        jButton1.setText("Tidak Valid");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pekerjaanField.setEditable(false);

        jLabel28.setText("Warga Negara");

        jButton2.setText("Validasi");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jenisKelaminField.setEditable(false);

        jLabel29.setText("Agama");

        agamaField.setEditable(false);

        jLabel30.setText("Jenis Kelamin");

        jLabel37.setText("Catatan");

        jLabel31.setText("Pekerjaan");

        jLabel32.setText("Alamat");

        jLabel33.setText("NIK");

        jLabel34.setText("No KK");

        jLabel35.setText("Keperluan");

        jLabel36.setText("Gol Darah");

        golDarahField.setEditable(false);

        keperluanField.setEditable(false);

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        nikField.setEditable(false);

        alamatLengkapField.setEditable(false);

        nokkField.setEditable(false);

        nomorSuratField.setEditable(false);

        jLabel7.setText("Nama");

        jLabel25.setText("TTL");

        jLabel27.setText("Usia");

        namaLengkapField.setEditable(false);

        jLabel1.setText("/");

        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        usiaField.setEditable(false);

        wargaNegaraField.setEditable(false);

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jenisKelaminField)
                                            .addComponent(agamaField)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel26)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(76, 76, 76))
                                                    .addComponent(usiaField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(wargaNegaraField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox3)
                                            .addComponent(jCheckBox4)
                                            .addComponent(jCheckBox5)
                                            .addComponent(jCheckBox6)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(namaLengkapField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tempatLahirField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tanggalLahirField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox1)
                                            .addComponent(jCheckBox2))))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35))))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(alamatLengkapField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nikField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nokkField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(keperluanField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(golDarahField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pekerjaanField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox12))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(judulSuratField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomorSuratField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(tahunField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton3)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel24)
                                                    .addComponent(jLabel22)
                                                    .addComponent(jLabel23))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(judulSuratField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(nomorSuratField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tahunField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel7)
                                                    .addComponent(namaLengkapField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel31)
                                                    .addComponent(pekerjaanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jCheckBox1)))
                                    .addComponent(jCheckBox12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel32)
                                            .addComponent(alamatLengkapField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jCheckBox2)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel26)
                                                .addComponent(tanggalLahirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tempatLahirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1))))
                                    .addComponent(jCheckBox11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jCheckBox3)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(usiaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel27)))
                                        .addComponent(nikField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCheckBox10)))
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel34)
                                        .addComponent(jCheckBox4))
                                    .addComponent(wargaNegaraField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(agamaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCheckBox5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nokkField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(keperluanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox8)))))
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(jenisKelaminField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel36)
                        .addComponent(jCheckBox6))
                    .addComponent(golDarahField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox7))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

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
                .addComponent(pengajuanSurat1)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Membuat instance dari pagePimpinan
        pagePimpinan pimpinanPage = new pagePimpinan();

        // Mengatur tab "Pengajuan Surat"
        pimpinanPage.setActiveTab(4); // Pastikan index 3 adalah tab "Pengajuan Surat"

        // Menampilkan halaman pimpinan
        pimpinanPage.setVisible(true);

        // Menutup halaman DetailSurat
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String dbURL = "jdbc:mysql://localhost:3306/kantordesafix"; // Ganti dengan URL database Anda
        String dbUser = "root"; // Ganti dengan username database Anda
        String dbPassword = ""; // Ganti dengan password database Anda

        // ID validasi yang ingin diperbarui
        idValidasi = pagePimpinan.idBaru; // Anda perlu menetapkan nilai idValidasi sesuai dengan logika aplikasi Anda

        String sql = "UPDATE status_validasi SET status_sekdes = 'Tidak Valid' WHERE id_validasi = ?";
    
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            // Set parameter id_validasi
            stmt.setInt(1, idValidasi);
        
            // Eksekusi update
            int rowsUpdated = stmt.executeUpdate();
        
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Status berhasil diubah menjadi 'Tidak Valid'.", 
                                          "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "ID Validasi tidak ditemukan.", 
                                            "Informasi", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah status: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Upload Scan Tanda Tangan");

        // Filter untuk file gambar
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String name = f.getName().toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "Gambar (*.jpg, *.jpeg, *.png)";
            }
        });

        // Tampilkan dialog
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            long fileSizeInBytes = selectedFile.length();
            long fileSizeInMB = fileSizeInBytes / (1024 * 1024);

            if (fileSizeInMB > 2) {
                JOptionPane.showMessageDialog(this, "Ukuran file melebihi 2 MB. Pilih file yang lebih kecil.",
                        "Ukuran File Terlalu Besar", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin mengunggah file ini?", "Konfirmasi Unggahan",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (FileInputStream fis = new FileInputStream(selectedFile)) {
                    int idValidasi = saveFileToDatabase(fis);

                    // Update status dan waktu di database
                    String statusSekdes = "Valid";
                    Timestamp waktuValidasiSekdes = new Timestamp(System.currentTimeMillis());
                    updateValidationStatus(idValidasi, statusSekdes, waktuValidasiSekdes);

                    JOptionPane.showMessageDialog(this, "File berhasil diunggah dan status diperbarui ke database!",
                            "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal mengunggah file: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada file yang dipilih.",
                    "Operasi Dibatalkan", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Simpan file ke database
    private int saveFileToDatabase(FileInputStream fis) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/kantordesafix";
        String dbUser = "root";
        String dbPassword = "";

        String sql = "INSERT INTO ttd_sekdes (ttd_sekredes) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setBinaryStream(1, fis);

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Baris yang dimasukkan: " + rowsInserted);
            if (rowsInserted > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Mengembalikan id_validasi
                    }
                }
            }
        }
        return -1; // Jika gagal
    }

    // Perbarui status validasi
    private void updateValidationStatus(int idValidasi, String statusSekdes, Timestamp waktuValidasiSekdes) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/kantordesafix";
        String dbUser = "root";
        String dbPassword = "";

        String sql = "UPDATE status_validasi SET status_sekdes = ?, waktu_divalidasi_sekdes = ? WHERE id_validasi = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            Timestamp timestamp = Timestamp.from(Instant.now()); // Mendapatkan waktu sekarang
            stmt.setString(1, "Valid");
            stmt.setTimestamp(2, timestamp);
            stmt.setInt(3, idValidasi);

            stmt.executeUpdate();
            
            System.out.println("ID Validasi: " + idValidasi);
            System.out.println("Status Sekdes: Valid");
            System.out.println("Waktu Validasi Sekdes: " + timestamp);
        }
        pagePimpinan pimpinanPage = new pagePimpinan();

        // Mengatur tab "Pengajuan Surat"
        pimpinanPage.setActiveTab(4); // Pastikan index 3 adalah tab "Pengajuan Surat"

        // Menampilkan halaman pimpinan
        pimpinanPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

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
            java.util.logging.Logger.getLogger(DetailSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailSurat().setVisible(true);
            }
        }); 
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField agamaField;
    private javax.swing.JTextField alamatLengkapField;
    private javax.swing.JButton disposisiMasuk;
    private javax.swing.JTextField golDarahField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField jenisKelaminField;
    private javax.swing.JTextField judulSuratField;
    private javax.swing.JTextField keperluanField;
    private javax.swing.JButton kirimDisposisi;
    private javax.swing.JButton kirimSurat;
    private javax.swing.JTextField namaLengkapField;
    private javax.swing.JTextField nikField;
    private javax.swing.JTextField nokkField;
    private javax.swing.JTextField nomorSuratField;
    private javax.swing.JTextField pekerjaanField;
    private javax.swing.JButton pengajuanSurat;
    private javax.swing.JButton pengajuanSurat1;
    private javax.swing.JButton suratMasuk;
    private javax.swing.JTextField tahunField;
    private javax.swing.JTextField tanggalLahirField;
    private javax.swing.JTextField tempatLahirField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usiaField;
    private javax.swing.JTextField wargaNegaraField;
    // End of variables declaration//GEN-END:variables
}
