/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisteminformasidesa;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.HorizontalAlignment;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.lang.Math;
import java.text.ParseException;
import java.util.Objects;
import java.sql.Blob;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class Surat {
    public Connection koneksi;
    public String nomor_surat;
    
    public Surat(String nomor_surat) {
        try {
            String url = "jdbc:mysql://localhost/kantor_desa";
            String username = "root";
            String password = "";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            koneksi = DriverManager.getConnection(url, username, password);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        this.nomor_surat = nomor_surat;
    }
    
    public void print() {
        try {
            Statement s = this.koneksi.createStatement();
            String sql = "SELECT surat.*, warga.*, desa.*, kepdes_detail.nama_lengkap AS nama_kepdes, sekdes_detail.nama_lengkap AS nama_sekdes FROM surat JOIN warga ON surat.nik = warga.nik JOIN desa ON warga.kode_desa = desa.kode_desa JOIN user AS kepdes ON desa.kepdes = kepdes.id_user JOIN user AS sekdes ON desa.sekdes = sekdes.id_user JOIN warga AS kepdes_detail ON kepdes.nik = kepdes_detail.nik JOIN warga AS sekdes_detail ON sekdes.nik = sekdes_detail.nik WHERE surat.nomor_surat = '"+nomor_surat+"'";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Simpan file PDF");

                // Atur default ke ekstensi PDF
                fileChooser.setSelectedFile(new File(r.getString("judul_surat")+".pdf"));

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String filePath = fileToSave.getAbsolutePath();

                    // Tambahkan ekstensi .pdf jika belum ada
                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }
                    
                    String kabupaten = r.getString("nama_kab");
                    String kecamatan = r.getString("nama_kec");
                    String desa = r.getString("nama_desa");
                    String alamat_desa = r.getString("alamat_desa");
                    String kode_desa = r.getString("kode_desa");
                    String kode_surat = r.getString("kode_surat");
                    String judul_surat = r.getString("judul_surat");
                    String nama_lengkap = r.getString("nama_lengkap");
                    Locale loc = new Locale.Builder().setLanguage("id").setRegion("ID").build();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String str_tgl_mulai = r.getString("mulai_berlaku");
                    String str_tgl_selesai = r.getString("tgl_berakhir");
                    Date tl = null, tgl_mulai = null, tgl_selesai = null;
                    try {
                        tl = sdf.parse(r.getString("tanggal_lahir"));
                        if (str_tgl_mulai != null && str_tgl_selesai != null) {
                            tgl_mulai = sdf.parse(str_tgl_mulai);
                            tgl_selesai = sdf.parse(str_tgl_selesai);
                        }
                    } catch(ParseException e) {
                        e.printStackTrace();
                    }
                    Date now = new Date();
                    long umur = (now.getTime() - tl.getTime()) / (1000l * 60 * 60 * 24 * 365);
                    SimpleDateFormat localFormat = new SimpleDateFormat("d MMMM yyyy", loc);
                    String ttl = r.getString("tempat_lahir") + ", " + localFormat.format(tl);
                    String warga_negara = r.getString("warga_negara");
                    String agama = r.getString("agama");
                    String jenis_kelamin = r.getString("jenis_kelamin");
                    String pekerjaan = r.getString("pekerjaan");
                    String alamat_lengkap = r.getString("alamat_lengkap");
                    String nik = r.getString("nik");
                    String no_kk = r.getString("no_kk");
                    String keperluan = r.getString("keperluan");
                    String mulai_berlaku = str_tgl_mulai != null ? localFormat.format(tgl_mulai) : "";
                    String tgl_akhir = str_tgl_selesai != null ? localFormat.format(tgl_selesai) : "";
                    String rentangSurat = str_tgl_mulai != null ? (mulai_berlaku+" s/d "+tgl_akhir) : "";
                    String gol_darah = r.getString("gol_darah");
                    String id_user = r.getString("id_user");
                    String nama_sekdes = r.getString("nama_sekdes");
                    String nama_kepdes = r.getString("nama_kepdes");
                    User user = new User(id_user);
                    
                    try {
                        Document document = new Document(new PdfDocument(new PdfWriter(filePath)));
                        document.setMargins(50,50,50,50);
                        // Membuat PDF menggunakan iText dan menyimpannya ke lokasi yang dipilih pengguna
                        PdfFont font = PdfFontFactory.createFont("Times-Roman");
                        PdfFont fontBold = PdfFontFactory.createFont("Times-Bold");
                        //Open the document.
                        document.setFont(font);
                        document.setFontSize(12);
                        document.setTextAlignment(TextAlignment.JUSTIFIED);
                        int lineHeight = 15;
                        TabStop defaultTab = new TabStop(32f, TabAlignment.LEFT);

                        Paragraph p1 = new Paragraph("PEMERINTAH " + kabupaten.toUpperCase()).setTextAlignment(TextAlignment.CENTER).setFont(font).setFontSize(16).setFixedLeading(lineHeight);
                        document.add(p1);
                        Paragraph p2 = new Paragraph(kecamatan.toUpperCase()).setTextAlignment(TextAlignment.CENTER).setFontSize(16).setFixedLeading(lineHeight);
                        document.add(p2);
                        Paragraph p3 = new Paragraph(desa.toUpperCase()).setFont(fontBold).setTextAlignment(TextAlignment.CENTER).setFontSize(16).setFixedLeading(lineHeight);
                        document.add(p3);
                        Paragraph p4 = new Paragraph(alamat_desa).setTextAlignment(TextAlignment.CENTER).setFontSize(10).setFixedLeading(lineHeight);
                        document.add(p4);

                        SolidLine line = new SolidLine(1f);
                        line.setLineWidth(2);
                        LineSeparator separator = new LineSeparator(line);
                        document.add(separator);

                        float[] columnWidths = {1, 1};
                        Table table1 = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
                        table1.addCell(new Cell().add(new Paragraph(kode_desa).setFontSize(10)).setBorder(Border.NO_BORDER));
                        table1.addCell(new Cell().add(new Paragraph(kode_surat).setFontSize(10).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
                        document.add(table1);

                        document.add(new Paragraph());
                        document.add(new Paragraph(judul_surat).setFont(fontBold).setTextAlignment(TextAlignment.CENTER).setFontSize(17).setFixedLeading(lineHeight));
                        document.add(new Paragraph("Nomor : "+nomor_surat).setTextAlignment(TextAlignment.CENTER).setFixedLeading(lineHeight));
                        document.add(new Paragraph());
        //                Paragraph p5 = new Paragraph();
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("Yang bertanda tangan di bawah ini kami "+desa+", "+kecamatan+", "+kabupaten+", menerangkan dengan sebenarnya bahwa :"));
        //                document.add(new Paragraph("Yang bertanda tangan di bawah ini kami Desa [nama_des], Kecamatan [nama_kec], Kabupaten [nama_kab], menerangkan dengan sebenarnya bahwa :"));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("1.").add(new Tab()).add("Nama").add(new Tab()).add(new Tab()).add(new Tab()).add(new Text(": "+r.getString("nama_lengkap")).setFont(fontBold)).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("2.").add(new Tab()).add("Tempat / tanggal lahir").add(new Tab()).add(": " + ttl).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("3.").add(new Tab()).add("Umur").add(new Tab()).add(new Tab()).add(new Tab()).add(": "+umur).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("4.").add(new Tab()).add("Warga Negara").add(new Tab()).add(new Tab()).add(": "+warga_negara).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("5.").add(new Tab()).add("Agama").add(new Tab()).add(new Tab()).add(new Tab()).add(": "+agama).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("6.").add(new Tab()).add("Jenis Kelamin").add(new Tab()).add(new Tab()).add(": "+jenis_kelamin).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("7.").add(new Tab()).add("Pekerjaan").add(new Tab()).add(new Tab()).add(new Tab()).add(": "+pekerjaan).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("8.").add(new Tab()).add("Tempat tinggal").add(new Tab()).add(new Tab()).add(": "+alamat_lengkap).setFixedLeading(lineHeight).setTextAlignment(TextAlignment.LEFT));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("9.").add(new Tab()).add("Surat bukti diri").setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add(new Tab()).add("KTP").add(new Tab()).add(new Tab()).add(new Tab()).add(": "+nik).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add(new Tab()).add("KK").add(new Tab()).add(new Tab()).add(new Tab()).add(": "+no_kk).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("10.").add(new Tab()).add("Keperluan").add(new Tab()).add(new Tab()).add(new Tab()).add(": "+keperluan).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("11.").add(new Tab()).add("Berlaku").add(new Tab()).add(new Tab()).add(new Tab()).add(": "+rentangSurat).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("12.").add(new Tab()).add("Golongan Darah").add(new Tab()).add(new Tab()).add(": "+gol_darah).setFixedLeading(lineHeight));
                        document.add(new Paragraph().addTabStops(defaultTab).add(new Tab()).add("Demikian Surat ini dibuat, untuk dipergunakan sebagaimana mestinya.").setFixedLeading(lineHeight));
                        document.add(new Paragraph());
                        document.add(new Paragraph());
                        document.add(new Paragraph());

                        // Kolom ttd
                        float[] columnWidths2 = {1, 1, 1};
                        Table table2 = new Table(UnitValue.createPercentArray(columnWidths2)).useAllAvailableWidth();
                        // Baris 1
                        table2.addCell(new Cell().add(new Paragraph("Pemegang Surat").setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
                        table2.addCell(new Cell().add(new Paragraph("Mengetahui,").setTextAlignment(TextAlignment.CENTER)).add(new Paragraph("Kepala "+desa).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
                        Paragraph tgl_surat = new Paragraph(desa + ", " + mulai_berlaku).setTextAlignment(TextAlignment.CENTER);
                        table2.addCell(new Cell().add(tgl_surat).add(new Paragraph("Sekretaris "+desa).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
                        
                        // Baris 2
                        ResultSet res = s.executeQuery("SELECT status_validasi.*, sekdes.nama_lengkap AS nama_sekdes, kepdes.nama_lengkap AS nama_kepdes, ttd_sekdes.ttd_sekredes, ttd_kades.ttd_kepdes FROM status_validasi JOIN warga AS kepdes ON status_validasi.kepdes = kepdes.nik LEFT JOIN warga AS sekdes ON status_validasi.sekdes = sekdes.nik LEFT JOIN ttd_kades ON status_validasi.kepdes = ttd_kades.nik LEFT JOIN ttd_sekdes ON status_validasi.sekdes = ttd_sekdes.nik WHERE status_validasi.status_sekdes = 'Valid' AND status_validasi.status_kepdes = 'Valid' AND status_validasi.nomor_surat = '"+nomor_surat+"';");
                        if (res.next()) {
                            table2.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
                            float tableWidth = table2.getWidth().getValue();
                            
                            Blob blobKepdes = res.getBlob("ttd_kepdes");
                            byte[] imageBytesKepdes = blobKepdes.getBytes(1, (int) blobKepdes.length());
                            ImageData imageDataKepdes = ImageDataFactory.create(imageBytesKepdes);
                            Image ttd_kepdes = new Image(imageDataKepdes);
                            ttd_kepdes.scaleToFit(tableWidth, 50f);
                            ttd_kepdes.setHorizontalAlignment(HorizontalAlignment.CENTER);
                            
                            Blob blobSekdes = res.getBlob("ttd_sekredes");
                            byte[] imageBytesSekdes = blobSekdes.getBytes(1, (int) blobSekdes.length());
                            ImageData imageDataSekdes = ImageDataFactory.create(imageBytesSekdes);
                            Image ttd_sekdes = new Image(imageDataSekdes);
                            ttd_sekdes.scaleToFit(tableWidth, 50f);
                            ttd_sekdes.setHorizontalAlignment(HorizontalAlignment.CENTER);
                            
                            table2.addCell(new Cell().add(ttd_kepdes.setAutoScale(true)).setBorder(Border.NO_BORDER).setPaddingLeft(16).setPaddingRight(16));
                            table2.addCell((new Cell()).add(ttd_sekdes.setAutoScale(true)).setBorder(Border.NO_BORDER));
                            
                            // Baris 3
                            table2.addCell(new Cell().add(new Paragraph(user.nama_lengkap).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
                            table2.addCell(new Cell().add(new Paragraph(res.getString("nama_kepdes")).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
                            table2.addCell(new Cell().add(new Paragraph(res.getString("nama_sekdes")).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER).setBorder(Border.NO_BORDER));
                        } else {
                            table2.addCell(new Cell().add(new Paragraph("\n\n\n\n")).add(new Paragraph()).add(new Paragraph()).add(new Paragraph()).add(new Paragraph()).add(new Paragraph()).setBorder(Border.NO_BORDER));
                            table2.addCell(new Cell().add(new Paragraph("\n\n\n\n")).setBorder(Border.NO_BORDER).setPaddingLeft(16).setPaddingRight(16));
                            table2.addCell(new Cell().add(new Paragraph("\n\n\n\n")).setBorder(Border.NO_BORDER));
                            // Baris 3
                            table2.addCell(new Cell().add(new Paragraph(user.nama_lengkap).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
                            table2.addCell(new Cell().add(new Paragraph(nama_kepdes).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
                            table2.addCell(new Cell().add(new Paragraph(nama_sekdes).setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER).setBorder(Border.NO_BORDER));
                        }

        //                table2.addCell(new Cell().add(new Paragraph("[nama_des], [tgl_surat]").setTextAlignment(TextAlignment.RIGHT)).add(new Paragraph("[jabatan] [nama_des]")).add(new Paragraph()).add(new Paragraph()).add(new Paragraph()).add(new Paragraph("[nama_pamong]")).setBorder(Border.NO_BORDER));
                        table2.setFixedLayout();
                        document.add(table2);
                        document.close();
                        JOptionPane.showMessageDialog(null, "Surat berhasil diunduh", "Cetak Surat", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("PDF berhasil disimpan ke: " + filePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
