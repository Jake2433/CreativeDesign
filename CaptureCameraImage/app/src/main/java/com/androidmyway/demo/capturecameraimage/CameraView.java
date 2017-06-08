package com.androidmyway.demo.capturecameraimage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CameraView extends Activity implements SurfaceHolder.Callback, OnClickListener{
        private static final String TAG = "CameraTest";
        Camera mCamera;
        boolean mPreviewRunning = false;

        @SuppressWarnings("deprecation")
		public void onCreate(Bundle icicle){
            super.onCreate(icicle);
            Log.e(TAG, "onCreate");

            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.cameraview);
            ImageView img = (ImageView) findViewById(R.id.blankImage);
            
            if(CaptureCameraImage.isBlack)
            	img.setBackgroundResource(android.R.color.black);
            else
            	img.setBackgroundResource(android.R.color.white);
            
            mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);
            mSurfaceView.setOnClickListener(this);
            mSurfaceHolder = mSurfaceView.getHolder();
            mSurfaceHolder.addCallback(this);
            mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
           
        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState){
            super.onRestoreInstanceState(savedInstanceState);
        }


        Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {

            public void onPictureTaken(byte[] data, Camera camera) {
                // TODO Auto-generated method stub
                if (data != null){
                    //Intent mIntent = new Intent();
                    //mIntent.putExtra("image",imageData);

                    mCamera.stopPreview();
                    mPreviewRunning = false;
                    mCamera.release();

                     try{
                    	 BitmapFactory.Options opts = new BitmapFactory.Options();
                    	 Bitmap bitmap= BitmapFactory.decodeByteArray(data, 0, data.length,opts);

                         /*
                    	 bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
                    	 int width = bitmap.getWidth();
                         int height = bitmap.getHeight();
                         int newWidth = 300;
                         int newHeight = 300;

                         // calculate the scale - in this case = 0.4f
                         float scaleWidth = ((float) newWidth) / width;
                         float scaleHeight = ((float) newHeight) / height;

                         // createa matrix for the manipulation
                         Matrix matrix = new Matrix();
                         // resize the bit map
                         matrix.postScale(scaleWidth, scaleHeight);
                         // rotate the Bitmap
                         matrix.postRotate(90);
                         Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                                 width, height, matrix, true);
                        */

                         ///////////////
                         Matrix mtx = new Matrix();
                         mtx.postRotate(90);
                         // Rotating Bitmap
                         Bitmap rotatedBMP = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mtx, true);

                         if (rotatedBMP != bitmap)
                             bitmap.recycle();
                         ////////////////
                    	 CaptureCameraImage.image.setImageBitmap(rotatedBMP);

                         try {
                             sendPhoto(rotatedBMP);
                         } catch (Exception e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                         }
                     }catch(Exception e){
                    	 e.printStackTrace();
                     }
                    //StoreByteImage(mContext, imageData, 50,"ImageName");
                    //setResult(FOTO_MODE, mIntent);
                    setResult(585);
                    finish();
                }       
            }
        };

    private void sendPhoto(Bitmap bitmap) throws Exception {
        new UploadTask().execute(bitmap);
    }

    private class UploadTask extends AsyncTask<Bitmap, Void, Void> {

        protected Void doInBackground(Bitmap... bitmaps) {
            if (bitmaps[0] == null)
                return null;
            setProgress(0);

            Bitmap bitmap = bitmaps[0];
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // convert Bitmap to ByteArrayOutputStream
            InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convert ByteArrayOutputStream to ByteArrayInputStream

            DefaultHttpClient httpclient = new DefaultHttpClient();
            try {
                HttpPost httppost = new HttpPost("http://192.168.25.34/savetofile.php"); // server
                //HttpPost httppost = new HttpPost("http://192.168.25.34/Webcam.php/savePhoto");
                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("myFile", System.currentTimeMillis() + ".jpg", in);
                httppost.setEntity(reqEntity);

                Log.i(TAG, "request " + httppost.getRequestLine());
                HttpResponse response = null;
                try {
                    response = httpclient.execute(httppost);
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    if (response != null)
                        Log.i(TAG, "response " + response.getStatusLine().toString());
                } finally {

                }
            } finally {

            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Uploaded successfully", Toast.LENGTH_LONG).show();
        }
    }

        protected void onResume(){
            Log.e(TAG, "onResume");
            super.onResume();
        }

        protected void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
        }

        protected void onStop(){
            Log.e(TAG, "onStop");
            super.onStop();
        }

        @TargetApi(9)
		public void surfaceCreated(SurfaceHolder holder){
            Log.e(TAG, "surfaceCreated");
            mCamera = Camera.open(CaptureCameraImage.cameraID);
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            Log.e(TAG, "surfaceChanged");

            // XXX stopPreview() will crash if preview is not running
            if (mPreviewRunning){
                mCamera.stopPreview();
            }

            Camera.Parameters p = mCamera.getParameters();
            p.setPreviewSize(300, 300);
            
            if(CaptureCameraImage.cameraID == 0){
            	String stringFlashMode = p.getFlashMode();
                /*
	            if (stringFlashMode.equals("torch"))
	                    p.setFlashMode("on"); // Light is set off, flash is set to normal 'on' mode
	            else
	                    p.setFlashMode("torch");
	             */
                p.setFlashMode("off");
            }
            
            mCamera.setParameters(p);
            try{
                mCamera.setPreviewDisplay(holder);
            }catch (Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mCamera.startPreview();
            mPreviewRunning = true;
            mCamera.takePicture(null, mPictureCallback, mPictureCallback);
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.e(TAG, "surfaceDestroyed");
            //mCamera.stopPreview();
            //mPreviewRunning = false;
            //mCamera.release();
        }

        private SurfaceView mSurfaceView;
        private SurfaceHolder mSurfaceHolder;

        public void onClick(View v) {
            // TODO Auto-generated method stub
            mCamera.takePicture(null, mPictureCallback, mPictureCallback);
        }

    }