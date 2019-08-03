package com.ulfa.bimbelbungabangsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulfa.bimbelbungabangsa.model.jadwal;

public class JadwalMengajarActivity extends AppCompatActivity {
EditText hari,nmguru,kelas,jam_mulai,jam_selesai;
Spinner matpel;
Button simpan,tampil;
String setHari,setNmguru,setKelas,setJamMulai,setJamSelesai,setMatpel,id;
DatabaseReference databasejadwal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_mengajar);
        databasejadwal = FirebaseDatabase.getInstance().getReference("jadwal");
        hari = findViewById(R.id.hari);
        nmguru = findViewById(R.id.nama_guru);
        kelas = findViewById(R.id.kelass);
        jam_mulai = findViewById(R.id.jam_mulai);
        jam_selesai = findViewById(R.id.jam_selesai);
        simpan = findViewById(R.id.Simpan);
        matpel = findViewById(R.id.Matpel);
        tampil = findViewById(R.id.Tampil_Data);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan();
            }
        });
        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JadwalMengajarActivity.this,list_jadwal.class);
                startActivity(intent);
            }
        });
    }
    private void simpan(){
        setHari = hari.getText().toString();
        setNmguru = nmguru.getText().toString();
        setKelas = kelas.getText().toString();
        setJamMulai = jam_mulai.getText().toString();
        setJamSelesai = jam_selesai.getText().toString();
        setMatpel = matpel.getSelectedItem().toString();
        if(!TextUtils.isEmpty(setHari)&& !TextUtils.isEmpty(setNmguru)&& !TextUtils.isEmpty(setKelas)&& !TextUtils.isEmpty(setJamMulai)&& !TextUtils.isEmpty(setJamSelesai)){
            id = databasejadwal.push().getKey();
            jadwal jadwall = new jadwal(id, setHari, setNmguru, setMatpel, setKelas, setJamMulai, setJamSelesai);
            databasejadwal.child(id).setValue(jadwall);
            Toast.makeText(this, "data berhasil di simpan", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data Harus Di Isi Semua", Toast.LENGTH_SHORT).show();
        }
    }
}
