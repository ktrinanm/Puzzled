package edu.suu.ktrinanm.puzzled;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileOutputStream;
import java.io.IOException;


public class PuzzleStep1 extends AppCompatActivity
{
    private int PICK_IMAGE_REQUEST = 1;
    private String filePath = "tempfile.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_step1);
    }


    public void onClick(View v)
    {
        if(v.getId() == R.id.stepOneFromGallery)
        {
            Intent intent = new Intent();
            // Show only images, no videos or anything else
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            // Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
        else if(v.getId() == R.id.stepOneNewPic)
        {
            Intent i = new Intent(this, TakePicture.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                Intent i = new Intent(this, ConfirmPicture.class);
                saveImageToInternalStorage(bitmap);
                i.putExtra("filePath", filePath);
                startActivity(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean saveImageToInternalStorage(Bitmap image) {

        try {
            // Use the compress method on the Bitmap object to write image to
            // the OutputStream
            FileOutputStream fos = openFileOutput(filePath, Context.MODE_PRIVATE);

            // Writing the bitmap to the output stream
            image = Bitmap.createScaledBitmap(image, 400, 400, false);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());
            return false;
        }
    }
}
