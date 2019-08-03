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

public class updateNilai extends AppCompatActivity {
EditText id_user,nilai,keterangan;
Spinner matpel;
DatabaseReference database;
FirebaseAuth auth;
Button update;
String setId,setNilai,setKeterangan,setMatpel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nilai);
        id_user = findViewById(R.id.new_id);
        nilai = findViewById(R.id.new_Nilai);
        keterangan = findViewById(R.id.new_keterangan);
        matpel = findViewById(R.id.new_spinnersMatpel);
        update = findViewById(R.id.update_nilai);
        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setId = id_user.getText().toString();
                setNilai = nilai.getText().toString();
                setKeterangan = keterangan.getText().toString();
                setMatpel = matpel.getSelectedItem().toString();

                datanilai datanil = new datanilai();
                datanil.setArtostId_user(setId);
                datanil.setArtostNilai(setNilai);
                datanil.setArtostKeterangan(setKeterangan);
                datanil.setArtostMatpel(setMatpel);
                update(datanil);

            }
        });

    }
    private void getData(){
        final String get_id_user = getIntent().getExtras().getString("id");
        final String get_nilai = getIntent().getExtras().getString("nilai");
        final String get_keterangan = getIntent().getExtras().getString("keterangan");

        id_user.setText(get_id_user);
        nilai.setText(get_nilai);
        keterangan.setText(get_keterangan);
    }
    private void update(datanilai data){
        String getKey = getIntent().getExtras().getString("key");
        database.child("nilais")
                .child(getKey)
                .setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        id_user.setText("");
                        nilai.setText("");
                        keterangan.setText("");
                        Toast.makeText(updateNilai.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }

}
