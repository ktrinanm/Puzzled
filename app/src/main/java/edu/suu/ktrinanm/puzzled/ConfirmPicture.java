package edu.suu.ktrinanm.puzzled;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfirmPicture extends AppCompatActivity
{
    //private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_picture);

        Intent i = getIntent();
        String pathName = i.getStringExtra("filePath");
        ImageView imageView = (ImageView) findViewById(R.id.img1);

        Bitmap bmap = getThumbnail(pathName);
        imageView.setImageBitmap(bmap);
    }

    public Bitmap getThumbnail(String filename)
    {
        Bitmap thumbnail = null;

        // If no file on external storage, look in internal storage
        if (thumbnail == null) {
            try {
                File filePath = getFileStreamPath(filename);
                FileInputStream fi = new FileInputStream(filePath);
                thumbnail = BitmapFactory.decodeStream(fi);
            } catch (Exception ex) {
                Log.e("getThumb()", ex.getMessage());
            }
        }
        return thumbnail;
    }

    public void onClick(View v)
    {
        Intent i;
        switch(v.getId())
        {
            case R.id.confPicNo:
                i = new Intent(this, PuzzleStep1.class);
                break;
            case R.id.confPicYes:
                i = new Intent(this, PuzzleScreen.class);
                break;
            default:
                i = new Intent();

        }

        startActivity(i);
    }
}
