package edu.suu.ktrinanm.puzzled;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v)
    {
        Intent i;

        switch(v.getId())
        {
            case R.id.startNewBtn:
                i = new Intent(this, PuzzleStep1.class);
                break;
            case R.id.viewOldBtn:
                i = new Intent(this, viewOld.class);
                break;
            case R.id.settingsBtn:
                i = new Intent(this, Settings.class);
                break;
            default:
                i = new Intent();
        }

        startActivity(i);
    }
}
