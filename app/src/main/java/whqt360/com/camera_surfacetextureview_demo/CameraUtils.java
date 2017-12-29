package whqt360.com.camera_surfacetextureview_demo;

import android.hardware.Camera;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenchao on 2017/12/5.
 */

public class CameraUtils {
    public static Camera.Size getClosestSupportedSize(List<Camera.Size> supportedSizes, final int requestedWidth, final int requestedHeight) {
        return (Camera.Size) Collections.min(supportedSizes, new Comparator<Camera.Size>() {

            private int diff(final Camera.Size size) {
                return Math.abs(requestedWidth - size.width) + Math.abs(requestedHeight - size.height);
            }

            @Override
            public int compare(final Camera.Size lhs, final Camera.Size rhs) {
                return diff(lhs) - diff(rhs);
            }
        });

    }

}
