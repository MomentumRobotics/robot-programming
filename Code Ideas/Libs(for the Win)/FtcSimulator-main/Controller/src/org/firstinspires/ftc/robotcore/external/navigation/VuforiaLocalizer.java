/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.robotcore.external.navigation;

import com.sun.istack.internal.NotNull;
import com.vuforia.Image;


import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class VuforiaLocalizer {
    FrameQueue frameQueue = new FrameQueue();

    public VuforiaTrackables loadTrackablesFromAsset(String trackablesFile) {
        return new VuforiaTrackables() {
            @Override
            public void setName(String name) {

            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public void activate() {

            }

            @Override
            public void deactivate() {

            }

            @Override
            public VuforiaLocalizer getLocalizer() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NotNull
            @Override
            public Iterator<VuforiaTrackable> iterator() {
                return null;
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return null;
            }

            @Override
            public boolean add(VuforiaTrackable vuforiaTrackable) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends VuforiaTrackable> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NotNull Collection<? extends VuforiaTrackable> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public VuforiaTrackable get(int index) {
                return null;
            }

            @Override
            public VuforiaTrackable set(int index, VuforiaTrackable element) {
                return null;
            }

            @Override
            public void add(int index, VuforiaTrackable element) {

            }

            @Override
            public VuforiaTrackable remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NotNull
            @Override
            public ListIterator<VuforiaTrackable> listIterator() {
                return null;
            }

            @NotNull
            @Override
            public ListIterator<VuforiaTrackable> listIterator(int index) {
                return null;
            }

            @NotNull
            @Override
            public List<VuforiaTrackable> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    public class CloseableFrame {
        public int getNumImages() {
            return 1;
        }

        public Image getImage(int i) {
            return new Image();
        }

        public void close() {
        }
    };

    public class FrameQueue {
        CloseableFrame frame = new CloseableFrame();

        public CloseableFrame take() throws InterruptedException {
            return frame;
        }
    };

    public FrameQueue getFrameQueue() {
        return frameQueue;
    };

    public void setFrameQueueCapacity(int queueCapacity) {
    }


    public enum CameraDirection {
        FRONT, BACK, DEFAULT, UNKNOWN
    };

    public static class Parameters {

        public String vuforiaLicenseKey;
        public CameraDirection cameraDirection;
        public Parameters.CameraMonitorFeedback cameraMonitorFeedback;
        public Boolean useExtendedTracking;
        public CameraName cameraName;

        public Parameters(int cameraViewId) {
        }

        public Parameters() {
            
        }

        public enum CameraMonitorFeedback {
            NONE, AXES , TEAPOT, BUILDINGS
        }
    };
}
