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
import com.ulfa.bimbelbungabangsa.adapter.AdapterData;
import com.ulfa.bimbelbungabangsa.adapter.AdapterJadwal;
import com.ulfa.bimbelbungabangsa.model.datajadwal;
import com.ulfa.bimbelbungabangsa.model.datamhs;

import java.util.ArrayList;

public class list_jadwal extends AppCompatActivity implements AdapterJadwal.dataListener{
DatabaseReference database;
FirebaseAuth auth;
RecyclerView rv;
ArrayList<datajadwal> data;
AdapterJadwal adapter;
RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jadwal);
        rv = findViewById(R.id.list_jadwal);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        rv.addItemDecoration(itemDecoration);
        database = FirebaseDatabase.getInstance().getReference();

        database.child("jadwal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                for(DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    datajadwal datajdwl = noteDataSnapshot.getValue(datajadwal.class);
                    datajdwl.setId_jadwal(noteDataSnapshot.getKey());
                    data.add(datajdwl);
                }
                adapter = new AdapterJadwal(data, list_jadwal.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDeleteData(datajadwal datajdwl, int position) {
        if(database != null){
            database.child("jadwal")
                    .child(datajdwl.getId_jadwal())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(list_jadwal.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
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
