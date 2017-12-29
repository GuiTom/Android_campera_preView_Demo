package whqt360.com.camera_surfacetextureview_demo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.View;

import java.io.IOException;
import java.security.Permission;
import java.util.List;

import static android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO;
import static android.opengl.GLSurfaceView.RENDERMODE_CONTINUOUSLY;
import static android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY;

public class MainActivity extends AppCompatActivity{

    private static final int MY_PERMISSIONS_REQUEST_CAMERA= 0;
    public static String TAG = "firstUI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){

        this.reqPermission(Manifest.permission.CAMERA);
    }
    @TargetApi(23)
    public void reqPermission(String permission){


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //检查目前是否有权限
            if (checkSelfPermission(permission)
                    != PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(
                        permission)) {
                    // 这里写一些向用户解释为什么我们需要读取联系人的提示得代码
                }

                //请求权限，系统会显示一个获取权限的提示对话框，当前应用不能配置和修改这个对话框
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,permission},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                return;
            }else {
                startActivity(new Intent(this,CameraActivity.class));
            }

        }else {
            startActivity(new Intent(this,CameraActivity.class));
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功
                    startActivity(new Intent(this,CameraActivity.class));
                } else {
                    // 授权失败
                }
                return;
            }

        }
    }
}
