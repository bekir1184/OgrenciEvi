package com.example.ogrencievi2.QrKod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ogrencievi2.AnaSayfa.AnaSayfa;
import com.example.ogrencievi2.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class olustur extends AppCompatActivity {
    ImageView kodResim;
    Button geriBtn;
    String evAdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olustur);
        kodResim=findViewById(R.id.kodResim);
        geriBtn=findViewById(R.id.geriBtn);

        geriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(olustur.this, AnaSayfa.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            FileInputStream fileInputStream=openFileInput("Bilgiler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();
            String dizi[]=line.split("/");
            evAdi=dizi[0];

            MultiFormatWriter multiFormatWriter= new MultiFormatWriter();

            BitMatrix bitMatrix= multiFormatWriter.encode(evAdi, BarcodeFormat.QR_CODE,500,500);

            BarcodeEncoder barcodeEncoder= new BarcodeEncoder();

            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);

            kodResim.setImageBitmap(bitmap);


        }
        catch (Exception e){


        }


    }
}
