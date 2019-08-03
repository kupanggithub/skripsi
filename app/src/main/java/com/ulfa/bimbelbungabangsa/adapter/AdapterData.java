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
import com.ulfa.bimbelbungabangsa.list_data;
import com.ulfa.bimbelbungabangsa.model.datamhs;
import com.ulfa.bimbelbungabangsa.updatedata;

import java.util.ArrayList;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolder> implements Filterable{
   private ArrayList<datamhs> data;
    private ArrayList<datamhs> fdata;
    private Context context;



    public interface dataListener{
        void onDeleteData(datamhs datahs, int position);
    }
    dataListener listener;
    public AdapterData(ArrayList<datamhs> data, Context ctx) {
        this.data = data;
        this.context = ctx;
        listener = (list_data)context;
        fdata = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(com.ulfa.bimbelbungabangsa.R.layout.card_holder, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
    String nama = fdata.get(position).getArtistName();
    String id_user = fdata.get(position).getArtistId_user();
        String alamat = fdata.get(position).getArtistAlamat();
        String nohp = fdata.get(position).getArtistNoHP();
        String matpel = fdata.get(position).getArtistMatpel();
        String kelas = fdata.get(position).getArtistKelas();
        String JenisKel = fdata.get(position).getArtistJeniskel();
        String tgllahir = fdata.get(position).getArtistTanggalLahir();

        holder.id_user.setText("ID : " + id_user);
        holder.nama.setText("Nama : " + nama);
        holder.alamat.setText("Alamat : " + alamat);
        holder.nohp.setText("No HP : " + nohp);
        holder.matpel.setText("Mata Pelajaran : " + matpel);
        holder.kelas.setText("Kelas : " + kelas);
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
                                bundle.putString("id_user", fdata.get(position).getArtistId_user());
                                bundle.putString("nama", fdata.get(position).getArtistName());
                                bundle.putString("alamat", fdata.get(position).getArtistAlamat());
                                bundle.putString("nohp", fdata.get(position).getArtistNoHP());
                                bundle.putString("matpel", fdata.get(position).getArtistMatpel());
                                bundle.putString("kelas", fdata.get(position).getArtistKelas());
                                bundle.putString("tgllahir",fdata.get(position).getArtistTanggalLahir());
                                bundle.putString("jeniskel",fdata.get(position).getArtistJeniskel());
                                bundle.putString("key", fdata.get(position).getArtistId());
                                Intent intent = new Intent(view.getContext(), updatedata.class);
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

                    ArrayList<datamhs> filteredList = new ArrayList<>();

                    for (datamhs dataitem : data) {

                        if ( dataitem.getArtistName().toLowerCase().contains(charString) )   {

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
                fdata = (ArrayList<datamhs>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return fdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,alamat,nohp,matpel,kelas,jeniskell,tgllahirr,id_user;
        LinearLayout list;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            nohp = itemView.findViewById(R.id.nohp);
            matpel = itemView.findViewById(R.id.matpel);
            kelas = itemView.findViewById(R.id.kelas);
            list = itemView.findViewById(R.id.list_item);
            jeniskell = itemView.findViewById(R.id.jeniskel);
            tgllahirr = itemView.findViewById(R.id.tgllahir);
            id_user = itemView.findViewById(R.id.id_user);
        }
    }
}
