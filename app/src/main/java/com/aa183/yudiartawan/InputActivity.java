
package com.aa183.yudiartawan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editnim, editnama, editnoTelp;
    private Spinner spstatuskelas, editprodi, editStatusTransfer, editstatusInvestasi, editkampus, editkonsentrasi;
    private ImageView ivgambar;
    private DatabaseHandler dbHandler;
    private boolean updateData = false;
    private int idMhs = 0;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //prodi
        Spinner coloredSpinner = findViewById(R.id.sp_prodi);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.prodi,
                R.layout.color_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinner.setAdapter(adapter);

        //transfer
        Spinner coloredSpinnertransfer = findViewById(R.id.sp_status_transfer);
        ArrayAdapter adaptertransfer = ArrayAdapter.createFromResource(
                this,
                R.array.starus_transfer,
                R.layout.color_spinner_layout
        );
        adaptertransfer.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinnertransfer.setAdapter(adaptertransfer);

        //invest
        Spinner coloredSpinnerinvest = findViewById(R.id.sp_status_investasi);
        ArrayAdapter adapterinvest2 = ArrayAdapter.createFromResource(
                this,
                R.array.status_investasi,
                R.layout.color_spinner_layout
        );
        adapterinvest2.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinnerinvest.setAdapter(adapterinvest2);

        //kampus
        Spinner coloredSpinnerkamps = findViewById(R.id.sp_kampus);
        ArrayAdapter adapterkamps = ArrayAdapter.createFromResource(
                this,
                R.array.kampus,
                R.layout.color_spinner_layout
        );
        adapterkamps.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinnerkamps.setAdapter(adapterkamps);

        //konsentrasi
        Spinner coloredSpinnerkons = findViewById(R.id.sp_konsentrasi);
        ArrayAdapter adapterkons = ArrayAdapter.createFromResource(
                this,
                R.array.konsentrasi,
                R.layout.color_spinner_layout
        );
        adapterkons.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinnerkons.setAdapter(adapterkons);

        //kelas
        Spinner coloredSpinnerkelas = findViewById(R.id.sp_status_kelas);
        ArrayAdapter adapterkelass = ArrayAdapter.createFromResource(
                this,
                R.array.status_kelas,
                R.layout.color_spinner_layout
        );
        adapterkelass.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        coloredSpinnerkelas.setAdapter(adapterkelass);



        editnim = findViewById(R.id.edit_nim);
        ivgambar = findViewById(R.id.iv_gambar);
        editnoTelp = findViewById(R.id.edit_telp);
        editnama = findViewById(R.id.edit_nama);
        editprodi = findViewById(R.id.sp_prodi);
        editStatusTransfer = findViewById(R.id.sp_status_transfer);
        editstatusInvestasi = findViewById(R.id.sp_status_investasi);
        editkampus = findViewById(R.id.sp_kampus);
        editkonsentrasi = findViewById(R.id.sp_konsentrasi);
        spstatuskelas = findViewById(R.id.sp_status_kelas);
        btnSimpan = findViewById(R.id.btn_simpan);

        dbHandler = new DatabaseHandler(this);

        Intent terimaIntent = getIntent();
        Bundle data = terimaIntent.getExtras();
        if(data.getString("OPERASI").equals("insert")){
            updateData = false;
        }else{
            updateData = true;
            idMhs = data.getInt("ID");
            editnim.setText(data.getString("NIM"));
            loadFromInternalStorage(data.getString("GAMBAR"));
            editnoTelp.setText(data.getString("NO_TELP"));
            editnama.setText(data.getString("NAMA"));

            //prodi
            String myString = data.getString("PRODI");
            ArrayAdapter myAdap = (ArrayAdapter) editprodi.getAdapter(); //cast to an ArrayAdapter
            int spinnerPosition = myAdap.getPosition(myString);
            editprodi.setSelection(spinnerPosition);

            //status transfer
            String StringTransfer = data.getString("STATUS_TRANSFER");
            ArrayAdapter adapterstatustransfer = (ArrayAdapter) editStatusTransfer.getAdapter(); //cast to an ArrayAdapter
            int spinnerPositionstatuetransfer = adapterstatustransfer.getPosition(StringTransfer);
            editStatusTransfer.setSelection(spinnerPositionstatuetransfer);

            //status investasi
            //editstatusInvestasi.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) data.getStringArrayList("STATUS_INVESTASI"));
            String Stringinvest = data.getString("STATUS_INVESTASI");
            ArrayAdapter adapterinvest = (ArrayAdapter) editstatusInvestasi.getAdapter(); //cast to an ArrayAdapter
            int spinnerPositionstatueinvest = adapterinvest.getPosition(Stringinvest);
            editstatusInvestasi.setSelection(spinnerPositionstatueinvest);

            //editkampus.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) data.getStringArrayList("KAMPUS"));
            String Stringkampus = data.getString("KAMPUS");
            ArrayAdapter adapterkampus = (ArrayAdapter) editkampus.getAdapter(); //cast to an ArrayAdapter
            int spinnerPositionkampus = adapterkampus.getPosition(Stringkampus);
            editkampus.setSelection(spinnerPositionkampus);

            //editkonsentrasi.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) data.getStringArrayList("KONSENTRASI"));
            String Stringkonsentrasi = data.getString("KONSENTRASI");
            ArrayAdapter adapterkonsentrasi = (ArrayAdapter) editkonsentrasi.getAdapter(); //cast to an ArrayAdapter
            int spinnerkonsentrasi = adapterkonsentrasi.getPosition(Stringkonsentrasi);
            editkonsentrasi.setSelection(spinnerkonsentrasi);

            //spstatuskelas.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) data.getStringArrayList("STATUS_KELAS"));
            String Stringsastuskelas = data.getString("STATUS_KELAS");
            ArrayAdapter adapterkelas = (ArrayAdapter) spstatuskelas.getAdapter(); //cast to an ArrayAdapter
            int spinnerPositionstatuskelas = adapterkelas.getPosition(Stringsastuskelas);
            spstatuskelas.setSelection(spinnerPositionstatuskelas);

        }

        ivgambar.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
    }

    private void pickImage(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(3,4)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                try {
                    Uri imageUri = result.getUri();
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //
                    String location = saveImageToInternalStorage(selectedImage, getApplication());
                    loadFromInternalStorage(location);
                }catch (FileNotFoundException er){
                    er.printStackTrace();
                    Toast.makeText(this,"Ada Kesalahan dalam pengambilan file gambar", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this,"Belum Ada Gambar yang dipilih", Toast.LENGTH_SHORT).show();
        }
    }

    public static String saveImageToInternalStorage(Bitmap bitmap, Context ctx){
        ContextWrapper ctxWrapper = new ContextWrapper(ctx);
        File file = ctxWrapper.getDir("images", MODE_PRIVATE);
        String uniqueID = UUID.randomUUID().toString();
        file = new File(file, "Mhs-"+ uniqueID + ".jpg");
        try {
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        }catch (IOException er){
            er.printStackTrace();
        }

        Uri savedImage = Uri.parse(file.getAbsolutePath());
        return  savedImage.toString();
    }

    private void loadFromInternalStorage(String imageLocation){
        try {
            File file = new File(imageLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            ivgambar.setImageBitmap(bitmap);
            ivgambar.setContentDescription(imageLocation);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "GAGAL Mengambil Gambar dari Media Penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.item_menu_hapus);

        if(updateData==true){
            item.setEnabled(true);
            item.getIcon().setAlpha(255);
        }else{
            item.setEnabled(false);
            item.getIcon().setAlpha(130);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.item_menu_hapus){
            hapusData();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void simpanData(){
        String nim, gambar, noTelp, nama, prodi, statusTransfer, statusInvestasi, kampus, konsentrasi, statusKelas;

        nim = editnim.getText().toString();
        gambar = ivgambar.getContentDescription().toString();
        noTelp = editnoTelp.getText().toString();
        nama = editnama.getText().toString();
        prodi = editprodi.getSelectedItem().toString();
        statusTransfer = editStatusTransfer.getSelectedItem().toString();
        statusInvestasi = editstatusInvestasi.getSelectedItem().toString();
        kampus = editkampus.getSelectedItem().toString();
        konsentrasi = editkonsentrasi.getSelectedItem().toString();
        statusKelas = spstatuskelas.getSelectedItem().toString();

        Mhs tempMhs = new Mhs(
                idMhs, nim, gambar, noTelp, nama, prodi, statusTransfer, statusInvestasi, kampus, konsentrasi, statusKelas
        );

        if(updateData == true){
            dbHandler.editMhs(tempMhs);
            Toast.makeText(this, "Data Mahasiswa Diperbaharui", Toast.LENGTH_SHORT).show();
        }else {
            dbHandler.tambahMhs(tempMhs);
            Toast.makeText(this, "Data Mahasiswa Ditambahkan", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private void hapusData(){
        dbHandler.hapusMhs(idMhs);
        Toast.makeText(this, "Data Mahasiswa Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        int idView = v.getId();

        if (idView == R.id.btn_simpan){
            simpanData();
        }else if(idView == R.id.iv_gambar){
            pickImage();
        }
    }
}
