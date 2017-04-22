package edu.suu.ktrinanm.puzzled;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;

public class ConfirmPicture extends AppCompatActivity
{
    private Bitmap bmap;
    public static Puzzle p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_picture);

        Intent i = getIntent();
        String pathName = i.getStringExtra("filePath");
        ImageView imageView = (ImageView) findViewById(R.id.img1);

        bmap = getThumbnail(pathName);
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

                //thumbnail = Bitmap.cr
                Matrix matrix = new Matrix();

                matrix.postRotate(90);

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(thumbnail,thumbnail.getWidth(),thumbnail.getHeight(),true);

                Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);
                int x = (rotatedBitmap.getHeight()/2)-200;
                int y = (rotatedBitmap.getWidth()/2)-200;

                if(x < 0)
                {
                    x = 0;
                }
                if(y < 0)
                {
                    y = 0;
                }
                thumbnail = Bitmap.createBitmap(rotatedBitmap, x, y, x+400, y+400);
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
                p = new Puzzle(bmap);
                i = new Intent(this, ChooseSize.class);
                break;
            default:
                i = new Intent();

        }

        startActivity(i);
    }
}
