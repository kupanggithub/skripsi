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
import com.ulfa.bimbelbungabangsa.list_jadwal;
import com.ulfa.bimbelbungabangsa.list_nilai;
import com.ulfa.bimbelbungabangsa.model.datajadwal;
import com.ulfa.bimbelbungabangsa.model.datanilai;
import com.ulfa.bimbelbungabangsa.updateJadwal;
import com.ulfa.bimbelbungabangsa.updateNilai;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdapterNilai extends RecyclerView.Adapter<AdapterNilai.ViewHolder> implements Filterable {
    private ArrayList<datanilai> datanilai;
    private ArrayList<datanilai> fdatanilai;
    private Context context;
    public interface dataListener{
        void onDeleteData(datanilai datanil, int position);
    }
    AdapterNilai.dataListener listener;
    public AdapterNilai(ArrayList<com.ulfa.bimbelbungabangsa.model.datanilai> datanilai, Context context) {
        this.datanilai = datanilai;
        this.context = context;
        listener = (list_nilai)context;
        fdatanilai = datanilai;

    }

    @Override
    public AdapterNilai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_nilai,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNilai.ViewHolder holder, final int position) {
    String id = fdatanilai.get(position).getArtostId_user();
    String matpel = fdatanilai.get(position).getArtostMatpel();
    String nilai = fdatanilai.get(position).getArtostNilai();
    String keterangan = fdatanilai.get(position).getArtostKeterangan();

    holder.id.setText("Id User : " + id);
    holder.matpel.setText("Mata Pelajaran : " + matpel);
    holder.nilai.setText("Nilai : " + nilai);
    holder.keterangan.setText("Keterangan : " + keterangan);
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
                            bundle.putString("id", fdatanilai.get(position).getArtostId_user());
                            bundle.putString("matpel", fdatanilai.get(position).getArtostMatpel());
                            bundle.putString("nilai", fdatanilai.get(position).getArtostNilai());
                            bundle.putString("keterangan", fdatanilai.get(position).getArtostKeterangan());
                            bundle.putString("key",fdatanilai.get(position).getArtostId());
                            Intent intent = new Intent(view.getContext(), updateNilai.class);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            break;
                        case 1:
                            //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                            listener.onDeleteData(fdatanilai.get(position), position);
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
        return fdatanilai.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    fdatanilai = datanilai;
                } else {

                    ArrayList<datanilai> filteredList = new ArrayList<>();

                    for (datanilai dataitem : datanilai) {

                        if ( dataitem.getArtostId_user().toLowerCase().contains(charString) )   {

                            filteredList.add(dataitem);
                        }
                    }

                    fdatanilai = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = fdatanilai;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                fdatanilai = (ArrayList<datanilai>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,matpel,nilai,keterangan;
        LinearLayout list;
        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.card_id_user);
            matpel = itemView.findViewById(R.id.card_matpel);
            nilai = itemView.findViewById(R.id.card_nilai);
            keterangan = itemView.findViewById(R.id.card_keterangan);
            list = itemView.findViewById(R.id.list_nilai);
        }
    }
}
