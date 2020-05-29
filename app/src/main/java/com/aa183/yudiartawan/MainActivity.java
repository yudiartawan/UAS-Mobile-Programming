package com.aa183.yudiartawan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    private ArrayList<Mhs> dataMhs = new ArrayList<>();
    private RecyclerView rvMhs;
    private MhsAdapter mhsAdapter;
    private DatabaseHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMhs = findViewById(R.id.rv_tampil_mhs);
        dbHandler = new DatabaseHandler(this);
        tampilDataMhs();

        //


        }



    private void tampilDataMhs(){
        dataMhs = dbHandler.getAllMhs();
        mhsAdapter = new MhsAdapter(this, dataMhs);
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(MainActivity.this);
        rvMhs.setLayoutManager(layManager);
        rvMhs.setAdapter(mhsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.item_menu_tambah){
            Intent bukaInput = new Intent(getApplicationContext(), InputActivity.class);
            bukaInput.putExtra("OPERASI", "insert");
            startActivity(bukaInput);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();
        tampilDataMhs();
    }
}
