package br.com.bgdo.pdftest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewerActivity extends AppCompatActivity {

    PDFView mPdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        mPdfView = findViewById(R.id.pdfView);
        String file = Environment.getExternalStorageDirectory() + "/Documents/" + getIntent().getStringExtra("filename");
        mPdfView.fromFile(new File(file)).load();
    }
}