package com.bingkong.bkbase.ui.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bingkong.bkbase.R;
import com.bingkong.bkbase.utils.ViewUtils;

/**
 * 带按钮的位于屏幕中间的对话框
 *
 * @author sunxuewei
 */
@SuppressLint("ValidFragment")
public class ConfirmDialog extends BaseDialog {
    /**
     * 设置标题上方的图片为默认成功的图片
     */
    public static final int TITLE_IMAGE_SUCCESS = 1;
    /**
     * 设置标题上方的图片为默认失败的图片
     */
    public static final int TITLE_IMAGE_FAIL = 2;
    private static final int BUTTON_HEIGHT = 45;
    private static final int BUTTON_TEXT_SIZE = 18;
    private static final int DESCRIPTION_TEXT_SIZE_SINGLE = 18;
    private static final int DESCRIPTION_TEXT_SIZE_MULTIPLE = 15;

    private static final String DESCRIPTION = "button_item";
    private static final String BUTTON_ITEM = "button_item";
    private static final String BUTTON_COLOR = "button_color";
    private static final String BUTTON_SIZE = "button_size";
    private static final String TITLE = "title";
    private static final String TOP_IMAGE = "top_image";
    private static final String CONTENT_IMAGE = "content_image";
    private static final String WARNING_IMAGE = "warning_image";
    private static final String BUTTON_BOLD = "button_bold";
    private static final String CLOSE_IMAGE = "close_image";
    private static final String HEIGHT_CLOSE_IMAGE = "height_close_image";
    private static final String DIALOG_WIDTH = "dialog_width";

    private OnConfirmListener mListener;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private DialogInterface.OnKeyListener mOnKeyListener;
    /**
     * 对话框文字内容
     */
    private CharSequence mDescription;
    /**
     * 支持Span
     */
    private boolean mSpanable;
    /**
     * 按钮文字
     */
    private String[] mButtonItems;
    /**
     * 定制化按钮文字颜色
     */
    private int[] mButtonColors;
    /**
     * 定制化字体颜色
     */
    private int[] mButtonTextSizes;
    /**
     * 按钮文字是否粗体
     */
    private boolean[] mButtonBold;
    /**
     * 对话框顶部显示的图片
     */
    private int mImage;
    /**
     * 对话框标题
     */
    private CharSequence mTitle;
    /**
     * 内容gravity
     */
    private int mDescriptionGravity = Gravity.CENTER;
    /**
     * 对话框内部，按钮上方显示的图片
     */
    private int mContentImage;
    /**
     * 对话框内部，标题上方显示的图片，如对号，叉号
     */
    private int mWarningImage;
    /**
     * 定制化body
     */
    private View mCustomerBodyView;
    /**
     * 自定义标题颜色
     */
    private int mTitleColor;
    /**
     * 自定义标题文案大小
     */
    private int mTitleSize;
    /**
     * 自定义标题文案加粗
     */
    private boolean mTitleBold;
    /**
     * 对话框右上方关闭按钮
     */
    private boolean mCloseImageView;
    /**
     * 关闭按钮距离对话框上边缘的距离
     */
    private int mHeightCloseImageView;
    /**
     * 自定义内容颜色
     */
    private int mDescriptionColor;
    /**
     * 标识弹窗是否消失
     */
    private boolean mAllowDismiss = true;

    //对话框宽度，默认为270
    private int mDialogWidth = 270;

    private boolean enableAnimation = true;

    public ConfirmDialog() {
    }

    public void setDialogWidth(int dialogWidth) {
        this.mDialogWidth = dialogWidth;
    }

    public void setConfirmListener(OnConfirmListener listener) {
        mListener = listener;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    public void setDescription(CharSequence description) {
        mDescription = description;
    }

    public void setSpanable(boolean spanable) {
        mSpanable = spanable;
    }

    public void setButtonItems(String[] buttonItems) {
        mButtonItems = buttonItems;
    }

    public void setCloseImageView(boolean closeImageView) {
        mCloseImageView = closeImageView;
    }

    public void setHeightCloseImageView(int heightCloseImageView) {
        mHeightCloseImageView = heightCloseImageView;
    }

    public void setButtonColors(int[] buttonColors) {
        mButtonColors = buttonColors;
    }

    public void setDescriptionGravity(int gravity) {
        mDescriptionGravity = gravity;
    }

    public void setButtonTextSizes(int[] textSizes) {
        mButtonTextSizes = textSizes;
    }

    public void setCancelable(boolean cancelable) {
        mCancelable = cancelable;
    }

    public void setButtonBold(boolean[] buttonBold) {
        mButtonBold = buttonBold;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * 特殊颜色的标题
     */
    private void setTitle(CharSequence spannableTitle) {
        mTitle = spannableTitle;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public void setContentImage(int contentImage) {
        mContentImage = contentImage;
    }

    public void setWarningImage(int warningImage) {
        mWarningImage = warningImage;
    }

    public void setCustomerBodyView(View view) {
        mCustomerBodyView = view;
    }

    public void enableDialogAnimation(boolean enable) {
        enableAnimation = enable;
    }

    public void setmOnKeyListener(DialogInterface.OnKeyListener listener) {
        mOnKeyListener = listener;
    }

    public void setDescriptionColor(int descriptionColor) {
        this.mDescriptionColor = descriptionColor;
    }

    public void setAllowDismiss(boolean mAllowDismiss) {
        this.mAllowDismiss = mAllowDismiss;
    }

    @Override
    protected View getDialogView(Bundle savedInstanceState) {
        restoreData(savedInstanceState);
        return initConfirmLayout();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    private void restoreData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (mDescription == null) {
                mDescription = savedInstanceState.getString(DESCRIPTION);
            }
            if (mButtonItems == null) {
                mButtonItems = savedInstanceState.getStringArray(BUTTON_ITEM);
            }
            if (mButtonColors == null) {
                mButtonColors = savedInstanceState.getIntArray(BUTTON_COLOR);
            }
            if (mButtonTextSizes == null) {
                mButtonTextSizes = savedInstanceState.getIntArray(BUTTON_SIZE);
            }
            if (mTitle == null) {
                mTitle = savedInstanceState.getCharSequence(TITLE);
            }
            if (mImage == 0) {
                mImage = savedInstanceState.getInt(TOP_IMAGE, 0);
            }
            if (mContentImage == 0) {
                mContentImage = savedInstanceState.getInt(CONTENT_IMAGE, 0);
            }
            if (mWarningImage == 0) {
                mWarningImage = savedInstanceState.getInt(WARNING_IMAGE, 0);
            }
            if (mButtonBold == null) {
                mButtonBold = savedInstanceState.getBooleanArray(BUTTON_BOLD);
            }
            if (mHeightCloseImageView == 0) {
                mHeightCloseImageView = savedInstanceState.getInt(HEIGHT_CLOSE_IMAGE, 0);
            }
            if (mCloseImageView == false) {
                mCloseImageView = savedInstanceState.getBoolean(CLOSE_IMAGE, false);
            }
            if (mDialogWidth == 270) {
                mDialogWidth = savedInstanceState.getInt(DIALOG_WIDTH, 270);
            }
        }

    }

    private View initConfirmLayout() {
        //相对布局
        RelativeLayout outLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams outLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        outLayout.setLayoutParams(outLayoutParams);
        //主体布局
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setId(R.id.PP_confirm_dialog_layout_id);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewUtils.dp2px(getActivity(), mDialogWidth), RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.setLayoutParams(layoutParams);

        // 顶部的形象切片
        buildTopImage(layout);
        // 标题
        buildTitleView(layout);
        // 定制化body
        if (mCustomerBodyView != null) {
            layout.addView(mCustomerBodyView);
        }
        // 描述
        buildDescriptionView(layout);
        // 描述下方的图片
        buildContentImage(layout);
        // 按钮上方的分割线
        View view = initDivider();
        layout.addView(view);
        view.setVisibility(TextUtils.isEmpty(mDescription) && mWarningImage == 0 && mCustomerBodyView == null
                ? View.GONE : View.VISIBLE);
        // 按钮
        buildButtons(layout);
        //添加布局
        outLayout.addView(layout);
        //右上角的关闭按钮
        buildCloseImageView(outLayout, layout, mHeightCloseImageView);
        return outLayout;
    }

    private void buildCloseImageView(ViewGroup outroot, ViewGroup root, int mHeightCloseImageView) {
        if (mCloseImageView == false) {
            return;
        }

        ImageView closeImageView = new ImageView(getActivity());
        closeImageView.setImageResource(R.drawable.pp_confirm_dialog_close);
        RelativeLayout.LayoutParams imageViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageViewParams.addRule(RelativeLayout.RIGHT_OF, root.getId());
        imageViewParams.addRule(RelativeLayout.ABOVE, root.getId());
        imageViewParams.setMargins(ViewUtils.dp2px(getActivity(), -20), 0, 0, ViewUtils.dp2px(getActivity(), mHeightCloseImageView));
        closeImageView.setLayoutParams(imageViewParams);
        closeImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        outroot.addView(closeImageView);
    }

    private void customize(TextView textView, int index) {
        if (mButtonColors != null && mButtonColors.length > index) {
            textView.setTextColor(mButtonColors[index]);
        }

        if (mButtonTextSizes != null && mButtonTextSizes.length > index) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mButtonTextSizes[index]);
        }
    }

    private void buildButtons(ViewGroup root) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setPadding(0, 0, 0, 0);
        for (int i = 0; i < mButtonItems.length; i++) {
            TextView item = initButton(i, mButtonItems[i], 1, mButtonItems.length);
            customize(item, i);
            layout.addView(item);
            if (mButtonItems.length > 1 && i != mButtonItems.length - 1) {
                View divider = null;
                divider = new View(getActivity());
                LayoutParams params = new LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT);
                divider.setBackgroundColor(getActivity().getResources().getColor(R.color.color_e6e6e6));
                layout.addView(divider, params);
            }
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dp2px(getActivity(), BUTTON_HEIGHT));
        root.addView(layout, layoutParams);
    }

    private void buildTopImage(ViewGroup root) {
        if (mImage == 0) {
            return;
        }

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(mImage);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        imageView.setLayoutParams(params);
        root.addView(imageView);

    }

    private void buildTitleView(ViewGroup root) {
        if (TextUtils.isEmpty(mTitle)) {
            return;
        }

        TextView textView = new TextView(getActivity());
        int padding = ViewUtils.dp2px(getActivity(), 20);
        if (mTitleBold) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        if (mTitleColor > 0) {
            textView.setTextColor(mTitleColor);
        } else {
            textView.setTextColor(0xFF333333);
        }
        if (mTitleSize > 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mTitleSize);
        } else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DESCRIPTION_TEXT_SIZE_SINGLE);
        }
        textView.setGravity(Gravity.CENTER);
        textView.setText(mTitle);


        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        // 无头部形象
        if (mImage == 0) {
            // 无标题图片
            if (mWarningImage == 0) {
                textView.setBackgroundResource(R.drawable.pp_text_bg_top_round_corner);
                textView.setPadding(padding, ViewUtils.dp2px(getActivity(), 22), padding, 0);
            } else {
                // 有标题图片
                textView.setBackgroundColor(Color.WHITE);
                textView.setPadding(padding, ViewUtils.dp2px(getActivity(), 20), padding,
                        ViewUtils.dp2px(getActivity(), 30));
            }
        } else {
            // 有头部形象
            textView.setBackgroundColor(Color.WHITE);
            textView.setPadding(padding, ViewUtils.dp2px(getActivity(), 17), padding, 0);
        }
        root.addView(textView);

    }

    private void buildDescriptionView(ViewGroup root) {
        if (TextUtils.isEmpty(mDescription)) {
            return;
        }

        TextView textView = new TextView(getActivity());
        int padding = ViewUtils.dp2px(getActivity(), 20);
        textView.setPadding(padding, padding, padding, padding);
        if (mDescriptionColor != 0) {
            textView.setTextColor(mDescriptionColor);
        } else {
            textView.setTextColor(0xFF333333);
        }
        textView.setGravity(mDescriptionGravity);
        textView.setText(mDescription);

        // 内容有图片
        if (mContentImage != 0) {
            textView.setPadding(padding, ViewUtils.dp2px(getActivity(), 9), padding, ViewUtils.dp2px(getActivity(), 15));
            textView.setLineSpacing(10, 1.0f);
        }
        // 简单处理，长度13为分界点
        else if (mDescription.length() > 13 || !TextUtils.isEmpty(mTitle)) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DESCRIPTION_TEXT_SIZE_MULTIPLE);
            // 有标题时padding不一样
            if (!TextUtils.isEmpty(mTitle) || mImage != 0) {
                textView.setPadding(padding, ViewUtils.dp2px(getActivity(), 9), padding, ViewUtils.dp2px(getActivity(), 22));
            } else {
                textView.setPadding(padding, ViewUtils.dp2px(getActivity(), 22), padding, ViewUtils.dp2px(getActivity(), 22));
            }
            textView.setLineSpacing(10, 1.0f);
        } else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DESCRIPTION_TEXT_SIZE_SINGLE);
            if (mImage == 0) {
                textView.setPadding(0, ViewUtils.dp2px(getActivity(), 29), 0,
                        ViewUtils.dp2px(getActivity(), 29));
            } else {
                textView.setPadding(0, ViewUtils.dp2px(getActivity(), 17), 0,
                        ViewUtils.dp2px(getActivity(), 29));
            }
        }

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        if (mImage == 0 && TextUtils.isEmpty(mTitle)) {
            textView.setBackgroundResource(R.drawable.pp_text_bg_top_round_corner);
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }
        root.addView(textView);

    }

    private void buildContentImage(ViewGroup root) {
        if (mContentImage == 0) {
            return;
        }

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(mContentImage);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        imageView.setLayoutParams(params);
        imageView.setBackgroundColor(Color.WHITE);
        root.addView(imageView);

    }

    private View initDivider() {
        View view = new View(getActivity());
        view.setBackgroundColor(getResources().getColor(R.color.color_e6e6e6));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(params);
        return view;
    }

    public Dialog getDialogBuilder() {
        Dialog dialog = new Dialog(getActivity(), R.style.PPEntranceTipDialog);
        if (enableAnimation)
            dialog.getWindow().getAttributes().windowAnimations = R.style.ConfirmDialogAnimation;

        return dialog;

    }

    private TextView initButton(final int index, String value, float weight, final int length) {
        final TextView textView = new TextView(getActivity());
        int padding = ViewUtils.dp2px(getActivity(), 10);
        textView.setPadding((int) (padding * 1.5f), padding, (int) (padding * 1.5f), padding);

        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, BUTTON_TEXT_SIZE);
        textView.setTextColor(getActivity().getResources().getColor(R.color.color_0bbe06));
        textView.setGravity(Gravity.CENTER);

        customize(textView, index + 1);

        textView.setClickable(true);

        if (length == 1) {
            textView.setBackgroundResource(R.drawable.pp_selector_white_button_bottom_round_corner);
        } else {
            // left corner
            if (index == 0) {
                textView.setBackgroundResource(R.drawable.pp_selector_white_button_bottom_left_round_corner);
            } else if (index == length - 1) {
                textView.setBackgroundResource(R.drawable.pp_selector_white_button_bottom_right_round_corner);
            }
        }

        textView.setText(value);
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);//LayoutParams.WRAP_CONTENT
        params.weight = weight;

        textView.setLayoutParams(params);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(getActivity(), index);
                }
                if (mAllowDismiss) {
                    dismiss();
                }
            }
        });

        if (mButtonBold != null && mButtonBold.length > index && mButtonBold[index]) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }

        return textView;
    }

    public void setTitleColor(int titleColor) {
        this.mTitleColor = titleColor;
    }

    public void setTitleSize(int titleSize) {
        this.mTitleSize = titleSize;
    }

    public void setTitleBold(boolean titleBold) {
        this.mTitleBold = titleBold;
    }

    public interface OnConfirmListener {
        void onClick(Context context, int index);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            // 设定整个对话框的高度
            dialog.getWindow().setLayout(ViewUtils.dp2px(getActivity(), mDialogWidth), ViewGroup.LayoutParams.WRAP_CONTENT);
            if (mOnKeyListener != null) {
                dialog.setOnKeyListener(mOnKeyListener);
            }
        }
    }

    public static class Builder {
        private ConfirmDialog mConfirmDialog;

        public Builder() {
            mConfirmDialog = new ConfirmDialog();
        }

        public Builder setConfirmListener(OnConfirmListener listener) {
            mConfirmDialog.setConfirmListener(listener);
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            mConfirmDialog.setOnDismissListener(onDismissListener);
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            mConfirmDialog.setmOnKeyListener(onKeyListener);
            return this;
        }

        /**
         * 设置对话框右上角关闭按钮
         * param closeImageView 默认值为false 不设置
         *
         * @return
         */
        public Builder setCloseImageView(boolean closeImageView) {
            mConfirmDialog.setCloseImageView(closeImageView);
            return this;
        }

        /**
         * 设置关闭按钮距离对话框上边缘的高度(dp)
         *
         * @param heightCloseImageView 默认值为0 ，需要调用方设置高度
         * @return
         */
        public Builder setHeightCloseImageView(int heightCloseImageView) {
            mConfirmDialog.setHeightCloseImageView(heightCloseImageView);
            return this;

        }

        /**
         * 设置对话框的文字内容
         *
         * @param description 描述
         * @return
         */
        public Builder setDescription(CharSequence description) {
            mConfirmDialog.setDescription(description);
            return this;
        }

        public Builder setSpanable(boolean spanable) {
            mConfirmDialog.setSpanable(spanable);
            return this;
        }


        /**
         * 设置对话框的按钮
         *
         * @param buttonItems 按钮
         * @return
         */
        public Builder setButtonItems(String[] buttonItems) {
            mConfirmDialog.setButtonItems(buttonItems);
            return this;
        }

        /**
         * 设置对话框字体颜色，有定制需求的可调用此方法
         *
         * @param buttonColors 按钮颜色
         * @return
         */
        public Builder setButtonColors(int[] buttonColors) {
            mConfirmDialog.setButtonColors(buttonColors);
            return this;
        }

        /**
         * 设置对话框字体大小，有定制需求的可调用此方法
         *
         * @param textSizes 按钮字体大小
         * @return
         */
        public Builder setButtonTextSizes(int[] textSizes) {
            mConfirmDialog.setButtonTextSizes(textSizes);
            return this;
        }

        /**
         * 设置对话框描述文字的gravity
         *
         * @param gravity 描述文字的gravity
         * @return
         */
        public Builder setDescriptionGravity(int gravity) {
            mConfirmDialog.setDescriptionGravity(gravity);
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mConfirmDialog.setCancelable(cancelable);
            return this;
        }

        /**
         * 设置引导按钮粗体
         *
         * @param buttonBold 按钮是否粗体
         * @return
         */
        public Builder setButtonBold(boolean[] buttonBold) {
            mConfirmDialog.setButtonBold(buttonBold);
            return this;
        }

        /**
         * 设置顶部显示的图片
         *
         * @param image
         * @return
         */
        public Builder setImage(int image) {
            mConfirmDialog.setImage(image);
            return this;
        }

        /**
         * 设置对话框的标题
         *
         * @param title 标题
         * @return
         */
        public Builder setTitle(String title) {
            mConfirmDialog.setTitle(title);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            mConfirmDialog.setTitle(title);
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            mConfirmDialog.setTitleColor(titleColor);
            return this;
        }

        public Builder setTitleBold(boolean titleBold) {
            mConfirmDialog.setTitleBold(titleBold);
            return this;
        }

        public Builder setTitleSize(int titleSize) {
            mConfirmDialog.setTitleSize(titleSize);
            return this;
        }

        public Builder setDescriptionColor(int descriptionColor) {
            mConfirmDialog.setDescriptionColor(descriptionColor);
            return this;
        }

        /**
         * 设置点击后对话框是否允许对话框消失
         *
         * @param mAllowDismiss
         * @return
         */
        public Builder setAllowDismiss(boolean mAllowDismiss) {
            mConfirmDialog.setAllowDismiss(mAllowDismiss);
            return this;
        }

        /**
         * 设置对话框内部，按钮上方显示的图片
         *
         * @param contentImage
         * @return
         */
        public Builder setContentImage(int contentImage) {
            mConfirmDialog.setContentImage(contentImage);
            return this;
        }

        /**
         * 设置对话框内部，标题上方的图标，如对号、叉号等
         *
         * @param warningImage 取值为TITLE_IMAGE_SUCCESS（成功）或TITLE_IMAGE_FAIL（失败），也可为图片资源id
         */
        public Builder setWarningImage(int warningImage) {
            mConfirmDialog.setWarningImage(warningImage);
            return this;
        }

        /**
         * 定制化body
         *
         * @param view 除了形象切片，标题和按钮之外的部分
         * @return
         */
        public Builder setCustomerBodyView(View view) {
            mConfirmDialog.setCustomerBodyView(view);
            return this;
        }

        /**
         * 设置对话框宽度
         *
         * @param width 自定义设置宽度，默认为270
         * @return
         */
        public Builder setDialogWidth(int width) {
            mConfirmDialog.setDialogWidth(width);
            return this;
        }


        public ConfirmDialog show(Context context) {
            if (context instanceof Activity && !((Activity) context).isFinishing()) {
                mConfirmDialog.show(((Activity) context).getFragmentManager(), "ConfirmDialog");
            }
            return mConfirmDialog;
        }

        public void dismiss() {
            if (mConfirmDialog != null) {
                mConfirmDialog.dismiss();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mDescription != null) {
            outState.putCharSequence(DESCRIPTION, mDescription);
        }
        if (mButtonItems != null) {
            outState.putStringArray(BUTTON_ITEM, mButtonItems);
        }
        if (mButtonColors != null) {
            outState.putIntArray(BUTTON_COLOR, mButtonColors);
        }
        if (mButtonTextSizes != null) {
            outState.putIntArray(BUTTON_SIZE, mButtonTextSizes);
        }
        if (mTitle != null) {
            outState.putCharSequence(TITLE, mTitle);
        }
        if (mImage != 0) {
            outState.putInt(TOP_IMAGE, mImage);
        }
        if (mContentImage != 0) {
            outState.putInt(CONTENT_IMAGE, mContentImage);
        }
        if (mWarningImage != 0) {
            outState.putInt(WARNING_IMAGE, mWarningImage);
        }
        if (mButtonBold != null) {
            outState.putBooleanArray(BUTTON_BOLD, mButtonBold);
        }
        if (mHeightCloseImageView != 0) {
            outState.putInt(HEIGHT_CLOSE_IMAGE, mHeightCloseImageView);
        }
        if (mCloseImageView != false) {
            outState.putBoolean(CLOSE_IMAGE, mCloseImageView);
        }
        if (mDialogWidth != 270) {
            outState.putInt(DIALOG_WIDTH, mDialogWidth);
        }

        super.onSaveInstanceState(outState);
    }

}
