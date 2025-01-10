
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sisteminformasidesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author satria
 */
public class DetailSurat extends javax.swing.JFrame { 
    User user = Session.loggedUser;
    int idJabatan = user.id_jabatan;    
    String NIK = user.nik;
    String catatan;
    String nomorSurat;
    String NomorSurat;
    int idValidasi;
    String idvalidasi;
    
    public DetailSurat() {
        initComponents();
        updateValidasiButton();

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

        validButton.setEnabled(allChecked);
    }
    
    public void setNomorSurat(String nomorSurat) {
        this.nomorSuratField.setText(nomorSurat);
    }

    public void setJudulSurat(String judulSurat) {
        this.judulSuratField.setText(judulSurat);
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkapField.setText(namaLengkap);
    }
   
    public void setTahun(String tahun) {
        this.tahunField.setText(tahun);
    }
    
    public void setSekdes(String sekdes) {
    
    }
    
    public void setStatusSekdes(String statusSekdes) {
    
    }
    
    public void setKepdes(String kepdes) {
    
    }
    
    public void setStatusKepdes(String statusKepdes) {
    
    }

    public void setTempatLahir(String tempat_lahir) {
        this.tempatLahirField.setText(tempat_lahir);    
    }
    
    public void setTanggalLahir(String tanggal_lahir) {
        this.tanggalLahirField.setText(tanggal_lahir);    
    }
    
    public void setUsia(String usia) {
        this.usiaField.setText(usia);    
    }
    
    public void setWargaNegara(String warga_negara) {
        this.wargaNegaraField.setText(warga_negara);    
    }
    
    public void setAgama(String agama) {
        this.agamaField.setText(agama);    
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelaminField.setText(jenisKelamin);    
    }
    
    public void setPekerjaan(String pekerjaan) {
        this.pekerjaanField.setText(pekerjaan);    
    }
    
    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkapField.setText(alamatLengkap);    
    }
    
    public void setNik(String nik) {
        this.nikField.setText(nik);    
    }
    
    public void setNokk(String no_kk) {
        this.nokkField.setText(no_kk);    
    }
    
    public void setKeperluan(String keperluan) {
        this.keperluanField.setText(keperluan);    
    }
    
    public void setGolDarah(String golDarah) {
        this.golDarahField.setText(golDarah);    
    }
    
    public void setIDValidasi(String idvalidasi) {
        this.idValidasiField.setText(idvalidasi);
    }    
    
    public int getIdValidasi1(String idvalidasi) {
        try {
            this.idValidasi = Integer.parseInt(idValidasiField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(DetailSurat.this, 
            "ID Validasi harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        return idValidasi;
    }
    
        public int getIdValidasi2(int idBaru) {
            this.idValidasi = idBaru;
            return idValidasi;
    }
    
    public void saveCatatanToDatabase() {
        this.catatan = catatanTextPane.getText();
    }
    
    public void getNomorSurat(String nomorSurat) {
        this.NomorSurat = nomorSuratField.getText();
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
        tidakvalidButton = new javax.swing.JButton();
        pekerjaanField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        validButton = new javax.swing.JButton();
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
        backButton = new javax.swing.JButton();
        usiaField = new javax.swing.JTextField();
        wargaNegaraField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        catatanTextPane = new javax.swing.JTextPane();
        idValidasiField = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel22.setText("Nomor Surat");

        tahunField.setEditable(false);

        jLabel23.setText("Tahun");

        judulSuratField.setEditable(false);

        jLabel24.setText("Judul Surat");

        tanggalLahirField.setEditable(false);

        tempatLahirField.setEditable(false);

        tidakvalidButton.setText("Tidak Valid");
        tidakvalidButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tidakvalidButtonMouseClicked(evt);
            }
        });
        tidakvalidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidakvalidButtonActionPerformed(evt);
            }
        });

        pekerjaanField.setEditable(false);

        jLabel28.setText("Warga Negara");

        validButton.setText("Validasi");
        validButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                validButtonMouseClicked(evt);
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

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        usiaField.setEditable(false);

        wargaNegaraField.setEditable(false);

        jScrollPane1.setViewportView(catatanTextPane);

        idValidasiField.setEditable(false);

        jLabel38.setText("Id Validasi");

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
                        .addComponent(validButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tidakvalidButton)
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
                            .addComponent(tahunField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(idValidasiField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                            .addComponent(backButton)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel24)
                                                    .addComponent(jLabel22)
                                                    .addComponent(jLabel23)
                                                    .addComponent(jLabel38))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(judulSuratField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(nomorSuratField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tahunField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(idValidasiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(validButton)
                    .addComponent(tidakvalidButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(0, 146, 89));

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User_fill@3x (2).png"))); // NOI18N
        usernameLabel.setText("Username");
        usernameLabel.setMaximumSize(new java.awt.Dimension(501, 444));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Detail Surat");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        DetailSurat detailsurat = new DetailSurat();
        detailsurat.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void tidakvalidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidakvalidButtonActionPerformed

        DatabaseCRUD db = new DatabaseCRUD();
        Connection conn = db.koneksi;      
        if (idJabatan == 1) {
            getIdValidasi1(idvalidasi);
            String sqlkepdes = "UPDATE status_validasi SET status_kepdes = ?, Catatan = ? WHERE id_validasi = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlkepdes)) {
                stmt.setString(1, "Tidak Valid");
                stmt.setString(2, this.catatan);
                stmt.setInt(3, this.idValidasi);
                System.out.print(idValidasi);
                
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DetailSurat.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (idJabatan == 2) {
            String sqlsekdes = "UPDATE status_validasi SET status_sekdes = ?, Catatan = ? WHERE id_validasi = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlsekdes)) {
                stmt.setString(1, "Tidak Valid");
                stmt.setString(2, this.catatan);
                stmt.setInt(3, this.idValidasi);
                
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DetailSurat.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(DetailSurat.this, "Tidak Berhasil Melakukan Validasi", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ValidasiSurat validasisurat = new ValidasiSurat();
        validasisurat.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_tidakvalidButtonActionPerformed

    private void validButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_validButtonMouseClicked
        saveCatatanToDatabase();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Upload Scan Tanda Tangan");
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
                    saveFileToDatabase(fis);
                    updateValidationStatus();
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

    private int saveFileToDatabase(FileInputStream fis) throws SQLException {
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            if (idJabatan == 1) {
                String sqlkepdes = "INSERT INTO ttd_kades (ttd_kepdes, nik) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlkepdes)){
                    stmt.setBinaryStream(1, fis);
                    stmt.setString(2, this.NIK);
                
                    stmt.executeUpdate();
                }
            } else if (idJabatan == 2) {
                String sqlsekdes = "INSERT INTO ttd_sekdes (ttd_sekredes, nik) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlsekdes)){
                    stmt.setBinaryStream(1, fis);
                    stmt.setString(2, this.NIK);
                    
                    stmt.executeUpdate();
                }
            } 
            else {
                JOptionPane.showMessageDialog(DetailSurat.this, "Tidak Berhasil Menyimpan File", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
 
    private void updateValidationStatus() throws SQLException {
        try {
            DatabaseCRUD db = new DatabaseCRUD();
            Connection conn = db.koneksi;
            if (idJabatan == 1) {
                getIdValidasi1(idvalidasi);
                String sqlkepdes = "UPDATE status_validasi SET status_kepdes = ?, waktu_divalidasi_kepdes = ?, Catatan = ? WHERE id_validasi = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sqlkepdes)) {
                    Timestamp timestamp = Timestamp.from(Instant.now());
                    stmt.setString(1, "Valid");
                    stmt.setTimestamp(2, timestamp);
                    stmt.setString(3, this.catatan);
                    stmt.setInt(4, this.idValidasi);
                    System.out.print(timestamp);
                    System.out.print(catatan);
                    System.out.print(idValidasi);
                    System.out.print(NomorSurat);
                
                    stmt.executeUpdate();
                }
            } else if (idJabatan == 2) {
                String sqlsekdes = "UPDATE status_validasi SET status_sekdes = ?, waktu_divalidasi_sekdes = ?, Catatan = ? WHERE id_validasi = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sqlsekdes)) {
                    Timestamp timestamp = Timestamp.from(Instant.now());
                    stmt.setString(1, "Valid");
                    stmt.setTimestamp(2, timestamp);
                    stmt.setString(3, this.catatan);
                    stmt.setInt(4, this.idValidasi);
                
                    stmt.executeUpdate();
                }    
            } else {
                JOptionPane.showMessageDialog(DetailSurat.this, "Tidak Berhasil Melakukan Validasi", "Error", JOptionPane.ERROR_MESSAGE);
            }            
        } catch (SQLException e) {
        }
        ValidasiSurat validasisurat = new ValidasiSurat();
        validasisurat.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_validButtonMouseClicked

    private void tidakvalidButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tidakvalidButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tidakvalidButtonMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> {
            new DetailSurat().setVisible(true);
        }); 
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField agamaField;
    private javax.swing.JTextField alamatLengkapField;
    private javax.swing.JButton backButton;
    private javax.swing.JTextPane catatanTextPane;
    private javax.swing.JTextField golDarahField;
    private javax.swing.JTextField idValidasiField;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jenisKelaminField;
    private javax.swing.JTextField judulSuratField;
    private javax.swing.JTextField keperluanField;
    private javax.swing.JTextField namaLengkapField;
    private javax.swing.JTextField nikField;
    private javax.swing.JTextField nokkField;
    private javax.swing.JTextField nomorSuratField;
    private javax.swing.JTextField pekerjaanField;
    private javax.swing.JTextField tahunField;
    private javax.swing.JTextField tanggalLahirField;
    private javax.swing.JTextField tempatLahirField;
    private javax.swing.JButton tidakvalidButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usiaField;
    private javax.swing.JButton validButton;
    private javax.swing.JTextField wargaNegaraField;
    // End of variables declaration//GEN-END:variables
}
