package net.penguincoders.doit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
SwitchCompat switchMode,brightswitch;
boolean nightMode;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchMode= findViewById(R.id.switchMode);
        sharedPreferences= getSharedPreferences("Mode", Context.MODE_PRIVATE);
        nightMode= sharedPreferences.getBoolean("nightMode",false);
        brightswitch=findViewById(R.id.switchbright);

        if(nightMode)
        {
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nightMode)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("nightMode",false);

                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("nightMode",true);
                }
                editor.apply();
            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_settings);

        bottomNavigationView.setOnNavigationItemSelectedListener(item ->
        {
            switch(item.getItemId())
            {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;

                case R.id.bottom_settings:
                    return true;
                case R.id.bottom_rate:
                    startActivity(new Intent(getApplicationContext(), RateActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;


            }
            return false;


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.testmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);

                builder.setMessage("Our App is useful for orgainzing your tasks throughout the day and help you achieve your goals.");
                builder.setTitle("About Us!");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {

                });
                builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            default:return super.onOptionsItemSelected(item);
        }

    }
}

