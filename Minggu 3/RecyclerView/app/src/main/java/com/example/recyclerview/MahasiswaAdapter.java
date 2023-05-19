package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private ArrayList<Mahasiswa> dataList;

    public MahasiswaAdapter(ArrayList<Mahasiswa> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNpm.setText(dataList.get(position).getNpm());
        holder.txtNohp.setText(dataList.get(position).getNohp());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
//
//    public int getItemCount() {
//        return dataList.size();
//    }


    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtNpm, txtNohp;
        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama =(TextView) itemView.findViewById(R.id.txt_nama_mahasiswa);
            txtNpm =(TextView) itemView.findViewById(R.id.txt_npm_mahasiswa);
            txtNohp =(TextView) itemView.findViewById(R.id.txt_nohp_mahasiswa);
        }
    }
}