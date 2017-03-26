package edu.suu.ktrinanm.puzzled;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TakePicture extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        CameraManager cm = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            CameraCharacteristics cc = cm.getCameraCharacteristics(cm.getCameraIdList()[0]);
            //CameraDevice cd = CameraManager.open();
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
