package com.ulfa.bimbelbungabangsa.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulfa.bimbelbungabangsa.R;
import com.ulfa.bimbelbungabangsa.list_data;
import com.ulfa.bimbelbungabangsa.list_jadwal;
import com.ulfa.bimbelbungabangsa.model.datajadwal;
import com.ulfa.bimbelbungabangsa.model.datamhs;
import com.ulfa.bimbelbungabangsa.model.datanilai;
import com.ulfa.bimbelbungabangsa.updateJadwal;
import com.ulfa.bimbelbungabangsa.updatedata;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.ViewHolder> implements Filterable {
    private ArrayList<datajadwal> datajadwals;
    private ArrayList<datajadwal> fdatajadwals;
    private Context context;

    public interface dataListener{
        void onDeleteData(datajadwal datajdwl, int position);
    }
    dataListener listener;
    public AdapterJadwal(ArrayList<datajadwal> datajadwals, Context context) {
        this.datajadwals = datajadwals;
        this.context = context;
        listener = (list_jadwal)context;
        fdatajadwals = datajadwals;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jadwal, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
    String hari = fdatajadwals.get(position).getHari();
    String nama_guru = fdatajadwals.get(position).getNama_guru();
    String kelas = fdatajadwals.get(position).getKelass();
    String matpel = fdatajadwals.get(position).getMatpell();
    String jam_mulai = fdatajadwals.get(position).getJam_mulai();
    String jam_selesai = fdatajadwals.get(position).getJam_selesai();

    holder.harii.setText("Hari : " + hari);
    holder.nm_guru.setText("Nama Guru : " + nama_guru);
    holder.kelass.setText("Kelas : " + kelas);
    holder.matpell.setText("Mata Pelajaran : " + matpel);
    holder.jam_mulaii.setText("Jam Mulai : " + jam_mulai);
    holder.jam_selesaii.setText("Jam Selesai : " + jam_selesai);

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
                            bundle.putString("hari", fdatajadwals.get(position).getHari());
                            bundle.putString("nm_guru", fdatajadwals.get(position).getNama_guru());
                            bundle.putString("kelas", fdatajadwals.get(position).getKelass());
                            bundle.putString("matpel", fdatajadwals.get(position).getMatpell());
                            bundle.putString("jam_mulai", fdatajadwals.get(position).getJam_mulai());
                            bundle.putString("jam_selesai", fdatajadwals.get(position).getJam_selesai());
                            bundle.putString("key",fdatajadwals.get(position).getId_jadwal());
                            Intent intent = new Intent(view.getContext(), updateJadwal.class);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            break;
                        case 1:
                            //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                            listener.onDeleteData(fdatajadwals.get(position), position);
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
    public int getItemCount() {
        return fdatajadwals.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    fdatajadwals = datajadwals;
                } else {

                    ArrayList<datajadwal> filteredList = new ArrayList<>();

                    for (datajadwal dataitem : datajadwals) {

                        if ( dataitem.getNama_guru().toLowerCase().contains(charString) )   {

                            filteredList.add(dataitem);
                        }
                    }

                    fdatajadwals = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = fdatajadwals;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                fdatajadwals = (ArrayList<datajadwal>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView harii,nm_guru,kelass,matpell,jam_mulaii,jam_selesaii;
        LinearLayout list;
        public ViewHolder(View itemView) {
            super(itemView);
            harii = itemView.findViewById(R.id.harii);
            nm_guru = itemView.findViewById(R.id.nm_guruu);
            kelass = itemView.findViewById(R.id.kelass);
            matpell = itemView.findViewById(R.id.matpell);
            jam_mulaii = itemView.findViewById(R.id.jam_mulaii);
            jam_selesaii = itemView.findViewById(R.id.jam_selesaii);
            list = itemView.findViewById(R.id.list_jadwal);

        }
    }
}
