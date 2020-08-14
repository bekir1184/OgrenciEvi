package com.example.ogrencievi2.Sayfalar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.ogrencievi2.KisiBilgileriDosya;
import com.example.ogrencievi2.R;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaBilgi;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.Not;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.NotAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Notalar_Frag extends Fragment {

    DatabaseReference databaseReference;
    List<Not> list =new ArrayList<>();
    View rootView;
    Activity context;
    String evAdi;
    ViewPager viewPager;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_notalar_, container, false);


        databaseReference = FirebaseDatabase.getInstance().getReference("Notlar");

        context=getActivity();





        //




        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

        try{
            FileInputStream fileInputStream = getActivity().openFileInput("Bilgiler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();
            String Dizi[]=line.split("/");
            evAdi=Dizi[0];


        }
        catch (Exception e){

        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    Not not=snapshot.getValue(Not.class);

                    if(evAdi.equals(not.getEvAdi())){
                        list.add(not);
                    }

                }
                NotAdapter notAdapter= new NotAdapter(context,list);
                viewPager=rootView.findViewById(R.id.notlarView);
                viewPager.setAdapter(notAdapter);

                viewPager.setPadding(50,0,50,10);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
