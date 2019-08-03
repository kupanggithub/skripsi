package com.ulfa.bimbelbungabangsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulfa.bimbelbungabangsa.model.datamhs;

public class updatedata extends AppCompatActivity {
    EditText nama,alamat,kelas,nohp,jeniskelamin,tgllahir;
    Spinner matpel;
    Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    String getiduser;
    String setNama,setAlamat,setKelas,setNohp,setMatpel,setJeniskel,setTgllahir,setiduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatedata);
        getSupportActionBar().setTitle("Update Data");
        nama = findViewById(R.id.new_nama);
        alamat = findViewById(R.id.new_alamat);
        kelas = findViewById(R.id.new_kelas);
        nohp = findViewById(R.id.new_nohp);
        matpel = findViewById(R.id.spinnersMatpel);
        jeniskelamin = findViewById(R.id.jeniskel);
        tgllahir = findViewById(R.id.tgllahir);
        update = findViewById(R.id.update);
    auth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance().getReference();
    getData();
    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setNama = nama.getText().toString();
            setAlamat = alamat.getText().toString();
            setKelas = kelas.getText().toString();
            setNohp = nohp.getText().toString();
            setMatpel = matpel.getSelectedItem().toString();
            setJeniskel = jeniskelamin.getText().toString();
            setTgllahir = tgllahir.getText().toString();
            setiduser = getiduser;
            datamhs setdata = new datamhs();
            setdata.setArtistId_user(setiduser);
            setdata.setArtistName(setNama);
            setdata.setArtistAlamat(setAlamat);
            setdata.setArtistKelas(setKelas);
            setdata.setArtistNoHP(setNohp);
            setdata.setArtistMatpel(setMatpel);
            setdata.setArtistJeniskel(setJeniskel);
            setdata.setArtistTanggalLahir(setTgllahir);
            update(setdata);
        }
    });
    }
    public void getData(){
        getiduser = getIntent().getExtras().getString("id_user");
        final String getNama = getIntent().getExtras().getString("nama");
        final String getKelas = getIntent().getExtras().getString("kelas");
        final String getAlamat = getIntent().getExtras().getString("alamat");
        final String getNohp = getIntent().getExtras().getString("nohp");
        final String getMatpel = getIntent().getExtras().getString("matpel");
        final String getJeniskelamin = getIntent().getExtras().getString("jeniskel");
        final String getTgllahir = getIntent().getExtras().getString("tgllahir");
        nama.setText(getNama);
        alamat.setText(getAlamat);
        kelas.setText(getKelas);
        nohp.setText(getNohp);
        jeniskelamin.setText(getJeniskelamin);
        tgllahir.setText(getTgllahir);
    }
    public void update(datamhs data){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("key");
        database.child("artists")
                .child(getKey)
                .setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nama.setText("");
                        alamat.setText("");
                        kelas.setText("");
                        nohp.setText("");
                        jeniskelamin.setText("");
                        tgllahir.setText("");
                        Toast.makeText(updatedata.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}


