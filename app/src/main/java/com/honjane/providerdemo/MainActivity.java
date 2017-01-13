package com.honjane.providerdemo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.honjane.providerdemo.utils.FileUtils;

import java.io.File;

public class MainActivity extends Activity {
    private static final int MSG_TAKE_PHOTO = 1;
    private String mFilePath;
    private String mFileName;
    private String mContentType = "application/pdf";//打开pdf
    private String mPDFPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileUtils.init();
        mFilePath = FileUtils.getFileDir() + File.separator;
        mPDFPath = FileUtils.getFileDir() + File.separator + "abc.pdf";

        findViewById(R.id.btn_open_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActionFile();

            }
        });

        findViewById(R.id.btn_open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump2Camera();
            }
        });


    }

    private void startActionFile() {
        if(mPDFPath==null){
            return;
        }
        File path = new File(mPDFPath);
        try {
            FileUtils.startActionFile(this,path,mContentType);
        }catch (ActivityNotFoundException e){

        }

    }

    public void jump2Camera() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = new File(mFilePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            mFileName = System.currentTimeMillis() + ".jpg";
            File file = new File(path, mFileName);
            if (file.exists()) {
                file.delete();
            }
            FileUtils.startActionCapture(this,file,MSG_TAKE_PHOTO);
        } else {
            Log.e("main","sdcard not exists");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MSG_TAKE_PHOTO:
                Log.i("main","to do");
                break;
        }
    }
}
