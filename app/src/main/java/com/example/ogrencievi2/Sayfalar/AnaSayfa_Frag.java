package com.example.ogrencievi2.Sayfalar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ogrencievi2.Ekleme.HarcamEkle_Frag;
import com.example.ogrencievi2.Grafik;
import com.example.ogrencievi2.QrKod.olustur;
import com.example.ogrencievi2.R;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaBilgi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AnaSayfa_Frag extends Fragment {
    View rootView;

    static String evAdi;
    static String kisiAdi;
    static String kisiResim;

    TextView kGide,kFatura,kKira,kDiger,evToplam,kisiBasi;

     int kiraToplam=0;
     int digerToplam=0;
     int faturaToplam=0;
     int gidaToplam=0;
    int genelToplam =0;

    Button harcamaEkle,grafik,harcamalar;
    Activity context;

    TextView evEdiTF;


    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_ana_sayfa_, container, false);

        databaseReference=FirebaseDatabase.getInstance().getReference("Harcamalar");
        evEdiTF = rootView.findViewById(R.id.evAdiET);

        kGide=rootView.findViewById(R.id.kGida);
        kFatura=rootView.findViewById(R.id.kFatura);
        kKira=rootView.findViewById(R.id.kKira);
        kDiger=rootView.findViewById(R.id.kDiger);
        evToplam=rootView.findViewById(R.id.toplamHarcama);
        kisiBasi=rootView.findViewById(R.id.kisiBasi);

        harcamalar =rootView.findViewById(R.id.harcamalar);
        harcamaEkle =rootView.findViewById(R.id.harcamaEkle);
        grafik = rootView.findViewById(R.id.grafikAc);
        context=getActivity();

        harcamalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new harcama());
            }
        });

        harcamaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new HarcamEkle_Frag());
            }
        });
        grafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            FileInputStream fileInputStream=getActivity().openFileInput("Bilgiler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();
            String dizi[]=line.split("/");
            evAdi=dizi[0];
            kisiAdi=dizi[1];
            kisiResim=dizi[2];


        }
        catch (Exception e){


        }
        evEdiTF.setText(evAdi);
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 kiraToplam=0;
                 digerToplam=0;
                 faturaToplam=0;
                 gidaToplam=0;
                 genelToplam =0;


                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    HarcamaBilgi harcamaBilgi=snapshot.getValue(HarcamaBilgi.class);
                    System.out.println(evAdi+" " +kisiAdi+"");
                    if( evAdi.equals(harcamaBilgi.getEvAdi())&&kisiAdi.equals(harcamaBilgi.getHarcamaYapanKisi())){


                        if(harcamaBilgi.getKategori().equals("Fatura")){
                            faturaToplam+=Integer.parseInt(harcamaBilgi.getFiyat());
                            System.out.println("Fatura");

                        }
                        else if(harcamaBilgi.getKategori().equals("Kira")){
                            kiraToplam+=Integer.parseInt(harcamaBilgi.getFiyat());
                            System.out.println(harcamaBilgi.getFiyat());

                        }
                        else if(harcamaBilgi.getKategori().equals("Gıda")){
                            gidaToplam+=Integer.parseInt(harcamaBilgi.getFiyat());

                        }
                        else if(harcamaBilgi.getKategori().equals("Diger")){
                            digerToplam+=Integer.parseInt(harcamaBilgi.getFiyat());

                        }

                    }
                    if(evAdi!=null && evAdi.equals(harcamaBilgi.getEvAdi())){
                        genelToplam+=Integer.parseInt(harcamaBilgi.getFiyat());
                    }

                    kGide.setText(gidaToplam+"");
                    kFatura.setText(faturaToplam+"");
                    kKira.setText(kiraToplam+"");
                    kDiger.setText(digerToplam +"");
                    evToplam.setText(genelToplam+"");
                    kisiBasi.setText(genelToplam/3+"");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });










    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.anaFragment,fragment);
        fragmentTransaction.commit();
    }
}
