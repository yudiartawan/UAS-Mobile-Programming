package com.aa183.yudiartawan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgGambar;
    private TextView tvNamaLengkap, tvNim, tvNoTelp, tvProdi, tvStatusTransfer, tvStatusInvestasi, tvKampus, tvKonsentrasi, tvStatusKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);
        tvNamaLengkap = findViewById(R.id.tv_nama_lengkap);
        imgGambar = findViewById(R.id.iv_gambar);
        tvNoTelp = findViewById(R.id.tv_no_telp);
        tvNim = findViewById(R.id.tv_nim);
        tvProdi = findViewById(R.id.tv_prodi);
        tvStatusTransfer = findViewById(R.id.tv_status_transfer);
        tvStatusInvestasi = findViewById(R.id.tv_status_investasi);
        tvKampus = findViewById(R.id.tv_kampus);
        tvKonsentrasi = findViewById(R.id.tv_konsentrasi);
        tvStatusKelas = findViewById(R.id.tv_status_kelas);

        Intent terimaData = getIntent();
        tvNamaLengkap.setText(terimaData.getStringExtra("NAMA"));
        tvNim.setText(terimaData.getStringExtra("NIM"));
        tvNoTelp.setText(terimaData.getStringExtra("NO_TELP"));
        tvProdi.setText(terimaData.getStringExtra("PRODI"));
        tvStatusTransfer.setText(terimaData.getStringExtra("STATUS_TRANSFER"));
        tvStatusInvestasi.setText(terimaData.getStringExtra("STATUS_INVESTASI"));
        tvKampus.setText(terimaData.getStringExtra("KAMPUS"));
        tvKonsentrasi.setText(terimaData.getStringExtra("KONSENTRASI"));
        tvStatusKelas.setText(terimaData.getStringExtra("STATUS_KELAS"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");
        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgGambar.setImageBitmap(bitmap);
            imgGambar.setContentDescription(imgLocation);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "GAGAL Mengambil Gambar dari Media Penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_bagikan) {
            String toDial = "tel:" + tvNoTelp.getText().toString();
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
        }if (item.getItemId() == R.id.item_pesan) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + tvNoTelp.getText().toString()));
            smsIntent.putExtra("sms_body", "Hello "+ tvNamaLengkap.getText().toString());
            startActivity(smsIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
