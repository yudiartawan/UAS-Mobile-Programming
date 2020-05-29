package com.aa183.yudiartawan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_PATH = "com.aa183.yudiartawan/";
    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_kampus";
    private final static String TABLE_MHS = "t_mhs";
    private final static String KEY_ID_MHS = "ID_Mhs";
    private final static String KEY_NIM = "Nim";
    private final static String KEY_NAMA = "Nama";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_NOTELP = "No_Telp";
    private final static String KEY_PRODI = "Prodi";
    private final static String KEY_STATUS_TRANSFER = "Status_Transfer";
    private final static String KEY_STATUS_INVESTASI = "Status_Investasi";
    private final static String KEY_KAMPUS = "Kampus";
    private final static String KEY_STATUS_KELAS = "Status_Kelas";
    private final static String KEY_KONSENTRASI = "Konsentrasi";
    private Context context;



    public DatabaseHandler(Context ctx){

        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MHS = "CREATE TABLE " + TABLE_MHS
                + "( " + KEY_ID_MHS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NIM + " TEXT UNIQUE, " + KEY_GAMBAR + " TEXT, "
                + KEY_NOTELP + " TEXT, "
                + KEY_NAMA + " TEXT, " + KEY_PRODI + " TEXT, "
                + KEY_STATUS_TRANSFER + " TEXT, " + KEY_STATUS_INVESTASI + " TEXT, "
                + KEY_KAMPUS + " TEXT, " + KEY_KONSENTRASI + " TEXT, "
                + KEY_STATUS_KELAS + " TEXT);";

        db.execSQL(CREATE_TABLE_MHS);
        inisialisasiMhsAwal(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_MHS;
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }



    public void tambahMhs(Mhs dataMhs){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NIM, dataMhs.getNim());
        cv.put(KEY_GAMBAR, dataMhs.getGambar());
        cv.put(KEY_NOTELP, dataMhs.getNoTelp());
        cv.put(KEY_NAMA, dataMhs.getNama());
        cv.put(KEY_PRODI, dataMhs.getProdi());
        cv.put(KEY_STATUS_TRANSFER, dataMhs.getStatusTransfer());
        cv.put(KEY_STATUS_INVESTASI, dataMhs.getStatusInvestasi());
        cv.put(KEY_KAMPUS, dataMhs.getKampus());
        cv.put(KEY_STATUS_KELAS, dataMhs.getStatusKelas());
        cv.put(KEY_KONSENTRASI, dataMhs.getKonsentrasi());

        db.insert(TABLE_MHS, null, cv);
        db.close();
    }


    public void tambahMhs(Mhs dataMhs, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_NIM, dataMhs.getNim());
        cv.put(KEY_GAMBAR, dataMhs.getGambar());
        cv.put(KEY_NOTELP, dataMhs.getNoTelp());
        cv.put(KEY_NAMA, dataMhs.getNama());
        cv.put(KEY_PRODI, dataMhs.getProdi());
        cv.put(KEY_STATUS_TRANSFER, dataMhs.getStatusTransfer());
        cv.put(KEY_STATUS_INVESTASI, dataMhs.getStatusInvestasi());
        cv.put(KEY_KAMPUS, dataMhs.getKampus());
        cv.put(KEY_STATUS_KELAS, dataMhs.getStatusKelas());
        cv.put(KEY_KONSENTRASI, dataMhs.getKonsentrasi());

        db.insert(TABLE_MHS, null, cv);
    }

    public void editMhs(Mhs dataMhs){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NIM, dataMhs.getNim());
        cv.put(KEY_GAMBAR, dataMhs.getGambar());
        cv.put(KEY_NOTELP, dataMhs.getNoTelp());
        cv.put(KEY_NAMA, dataMhs.getNama());
        cv.put(KEY_PRODI, dataMhs.getProdi());
        cv.put(KEY_STATUS_TRANSFER, dataMhs.getStatusTransfer());
        cv.put(KEY_STATUS_INVESTASI, dataMhs.getStatusInvestasi());
        cv.put(KEY_KAMPUS, dataMhs.getKampus());
        cv.put(KEY_KONSENTRASI, dataMhs.getKonsentrasi());
        cv.put(KEY_STATUS_KELAS, dataMhs.getStatusKelas());

        db.update(TABLE_MHS, cv, KEY_ID_MHS + "=?", new String[]{String.valueOf(dataMhs.getIdMhs())});
        db.close();
    }

    public void hapusMhs(int idMhs){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_MHS, KEY_ID_MHS + "=?", new String[]{String.valueOf(idMhs)});
        db.close();
    }

    public ArrayList<Mhs> getAllMhs() {
        ArrayList<Mhs> dataMhs = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_MHS;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()){
            do {
                Mhs tempMhs = new Mhs(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7),
                        csr.getString(8),
                        csr.getString(9),
                        csr.getString(10)
                );

                dataMhs.add(tempMhs);
            }while (csr.moveToNext());
        }
        return dataMhs;
    }

    private String storeImageFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiMhsAwal(SQLiteDatabase db){
        int idMhs = 0;

        //menambahkan data Baris ke 1
        Mhs mhs1 = new Mhs(
                idMhs,
                "180030570",
                storeImageFile(R.drawable.berita1),
                "08873418080",
                "I Gede Yudi Artawan",
                "Sistem Komputer",
                "Tidak Transfer",
                "Bukan Investasi",
                "Renon",
                "Intelligent System",
                "Karyawan Malam"
        );

        tambahMhs(mhs1, db);
        idMhs++;
    }

}

