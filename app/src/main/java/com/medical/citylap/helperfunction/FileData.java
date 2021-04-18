package com.medical.citylap.helperfunction;

import android.graphics.Bitmap;

public class FileData {

    Bitmap bitmap;
    byte [] bytes ;

    public FileData(Bitmap bitmap, byte[] bytes) {
        this.bitmap = bitmap;
        this.bytes = bytes;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
