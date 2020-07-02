package com.example.studydemo.customwidget.customdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.studydemo.ArouterConstants;
import com.example.studydemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@Route(path = ArouterConstants.CUSTOMDIALOT_ACT)
public class CustomDialogAct extends Activity implements View.OnClickListener, MyDialog.OnCenterItemClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private ImageView imageView;

    private MyDialog myDialog;
    private static final int REQUEST_SYSTEM_PIC = 1;
    private static final int CAMERA_RESULT = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);
        btn9 = (Button) findViewById(R.id.btn_9);
        btn10 = (Button) findViewById(R.id.btn_10);
        btn10.setOnClickListener(this);
        btn11 = (Button) findViewById(R.id.btn_11);
        btn11.setOnClickListener(this);
        imageView = findViewById(R.id.camera_dis);
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn9.setOnClickListener(this);
        myDialog = new MyDialog(this, R.layout.dialog_2, new int[]{R.id.dialog_btn});
        myDialog.setOnCenterItemClickListener((MyDialog.OnCenterItemClickListener) this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                showAlterDialog();
                break;
            case R.id.btn_2:
                showListDialog();
                break;
            case R.id.btn_3:
                showSingDialog();
                break;
            case R.id.btn_4:
                showMultiChoiceDialog();
                break;
            case R.id.btn_5:
                showProgressDialog();
                break;
            case R.id.btn_6:
                showWhiteDialog();
                break;
            case R.id.btn_7:
                DiyDialog1();
                break;
            case R.id.btn_8:
                DiyDialog2();
                break;
            case R.id.btn_9:
                myDialog.show();
                break;
            case R.id.btn_10:
                new Dialogchoosephoto(CustomDialogAct.this) {
                    @Override
                    public void btnPickByTake() {
                        //拍照
                        //点击拍照时做的事

                        //方法1
//                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_RESULT);
                        // 创建一个File对象，用于保存摄像头拍下的图片，这里把图片命名为output_image.jpg
                        // 并将它存放在手机SD卡的应用关联缓存目录下
                        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

                        // 对照片的更换设置
                        try {
                            // 如果上一次的照片存在，就删除
                            if (outputImage.exists()) {
                                outputImage.delete();
                            }
                            // 创建一个新的文件
                            outputImage.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // 如果Android版本大于等于7.0
                        if (Build.VERSION.SDK_INT >= 24) {
                            // 将File对象转换成一个封装过的Uri对象
                            imageUri = FileProvider.getUriForFile(CustomDialogAct.this, "com.example.lenovo.cameraalbumtest.fileprovider", outputImage);
                        } else {
                            // 将File对象转换为Uri对象，这个Uri标识着output_image.jpg这张图片的本地真实路径
                            imageUri = Uri.fromFile(outputImage);
                        }

                        // 动态申请权限
                        if (ContextCompat.checkSelfPermission(CustomDialogAct.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions((Activity) CustomDialogAct.this, new String[]{Manifest.permission.CAMERA}, CAMERA_RESULT);
                        } else {
                            // 启动相机程序
                            startCamera();
                        }
                    }

                    @Override
                    public void btnPickBySelect() {
                        //相册
                        //点击相册时做的事
                        if (ContextCompat.checkSelfPermission(CustomDialogAct.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(CustomDialogAct.this, new
                                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            //打开系统相册
                            openAlbum();
                        }
                    }

                }.show();
                break;
            case R.id.btn_11:
                ARouter.getInstance().build(ArouterConstants.MENU_ACT).navigation();
                break;
        }
    }

    //打开系统相册
    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_SYSTEM_PIC);
    }

    //调用相机
    private void startCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 指定图片的输出地址为imageUri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_RESULT);
    }

    /**
     * 普通dialog
     */
    private void showAlterDialog() {
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(CustomDialogAct.this);
        alterDiaglog.setIcon(R.drawable.actor);//图标
        alterDiaglog.setTitle("简单的dialog");//文字
        alterDiaglog.setMessage("生存还是死亡");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("生存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomDialogAct.this, "点击了生存", Toast.LENGTH_SHORT).show();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomDialogAct.this, "点击了死亡", Toast.LENGTH_SHORT).show();
            }
        });

        alterDiaglog.setNeutralButton("不生不死", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomDialogAct.this, "点击了不生不死", Toast.LENGTH_SHORT).show();
            }
        });

        //显示
        alterDiaglog.show();
    }

    /**
     * 列表Dialog
     */
    private void showListDialog() {
        final String[] items = {"我是1", "我是2", "我是3"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(CustomDialogAct.this);
        listDialog.setIcon(R.drawable.actor);//图标
        listDialog.setTitle("我就是个列表Dialog");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomDialogAct.this, "点击了" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        listDialog.show();
    }

    /**
     * 单选Dialog
     */
    int choice;

    private void showSingDialog() {
        final String[] items = {"我是1", "我是2", "我是3"};
        AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(CustomDialogAct.this);
        singleChoiceDialog.setIcon(R.drawable.actor);
        singleChoiceDialog.setTitle("我是单选Dialo");
        //第二个参数是默认的选项
        singleChoiceDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
            }
        });
        singleChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (choice != -1) {
                    Toast.makeText(CustomDialogAct.this,
                            "你选择了" + items[choice],
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        singleChoiceDialog.show();
    }

    /**
     * 多选对话框
     */
    ArrayList<Integer> choices = new ArrayList<>();

    private void showMultiChoiceDialog() {
        final String[] items = {"我是1", "我是2", "我是3"};
        //设置默认选择都是false
        final boolean initchoices[] = {false, false, false};
        choices.clear();
        AlertDialog.Builder multChoiceDialog = new AlertDialog.Builder(CustomDialogAct.this);
        multChoiceDialog.setIcon(R.drawable.actor);
        multChoiceDialog.setTitle("我是个多选Dialog");
        multChoiceDialog.setMultiChoiceItems(items, initchoices, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    choices.add(which);
                } else {
                    choices.remove(which);
                }
            }
        });
        multChoiceDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = choices.size();
                String str = "";
                for (int i = 0; i < size; i++) {
                    str += items[choices.get(i)] + "";
                }
                Toast.makeText(CustomDialogAct.this,
                        "你选中了" + str,
                        Toast.LENGTH_SHORT).show();
            }
        });
        multChoiceDialog.show();
    }

    /**
     * 等待Dialog具有屏蔽其他控件的交互能力
     *
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
    private void showProgressDialog() {
        final int MAX = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("我是个等待的Dialog");
        progressDialog.setMessage("等待中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * 进度条Dialog
     */
    private void showWhiteDialog() {
        /* @setProgress 设置初始进度
         * @setProgressStyle 设置样式（水平进度条）
         * @setMax 设置进度最大值
         */
        final int Max = 100;
        final ProgressDialog progressDialog = new ProgressDialog(CustomDialogAct.this);
        progressDialog.setProgress(0);
        progressDialog.setIcon(R.drawable.actor);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(Max);
        progressDialog.show();
        /**
         * 开个线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int p = 0;
                while (p < Max) {
                    try {
                        Thread.sleep(100);
                        p++;
                        progressDialog.setProgress(p);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.cancel();//达到最大就消失
            }

        }).start();
    }

    /**
     * 自定义1 控制普通的dialog的位置，大小，透明度
     * 在普通的dialog.show下面添加东西
     */
    private void DiyDialog1() {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(CustomDialogAct.this);
        alterDiaglog.setIcon(R.drawable.actor);//图标
        alterDiaglog.setTitle("简单的dialog");//文字
        alterDiaglog.setMessage("生存还是死亡");//提示消息
        //积极的选择
        alterDiaglog.setPositiveButton("生存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomDialogAct.this, "点击了生存", Toast.LENGTH_SHORT).show();
            }
        });
        //消极的选择
        alterDiaglog.setNegativeButton("死亡", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomDialogAct.this, "点击了死亡", Toast.LENGTH_SHORT).show();
            }
        });

        alterDiaglog.setNeutralButton("不生不死", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomDialogAct.this, "点击了不生不死", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alterDiaglog.create();

        //显示
        dialog.show();
        //自定义的东西
        //放在show()之后，不然有些属性是没有效果的，比如height和width
        Window dialogWindow = dialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // 设置高度和宽度
        p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.6); // 宽度设置为屏幕的0.65

        p.gravity = Gravity.TOP;//设置位置

        p.alpha = 0.8f;//设置透明度
        dialogWindow.setAttributes(p);
    }

    /**
     * 自定义dialog2 简单自定义布局
     */
    private void DiyDialog2() {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(CustomDialogAct.this, R.style.MyDialog);
        alterDiaglog.setView(R.layout.dialog_1);//加载进去
        AlertDialog dialog = alterDiaglog.create();
        //显示
        dialog.show();
        //自定义的东西
    }

    /**
     * 完全自定义dialog2
     */
//    private void DiyDialog3() {
//        MyDialog1 myDialog1 = new MyDialog1(MainActivity.this);
//        myDialog1.setContentView(R.layout.dialog_2);
//        Button s = (Button)myDialog1.findViewById(R.id.dialog_btn);
//        s.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"点击了",Toast.LENGTH_SHORT).show();
//            }
//        });
//        myDialog1.show();
//    }
    @Override
    public void OnCenterItemClick(MyDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_btn:
                Toast.makeText(getApplicationContext(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    @TargetApi(19)
    private void handleImageOnKitkat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
                        "//downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是File类型的uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        //根据图片路径显示图片
        displayImage(imagePath);

    }

    private void handleImageBeforeKitkat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);

    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相册照片
        if (requestCode == REQUEST_SYSTEM_PIC && resultCode == RESULT_OK && null != data) {
            if (Build.VERSION.SDK_INT >= 19) {
                handleImageOnKitkat(data);
            } else {
                handleImageBeforeKitkat(data);
            }
        }
        //相机照片
        if (requestCode == CAMERA_RESULT) {
            //方法1
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(photo);

            try {
                // 将图片解析成Bitmap对象
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                // 将图片显示出来
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}