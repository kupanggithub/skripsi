package com.ulfa.bimbelbungabangsa;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulfa.bimbelbungabangsa.adapter.AdapterJadwal;
import com.ulfa.bimbelbungabangsa.adapter.AdapterNilai;
import com.ulfa.bimbelbungabangsa.model.datajadwal;
import com.ulfa.bimbelbungabangsa.model.datanilai;

import java.util.ArrayList;

public class list_nilai extends AppCompatActivity implements AdapterNilai.dataListener{
DatabaseReference database;
FirebaseAuth auth;
RecyclerView rv;
ArrayList<datanilai> data;
AdapterNilai


        adapter;
RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nilai);
        rv = findViewById(R.id.list_nilai);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        rv.addItemDecoration(itemDecoration);
        database = FirebaseDatabase.getInstance().getReference();
        database.child("nilais").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                for(DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    datanilai datanil = noteDataSnapshot.getValue(datanilai.class);
                    datanil.setArtostId(noteDataSnapshot.getKey());
                    data.add(datanil);
                }
                adapter = new AdapterNilai(data, list_nilai.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDeleteData(datanilai datanil, int position) {
        if(database != null){
            database.child("nilais")
                    .child(datanil.getArtostId())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(list_nilai.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
