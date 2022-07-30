package com.example.demo.io;

import com.example.demo.file.PdfToWord;

public class PdfWord {
    public static void main(String[] args) {
        PdfToWord pdfToWord = new PdfToWord();
        String path="E:\\微信下载\\WeChat Files\\cw846070612\\FileStorage\\Fav\\Temp\\02161ad4\\res\\6bea193b5d966d7338ad7d22ea8837e8.pdf";
        pdfToWord.convertText(path);


//        //Create a PdfDocument instance
//        PdfDocument pdf = new PdfDocument();
//        //Load a PDF file
//        pdf.loadFromFile("E:\\微信下载\\WeChat Files\\cw846070612\\FileStorage\\Fav\\Temp\\02161ad4\\res\\6bea193b5d966d7338ad7d22ea8837e8.pdf");
//        //Save to .docx file
//        pdf.saveToFile("ToWord.doc", FileFormat.DOC);
//        pdf.close();
        System.out.println("ok!!!!");
    }
}
