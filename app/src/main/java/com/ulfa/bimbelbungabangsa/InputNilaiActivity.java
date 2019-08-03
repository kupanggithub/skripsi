package com.ulfa.bimbelbungabangsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulfa.bimbelbungabangsa.model.Artost;

public class
InputNilaiActivity extends AppCompatActivity {

    EditText etId;
    EditText etNilaiMatpel;
    EditText etNilaiNilai;
    EditText etNilaiKeterangan;
    Button btnNilaiSimpan,tampil_nilai;

    DatabaseReference databaseNilais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nilai);

        databaseNilais = FirebaseDatabase.getInstance().getReference("nilais");

        etId = (EditText) findViewById(R.id.etId);
        etNilaiMatpel = (EditText) findViewById(R.id.etNilaiMatpel);
        etNilaiNilai = (EditText) findViewById(R.id.etNilaiNilai);
        etNilaiKeterangan = (EditText) findViewById(R.id.etNilaiKeterangan);
        btnNilaiSimpan = (Button) findViewById(R.id.btnNilaiSimpan);
        tampil_nilai = findViewById(R.id.tampil_nilai);

        btnNilaiSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtost();
            }
        });

        tampil_nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputNilaiActivity.this,list_nilai.class);
                startActivity(intent);
            }
        });
    }

    private void addArtost(){
        String id_user = etId.getText().toString().trim();
        String matpel = etNilaiMatpel.getText().toString().trim();
        String nilai = etNilaiNilai.getText().toString().trim();
        String keterangan = etNilaiKeterangan.getText().toString().trim();

        if (!TextUtils.isEmpty(id_user)&& !TextUtils.isEmpty(matpel)&& !TextUtils.isEmpty(nilai)&& !TextUtils.isEmpty(keterangan)){

            String id = databaseNilais.push().getKey();
            Artost artost = new Artost(id,id_user,matpel,nilai,keterangan);

            databaseNilais.child(id).setValue(artost);

            Toast.makeText(this, "Nilai Added", Toast.LENGTH_LONG).show();
            startActivity(new Intent(InputNilaiActivity.this, InputNilaiActivity.class));
        }else{
            Toast.makeText(this,"Data Harus Di Isi Semua",Toast.LENGTH_LONG).show();
        }
    }


}
