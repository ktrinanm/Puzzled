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
//
//    private void storeImage(Bitmap image) {
//        File pictureFile = getOutputMediaFile();
////        if (pictureFile == null) {
////            Log.d("TAG", "file is null");
////            return;
////        }
//        try {
//            FileOutputStream fos = new FileOutputStream(pictureFile);
//            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
//            fos.close();
//        } catch (FileNotFoundException e) {
//        } catch (IOException e) {
//        }
//    }
//
//    private  File getOutputMediaFile(){
//        // To be safe, you should check that the SDCard is mounted
//        // using Environment.getExternalStorageState() before doing this.
//        File mediaStorageDir = new File(Environment.getDataDirectory()+"");
//
//        // This location works best if you want the created images to be shared
//        // between applications and persist after your app has been uninstalled.
//
//        // Create the storage directory if it does not exist
//        if (! mediaStorageDir.exists()){
//            if (! mediaStorageDir.mkdirs()){
//                return null;
//            }
//        }
//        // Create a media file name
//        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
//        File mediaFile;
//        String mImageName="MI_"+ timeStamp +".png";
//        filePath = mediaStorageDir.getPath() + File.separator + mImageName;
//        mediaFile = new File(filePath);
//        return mediaFile;
//    }
}
