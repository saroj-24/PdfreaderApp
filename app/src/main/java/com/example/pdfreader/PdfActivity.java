package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

public class PdfActivity extends AppCompatActivity {

    String filepath = "";
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Toolbar tool = findViewById(R.id.toolbar1);
        setSupportActionBar(tool);

        pdfView = findViewById(R.id.pdfView);
        filepath = getIntent().getStringExtra("path");
        File file = new File(filepath);
        Uri path = Uri.fromFile(file);
        pdfView.fromUri(path).load();
        pdfView.fitToWidth();


        pdfView.fromAsset("path")
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(1);


    }
}