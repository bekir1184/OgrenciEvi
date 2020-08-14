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
import android.widget.ListView;

import com.example.ogrencievi2.Grafik;
import com.example.ogrencievi2.R;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaBilgi;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaListe;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
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


public class harcama extends Fragment {

    DatabaseReference databaseReference;
    List<HarcamaBilgi> list =new ArrayList<>();
    List<HarcamaBilgi> listSon =new ArrayList<>();
    ListView listView;
    View rootView;
    Activity context;
    static String evAdi="";
    static int kiraToplam=0;
    static int digerToplam=0;
    static int faturaToplam=0;
    static int gidaToplam=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_harcama, container, false);
        list= new ArrayList<>();
        listView=rootView.findViewById(R.id.listViewHarcama);
        databaseReference = FirebaseDatabase.getInstance().getReference("Harcamalar");
        context=getActivity();
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
        }
        catch (Exception e){
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    HarcamaBilgi harcamaBilgi=snapshot.getValue(HarcamaBilgi.class);
                    System.out.println(evAdi+" -->"+harcamaBilgi.getEvAdi());
                    if(evAdi.equals(harcamaBilgi.getEvAdi())){
                        list.add(harcamaBilgi);
                        if(harcamaBilgi.getKategori().equals("Fatura")){
                            faturaToplam+=Integer.parseInt(harcamaBilgi.getFiyat());
                            System.out.println("fatura"+faturaToplam);

                        }
                        else if(harcamaBilgi.getKategori().equals("Kira")){
                            kiraToplam+=Integer.parseInt(harcamaBilgi.getFiyat());
                            System.out.println("kira Toplam"+kiraToplam);

                        }
                        else if(harcamaBilgi.getKategori().equals("Gıda")){
                            gidaToplam+=Integer.parseInt(harcamaBilgi.getFiyat());
                            System.out.println("gida toplam"+gidaToplam);

                        }
                        else if(harcamaBilgi.getKategori().equals("Diger")){
                            digerToplam+=Integer.parseInt(harcamaBilgi.getFiyat());
                            System.out.println("diğer"+digerToplam);
                        }
                    }
                }
                for(int i= list.size()-1; i>=0 ;i--){
                    listSon.add(list.get(i));
                }
                HarcamaListe harcamaListe= new HarcamaListe(context,listSon);//hata

                listView.setAdapter(harcamaListe);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}
