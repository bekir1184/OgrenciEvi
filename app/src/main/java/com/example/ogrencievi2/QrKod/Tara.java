package com.example.ogrencievi2.QrKod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ogrencievi2.AnaSayfa.AnaSayfa;
import com.example.ogrencievi2.KisiBilgileriDosya;
import com.example.ogrencievi2.Login.Ev;
import com.example.ogrencievi2.Login.EveGir;
import com.example.ogrencievi2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.google.zxing.qrcode.encoder.QRCode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Tara extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;
    DatabaseReference reference;
    String evAdi;
    String resim =QrKod.kisiResim;
    String isim= QrKod.isimStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tara);

        reference= FirebaseDatabase.getInstance().getReference("Evler");

        zXingScannerView =new ZXingScannerView(this);
        setContentView(zXingScannerView);


    }

    @Override
    public void handleResult(final Result result) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    Ev ev= snapshot.getValue(Ev.class);
                    if(ev.getEvAdi().equals(result.getText())){
                        System.out.println("Bulundu");
                        try {
                            evAdi=result.getText();
                            girisYapildi();
                            kisiKaydet();
                            anaSayfayaGit();
                        }
                        catch (Exception e){

                        }
                    }
                    else{
                        System.out.println("bulunumadı");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
    public void anaSayfayaGit(){
        Intent intent =new Intent(Tara.this, AnaSayfa.class);
        startActivity(intent);
    }
    public void girisYapildi() throws IOException {
        FileOutputStream fileOutputStream = openFileOutput("Giris.txt", MODE_PRIVATE);
        fileOutputStream.write("1".getBytes());
        fileOutputStream.close();
    }
    public void kisiKaydet() throws IOException {

        FileOutputStream kisiBilgi=openFileOutput("Bilgiler.txt",MODE_PRIVATE);
        kisiBilgi.write(evAdi.getBytes());
        kisiBilgi.write("/".getBytes());
        kisiBilgi.write(isim.getBytes());
        kisiBilgi.write("/".getBytes());
        kisiBilgi.write(resim.getBytes());
        kisiBilgi.close();
    }
}
