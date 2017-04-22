package edu.suu.ktrinanm.puzzled;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.io.FileOutputStream;

/**
 * Created by ktrinanm on 3/31/17.
 */

public class Puzzle {
    private Bitmap puzzleBmap;
    private int size;
    private PuzzlePiece [] pieces;
    private String name;

    public Puzzle(Bitmap b)
    {
        puzzleBmap = b;
        size = 0;
        name = "";
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int s)
    {
        size = s;
        makePuzzle();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String n)
    {
        name = n;
    }

    public void makePuzzle()
    {
        pieces = new PuzzlePiece[size];
        int puzzleWidth = puzzleBmap.getWidth();
        int puzzleHeight = puzzleBmap.getHeight();
        int pieceWidth = (int)(puzzleWidth/Math.sqrt(size));
        int pieceHeight = (int)(puzzleHeight/Math.sqrt(size));

        for(int i = 1; i*pieceHeight < puzzleHeight; i++)
        {
            for(int j = 1; j*pieceWidth < puzzleWidth; j++)
            {
                Bitmap temp = Bitmap.createBitmap(puzzleBmap, (j-1)*pieceWidth, (i-1)*pieceHeight, pieceWidth,pieceHeight);
                pieces[i+j-2] = new PuzzlePiece(temp, (i-1)+(j-1));
            }
        }
    }

    public void savePuzzle(Context c)
    {
        String filename = name + "puzzle";
        FileOutputStream outputStream = null;

        try{
            outputStream = c.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write((size + "\n").getBytes());
            for(int i = 0; i < size; i++)
            {
                outputStream.write((pieces[i].getCurrSpot() + "\n").getBytes());
            }
            outputStream.close();

            outputStream = c.openFileOutput(filename + ".png", Context.MODE_PRIVATE);
            puzzleBmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();

            filename = name+"piece";
            for(int i = 0; i < size; i++)
            {
                outputStream = c.openFileOutput(filename + i + ".png", Context.MODE_PRIVATE);
                pieces[i].getPieceBitmap().compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            }

            filename = "allPuzzles";
            outputStream = c.openFileOutput(filename, Context.MODE_PRIVATE);
        }
        catch(Exception e)
        {
            Log.d("Error", "Error saving puzzle. " + e.getMessage());
        }
    }
}
