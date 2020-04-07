package com.chobocho.imagematch.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface DrawModeManager {
    void onDraw(Canvas g, Bitmap[] blockImages, Bitmap[] buttonImages);
}
