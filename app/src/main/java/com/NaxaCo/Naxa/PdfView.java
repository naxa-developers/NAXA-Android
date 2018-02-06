package com.NaxaCo.Naxa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by cri_r on 04/02/2018.
 */

public class PdfView extends AppCompatActivity {
    String value;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view);
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("android_tutorial.pdf").load();
    }
}
