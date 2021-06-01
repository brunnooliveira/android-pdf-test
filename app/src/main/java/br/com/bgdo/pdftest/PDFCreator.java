package br.com.bgdo.pdftest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFCreator {

    public static void createTwoPages(String fileName) throws IOException {
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

        File file = new File(Environment.getExternalStorageDirectory(), "/Documents/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        pdfDocument.writeTo(new FileOutputStream(file));
        pdfDocument.close();
    }

    public static void createInvoice(Resources resources, String fileName) throws IOException {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        int pageWidth = 1200;

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, 2010, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.istio);
        Bitmap scaledBitmapHeader = Bitmap.createScaledBitmap(bitmap, 1200, 418, false);
        Bitmap bitmapLinux = BitmapFactory.decodeResource(resources, R.drawable.linux);
        Bitmap scaledBitmapIcon = Bitmap.createScaledBitmap(bitmapLinux, 150, 150, false);

        canvas.drawBitmap(scaledBitmapHeader, 0, 0, paint);
        canvas.drawBitmap(scaledBitmapIcon, 1005, 353, paint);

        Paint titlePaint = new Paint();
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(70);
        canvas.drawText("Istio Pizza", pageWidth/2, 270, titlePaint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(30f);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Call: 85 99999-9999", 1160, 40, paint);
        canvas.drawText("85 99999-9988", 1160, 80, paint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(70);
        canvas.drawText("Invoice", pageWidth/2, 500, titlePaint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(35f);
        paint.setColor(Color.BLACK);
        canvas.drawText("Customer Name: Brunno Oliveira", 20, 590, paint);
        canvas.drawText("Contact No.: 85 99999-7777", 20, 640, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Invoice No.: 234234", pageWidth-20, 590, paint);
        canvas.drawText("Date: " + new SimpleDateFormat("dd/MM/yy").format(new Date()), pageWidth-20, 640, paint);
        canvas.drawText("Time:" + new SimpleDateFormat("HH:mm:ss").format(new Date()), pageWidth-20, 690, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(20, 780, pageWidth-20, 860, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Si. No.", 40, 830, paint);
        canvas.drawText("Item Description", 200, 830, paint);
        canvas.drawText("Price", 700, 830, paint);
        canvas.drawText("Qty.", 900, 830, paint);
        canvas.drawText("Total", 1050, 830, paint);

        canvas.drawLine(180, 790, 180, 840, paint);
        canvas.drawLine(680, 790, 680, 840, paint);
        canvas.drawLine(880, 790, 880, 840, paint);
        canvas.drawLine(1030, 790, 1030, 840, paint);

        canvas.drawText("1.", 40, 950, paint);
        canvas.drawText("Margherita", 200, 950, paint);
        canvas.drawText("200.0", 700, 950, paint);
        canvas.drawText("2", 900, 950, paint);
        canvas.drawText("400.0", 1050, 950, paint);

        pdfDocument.finishPage(page);

        File file = new File(Environment.getExternalStorageDirectory(), "/Documents/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        pdfDocument.writeTo(new FileOutputStream(file));
        pdfDocument.close();
    }

    public static void createWithImage(Resources resources, String fileName) throws IOException {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.istio);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 30, 30, false);

        canvas.drawBitmap(scaledBitmap, 40, 50, paint);
        pdfDocument.finishPage(page);

        File file = new File(Environment.getExternalStorageDirectory(), "/Documents/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        pdfDocument.writeTo(new FileOutputStream(file));
        pdfDocument.close();
    }

    public static void createForm(String fileName) throws IOException {
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
        File file = new File(Environment.getExternalStorageDirectory(), "/Documents/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        pdfDocument.writeTo(new FileOutputStream(file));
        pdfDocument.close();
    }
}
