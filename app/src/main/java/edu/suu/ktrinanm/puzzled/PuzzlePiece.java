package edu.suu.ktrinanm.puzzled;

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
