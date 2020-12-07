package com.bingkong.bkbase.base.action;

import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bingkong.bkbase.R;

import java.io.File;
import java.util.ArrayList;

public class NinePicGridAdapter extends BaseAdapter {

    public ArrayList<String> selectedList = new ArrayList<>();
    private Context mContext;
    private ImageItemDeleteListener updateListener;
    public String PicName;

    public String getPicName() {
        return PicName;
    }

    public void setPicName(String picName) {
        PicName = picName;
    }


    public NinePicGridAdapter(Context context, ArrayList<String> allSelectedPicture) {
        this.selectedList = allSelectedPicture;
        this.mContext = context;

    }

    public void setImageReMoveListener(ImageItemDeleteListener updateListener) {
        this.updateListener = updateListener;
    }

    @Override
    public int getCount() {
        return selectedList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.ice_image_item_mygridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setData(position);

        return convertView;
    }

    public class ViewHolder {
        public ImageView image;//显示的图片
        public ImageView iv_add;//显示加号图片
        public ImageView btn;//显示删除按键

        public ViewHolder(View view) {
            findViews(view);
        }

        public void findViews(View view) {
            image = (ImageView) view.findViewById(R.id.iv);
            iv_add = (ImageView) view.findViewById(R.id.iv_add);
            int screenWidth = getScreenWidth(mContext);
            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            ViewGroup.LayoutParams layoutParams_add = iv_add.getLayoutParams();
            layoutParams.width = (int) (screenWidth - mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_16) * 4) / 3;
            layoutParams.height = (int) (screenWidth - mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_16) * 4) / 3;
            layoutParams_add.width = (int) (screenWidth - mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_16) * 4) / 3;
            layoutParams_add.height = (int) (screenWidth - mContext.getResources().getDimension(R.dimen.activity_horizontal_margin_16) * 4) / 3;
            btn = (ImageView) view.findViewById(R.id.child_delete);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        public void setData(final int position) {
            // LogUtils.e("position=" + position + "---selectedList.size()=" + selectedList.size());
            if (position == selectedList.size()) {
                //添加图片
                image.setVisibility(View.GONE);//内容图片隐藏
                iv_add.setVisibility(View.GONE);//显示加号图片
                btn.setVisibility(View.GONE);//删除图片隐藏
                iv_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else {
                //显示图片
                iv_add.setVisibility(View.GONE);//加号图片隐藏
                image.setVisibility(View.VISIBLE);//内容图片显示
                btn.setVisibility(View.VISIBLE);//删除按键显示
                image.setImageURI(Uri.fromFile(new File(selectedList.get(position))));

//                ImageLoader.getInstance().displayImage("file://" + selectedList.get(position),
//                        image);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击后移除图片
                        updateListener.onItemDelete(position);
                    }
                });
            }
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    private int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 发布图片列表中 移除图片
     */
    public interface ImageItemDeleteListener {
        void onItemDelete(int position);
    }

}


