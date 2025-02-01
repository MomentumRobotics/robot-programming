package android.media;

// Dummy implementation for virtual robot


public class AudioTrack {
    public static final Object MODE_STATIC = 1;

    public AudioTrack(Object streamMusic, int sampleRate, Object channelOutMono, Object encodingPcm16bit, int i, Object modeStatic) {
    }

    public AudioTrack(AudioAttributes build, AudioFormat build1, int i, Object modeStatic, int audioSessionIdGenerate) {
    }

    public void play() {
    }

    public void setNotificationMarkerPosition(int length) {
    }

    public void write(short[] buffer, int i, int length) {
    }

    public void pause() {
    }

    public void flush() {
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener ftcAndroidTone) {
    }

    public interface OnPlaybackPositionUpdateListener {
        public void onMarkerReached(AudioTrack track);
        public void onPeriodicNotification(AudioTrack track);
    };
}
