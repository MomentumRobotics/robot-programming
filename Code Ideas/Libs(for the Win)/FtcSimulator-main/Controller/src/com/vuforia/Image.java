package com.vuforia;

public class Image {
    int width = 128;
    int height = 128;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFormat() {
        return 0;
    }

    public byte[] getPixels() {
        return new byte[128*128];
    }
}
