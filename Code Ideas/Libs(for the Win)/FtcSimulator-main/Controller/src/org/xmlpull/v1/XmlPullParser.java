package org.xmlpull.v1;

import java.io.InputStream;

public class XmlPullParser {
    public static final int FEATURE_PROCESS_NAMESPACES = 1;
    public static final int START_TAG = 2;
    public static final int END_TAG = 3;
    public static final int TEXT = 4;

    public void setFeature(int featureProcessNamespaces, boolean b) {
    }

    public void setInput(InputStream input, Object o) {
    }

    public void nextTag() {
    }

    public int getEventType() {
        return 0;
    }

    public String getName() {
        return "Dummy parser";
    }

    public int next() {
        return 0;
    }

    public void require(int startTag, Object o, String sequence) {
    }

    public String getText() {
        return null;
    }

    public String getAttributeValue(Object o, String name) {
        return null;
    }
}
