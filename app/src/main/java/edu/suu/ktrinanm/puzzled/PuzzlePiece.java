package edu.suu.ktrinanm.puzzled;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ktrinanm on 3/31/17.
 */

public class PuzzlePiece extends Drawable
{
    private int id;
    private Bitmap image;
    private int currSpot = -1;

    public PuzzlePiece(Bitmap img, int spotId)
    {
        id = spotId;
        image = img;
    }

    public int getCurrSpot()
    {
        return currSpot;
    }

    public Bitmap getPieceBitmap()
    {
        return image;
    }

    public void setCurrSpot(int spot)
    {
        currSpot = spot;
    }

    @Override
    public void draw(@NonNull Canvas canvas)
    {

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha)
    {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter)
    {

    }

    @Override
    public int getOpacity()
    {
        return PixelFormat.OPAQUE;
    }
}
