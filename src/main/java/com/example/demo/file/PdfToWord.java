package com.example.demo.file;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class PdfToWord {
    /**
     * 转换
     */
    public void convertText(String pdfPath) {
        PDDocument doc = null;
        OutputStream fos = null;
        Writer writer = null;
        PDFTextStripper stripper = null;
//        try {
//            doc = PDDocument.load(new FileInputStream(pdfPath));
//            fos = new FileOutputStream(pdfPath.substring(0, pdfPath.indexOf(".")) + ".doc");
//            writer = new OutputStreamWriter(fos, "UTF-8");
//            stripper = new PDFTextStripper();
//            int pageNumber = doc.getNumberOfPages();
//            stripper.setSortByPosition(true);
//            stripper.setStartPage(1);
//            stripper.setEndPage(pageNumber);
//            stripper.writeText(doc, writer);
//            writer.close();
//            doc.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
             doc = PDDocument.load(new File(pdfPath));
            int pagenumber = doc.getNumberOfPages();
            pdfPath = pdfPath.substring(0, pdfPath.lastIndexOf("."));
            String fileName = pdfPath + ".doc";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
             fos = new FileOutputStream(fileName);
             writer = new OutputStreamWriter(fos, "utf-8");
             stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);// 排序
            stripper.setStartPage(1);// 设置转换的开始页
            stripper.setEndPage(pagenumber);// 设置转换的结束页
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
            System.out.println("pdf转换word成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("end..");
    }
}