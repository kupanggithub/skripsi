package com.ulfa.bimbelbungabangsa.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulfa.bimbelbungabangsa.R;
import com.ulfa.bimbelbungabangsa.model.datangajar;
import com.ulfa.bimbelbungabangsa.updatePengajar;

import java.util.ArrayList;

public class AdapterPengajar extends RecyclerView.Adapter<AdapterPengajar.ViewHolder> implements Filterable{
    private ArrayList<datangajar> data;
    private ArrayList<datangajar> fdata;
    private Context context;



    public interface dataListener{
        void onDeleteData(datangajar datahs, int position);
    }
    dataListener listener;
    public AdapterPengajar(ArrayList<datangajar> data, Context ctx) {
        this.data = data;
        this.context = ctx;
        listener = (dataListener) context;
        fdata = data;

    }

    @Override
    public AdapterPengajar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_pengajar, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String nama = fdata.get(position).getPengajarName();
        String id_user = fdata.get(position).getPengajarId_user();
        String alamat = fdata.get(position).getPengajarAlamat();
        String nohp = fdata.get(position).getPengajarNoHP();
        String matpel = fdata.get(position).getPengajarMatpel();
        String pendidikan = fdata.get(position).getPengajarPendidikan();
        String JenisKel = fdata.get(position).getPengajarJeniskel();
        String tgllahir = fdata.get(position).getPengajarTanggalLahir();

        holder.id_user.setText("ID : " + id_user);
        holder.nama.setText("Nama : " + nama);
        holder.alamat.setText("Alamat : " + alamat);
        holder.nohp.setText("No HP : " + nohp);
        holder.matpel.setText("Mata Pelajaran : " + matpel);
        holder.pendidikan.setText("Pendidikan Terakhir : " + pendidikan);
        holder.jeniskell.setText("Jenis Kelamin : " + JenisKel);
        holder.tgllahirr.setText("Tanggal Lahir : " + tgllahir);

        holder.list.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                Bundle bundle = new Bundle();
                                bundle.putString("id_user", fdata.get(position).getPengajarId_user());
                                bundle.putString("nama", fdata.get(position).getPengajarName());
                                bundle.putString("alamat", fdata.get(position).getPengajarAlamat());
                                bundle.putString("nohp", fdata.get(position).getPengajarNoHP());
                                bundle.putString("matpel", fdata.get(position).getPengajarMatpel());
                                bundle.putString("pendidikan terakhir", fdata.get(position).getPengajarPendidikan());
                                bundle.putString("tgllahir",fdata.get(position).getPengajarTanggalLahir());
                                bundle.putString("jeniskel",fdata.get(position).getPengajarJeniskel());
                                bundle.putString("key", fdata.get(position).getPengajarId());
                                Intent intent = new Intent(view.getContext(), updatePengajar.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                                listener.onDeleteData(fdata.get(position), position);
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    fdata = data;
                } else {

                    ArrayList<datangajar> filteredList = new ArrayList<>();

                    for (datangajar dataitem : data) {

                        if ( dataitem.getPengajarName().toLowerCase().contains(charString) )   {

                            filteredList.add(dataitem);
                        }
                    }

                    fdata = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = fdata;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                fdata = (ArrayList<datangajar>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return fdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,alamat,nohp,matpel,pendidikan,jeniskell,tgllahirr,id_user;
        LinearLayout list;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            nohp = itemView.findViewById(R.id.nohp);
            matpel = itemView.findViewById(R.id.matpel);
            pendidikan = itemView.findViewById(R.id.pendidikan);
            list = itemView.findViewById(R.id.list_item);
            jeniskell = itemView.findViewById(R.id.jeniskel);
            tgllahirr = itemView.findViewById(R.id.tgllahir);
            id_user = itemView.findViewById(R.id.id_user);
        }
    }
}
