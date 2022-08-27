package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.RecoverySystem;
import android.widget.ArrayAdapter;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements pdfselection {
     private NameAdpter adpter;
     private List<File> pdflist;
     private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runtimepermission();
        Toolbar tool = findViewById(R.id.toolbar1);
        setSupportActionBar(tool);
    }
    private void runtimepermission()
    {
        Dexter.withContext(MainActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                displayPdf();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
               permissionToken.continuePermissionRequest(); // it will ask user for permission again and again
            }
        }).check();
    }


    public ArrayList<File> findpdf(File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for(File singleFile :files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
            {
                arrayList.addAll(findpdf(singleFile));
            }
            else
            {
                if(singleFile.getName().endsWith(".pdf"))
                {
                    arrayList.add(singleFile);
                }
            }
        }
        return  arrayList;
    }
    public void displayPdf()
    {
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        pdflist = new ArrayList<>();
        pdflist.addAll(findpdf(Environment.getExternalStorageDirectory()));
        adpter = new NameAdpter(this,pdflist,this);
        recyclerView.setAdapter(adpter);
    }

    @Override
    public void onPdfSeleceted(File file) {
       startActivity(new Intent(MainActivity.this,PdfActivity.class).
               putExtra("path",file.getAbsolutePath()));

    }
}