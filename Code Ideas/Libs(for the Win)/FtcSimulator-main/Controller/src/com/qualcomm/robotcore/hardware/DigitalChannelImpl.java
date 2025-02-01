package com.qualcomm.robotcore.hardware;

public class DigitalChannelImpl implements DigitalChannel {

    private volatile DigitalChannel.Mode mode = Mode.INPUT;
    private volatile boolean state = true;

    @Override
    public Mode getMode() {
        return mode;
    }

    @Override
    public void setMode(Mode mode) {
        this.mode = mode;
        state = mode == Mode.INPUT;
    }

    @Override
    public boolean getState() {
        if (mode == Mode.INPUT) return state;
        else throw new ActionNotSupportedException("Cannot read digital channel when in OUTPUT mode.");
    }

    @Override
    public void setState(boolean state) {
        if (mode == Mode.OUTPUT) this.state = state;
        else throw new ActionNotSupportedException("Cannot write to digital channel when in INPUT mode.");
    }

    /**
     * For internal use only. Update state of the digital channel.
     * @param booleanState
     * @param booleanState
     */
    public Object updateState(double millis, Object booleanState){
        this.state = state;
        return booleanState;
    }


    /**
     * For internal use only. Read the state of the digital channel.
     * @return
     */
    public boolean readStateInternal(){
        return this.state;
    }

}
