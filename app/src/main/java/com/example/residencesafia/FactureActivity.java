package com.example.residencesafia;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FactureActivity extends AppCompatActivity {

    BottomNavigationView bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facture);

        bottomMenu = findViewById(R.id.bottom_menu);

        bottomMenu.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.itHome) {
                Intent intent = new Intent(FactureActivity.this, Home.class);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.location) {
                Intent intent = new Intent(FactureActivity.this, GoogleMaps.class);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.facture) {
                return true;
            }
            return false;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        bottomMenu.setSelectedItemId(R.id.facture);
    }
}
