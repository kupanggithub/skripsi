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
import com.ulfa.bimbelbungabangsa.model.Pengajar;

public class materiMengajar extends AppCompatActivity {
    EditText etx_tgl_lhir, etx_id;
    EditText etx_Nama, etx_alamat, etx_NoHp, etx_jk, etx_pendidikan;
    Button simpan1, tampil1;
    Spinner spinerMatpel;
    String id, namaGuru,nohpGuru,alamat_Guru,matpel_Guru,jnskelGuru,tgllahir_Guru,setID_Guru,pendidikan1;
    DatabaseReference databasePengajar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_mengajar);

        databasePengajar = FirebaseDatabase.getInstance().getReference("pengajar");
            etx_Nama = (EditText) findViewById(R.id.editTextName_guru);
            etx_alamat = (EditText) findViewById(R.id.editTextAlamat_guru);
            etx_NoHp = (EditText) findViewById(R.id.editTextNoHP_guru);
            etx_jk = (EditText) findViewById(R.id.jeniskel_guru);
            etx_tgl_lhir = (EditText) findViewById(R.id.tgl_lahir_guru);
            etx_id = (EditText) findViewById(R.id.etID_guru);
            etx_pendidikan = (EditText) findViewById(R.id.pendidikan_terakhir);
            simpan1 = (Button) findViewById(R.id.buttonSimpanGuru);
            tampil1 = (Button) findViewById(R.id.tampilGuru);

            spinerMatpel = (Spinner) findViewById(R.id.spinnersMatpel_guru);

            tampil1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(materiMengajar.this,List_Pengajar.class);
                    startActivity(intent);
                }
            });
           simpan1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   addPengajar();
               }
           });
            }

        private void addPengajar(){

            namaGuru = etx_Nama.getText().toString().trim();
            pendidikan1 = etx_pendidikan.getText().toString().trim();
            nohpGuru = etx_NoHp.getText().toString().trim();
            alamat_Guru = etx_alamat.getText().toString().trim();
            matpel_Guru = spinerMatpel.getSelectedItem().toString();
            jnskelGuru = etx_jk.getText().toString();
            tgllahir_Guru = etx_tgl_lhir.getText().toString();
            setID_Guru = etx_id.getText().toString();

        if(!TextUtils.isEmpty(namaGuru) && !TextUtils.isEmpty(pendidikan1)&& !TextUtils.isEmpty(nohpGuru)&& !TextUtils.isEmpty(alamat_Guru)&& !TextUtils.isEmpty(setID_Guru)&& !TextUtils.isEmpty(jnskelGuru)&& !TextUtils.isEmpty(tgllahir_Guru)){

            id = databasePengajar.push().getKey();
            Pengajar pengajar = new Pengajar(id,setID_Guru,namaGuru,pendidikan1,nohpGuru,alamat_Guru,matpel_Guru,jnskelGuru,tgllahir_Guru);

            databasePengajar.child(id).setValue(pengajar);

            Toast.makeText(this, "Data Ditambahkan", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Data harus di isi semua",Toast.LENGTH_LONG).show();
        }
    }
}
