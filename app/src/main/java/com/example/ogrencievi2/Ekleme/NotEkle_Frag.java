package com.example.ogrencievi2.Ekleme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ogrencievi2.KisiBilgileriDosya;
import com.example.ogrencievi2.R;
import com.example.ogrencievi2.Sayfalar.AnaSayfa_Frag;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.Not;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DatabaseMetaData;


public class NotEkle_Frag extends Fragment {
    View rootView;
    DatabaseReference databaseReference;
    EditText notBaslık,notKonu;
    Button notEkleBtn;

    String ekleyenKisi;
    String evAdi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_not_ekle_, container, false);

        notEkleBtn=rootView.findViewById(R.id.notEkle);
        notBaslık=rootView.findViewById(R.id.notBaslik);
        notKonu=rootView.findViewById(R.id.icerik);

        databaseReference= FirebaseDatabase.getInstance().getReference("Notlar");

        try{
            FileInputStream fileInputStream = getActivity().openFileInput("Bilgiler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();
            String Dizi[]=line.split("/");
            evAdi=Dizi[0];
            ekleyenKisi=Dizi[1];

        }
        catch (Exception e){

        }



        notEkleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notBaslikstr=notBaslık.getText().toString();
                String notkonustr=notKonu.getText().toString();

                if(TextUtils.isEmpty(notBaslikstr)){
                    Toast.makeText(getContext(),"Not başlığı girilmeden not ekleyemezsin",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(notkonustr)){
                    Toast.makeText(getContext(),"Not içeriği girilmeden not ekleyemezsin",Toast.LENGTH_LONG).show();
                }
                else if (!TextUtils.isEmpty(notkonustr) && !TextUtils.isEmpty(notBaslikstr)){
                    String id =databaseReference.push().getKey();



                    Not not = new Not(notBaslikstr,notkonustr,id,ekleyenKisi,evAdi);

                    databaseReference.child(id).setValue(not);

                    Toast.makeText(getContext(),"Not eklendi ",Toast.LENGTH_LONG).show();

                    setFragment(new AnaSayfa_Frag());
                }

            }
        });


        return rootView;
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.anaFragment,fragment);
        fragmentTransaction.commit();
    }



}
