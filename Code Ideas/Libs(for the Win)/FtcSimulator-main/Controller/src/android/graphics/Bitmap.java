package android.graphics;

// Dummy implementation to support some vuforia functions
public class Bitmap {
    public Bitmap(int width, int height, Config config) {
    }

    public static Bitmap createBitmap(int width, int height, Config config) {
        return new Bitmap(width, height, config);
    }

    public void copyPixelsFromBuffer(byte[] pixels) {
    }

    public enum Config {
        RGB_565;
    }
}
