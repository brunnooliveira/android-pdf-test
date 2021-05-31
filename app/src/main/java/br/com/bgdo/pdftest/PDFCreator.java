package br.com.bgdo.pdftest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFCreator {

    public static void createTwoPages() throws IOException {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        canvas.drawText("Testando PDF", 40, 50, paint);
        pdfDocument.finishPage(page);

        PdfDocument.PageInfo pageInfo2 = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page page2 = pdfDocument.startPage(pageInfo2);
        Canvas canvas2 = page2.getCanvas();
        canvas2.drawText("Testando PDF page 2", 40, 50, paint);
        pdfDocument.finishPage(page2);

        File file = new File(Environment.getExternalStorageDirectory(), "/Documents/testPDF.pdf");
        if (!file.exists()) {
            file.createNewFile();
        }

        pdfDocument.writeTo(new FileOutputStream(file));
        pdfDocument.close();
    }

    public static void createForm() throws IOException {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(12f);
        canvas.drawText("BGDO Enterprises", pageInfo.getPageWidth() / 2, 30, paint);

        paint.setTextSize(6f);
        paint.setTextScaleX(1.5f);
        paint.setColor(Color.rgb(122, 119, 119));
        canvas.drawText("Rua Dacolá, 500, Bairro Qualquer", pageInfo.getPageWidth() / 2, 40, paint);
        paint.setTextScaleX(1f);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(9f);
        paint.setColor(Color.rgb(122, 119, 119));
        canvas.drawText("Customer Information", 10, 70, paint);

        String[] informationArray = new String[] {"Name", "Company Name", "Address", "Phone", "Email"};

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(8f);
        paint.setColor(Color.BLACK);

        int startXPosition = 10;
        int endXPosition = pageInfo.getPageWidth() - 10;
        int startYPosition = 100;

        for (int i = 0; i<5; i++) {
            canvas.drawText(informationArray[i], startXPosition, startYPosition, paint);
            canvas.drawLine(startXPosition, startYPosition+3, endXPosition, startYPosition+3, paint);
            startYPosition+=20;
        }
        canvas.drawLine(80, 92, 80, 190, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(10, 200, pageInfo.getPageWidth()-10, 300, paint);
        canvas.drawLine(85, 200, 85, 300, paint);
        canvas.drawLine(163, 200, 163, 300, paint);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawText("Photo", 35, 250, paint);
        canvas.drawText("Photo", 110, 250, paint);
        canvas.drawText("Photo", 190, 250, paint);

        canvas.drawText("Note:", 10, 320, paint);
        canvas.drawLine(35, 325, pageInfo.getPageWidth()-10, 325, paint);
        canvas.drawLine(10, 345, pageInfo.getPageWidth()-10, 345, paint);
        canvas.drawLine(10, 365, pageInfo.getPageWidth()-10, 365, paint);

        pdfDocument.finishPage(page);
        File file = new File(Environment.getExternalStorageDirectory(), "/Documents/testPDF.pdf");
        if (!file.exists()) {
            file.createNewFile();
        }

        pdfDocument.writeTo(new FileOutputStream(file));
        pdfDocument.close();
    }
}
