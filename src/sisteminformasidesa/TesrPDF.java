///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//package sisteminformasidesa;
//
//import com.ironsoftware.ironpdf.PdfDocument;
//import com.ironsoftware.ironpdf.edit.PageSelection;
//import java.io.IOException;
//import java.nio.file.Paths;
//
///**
// *
// * @author Acer
// */
//public class TesrPDF {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        try {
//            PdfDocument PdfDoc = PdfDocument.fromFile(Paths.get("src/resources/surat_izin_keramaian.pdf"));
//            PdfDoc.replaceText(PageSelection.firstPage(), "[nama]", "Hello World");
//            PdfDoc.saveAs(Paths.get("src/resources/new-surat.pdf"));
//        } catch (IOException e) {
//        }
//    }
//    
//}
