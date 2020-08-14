package com.example.ogrencievi2.Sayfalar.FirebaseClaslar;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ogrencievi2.KisiBilgileriDosya;
import com.example.ogrencievi2.R;
import com.example.ogrencievi2.Sayfalar.harcama;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.LogRecord;

import co.dift.ui.SwipeToAction;

public class HarcamaListe extends ArrayAdapter<HarcamaBilgi> {

    private Activity context;
    private List<HarcamaBilgi> list ;
    String kisiAdi;


    public HarcamaListe(Activity context,List<HarcamaBilgi> list){
        super(context,R.layout.harcamaitem,list);//Hata
        if(context!=null){
            System.out.println("Context yok");
        }
        this.context=context;
        this.list=list;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.harcamaitem,null,true);

        Button ana=listViewItem.findViewById(R.id.activeSil);
        final Button button=listViewItem.findViewById(R.id.silButton);
        TextView kullaniciIsim=listViewItem.findViewById(R.id.kullaniciAdiItem);
        TextView kategori =listViewItem.findViewById(R.id.KategoriItem);
        TextView fiyat =listViewItem.findViewById(R.id.fiyatItem);
        ImageView kategoriResim =listViewItem.findViewById(R.id.kategoriResimItem);


        final HarcamaBilgi harcamaBilgi= list.get(position);

        kullaniciIsim.setText(harcamaBilgi.getHarcamaYapanKisi());
        kategori.setText(harcamaBilgi.getKategori());
        fiyat.setText(harcamaBilgi.getFiyat());


        kategoriResim.setImageResource(harcamaBilgi.getKategoriResim());

        button.setVisibility(View.INVISIBLE);

        try {
            FileInputStream fileInputStream=context.openFileInput("Bilgiler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();
            String dizi[]=line.split("/");
            kisiAdi=dizi[1];
        }
        catch (Exception e){


        }

        ana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(harcamaBilgi.getHarcamaYapanKisi().equals(kisiAdi)){


                        button.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setVisibility(View.INVISIBLE);
                        }
                    },2000);

                }


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.isCursorVisible()){
                    harcamasil(harcamaBilgi.getID());
                }
            }
        });

        return  listViewItem;
    }

    public void harcamasil(String ID){
        DatabaseReference harcama= FirebaseDatabase.getInstance().getReference("Harcamalar").child(ID);
        harcama.removeValue();

    }

}
