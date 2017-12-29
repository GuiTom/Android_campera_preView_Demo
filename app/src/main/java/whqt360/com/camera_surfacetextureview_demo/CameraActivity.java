package whqt360.com.camera_surfacetextureview_demo;

import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.nfc.Tag;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.annotation.Target;
import java.util.List;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO;
import static android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY;

public class CameraActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private Camera mCamera;
    public static String TAG = "secondUI";
    private GLRender glRender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        glSurfaceView = (GLSurfaceView)findViewById(R.id.surfaceView);

        glRender  = new GLRender();
        glRender.glSurfaceView = glSurfaceView;
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(glRender);
        glSurfaceView.setRenderMode(RENDERMODE_WHEN_DIRTY);
        initCamera();

    }

    private void initCamera() {

       int camNum = Camera.getNumberOfCameras();
       int camId = 0;
       for(int i=0;i<camNum;i++){
           Camera.CameraInfo cameraInfo=new Camera.CameraInfo();
           Camera.getCameraInfo(i,cameraInfo);
           if(cameraInfo.facing == CAMERA_FACING_BACK){
               camId = i;
               break;
           }
       }

           mCamera = Camera.open(camId);

        Camera.Parameters parameters = mCamera.getParameters();

        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        Camera.Size size = CameraUtils.getClosestSupportedSize(sizes,1280,720);
        if(parameters.getSupportedFocusModes().contains(FOCUS_MODE_CONTINUOUS_VIDEO)){
            parameters.setFocusMode(FOCUS_MODE_CONTINUOUS_VIDEO);
        }
        parameters.setPreviewSize(size.width,size.height);

        parameters.setPreviewFrameRate(25);
        parameters.setRecordingHint(true);

        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        glRender.camera=mCamera;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mCamera.startPreview();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCamera.stopPreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCamera.release();
        mCamera = null;
    }
}
