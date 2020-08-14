package com.example.ogrencievi2.Ekleme;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ogrencievi2.Login.EvOlustur;
import com.example.ogrencievi2.R;
import com.example.ogrencievi2.Sayfalar.AnaSayfa_Frag;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaBilgi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class HarcamEkle_Frag extends Fragment{
    View rootView;
    Button gidaB,faturaB,kiraB,digerB,ekleB;
    CardView gidaC,faturaC,kiraC,digerC;
    EditText fiyat;
    int Resimler[];
    String kategori ="Gıda";
    int resimID;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_harcam_ekle_, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Harcamalar");

        Resimler = new int[]{R.drawable.bill,R.drawable.house,R.drawable.burger,R.drawable.document};

        gidaB=rootView.findViewById(R.id.gidaButton);
        kiraB=rootView.findViewById(R.id.kiraButton);
        faturaB=rootView.findViewById(R.id.faturaButton);
        digerB=rootView.findViewById(R.id.digerButton);

        gidaC=rootView.findViewById(R.id.Gida);
        faturaC=rootView.findViewById(R.id.Fatura);
        kiraC=rootView.findViewById(R.id.Kira);
        digerC=rootView.findViewById(R.id.Diger);

        fiyat =rootView.findViewById(R.id.tutar);

        ekleB =rootView.findViewById(R.id.ekleButton);



        gidaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gidaC.setCardBackgroundColor(Color.rgb(252,81,133));
                kiraC.setCardBackgroundColor(Color.rgb(63,193,201));
                faturaC.setCardBackgroundColor(Color.rgb(63,193,201));
                digerC.setCardBackgroundColor(Color.rgb(63,193,201));
                kategori= "Gıda";
                resimID=Resimler[2];
            }
        });
        kiraB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gidaC.setCardBackgroundColor(Color.rgb(63,193,201));
                kiraC.setCardBackgroundColor(Color.rgb(252,81,133));
                faturaC.setCardBackgroundColor(Color.rgb(63,193,201));
                digerC.setCardBackgroundColor(Color.rgb(63,193,201));
                kategori= "Kira";
                resimID=Resimler[1];
            }
        });
        faturaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gidaC.setCardBackgroundColor(Color.rgb(63,193,201));
                kiraC.setCardBackgroundColor(Color.rgb(63,193,201));
                faturaC.setCardBackgroundColor(Color.rgb(252,81,133));
                digerC.setCardBackgroundColor(Color.rgb(63,193,201));
                kategori= "Fatura";
                resimID=Resimler[0];
            }
        });
        digerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gidaC.setCardBackgroundColor(Color.rgb(63,193,201));
                kiraC.setCardBackgroundColor(Color.rgb(63,193,201));
                faturaC.setCardBackgroundColor(Color.rgb(63,193,201));
                digerC.setCardBackgroundColor(Color.rgb(252,81,133));
                kategori= "Diger";
                resimID=Resimler[3];
            }
        });
        ekleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                harcamaEkle();
                FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.anaFragment,new AnaSayfa_Frag());
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }



    public void harcamaEkle(){
        String evIsim="";
        String kisiResim="";
        String ekleyenKisi="";
            try{
                FileInputStream fileInputStream = getActivity().openFileInput("Bilgiler.txt");
                InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

                String line =bufferedReader.readLine();
                String Dizi[]=line.split("/");
                evIsim=Dizi[0];
                ekleyenKisi=Dizi[1];
                kisiResim=Dizi[2];

            }
            catch (Exception e){

            }
        int kisiResimId=Integer.parseInt(kisiResim);
        String fiyatStr =fiyat.getText().toString();
        if(!TextUtils.isEmpty(fiyatStr)){
            String id =databaseReference.push().getKey();

            HarcamaBilgi harcamaBilgi= new HarcamaBilgi(evIsim,ekleyenKisi,fiyatStr,kategori,resimID,kisiResimId,id);

            databaseReference.child(id).setValue(harcamaBilgi);
        }
        else{
            Toast.makeText(getContext(),"Fiyat girilmeden harcama ekleyemezsin",Toast.LENGTH_LONG).show();
        }

    }
}
