package edu.suu.ktrinanm.puzzled;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ChooseSize extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_size);
    }

    public void onClick(View v)
    {
        int choiceId = ((RadioGroup)findViewById(R.id.sizeBtns)).getCheckedRadioButtonId();
        int pSize = Integer.parseInt(((RadioButton)findViewById(choiceId)).getText().toString().substring(0,2).trim());
        String pName = ((TextView)findViewById(R.id.nameField)).getText().toString();

        ConfirmPicture.p.setSize(pSize);
        ConfirmPicture.p.setName(pName);
        ConfirmPicture.p.savePuzzle(this);
        Intent i = new Intent(this, PuzzleScreen.class);
        startActivity(i);
    }
}
