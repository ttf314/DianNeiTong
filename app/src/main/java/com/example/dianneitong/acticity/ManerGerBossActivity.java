package com.example.dianneitong.acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dianneitong.R;
import com.example.dianneitong.UserManager2Activity;
import com.example.dianneitong.UserManagerActivity;

public class ManerGerBossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maner_ger_boss);
    }


    public void lookfoods(View view) {
        Intent intent = new Intent(ManerGerBossActivity.this, FoodManegerActivity.class);
        startActivity(intent);
    }

    public void lookCar(View view) {
        Intent intent = new Intent(ManerGerBossActivity.this, ShowCarActivity.class);
        startActivity(intent);

    }

    public void lookdingdan(View view) {
        Intent intent = new Intent(ManerGerBossActivity.this, ShowDingDanActivity.class);
        startActivity(intent);
    }

    public void lookuser(View view) {
        Intent intent = new Intent(ManerGerBossActivity.this, UserManager2Activity.class);
        startActivity(intent);

    }
}
