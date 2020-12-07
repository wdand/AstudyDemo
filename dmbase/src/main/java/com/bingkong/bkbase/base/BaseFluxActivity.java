package com.bingkong.bkbase.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bingkong.bkbase.event.ModuleMessage;
import com.bingkong.bkbase.ui.act.BaseView;
import com.bingkong.bknet.http.retrofit.TokenManager;
import com.blankj.utilcode.util.StringUtils;
import com.squareup.otto.Bus;
import com.bingkong.bkbase.base.action.ActionAct;
import com.bingkong.bkbase.flux.actions.ActionsCreator;
import com.bingkong.bkbase.flux.annotation.BindEvent;
import com.bingkong.bkbase.flux.dispatcher.Dispatcher;
import com.bingkong.bkbase.flux.stores.Store;
import com.bingkong.bkbase.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by fly on 16/2/17.
 */
public abstract class BaseFluxActivity<STORE extends Store, CREATER extends ActionsCreator> extends ActionAct {
    private String TAG = "BaseFluxActivity";
    protected Dispatcher dispatcher;
    private STORE store;
    private CREATER actionCreater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 是否启用flux模式
     *
     * @return
     */
    protected boolean flux() {
        return false;
    }


    @Override
    public void initBus() {
        LogUtils.loge(TAG, "initBus: " + this.getClass().getSimpleName() + "flux=" + flux());
        if (flux()) {
            dispatcher = Dispatcher.get(new Bus());
        }
        //进行订阅注册
        if (flux() && store() != null) {
            dispatcher.start();
            dispatcher.register(this);
            dispatcher.register(store());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑
        if (flux() && store() != null) {
            dispatcher.stop();
            dispatcher.unregister(this);
            dispatcher.unregister(store());
        }
    }


    /**
     * store 的订阅者方法,用于store.emitStoreChange的响应
     *
     * @param event
     */
    @BindEvent
    public void onEvent(Store.StoreChangeEvent event) {
        updateView(event);
    }


    /**
     * UI的更新
     *
     * @param event
     */
    protected void updateView(Store.StoreChangeEvent event) {
        if (!event.url.equals("refreshToken")
                && (TokenManager.isTokenProblem(event.msg))) {
            if (!StringUtils.isEmpty(event.reqToken) &&
                    event.reqToken.equals(TokenManager.getInstance().getToken())) {
                EventBus.getDefault().post(new ModuleMessage(ModuleMessage.EVENT_REFRESH_TOKEN,
                        this.getClass().getSimpleName()));
            }
        }

    }

    /**
     * 实例化store
     *
     * @return
     */
    protected final STORE store() {
        if (store == null) {
            store = (STORE) newInstance(getType(Store.class.getName()),
                    new Class<?>[]{Dispatcher.class}, dispatcher);
        }
        return store;
    }

    /**
     * 实例化actionsCreator
     *
     * @return
     */
    protected final CREATER actionsCreator() {
        if (actionCreater == null) {
            actionCreater = (CREATER) newInstance(getType(ActionsCreator.class.getName()),
                    new Class<?>[]{Dispatcher.class, BaseView.class},
                    dispatcher, this);
        }
        return actionCreater;
    }

    /**
     * 在实现类中向上寻找,获取范型的实际类型,并将类型返回
     * <p>
     * 获取子类的泛型参数
     *
     * @return
     */
    protected Class<?> getType(String name) {
        Type superclass = getClass().getGenericSuperclass();
        while (superclass != null && !(superclass instanceof ParameterizedType)) {
            superclass = ((Class) superclass).getGenericSuperclass();
        }
        if (superclass == null) {
            throw new RuntimeException("Missing type parameter.");
        }
        Type[] types = ((ParameterizedType) superclass).getActualTypeArguments();
        for (int i = 0; i < types.length; i++) {
//            Class<?> cls = (Class<?>) types[i];
            Class<?> cls = null;
            if (types[i] instanceof Class) {
                cls = (Class<?>) types[i];
            } else {
                Type t = types[i];
                if (t instanceof ParameterizedType) {
                    cls = (Class<?>) ((ParameterizedType) t).getRawType();
                }
            }
            if (cls == null) {
                throw new RuntimeException("Missing type parameter.");
            }
            if (cls.getName().equals(name)) {
                return cls;
            }
            Class c = cls;
            while ((c = c.getSuperclass()) != null && !c.getName().equals("java.lang.Object")) {
                if (c.getName().equals(name)) {
                    return cls;
                }
            }
        }
        return null;
    }

    /**
     * 实例化某个类,通过反射的构造方法生成实例
     *
     * @param cls
     * @param params
     * @param args
     * @param <T>
     * @return
     */
    protected <T> T newInstance(Class<T> cls, Class<?>[] params, Object... args) {
        try {
            Constructor<?> constructor = cls.getDeclaredConstructor(params);
            constructor.setAccessible(true);
            return (T) constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
