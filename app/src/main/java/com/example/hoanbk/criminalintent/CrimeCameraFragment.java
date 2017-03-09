package com.example.hoanbk.criminalintent;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

/**
 * Created by hoanbk on 3/8/2017.
 */

public class CrimeCameraFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private static String[] PERMISSIONS_CAMERA = {
            Manifest.permission.CAMERA
    };

    private static final String TAG = "CrimeCameraFragment";
    private Camera mCamera;
    private SurfaceView mSurfaceView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int permission = getActivity().checkSelfPermission(Manifest.permission.CAMERA);
            if(permission != PackageManager.PERMISSION_GRANTED){
                getActivity().requestPermissions(PERMISSIONS_CAMERA,
                        REQUEST_CAMERA);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_camera, container, false);
        Button takePictureButton = (Button)v.findViewById(R.id.crime_camera_takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mSurfaceView = (SurfaceView)v.findViewById(R.id.crime_camera_pictureSurfaceView);
        final SurfaceHolder holder = mSurfaceView.getHolder();
        // setType() and
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new SurfaceHolder.Callback(){
            public void surfaceCreated(SurfaceHolder holder1){
                // tell camera to use this surface as its preview
                try{
                    if(mCamera != null){
                        mCamera.setPreviewDisplay(holder1);
                    }
                }catch (IOException e){

                }
            }

            public void surfaceDestroyed(SurfaceHolder holder1){
                // we can no longer display on this surface, so // STOPSHIP: 3/8/2017
                if(mCamera != null){
                    mCamera.stopPreview();
                }
            }

            public void surfaceChanged(SurfaceHolder holder1, int format, int width, int height){
                if(mCamera == null){
                    return;
                }
                // the surface has changed size
                // update the camera preview
                Camera.Parameters parameters = mCamera.getParameters();
                Camera.Size s = getBestSupportedSize(parameters.getSupportedPreviewSizes(), width, height);
                // to be reset in the next action
                parameters.setPreviewSize(s.width, s.height);
                mCamera.setParameters(parameters);
                try{
                    mCamera.startPreview();
                }catch (Exception e){
                    mCamera.release();
                    mCamera = null;
                }
            }
        });
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int permission = getActivity().checkSelfPermission(Manifest.permission.CAMERA);
            if(permission == PackageManager.PERMISSION_GRANTED){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
                    mCamera = Camera.open(0);
                } else {
                    mCamera = Camera.open();
                }
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(mCamera != null){
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CAMERA: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
                        mCamera = Camera.open(0);
                    } else {
                        mCamera = Camera.open();
                    }
                }
            }
        }
    }

    private Camera.Size getBestSupportedSize (List<Camera.Size> sizes, int width, int height){
        Camera.Size bestSize = sizes.get(0);
        int largestArea = bestSize.width * bestSize.height;
        for(Camera.Size s : sizes){
            int area = s.width * s.height;
            if(area > largestArea){
                bestSize = s;
                largestArea = area;
            }
        }
        return bestSize;
    }
}
