package com.example.ogrencievi2.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ogrencievi2.AnaSayfa.AnaSayfa;
import com.example.ogrencievi2.KisiBilgileriDosya;
import com.example.ogrencievi2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EvOlustur extends AppCompatActivity {
    EditText evİsim,kisiIsim,sifre1,sifre2;
    Button olustur;
    DatabaseReference databaseReference;

    String kisiResim="0";

    FileOutputStream fileOutputStream;
    FileOutputStream kisiBilgi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ev_olustur);

        evİsim=findViewById(R.id.evAdiET);
        kisiIsim=findViewById(R.id.isimET);
        sifre1=findViewById(R.id.sifreET1);
        sifre2=findViewById(R.id.sifreET);
        olustur=findViewById(R.id.OlusturBtn);

        databaseReference = FirebaseDatabase.getInstance().getReference("Evler");

        olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evEkle();
            }
        });





    }
    public void evEkle(){
        String evIsimstr=evİsim.getText().toString();
        String kisiIsimstr=kisiIsim.getText().toString();
        String sifre1str=sifre1.getText().toString();
        String sifre2str=sifre2.getText().toString();
        if(!TextUtils.isEmpty(evIsimstr) && !TextUtils.isEmpty(kisiIsimstr)&&
                !TextUtils.isEmpty(sifre1str) && !TextUtils.isEmpty(sifre2str) ){

            if(sifre1str.equals(sifre2str)){
                String id =databaseReference.push().getKey();

                Ev ev= new Ev(evIsimstr,kisiIsimstr,sifre1str,id,kisiResim);

                databaseReference.child(id).setValue(ev);

                Toast.makeText(EvOlustur.this,"Ev oluşturuldu",Toast.LENGTH_LONG).show();

                try{
                    girisYapildi();
                    kisiKaydet();
                }
                catch (Exception e){}

                anaSayfayaGit();
            }
            else{
                Toast.makeText(EvOlustur.this,"Şifreler aynı değil",Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(EvOlustur.this,"Lutfen tum alanları doldurunuz", Toast.LENGTH_LONG).show();

        }

    }
    public void anaSayfayaGit(){
        Intent intent =new Intent(EvOlustur.this, AnaSayfa.class);
        startActivity(intent);
    }
    public void girisYapildi() throws IOException {
        fileOutputStream =openFileOutput("Giris.txt",MODE_PRIVATE);
        fileOutputStream.write("1".getBytes());
        fileOutputStream.close();
    }
    public void kisiKaydet() throws IOException {

        KisiBilgileriDosya.evAdi=evİsim.getText().toString();
        KisiBilgileriDosya.kisiAdi=kisiIsim.getText().toString();

        kisiBilgi=openFileOutput("Bilgiler.txt",MODE_PRIVATE);
        kisiBilgi.write(evİsim.getText().toString().getBytes());
        kisiBilgi.write("/".getBytes());
        kisiBilgi.write(kisiIsim.getText().toString().getBytes());
        kisiBilgi.write("/".getBytes());
        kisiBilgi.write(kisiResim.getBytes());
        kisiBilgi.close();
    }
}
