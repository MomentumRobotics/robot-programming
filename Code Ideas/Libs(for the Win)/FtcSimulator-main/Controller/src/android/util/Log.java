package android.util;

import java.util.logging.Level;
import java.util.logging.Logger;


//FIXME: perhaps these aren't the best levels to use...one day I'll actually understand java logging, but today is not that day

public class Log {
    public static void e(String tag, String msg) {
        Logger logger = Logger.getGlobal();
        logger.log(Level.SEVERE, tag + ": " + msg);
    }

    public static void w(String tag, String msg) {
        Logger logger = Logger.getGlobal();
        logger.log(Level.WARNING, tag + ": " + msg);
    }

    public static void i(String tag, String msg) {
        Logger logger = Logger.getGlobal();
        logger.log(Level.INFO, tag + ": " + msg);
    }

    public static void v(String tag, String msg) {
        Logger logger = Logger.getGlobal();
        logger.log(Level.ALL, tag + ": " + msg);
    }

    public static void d(String tag, String msg) {
        Logger logger = Logger.getGlobal();
        logger.log(Level.FINE, tag + ": " + msg);
    }

}
