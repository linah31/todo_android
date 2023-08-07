package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        RateUsDialog rateUsDialog = new RateUsDialog(RateActivity.this);
        rateUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        rateUsDialog.setCancelable(false);
        rateUsDialog.show();

       BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_rate);

        bottomNavigationView.setOnNavigationItemSelectedListener(item ->
        {
            switch(item.getItemId())
            {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;

                case R.id.bottom_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_rate:
                    return true;


            }
            return false;


        });

    }

}
