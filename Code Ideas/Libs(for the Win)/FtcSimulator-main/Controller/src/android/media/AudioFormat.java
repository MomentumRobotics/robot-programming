package android.media;

// Dummy implementation for virtual robot

public class AudioFormat {
    public static final int ENCODING_PCM_16BIT = 1;
    public static final int CHANNEL_OUT_MONO = 2;

    public static class Builder {
        public Builder setEncoding(int encodingPcm16bit) {
            return this;
        }

        public Builder setSampleRate(int sampleRate) {
            return this;
        }

        public Builder setChannelMask(int channelOutMono) {
            return this;
        }

        public AudioFormat build() {
            return new AudioFormat();
        }

    }
}
