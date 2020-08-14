package com.example.ogrencievi2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaBilgi;
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

public class Grafik extends AppCompatActivity {

    static int kiraToplam=0;
    static int digerToplam=0;
    static int faturaToplam=0;
    static int gidaToplam=0;

    static String evAdi;

    DatabaseReference databaseReference;


    int diziToplam[];
    String isimler[]={"Kira","Gida","Fatura","Diger"};
    
    int renkler []={R.color.blue_active,R.color.red_active,R.color.green_active,R.color.yellow_active};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);
        databaseReference = FirebaseDatabase.getInstance().getReference("Harcamalar");
        

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


        }
        catch (Exception e){


        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kiraToplam=gidaToplam=faturaToplam=digerToplam=0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    HarcamaBilgi harcamaBilgi=snapshot.getValue(HarcamaBilgi.class);
                    if(evAdi.equals(harcamaBilgi.getEvAdi())){

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
                    diziToplam= new int[]{kiraToplam, gidaToplam, faturaToplam, digerToplam};
                    setupPieChart();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void setupPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0;i<diziToplam.length;i++){
            pieEntries.add(new PieEntry(diziToplam[i],isimler[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Harcama Gider Tablosu");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(dataSet);
        PieChart pieChart= findViewById(R.id.chart);
        pieChart.setData(pieData);
        pieChart.animateY(1000);
        pieChart.invalidate();

    }
}
