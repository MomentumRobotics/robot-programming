package android.media;

// Dummy implementation for virtual robot

public class AudioAttributes {
    public static class Builder {
        public Builder setLegacyStreamType(Object streamMusic) {
            return this;
        }

        public AudioAttributes build() {
            return new AudioAttributes();
        }
    }
}
