package com.example.studydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.example.homepage.HomeActivity;
import com.example.studydemo.adapter.UserAdapter;
import com.example.studydemo.banner.BannerAct;
import com.example.studydemo.broadcastreceiver.MyStaticBroadcastReceiver;
import com.example.studydemo.clipUserIcon.ClHeaderActivity;
import com.example.studydemo.datas.User;
import com.example.studydemo.recycleviewdemo.RecycleViewDeleteAct;
import com.example.studydemo.utils.GetGPSUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import es.dmoral.toasty.Toasty;

import static com.example.studydemo.clipUserIcon.ClHeaderActivity.getBase64String;

@Route(path = ArouterConstants.ALLDEMO_ACT)
public class AllDemoActivity extends Activity {
    ListView listView;
    ImageView userIconMy;
    DynamicReceiver dynamicReceiver = new DynamicReceiver();
    private Uri changeHeadPicUri;
    private String permissionInfo;
    private final int SDK_PERMISSION_REQUEST = 127;
    public static final String TAG = "AllDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this,false);
//        getPersimmions();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_all_demo);
//        String rid = JPushInterface.getRegistrationID(getApplicationContext());
//        Log.d("rid", "onCreate: " + rid);

        IntentFilter filter = new IntentFilter();
        filter.addAction("DynamicReceiver");
        //注册广播接收
        registerReceiver(dynamicReceiver, filter);

        TextView textView = findViewById(R.id.save_data);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        //第二个参数为缺省值，如果不存在该key，返回缺省值
        String name = sp.getString("name", "111");
        textView.setText(name);
        List<User> list = new ArrayList<>();
        initData(list);
        userIconMy = findViewById(R.id.userIconMy);
        listView = findViewById(R.id.main_listView);
        UserAdapter adapter = new UserAdapter(AllDemoActivity.this, list);
        listView.setAdapter(adapter);
        //为列表视图中选中的项添加响应事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();//获取选择项的值
                switch (position) {
                    case 0:
                        ARouter.getInstance().build(ArouterConstants.BOTTOM_NACIGATION_PARENT).
                                withString("buttonName", "string").
                                navigation();
                        break;
                    case 1:
//                        EventBus.getDefault().post(new String("213"));
                        Intent intent213 = new Intent(AllDemoActivity.this, RetrofitRxJavaDemo.class);
                        startActivity(intent213);
                        break;
                    case 2:
//                        ARouter.getInstance().build(ArouterConstants.RECYCLEVIEW_DETETEDEMO).
//                                navigation();
                        Intent intent = new Intent(AllDemoActivity.this, RecycleViewDeleteAct.class);
                        startActivity(intent);
                        break;
                    case 3:
                        ARouter.getInstance().build(ArouterConstants.CUSTOMDIALOT_ACT).
                                navigation();
                        break;
                    case 4:
                        sendStatic();
                        break;
                    case 5:
                        Intent intents = new Intent();
                        intents.setAction("DynamicReceiver");
                        intents.putExtra("sele", "动态广播");
                        sendBroadcast(intents);
                        break;
                    case 6:
                        ARouter.getInstance().build(ArouterConstants.RECYCLEVIEW_CHECKED_PARENT).
                                navigation();
                        break;
                    case 7:
                        Intent intentss = new Intent(AllDemoActivity.this, BannerAct.class);
                        startActivity(intentss);
                        break;
                    case 8:
                        ARouter.getInstance().build(ArouterConstants.MENU_ACT).navigation();
                        break;
                    case 9:
                        ARouter.getInstance().build(ArouterConstants.ADDRESS_SELECT_ACT).navigation();
                        break;
                    case 10:
                        daoxu("hello world");
                        break;
                    case 11:
                        saveData("weidong");
                        break;
                    case 12:
                        Intent intentother = new Intent(AllDemoActivity.this, HomeActivity.class);
                        startActivity(intentother);
                        Toasty.error(AllDemoActivity.this, "This is an error toast.", Toast.LENGTH_SHORT, true).show();
                        break;
                    case 13:
                        downloadImage("https://cdn.yizong.cn/2/2208/d1e8896e48e39f85688ebe9ea315f62b.jpg");
                        break;
                    case 14:
                        Intent loctionIntent = new Intent(AllDemoActivity.this, LocationActivity.class);
                        startActivity(loctionIntent);
                        break;
                    case 15:
                        allShare();
                        break;
                }
//                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void daoxu(String s) {
        char[] chars = s.toCharArray();
        String str = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            str = str + chars[i];
        }
        Log.e("daoxu", "daoxu: ................" + str);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void initData(List<User> list) {
        list.add(new User(R.drawable.actor, "3种底部导航栏实现方式", ""));
        list.add(new User(R.drawable.actor, "EventBus未完成", ""));
        list.add(new User(R.drawable.actor, "带侧滑菜单的Recycleview", ""));
        list.add(new User(R.drawable.actor, "自定义Dialog", ""));
        list.add(new User(R.drawable.actor, "静态广播", ""));
        list.add(new User(R.drawable.actor, "动态广播", ""));
        list.add(new User(R.drawable.actor, "recycleView 单选、多选", ""));
        list.add(new User(R.drawable.actor, "轮播Banner", ""));
        list.add(new User(R.drawable.actor, "MenuActivity", ""));
        list.add(new User(R.drawable.actor, "地址选择Activity", ""));
        list.add(new User(R.drawable.actor, "daoxu", ""));
        list.add(new User(R.drawable.actor, "savaData", ""));
        list.add(new User(R.drawable.actor, "othorModel", ""));
        list.add(new User(R.drawable.actor, "修改头像", ""));
        list.add(new User(R.drawable.actor, "获取地理位置", ""));
        list.add(new User(R.drawable.actor, "分享", ""));
    }

    public void downloadImage(String Url) {
        final String s = Url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = s;
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                            startCropPhoto(imageFile.getPath());//  scanning circle

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void startCropPhoto(String path) {
        Intent intent = new Intent();
        intent.setClass(this, ClHeaderActivity.class);
        intent.putExtra("path", path);
        intent.putExtra("side_length", 200);//裁剪图片宽高
        startActivityForResult(intent, 24);
    }

    private void saveData(String info) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();   //获取编辑器
        editor.putString("name", info); //存入String型数据
        editor.putInt("age", 18);         //存入Int类型数据
        editor.commit();                //提交修改，否则不生效
    }

    //静态广播点击
    public void sendStatic() {
        Intent intent = new Intent(AllDemoActivity.this, MyStaticBroadcastReceiver.class);//显示指定组件名
        intent.setAction("weidong");
        intent.putExtra("info", "panhouye");
        sendBroadcast(intent);
    }

    //通过继承 BroadcastReceiver建立动态广播接收器
    class DynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //通过土司验证接收到广播
            Toast t = Toast.makeText(context, "动态广播：" + intent.getStringExtra("sele"), Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP, 0, 0);//方便录屏，将土司设置在屏幕顶端
            t.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销静态广播
        unregisterReceiver(dynamicReceiver);
    }

    /**
     * Android原生分享功能
     * 默认选取手机所有可以分享的APP
     */
    public void allShare() {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "share");//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, "share with you:" + "android");//添加分享内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "share");
        startActivity(share_intent);
    }

    /**
     * Android原生分享功能
     *
     * @param appName:要分享的应用程序名称
     */
    private void share(String appName) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType("text/plain");
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        share_intent.putExtra(Intent.EXTRA_TEXT, "推荐您使用一款软件:" + appName);
        share_intent = Intent.createChooser(share_intent, "分享");
        startActivity(share_intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 24:
                if (resultCode == 140) {
                    if (data != null) {
                        setPicToView(data);
                    }
                }
            default:
                break;
        }
    }

    private void setPicToView(Intent picdata) {

        Uri uri = picdata.getData();
        changeHeadPicUri = uri;

        if (uri == null) {
            return;
        }
        // userIcon.setImageURI(uri);
        String showPic = getFilePathByUri(AllDemoActivity.this, uri);
//        showPic = mAvartaUrl;
        Bitmap bmp = BitmapFactory.decodeFile(showPic);
        userIconMy.setImageBitmap(bmp);
        UploadImage(getBase64String(bmp), showPic);
//        userInfoBean.setCreatorAvatarUrl(showPic);
    }

    //上传头像
    public void UploadImage(String base64file, String file) {
    }

    //uri 获取 path
    public static String getFilePathByUri(Context context, Uri uri) {
        String path = null;
        // 以 file:// 开头的
        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            path = uri.getPath();
            return path;
        }
        // 以 content:// 开头的，比如 content://media/extenral/images/media/17766
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if (columnIndex > -1) {
                        path = cursor.getString(columnIndex);
                    }
                }
                cursor.close();
            }
            return path;
        }
        // 4.4及之后的 是以 content:// 开头的，比如 content://com.android.providers.media.documents/document/image%3A235700
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                if (isExternalStorageDocument(uri)) {
                    // ExternalStorageProvider
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    if ("primary".equalsIgnoreCase(type)) {
                        path = Environment.getExternalStorageDirectory() + "/" + split[1];
                        return path;
                    }
                } else if (isDownloadsDocument(uri)) {
                    // DownloadsProvider
                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                            Long.valueOf(id));
                    path = getDataColumn(context, contentUri, null, null);
                    return path;
                } else if (isMediaDocument(uri)) {
                    // MediaProvider
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{split[1]};
                    path = getDataColumn(context, contentUri, selection, selectionArgs);
                    return path;
                }
            }
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
             */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }
        } else {
            return true;
        }
    }
}
