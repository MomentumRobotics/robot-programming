package com.qualcomm.robotcore.hardware;

public class PassiveColorSensorImpl implements ColorSensor {

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int alpha = 0;

    public static class ColorTriple {
        int R, G, B;
        public ColorTriple(int R, int G, int B) {
            this.R=R;
            this.G=G;
            this.B=B;
        }
    };


    /**
     * Internal Use Only. Update the color sensor using provided values of R, G, B
     * @param millis
     * @param colorTriple
     * @return
     */
    public synchronized Object updateState(double millis, Object colorTriple){
        ColorTriple color = (ColorTriple)colorTriple;
        red = color.R;
        green = color.G;
        blue = color.B;
        alpha = Math.max(red, Math.max(green, blue));
        return null;
    }

    @Override
    public synchronized int red() {
        return red;
    }

    @Override
    public synchronized int green() {
        return green;
    }

    @Override
    public synchronized int blue() {
        return blue;
    }

    @Override
    public synchronized int alpha() {
        return alpha;
    }

    @Override
    public int argb() {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

}
