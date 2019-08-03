package com.ulfa.bimbelbungabangsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        ImageView inputdata = (ImageView) findViewById(R.id.btnInputDataSiswa);
        ImageView inputnilai = (ImageView) findViewById(R.id.btnInputNilai);
        ImageView jadwalmengajar = (ImageView) findViewById(R.id.btnJadwalMengajar);
        ImageView materiMengajar = (ImageView) findViewById(R.id.btnmateri);

        inputdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDataSiswa();
            }
        });
        inputnilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputNilai();
            }
        });
        jadwalmengajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JadwalMengajar();
            }
        });
        materiMengajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materiMengajarSiswa();
            }
        });
    }



    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    private void InputDataSiswa(){
        startActivity(new Intent(SecondActivity.this, InputDataActivity.class));
    }

    private void InputNilai(){
        startActivity(new Intent(SecondActivity.this, InputNilaiActivity.class));
    }

    private void JadwalMengajar(){
        startActivity(new Intent(SecondActivity.this, JadwalMengajarActivity.class));
    }
    private void materiMengajarSiswa(){
        startActivity(new Intent(SecondActivity.this, materiMengajar.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                break;

            }
            case R.id.profileMenu:
                startActivity(new Intent(SecondActivity.this, ProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
