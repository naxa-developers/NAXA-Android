package com.NaxaCo.Naxa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class Info extends AppCompatActivity {
    PDFView pdfView;
    Button vLandMeasurement;
    Button vTest;
    String Vvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        vLandMeasurement=findViewById(R.id.measure);
        vTest=findViewById(R.id.test);
        vTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Info.this,PdfView.class);
                startActivity(intent);
            }
        });
    }
}
