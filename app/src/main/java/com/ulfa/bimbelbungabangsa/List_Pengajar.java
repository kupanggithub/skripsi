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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulfa.bimbelbungabangsa.adapter.AdapterPengajar;
import com.ulfa.bimbelbungabangsa.model.datangajar;

import java.util.ArrayList;

public class List_Pengajar extends AppCompatActivity implements AdapterPengajar.dataListener{
    private DatabaseReference database;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<datangajar> data;
    private AdapterPengajar adapter;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__pengajar);

        rv = findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        rv.addItemDecoration(itemDecoration);
        database = FirebaseDatabase.getInstance().getReference();
        database.child("pengajar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                for(DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    datangajar datahs = noteDataSnapshot.getValue(com.ulfa.bimbelbungabangsa.model.datangajar.class);
                    datahs.setPengajarId(noteDataSnapshot.getKey());
                    data.add(datahs);
                }
                adapter = new AdapterPengajar(data, List_Pengajar.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onDeleteData(datangajar datahs, int position) {
        //String userID = auth.getUid();
        if(database != null){
            database.child("pengajar")
                    .child(datahs.getPengajarId())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(List_Pengajar.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
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
