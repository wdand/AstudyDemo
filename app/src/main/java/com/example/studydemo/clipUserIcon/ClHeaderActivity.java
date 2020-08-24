package com.example.studydemo.clipUserIcon;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import com.example.studydemo.BaseActivity;
import com.example.studydemo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 图片裁剪activity
 *

 使用方法：
 intent.setData(uri);//源图片Uri
 intent.putExtra("side_length", 130);//裁剪图片宽高

 */
public class ClHeaderActivity extends BaseActivity implements View.OnTouchListener {
    private String TAG = "ClHeaderActivity";
    private ImageView srcPic;
    private ImageView iv_back;
    private View bt_ok;
    private ClView clipview;
    private Button camera;
    private Button library;
    private Button cancle;
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private File tempFile;
    private Uri outputFileUri;
    private String m_photoUrl;
    private static final int RESULT_CAPTURE = 200;
    private static final int RESULT_PICK = 201;
    private static final int CROP_PHOTO = 202;
    /**
     * 动作标志：无
     */
    private static final int NONE = 0;
    /**
     * 动作标志：拖动
     */
    private static final int DRAG = 1;
    /**
     * 动作标志：缩放
     */
    private static final int ZOOM = 2;
    /**
     * 初始化动作标志
     */
    private int mode = NONE;

    /**
     * 记录起始坐标
     */
    private PointF start = new PointF();
    /**
     * 记录缩放时两指中间点坐标
     */
    private PointF mid = new PointF();
    private float oldDist = 1f;

    private Bitmap bitmap;

    private int side_length;//裁剪区域边长

    public static int DESIGNED_REQUECT_CODE = 1;
    static String[] permissions ={ Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onResume() {
        super.onResume();
        if(!EasyPermissions.hasPermissions(this,permissions)) {
            EasyPermissions.requestPermissions(this,"This App's Function needs camara ,storage and internet permissions",
                    DESIGNED_REQUECT_CODE, permissions);
        }
        Log.i(TAG,"onPermissionsGranted list ==  + onResume");
    }

    @Override
    public void onPermissionsGranted(int i, List<String> list) {
        Log.i("lsm","onPermissionsGranted list == " + list);
        if (list.size() != permissions.length) {
            finish();
        }
    }

    @Override
    public void onPermissionsDenied(int i, List<String> list) {

    }


    /**
     * 初始化图片
     * step 1: decode 出 720*1280 左右的照片  因为原图可能比较大 直接加载出来会OOM
     * step 2: 将图片缩放 移动到imageView 中间
     */
    private void initSrcPic() {
        String path = getIntent().getStringExtra( "path" );
        if (TextUtils.isEmpty( path )) {
            return;
        }
        //原图可能很大，现在手机照出来都3000*2000左右了，直接加载可能会OOM
        //这里 decode 出 720*1280 左右的照片
        bitmap = BitmapUtil.decodeSampledBitmap( path, 720, 1280 );

        if (bitmap == null) {
            return;
        }


        //图片的缩放比
        float scale;
        if (bitmap.getWidth() > bitmap.getHeight()) {//宽图
            scale = (float) srcPic.getWidth() / bitmap.getWidth();

            //如果高缩放后小于裁剪区域 则将裁剪区域与高的缩放比作为最终的缩放比
            Rect rect = clipview.getClipRect();
            float minScale = rect.height() / bitmap.getHeight();//高的最小缩放比
            if (scale < minScale) {
                scale = minScale;
            }
        } else {//高图
            scale = (float) srcPic.getWidth() / 3*2 / bitmap.getWidth();//宽缩放到imageview的宽的1/2
        }

        // 缩放
        matrix.postScale( scale, scale );

        // 平移   将缩放后的图片平移到imageview的中心
        int midX = srcPic.getWidth() / 2;//imageView的中心x
        int midY = srcPic.getHeight() / 2;//imageView的中心y
        int imageMidX = (int) (bitmap.getWidth() * scale / 2);//bitmap的中心x
        int imageMidY = (int) (bitmap.getHeight() * scale / 2);//bitmap的中心y
        matrix.postTranslate( midX - imageMidX, midY - imageMidY );

        srcPic.setScaleType( ImageView.ScaleType.MATRIX );
        srcPic.setImageMatrix( matrix );
        srcPic.setImageBitmap( bitmap );

    }

    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set( matrix );
                // 设置开始点位置
                start.set( event.getX(), event.getY() );
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing( event );
                if (oldDist > 10f) {
                    savedMatrix.set( matrix );
                    midPoint( mid, event );
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set( savedMatrix );
                    matrix.postTranslate( event.getX() - start.x, event.getY()
                            - start.y );
                } else if (mode == ZOOM) {
                    float newDist = spacing( event );
                    if (newDist > 10f) {
                        matrix.set( savedMatrix );
                        float scale = newDist / oldDist;
                        matrix.postScale( scale, scale, mid.x, mid.y );
                    }
                }
                break;
        }
        view.setImageMatrix( matrix );
        return true;
    }

    /**
     * 多点触控时，计算最先放下的两指距离
     *
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        float x = event.getX( 0 ) - event.getX( 1 );
        float y = event.getY( 0 ) - event.getY( 1 );
        return (float) Math.sqrt( x * x + y * y );
    }

    /**
     * 多点触控时，计算最先放下的两指中心坐标
     *
     * @param point
     * @param event
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX( 0 ) + event.getX( 1 );
        float y = event.getY( 0 ) + event.getY( 1 );
        point.set( x / 2, y / 2 );
    }


    /**
     * 获取缩放后的截图
     * 1.截取裁剪框内bitmap
     * 2.将bitmap缩放到宽高为side_length
     *
     * @return
     */
    private Bitmap getZoomedCropBitmap() {

        srcPic.setDrawingCacheEnabled( true );
        srcPic.buildDrawingCache();

        Rect rect = clipview.getClipRect();

        Bitmap cropBitmap = null;
        Bitmap zoomedCropBitmap = null;
        try {
            cropBitmap = Bitmap.createBitmap( srcPic.getDrawingCache(), rect.left, rect.top, rect.width(), rect.height() );
            zoomedCropBitmap = BitmapUtil.zoomBitmap( cropBitmap, side_length, side_length );
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cropBitmap != null) {
            cropBitmap.recycle();
        }

        // 释放资源
        srcPic.destroyDrawingCache();

        return zoomedCropBitmap;
    }


    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
        Bitmap zoomedCropBitmap = getZoomedCropBitmap();
        if (zoomedCropBitmap == null) {
            Log.e( TAG, "zoomedCropBitmap == null" );
            return;
        }

        Uri mSaveUri = Uri.fromFile( new File( getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg" ) );

        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream( mSaveUri );
                if (outputStream != null) {
                    zoomedCropBitmap.compress( Bitmap.CompressFormat.JPEG, 90, outputStream );
                }
            } catch (IOException ex) {
                // TODO: report error to caller
                Log.e( TAG, "Cannot open file: " + mSaveUri, ex );
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            Intent intent = new Intent();
            intent.setData( mSaveUri );
            setResult( RESULT_OK, intent );
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        switch (requestCode) {
            case RESULT_CAPTURE:
                if (resultCode == RESULT_OK) {
                    starCropPhoto(Uri.fromFile(tempFile));
                }
                break;
            case RESULT_PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    starCropPhoto(uri);
                }

                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        setPicToView(data);
                    }
                }
                break;

            default:
                break;
        }
    }

    /**
     * 打开截图界面
     * @param uri 原图的Uri
     */
    public void starCropPhoto(Uri uri) {
        Log.i( "return picture", "done.setOnClickListener: " + uri +"==========");

        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipHeaderActivity.class);
        intent.setData(uri);
        intent.putExtra("side_length", 200);//裁剪图片宽高
        startActivityForResult(intent, CROP_PHOTO);

        //调用系统的裁剪
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", crop);
//        intent.putExtra("outputY", crop);
//        intent.putExtra("return-data", true);
//        intent.putExtra("noFaceDetection", true);
//        startActivityForResult(intent, CROP_PHOTO);
    }
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;

    }


    private void setPicToView(Intent picdata) {

        Uri uri = picdata.getData();

        if (uri == null) {
            return;
        }
        // userIcon.setImageURI(uri);
        String showPic = getRealPathFromURI(uri);
//        Bitmap bmp = BitmapFactory.decodeFile( showPic );
//        headPic.setImageBitmap( bmp );
        Intent intent = new Intent();
        intent.setData( uri );
        setResult( 140, intent );
        finish();
    }
    //uri 获取 path
    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            if (cursor.moveToFirst()){
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                if (idx > -1){
                    result = cursor.getString(idx);
                }
                cursor.close();
            }
        }
        return result;
    }
    public static String getBase64String(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if(bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Log.i("lsm","getBase64String byteArray == " + byteArray.length);
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } else {
            Log.i("lsm","getBase64String  bitmap null");
            return null;
        }
    }

    @Override
    public void initBus() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        }else{
            tempFile = new File(checkDirPath( Environment.getExternalStorageDirectory().getPath()+"/clipHeaderLikeQQ/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
        side_length = getIntent().getIntExtra( "side_length", 400 );
        Log.i( TAG, "init: "+side_length );
        iv_back = (ImageView) findViewById( R.id.iv_backs );
        srcPic = (ImageView) findViewById( R.id.src_pics );
        clipview = (ClView) findViewById( R.id.clipViewcs );
        camera =(Button)findViewById( R.id.camera );
        library = (Button)findViewById( R.id.library );
        cancle = (Button)findViewById( R.id.cancle );
        srcPic.setOnTouchListener( this );

        //clipview中有初始化原图所需的参数，所以需要等到clipview绘制完毕再初始化原图
        ViewTreeObserver observer = clipview.getViewTreeObserver();
        observer.addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            public void onGlobalLayout() {
                clipview.getViewTreeObserver().removeGlobalOnLayoutListener( this );
                initSrcPic();
            }
        } );

        iv_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
        camera.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempFile = new File( MyFileUtils.getFileName());
                if (Build.VERSION.SDK_INT >= 24){
                    outputFileUri = FileProvider.getUriForFile(ClHeaderActivity.this,"com.example.lenovo.cameraalbumtest.fileprovider", tempFile);
                }else {
                    outputFileUri = Uri.fromFile(tempFile);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, RESULT_CAPTURE);
                }
            }
        } );
        library.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "please choose picture"), RESULT_PICK);
            }
        } );
        cancle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cl_header ;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(int code, String msg) {

    }
}