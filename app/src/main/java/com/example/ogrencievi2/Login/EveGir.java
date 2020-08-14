package com.example.ogrencievi2.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ogrencievi2.AnaSayfa.AnaSayfa;
import com.example.ogrencievi2.KisiBilgileriDosya;
import com.example.ogrencievi2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EveGir extends AppCompatActivity {
    EditText evAdi,kisiAdi,sifre;
    Button girBtn;

    List<String> evSifre=new ArrayList<>();


    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve_gir);

        databaseReference= FirebaseDatabase.getInstance().getReference("Evler");


        girBtn=findViewById(R.id.GirBtn);
        evAdi=findViewById(R.id.EvAdiAlani);
        kisiAdi=findViewById(R.id.isimAlani);
        sifre=findViewById(R.id.sifreAlani);


        girBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girisYap();
            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    Ev ev= snapshot.getValue(Ev.class);
                    evSifre.add(ev.getEvAdi()+ev.getSifre());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void girisYap(){
        String evIsimstr =evAdi.getText().toString();
        String kisiIsimstr=kisiAdi.getText().toString();
        String sifreStr = sifre.getText().toString();
        int sayac=0;
        if(!TextUtils.isEmpty(evIsimstr) && !TextUtils.isEmpty(kisiIsimstr) && !TextUtils.isEmpty(sifreStr)){
            final String evAdiSifre=evAdi.getText().toString()+sifre.getText().toString();
            for (String gez:evSifre){
                if(gez.equals(evAdiSifre)){
                    try {
                        girisYapildi();
                        kisiKaydet();
                        anaSayfayaGit();
                        sayac++;

                    }catch (Exception e){

                    }

                }


            }
            if(sayac==0){
                Toast.makeText(EveGir.this,"Ev adi yada şifre hatali", Toast.LENGTH_LONG).show();
                evAdi.setText("");
                sifre.setText("");
            }


        }
        else{
            Toast.makeText(EveGir.this,"Lutfen tum alanları doldurunuz", Toast.LENGTH_LONG).show();
        }

    }

    public void anaSayfayaGit(){
        Intent intent =new Intent(EveGir.this, AnaSayfa.class);
        startActivity(intent);
    }
    public void girisYapildi() throws IOException {
        FileOutputStream fileOutputStream = openFileOutput("Giris.txt", MODE_PRIVATE);
        fileOutputStream.write("1".getBytes());
        fileOutputStream.close();
    }
    public void kisiKaydet() throws IOException {

        KisiBilgileriDosya.evAdi=evAdi.getText().toString();
        KisiBilgileriDosya.kisiAdi=kisiAdi.getText().toString();

        FileOutputStream kisiBilgi=openFileOutput("Bilgiler.txt",MODE_PRIVATE);
        kisiBilgi.write(evAdi.getText().toString().getBytes());
        kisiBilgi.write("/".getBytes());
        kisiBilgi.write(kisiAdi.getText().toString().getBytes());
        kisiBilgi.write("/".getBytes());
        kisiBilgi.write("0".getBytes());
        kisiBilgi.close();
    }

}
