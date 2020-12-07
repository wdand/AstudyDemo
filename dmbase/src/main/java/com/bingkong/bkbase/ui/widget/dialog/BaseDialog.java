package com.bingkong.bkbase.ui.widget.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bingkong.bkbase.R;

import androidx.annotation.NonNull;


/**
 * @author yun
 */
public abstract class BaseDialog extends DialogFragment {
    private final String CANCELABLE = "cancelable";
    private final String CANCELABLE_OUTSIDE = "cancelable_outside";
    protected boolean mCancelable = false;
    protected boolean mCancelableOutSide = false;

    protected abstract View getDialogView(Bundle savedInstanceState);

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = getDialogBuilder();
        if (savedInstanceState != null) {
            mCancelable = savedInstanceState.getBoolean(CANCELABLE);
            mCancelableOutSide = savedInstanceState.getBoolean(CANCELABLE_OUTSIDE);
        }
        dialog.setCancelable(mCancelable);
        dialog.setCanceledOnTouchOutside(mCancelableOutSide);

        if (!mCancelable) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
        dialog.setOnDismissListener(getOnDismissListener());

        View view = getDialogView(savedInstanceState);
        if (view != null) {
            dialog.setContentView(view);
        }
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        setDialogSizeAndLocation();
    }

    protected void setDialogSizeAndLocation() {
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    public Dialog getDialogBuilder() {
        return new Dialog(getActivity(), R.style.PPDialog);
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        transaction.add(this, tag);
        return transaction.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        this.show(manager.beginTransaction(), tag);
    }

    protected DialogInterface.OnDismissListener getOnDismissListener() {
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mCancelable) {
            outState.putBoolean(CANCELABLE, mCancelable);
        }
        if (mCancelableOutSide) {
            outState.putBoolean(CANCELABLE_OUTSIDE, mCancelableOutSide);
        }
        super.onSaveInstanceState(outState);
    }


}