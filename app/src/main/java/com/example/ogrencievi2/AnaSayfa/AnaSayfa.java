package com.example.ogrencievi2.AnaSayfa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ogrencievi2.Ekleme.AlarmAyarla_Frag;
import com.example.ogrencievi2.Ekleme.HarcamEkle_Frag;
import com.example.ogrencievi2.Ekleme.NotEkle_Frag;
import com.example.ogrencievi2.R;
import com.example.ogrencievi2.Sayfalar.AnaSayfa_Frag;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaBilgi;
import com.example.ogrencievi2.Sayfalar.FirebaseClaslar.HarcamaListe;
import com.example.ogrencievi2.Sayfalar.Notalar_Frag;
import com.example.ogrencievi2.Sayfalar.harcama;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.gjiazhe.multichoicescirclebutton.MultiChoicesCircleButton;
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



public class AnaSayfa extends AppCompatActivity {






    BubbleNavigationConstraintView bubbleNavigationConstraintView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        setFragment(new AnaSayfa_Frag());
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnaSayfa_Frag sayfaFrag = new AnaSayfa_Frag();
        setFragment(sayfaFrag);




    }



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.anaFragment,fragment);
        fragmentTransaction.commit();
    }


}
