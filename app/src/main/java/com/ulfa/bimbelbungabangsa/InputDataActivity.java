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
import com.ulfa.bimbelbungabangsa.model.Artist;

public class InputDataActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextKelas;
    EditText editTextAlamat;
    EditText editTextNoHP,jeniskel,tgl_lahir,etID;
    Button buttonSimpan,tampil;
    Spinner spinnerMatpel;
    String id,name,kelas,nohp,alamat,matpel,jnskel,tgllahir,setID;
    DatabaseReference databaseArtists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");
        etID = findViewById(R.id.etID);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextKelas = (EditText) findViewById(R.id.editTextKelas);
        editTextNoHP = (EditText) findViewById(R.id.editTextNoHP);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        buttonSimpan = (Button) findViewById(R.id.buttonSimpan);
        jeniskel = (EditText) findViewById(R.id.jeniskel1);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        spinnerMatpel = (Spinner) findViewById(R.id.spinnersMatpel);
        tampil = (Button) findViewById(R.id.tampil);
        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputDataActivity.this,list_data.class);
                startActivity(intent);
            }
        });
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });
            }

            private void addArtist(){
            name = editTextName.getText().toString().trim();
             kelas = editTextKelas.getText().toString().trim();
             nohp = editTextNoHP.getText().toString().trim();
            alamat = editTextAlamat.getText().toString().trim();
             matpel = spinnerMatpel.getSelectedItem().toString();
             jnskel = jeniskel.getText().toString();
             tgllahir = tgl_lahir.getText().toString();
             setID = etID.getText().toString();

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(kelas)&& !TextUtils.isEmpty(nohp)&& !TextUtils.isEmpty(alamat)&& !TextUtils.isEmpty(setID)&& !TextUtils.isEmpty(jnskel)&& !TextUtils.isEmpty(tgllahir)){

                id = databaseArtists.push().getKey();
                Artist artist = new Artist(id,setID,name,kelas,nohp,alamat,matpel,jnskel,tgllahir);

                databaseArtists.child(id).setValue(artist);

                Toast.makeText(this, "Data Ditambahkan", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this,"Data harus di isi semua",Toast.LENGTH_LONG).show();
            }
            }
    }

