package br.com.bgdo.pdftest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);

        Button buttonTestPdf = findViewById(R.id.button_test_pdf);
        buttonTestPdf.setOnClickListener(v -> {
            try {
                String fileName = "invoice.pdf";
                PDFCreator.createInvoice(getResources(), fileName);
                Intent intent = new Intent(this, ViewerActivity.class);
                intent.putExtra("filename", fileName);
                startActivity(intent);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}