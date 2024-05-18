package com.example.residencesafia;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.cardview.widget.CardView;

import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    ImageView imageView;
    ViewPager viewPager;
    TextView textView, textView2, textView3, textView4;
    BottomNavigationView bottomMenu;
    CardView cardView;
    private int[] photos = {R.drawable.appartements, R.drawable.appartements2, R.drawable.appartements3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        imageView = findViewById(R.id.imageView);
        viewPager = findViewById(R.id.viewPager);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        bottomMenu = findViewById(R.id.bottom_menu);
        cardView = findViewById(R.id.cardView);

        // Set up ViewPager
        PhotoPagerAdapter pagerAdapter = new PhotoPagerAdapter(this, photos);
        viewPager.setAdapter(pagerAdapter);

        bottomMenu.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.itHome) {
                return true;
            } else if (itemId == R.id.location) {
                Intent intent = new Intent(Home.this, GoogleMaps.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.facture) {
                return true;
            }
            return false;
        });
    }
}
