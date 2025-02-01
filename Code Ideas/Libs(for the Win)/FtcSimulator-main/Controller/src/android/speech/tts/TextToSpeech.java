package android.speech.tts;

import android.content.Context;

import java.util.Locale;

public class TextToSpeech {
    public static final int ERROR = 1;
    public static final int SUCCESS = 0;

    public TextToSpeech(Context appContext, OnInitListener onInitListener) {
    }

    public void setLanguage(Locale locale) {
    }

    public interface OnInitListener {
        /**
         * Called to signal the completion of the TextToSpeech engine initialization.
         *
         * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
         */
        void onInit(int status);
    }

}
