package com.qualcomm.robotcore.hardware;

public class I2cDeviceReader {
    I2cDevice device;
    I2cAddr i2cAddr;
    int memStart;
    int memLen;

    public I2cDeviceReader(I2cDevice device, I2cAddr i2cAddr, int memStart, int memLen) {
        this.device = device;
        this.i2cAddr = i2cAddr;
        this.memStart = memStart;
        this.memLen = memLen;
    }

    public byte[] getReadBuffer() {
        throw new UnsupportedOperationException("Help expand virtual_robot by implementing this.");
    }
}
