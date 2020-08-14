package com.example.ogrencievi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ogrencievi2.AnaSayfa.AnaSayfa;
import com.example.ogrencievi2.Login.EvOlustur;
import com.example.ogrencievi2.Login.EveGir;
import com.example.ogrencievi2.QrKod.QrKod;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button eveGir,evOlustur,QRBtn;
    FileInputStream fileInputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eveGir=findViewById(R.id.eveGirBtn);
        evOlustur=findViewById(R.id.evOlusturBtn);
        QRBtn =findViewById(R.id.QRKod);

        QRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, QrKod.class);
                startActivity(intent);
            }
        });

        eveGir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, EveGir.class);
                startActivity(intent);
            }
        });

        evOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, EvOlustur.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            fileInputStream=openFileInput("Giris.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();

            if(line.equals("1")){
                Intent intent = new Intent(MainActivity.this, AnaSayfa.class);
                startActivity(intent);
            }
        }
        catch(Exception e){

        }

    }
}
