package com.ulfa.bimbelbungabangsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulfa.bimbelbungabangsa.model.datajadwal;
import com.ulfa.bimbelbungabangsa.model.datanilai;

public class updateJadwal extends AppCompatActivity {
EditText hari,nmguru,kelas,jam_mulai,jam_selesai;
Button update;
Spinner matpel;
private DatabaseReference database;
private FirebaseAuth auth;
String sethari,setnmguru,setkelas,setjam_mulai,setjam_selesai,setmatpel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jadwal);

        hari = findViewById(R.id.new_hari);
        nmguru = findViewById(R.id.new_nmguru);
        kelas = findViewById(R.id.new_kelas);
        jam_mulai = findViewById(R.id.new_jammulai);
        jam_selesai = findViewById(R.id.new_jamselesai);
        update = findViewById(R.id.update_jadwal);
        matpel = findViewById(R.id.news_spinnersMatpel);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sethari = hari.getText().toString();
                setnmguru = nmguru.getText().toString();
                setkelas = kelas.getText().toString();
                setjam_mulai = jam_mulai.getText().toString();
                setjam_selesai = jam_selesai.getText().toString();
                setmatpel = matpel.getSelectedItem().toString();

                datajadwal setjadwal = new datajadwal();
                setjadwal.setHari(sethari);
                setjadwal.setNama_guru(setnmguru);
                setjadwal.setKelass(setkelas);
                setjadwal.setJam_mulai(setjam_mulai);
                setjadwal.setJam_selesai(setjam_selesai);
                setjadwal.setMatpell(setmatpel);
                update(setjadwal);
            }
        });
    }

    private void getData(){
        final String getHari = getIntent().getExtras().getString("hari");
        final String getNmguru = getIntent().getExtras().getString("nm_guru");
        final String getKelas = getIntent().getExtras().getString("kelas");
        final String getMatpel = getIntent().getExtras().getString("matpel");
        final String getJam_mulai = getIntent().getExtras().getString("jam_mulai");
        final String getJam_selesai = getIntent().getExtras().getString("jam_selesai");

        hari.setText(getHari);
        nmguru.setText(getNmguru);
        kelas.setText(getKelas);
        jam_selesai.setText(getJam_selesai);
        jam_mulai.setText(getJam_mulai);

    }

    private void update(datajadwal data){
    String getKey = getIntent().getExtras().getString("key");
        database.child("jadwal")
                .child(getKey)
                .setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        hari.setText("");
                        nmguru.setText("");
                        kelas.setText("");
                        jam_selesai.setText("");
                        jam_mulai.setText("");
                        Toast.makeText(updateJadwal.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }


}
