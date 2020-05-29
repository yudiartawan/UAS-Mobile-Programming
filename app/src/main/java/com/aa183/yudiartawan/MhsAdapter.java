package com.aa183.yudiartawan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.MhsViewHolder> {

    private Context context;
    private ArrayList<Mhs> dataMhs;

    public MhsAdapter(Context context, ArrayList<Mhs> dataMhs) {
        this.context = context;
        this.dataMhs = dataMhs;
    }

    @NonNull
    @Override
    public MhsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_mhs, parent, false);
        return new MhsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsViewHolder holder, int position) {
        Mhs tempMhs = dataMhs.get(position);
        holder.idMhs = tempMhs.getIdMhs();
        holder.tvNim.setText(tempMhs.getNim());
        holder.gambar = tempMhs.getGambar();
        holder.noTelp = tempMhs.getNoTelp();
        holder.tvNamaLengkap.setText(tempMhs.getNama());
        holder.Prodi = tempMhs.getProdi();
        holder.StatusTransfer = tempMhs.getStatusTransfer();
        holder.StatusInvestasi = tempMhs.getStatusInvestasi();
        holder.Kampus = tempMhs.getKampus();
        holder.Konsentrasi = tempMhs.getKonsentrasi();
        holder.StatusKelas = tempMhs.getStatusKelas();

        try {
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgGambar.setImageBitmap(bitmap);
            holder.imgGambar.setContentDescription(holder.gambar);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "GAGAL Mengambil Gambar dari Media Penyimpanan", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return dataMhs.size();
    }

    public class MhsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imgGambar;
        private TextView tvNamaLengkap, tvNim;
        private int idMhs;
        private String gambar, noTelp, Prodi, StatusTransfer, StatusInvestasi, Kampus, Konsentrasi, StatusKelas;

        public MhsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgGambar = itemView.findViewById(R.id.iv_gambar);
            tvNamaLengkap = itemView.findViewById(R.id.tv_nama_lengkap);
            tvNim = itemView.findViewById(R.id.tv_nim);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent bukaMhs = new Intent(context, TampilActivity.class);
            bukaMhs.putExtra("ID", idMhs);
            bukaMhs.putExtra("NIM", tvNim.getText());
            bukaMhs.putExtra("GAMBAR", gambar);
            bukaMhs.putExtra("NO_TELP", noTelp);
            bukaMhs.putExtra("NAMA", tvNamaLengkap.getText());
            bukaMhs.putExtra("PRODI", Prodi);
            bukaMhs.putExtra("STATUS_TRANSFER", StatusTransfer);
            bukaMhs.putExtra("STATUS_INVESTASI", StatusInvestasi);
            bukaMhs.putExtra("KAMPUS", Kampus);
            bukaMhs.putExtra("KONSENTRASI", Konsentrasi);
            bukaMhs.putExtra("STATUS_KELAS", StatusKelas);
            context.startActivity(bukaMhs);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI","update");
            bukaInput.putExtra("ID", idMhs);
            bukaInput.putExtra("NIM", tvNim.getText());
            bukaInput.putExtra("GAMBAR", gambar);
            bukaInput.putExtra("NO_TELP", noTelp);
            bukaInput.putExtra("NAMA", tvNamaLengkap.getText());
            bukaInput.putExtra("PRODI", Prodi);
            bukaInput.putExtra("STATUS_TRANSFER", StatusTransfer);
            bukaInput.putExtra("STATUS_INVESTASI", StatusInvestasi);
            bukaInput.putExtra("KAMPUS", Kampus);
            bukaInput.putExtra("KONSENTRASI", Konsentrasi);
            bukaInput.putExtra("STATUS_KELAS", StatusKelas);
            context.startActivity(bukaInput);

            return true;
        }


    }
}
